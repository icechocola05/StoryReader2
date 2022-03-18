package controller.makeStory;

import java.io.IOException;
import java.util.List;

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

import model.dto.Emotion;
import model.dto.Sentence;
import model.dto.Story;
import model.dto.Voice;



@WebServlet("/DoMakeJsonServlet")
public class DoMakeJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DoMakeJsonServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray jsonArray=new JSONArray();
		
		HttpSession session = request.getSession(true);
		
		List<Sentence> sentenceSet = (List<Sentence>) session.getAttribute("sentenceSet");
		List<Voice> voiceSet = (List<Voice>) session.getAttribute("voiceSet");
		List<Emotion> emotionSet = (List<Emotion>) session.getAttribute("emotionSet");
		
		//현재 스토리에 대한 문장 정보를 JSON 파일로 생성
		try {
			
			for(int i = 0; i<sentenceSet.size(); i++) {
				Sentence sent = sentenceSet.get(i);
				
				String text = sent.getSentence();
				String voice_name = voiceSet.get(sent.getVoiceId()-1).getVoiceName();
				String emotion_name = emotionSet.get(sent.getEmotionId()-1).getEmotionName();
				float intensityVal = sent.getIntensity();
				
				//JSON 생성
				JSONObject jsonObject=new JSONObject(); 
				JSONObject voiceInfo=new JSONObject();
				JSONObject emoInfo=new JSONObject();
				
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
				
		        jsonArray.add(jsonObject);
			}
		}
		catch(Exception e) { //try - catch 필요 없음
			e.printStackTrace();
		}
		request.setAttribute("resultJson", jsonArray);
		request.setAttribute("i", 0);//0으로 초기화
		RequestDispatcher rd = request.getRequestDispatcher("/DoTTSConnection");
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
