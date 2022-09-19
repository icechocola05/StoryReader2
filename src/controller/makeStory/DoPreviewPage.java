package controller.makeStory;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.Emotion;
import model.dto.Sentence;
import model.dto.Voice;
import util.view.ViewProcessing;


@WebServlet("/DoPreviewPage")
public class DoPreviewPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DoPreviewPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
		
        HttpSession session = request.getSession(true);
		
		ArrayList<Sentence> sentenceSet = (ArrayList<Sentence>)session.getAttribute("sentenceSet");
		ArrayList<Voice> voiceSet = (ArrayList<Voice>)session.getAttribute("voiceSet");
		ArrayList<Emotion> emotionSet = (ArrayList<Emotion>)session.getAttribute("emotionSet");
		
		ArrayList<String> voiceColorList = new ArrayList<String>();
		ArrayList<String> emoticonNameList = new ArrayList<String>();
		ArrayList<String> opacityList = new ArrayList<String>();
		
		ViewProcessing vp = new ViewProcessing();
		
		for(int i=0;i<sentenceSet.size();i++) {
			
			//각 문장의 voice_id와 맞는 voice_color를 리스트 형태로 저장
			voiceColorList.add(voiceSet.get(sentenceSet.get(i).getVoiceId()-1).getVoiceColor());
			
			//각 문장의 emotion_id에 적절한 emoticon의 이름을 저장
			emoticonNameList.add(vp.getEmotionName(sentenceSet.get(i)));
			
			//각 문장의 emotion_intensity를 적절한 opacity로 분류
			 opacityList.add(vp.getColorOpacityList(sentenceSet.get(i)));
		
		}
		
		//request.setAttribute("sentenceSet", sentenceSet);
		request.setAttribute("voiceColorList", voiceColorList);
		request.setAttribute("emoticonNameList", emoticonNameList);
		request.setAttribute("opacityList", opacityList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/previewPage.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
