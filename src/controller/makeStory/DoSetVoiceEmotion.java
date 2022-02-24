package controller.makeStory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.Emotion;
import model.dto.Page;
import model.dto.Sentence;
import model.dto.Story;
import model.dto.User;
import model.dto.Voice;

/*
 * 문장 설정 정보 가져오기
 * 설정 정보 : 문장, 화자, 음색, 감정, 감정세기
 */
@WebServlet("/DoSetVoiceEmotion")
public class DoSetVoiceEmotion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DoSetVoiceEmotion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
	      request.setCharacterEncoding("UTF-8");
	      HttpSession session = request.getSession(true);
	      
	      User currUser = (User) session.getAttribute("currUser");
	      Story currStory = (Story) session.getAttribute("currStory");
	      ArrayList<String> sentence_list = (ArrayList<String>) session.getAttribute("sentence_list");
	      List<Voice> voiceSet = (List<Voice>) session.getAttribute("voiceSet");
	      List<Emotion> emotionSet = (List<Emotion>) session.getAttribute("emotionSet");
	      ArrayList<Sentence> sentenceSet = new ArrayList<Sentence>();
	      
	      int sentenceSize = sentence_list.size();
	      
	      String sentenceInput, speakerInput, voiceVal, emotionVal;
	      float intensity;
	      String n;
	      //문장 별로 설정 값을 sentenceSet(session)에 저장한다.
	      for (int i = 0; i < sentenceSize; i++) {
	        n = Integer.toString(i);
	        
	        //문장 별 설정 값들을 가져온다.
	        sentenceInput = request.getParameter("sentence"+n);
	        speakerInput = request.getParameter("speaker"+n);
	        voiceVal = request.getParameter("voiceVal" + n);
	        System.out.println(voiceVal);
	        emotionVal = request.getParameter("emotionVal" + n);
	        System.out.println(emotionVal);
	        intensity = Float.parseFloat(request.getParameter("intensity" + n));
	        System.out.println(intensity);
	        
	        //Emotion, Voice의 id 값은 List에서 다시 구한다.
	        int emotionId = 0;
	        int voiceId = 0;
	        for(int j=0; j<voiceSet.size(); j++) {
	           if(voiceSet.get(j).getVoiceName().equals(voiceVal)) {
	              voiceId = voiceSet.get(j).getVoiceId();
	           }
	        }
	        
	        for(int j=0; j<emotionSet.size(); j++) {
	           if(emotionSet.get(j).getEmotionName().equals(emotionVal)) {
	              emotionId = emotionSet.get(j).getEmotionId();
	           }
	        }
	        
	        //List 형태로 Sentence 저장
	        Sentence invidSent = new Sentence();
	        invidSent.setSentence(sentenceInput);
	        invidSent.setSpeaker(speakerInput);
	        invidSent.setEmotionId(emotionId);
	        invidSent.setVoiceId(voiceId);
	        invidSent.setIntensity(intensity);

	        sentenceSet.add(invidSent);
	        
	     }
	     
	      request.setAttribute("isBegan", 1);
	      request.setAttribute("playAll","false");
	      session.setAttribute("sentenceSet", sentenceSet);
	      RequestDispatcher rd = request.getRequestDispatcher("/DoMakeJsonServlet");
	      rd.forward(request, response);
	      System.out.println("OK last");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
