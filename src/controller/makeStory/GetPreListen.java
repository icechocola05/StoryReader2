package controller.makeStory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.dto.User;


/**
 * Servlet implementation class GetPreListen
 */
@WebServlet("/GetPreListen")
public class GetPreListen extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetPreListen() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩
		response.setContentType("text/html; charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
		
	    StringBuffer jb = new StringBuffer();
	    String line = null;
	    try {
	    	BufferedReader reader = request.getReader();
	    	while ((line = reader.readLine()) != null)
	    		jb.append(line);
	    } catch (Exception e) {/* report an error */ }
	    				
	    JSONParser parser = new JSONParser();
	    JSONObject obj;
	    String text="", voice_name="", emotion_name="", intensityVal="";
		try {
			obj = (JSONObject)parser.parse(jb.toString());
			text = obj.get("sentence").toString();
		    voice_name = obj.get("voice_name").toString();
		    emotion_name = obj.get("emotion_name").toString();
		    intensityVal = obj.get("intensity").toString();
		    
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    	
		//JSON 생성
		JSONObject jsonObject=new JSONObject(); 
		JSONObject voiceInfo=new JSONObject();
		JSONObject emoInfo=new JSONObject();
		System.out.println(text);
		jsonObject.put("text", text);
		jsonObject.put("lang", "ko");

		voiceInfo.put("name", voice_name);
		voiceInfo.put("gender", "");
		voiceInfo.put("age", ""); 
		voiceInfo.put("variant", "");
		voiceInfo.put("onvoicefailure", "priorityselect");
		jsonObject.put("voice", voiceInfo);
        
		emoInfo.put("name", emotion_name);
		emoInfo.put("value", intensityVal);
		jsonObject.put("emotionInfo", emoInfo);
		
		//현재 사용자 아이디 가져오기
		HttpSession session = request.getSession(true);
		User currUser = (User)session.getAttribute("currUser");
				
		try {
			URL url = new URL("http://220.69.171.37:5000/tts"); // 음성합성기 URL 넣기
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; UTF-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true); // 출력 가능한 상태로 만들기

			// post request
			String result = jsonObject.toString();
			System.out.println(result);
			
			OutputStream wr = con.getOutputStream();
			byte[] input = result.getBytes("UTF-8");
			wr.write(input, 0, input.length);//request json 전송


			int responseCode = con.getResponseCode();
			System.out.println(responseCode);
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출되면 결과 받기
				InputStream is = con.getInputStream();
				int read = 0;
				byte[] bytes = new byte[1024];
				
				String path = getServletContext().getRealPath("pre/");
	            System.out.println(path);
	            request.setAttribute("path", path);

	            File fileSaveDir = new File(path);
	            // 파일 경로 없으면 생성
	            if (!fileSaveDir.exists()) {
	               fileSaveDir.mkdirs();
	            }
	            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	            SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd-hh-mm-ss");
	            String FileName = "1"+ sdf.format(timestamp) + "-prelisten.wav";
	            		//currUser.getUserLoginId()+ sdf.format(timestamp) + "-prelisten.wav";
	            File audioFile = new File(path, FileName);
	            audioFile.createNewFile();

				OutputStream outputStream = new FileOutputStream(audioFile);
				while ((read = is.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read); // wav 파일에 작성 
				}
				
				System.out.println("생성!");
				outputStream.close();
				is.close();	
				
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(FileName);
			}

			else { // 오류 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				String inputLine;
				StringBuffer response1 = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					response1.append(inputLine);
				}
				br.close();
				System.out.println(response1.toString());
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
