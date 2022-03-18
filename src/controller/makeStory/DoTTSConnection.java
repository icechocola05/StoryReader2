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
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.dto.Sentence;
import model.dto.Story;


/*
 * TTS 서버 연결
 * 한 문장에 대한 음성파일(TTS서버의 response) 저장
 * */
@WebServlet("/DoTTSConnection")
public class DoTTSConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DoTTSConnection() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		ArrayList<Sentence> sentenceSet = (ArrayList<Sentence>)session.getAttribute("sentenceSet");
		
		//json 형식의 text table data(session의 attribute) 가져오기
		JSONArray resultJson = new JSONArray();
		resultJson = (JSONArray) request.getAttribute("resultJson");
		int index = (int) request.getAttribute("i");
		System.out.println(index);
		
		request.setAttribute("lastNum", resultJson.size() - 1);
		request.setAttribute("isBegan", 1);
		
		if (index== resultJson.size()) {
			RequestDispatcher rd = request.getRequestDispatcher("/DoPreviewPage");// 원래 경로 : /readScript
			rd.forward(request, response);
			return;
		} else {
			request.setAttribute("i", index + 1);
		}

		try {
			URL url = new URL("http://220.69.171.37:5000/tts"); // 음성합성기 URL 넣기
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; UTF-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true); // 출력 가능한 상태로 만들기

			// post request
			JSONObject postParams = (JSONObject) resultJson.get(index);
			String result = postParams.toString();
			
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
				
				String path = "C:"+File.separator+"StoryReader2"+File.separator+"output"; //getServletContext().getRealPath("output/"); 
				
	            System.out.println(path);
	            request.setAttribute("path", path);

	            File fileSaveDir = new File(path);
	            // 파일 경로 없으면 생성
	            if (!fileSaveDir.exists()) {
	               fileSaveDir.mkdirs();
	            }
	            
	            //파일 이름 중복 방지 랜덤 생성
	            String audioFileName =null;
	            audioFileName = UUID.randomUUID().toString();
	            File audioFile = new File(path, audioFileName + ".wav");
	            audioFile.createNewFile();
	            
				OutputStream outputStream = new FileOutputStream(audioFile);
				while ((read = is.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read); // wav 파일에 작성 
				}
				
				String audioFilePath = audioFileName + ".wav";//audioFile.getAbsolutePath();
				sentenceSet.get(index).setSentenceWavUrl(audioFilePath);//파일 경로 저장
				
				System.out.println("생성!" + index);
				outputStream.close();
				is.close();	
			}

			else { // 오류 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				String inputLine;
				StringBuffer response1 = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					response1.append(inputLine);
				}
				br.close();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/DoTTSConnection");
		rd.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
