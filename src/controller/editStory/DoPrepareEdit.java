package controller.editStory;

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

import model.dao.SentenceDAO;
import model.dao.SettingDAO;
import model.dto.Emotion;
import model.dto.Sentence;
import model.dto.Voice;
import util.view.ViewProcessing;

//설정 수정 페이지로 전환
@WebServlet("/DoPrepareEdit")
public class DoPrepareEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DoPrepareEdit() {
        super();
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
		//DB의 Emotion, Voice 가져오기 + session에 저장
	    ServletContext sc = getServletContext();
	    Connection con = (Connection)sc.getAttribute("DBconnection");
	    List<Voice> voiceSet = SettingDAO.getVoice(con);
	    List<Emotion> emotionSet = SettingDAO.getEmotion(con);
	    int selectedPageId = (int)session.getAttribute("selectedPageId");
	    ArrayList<Sentence> sentenceSet =  (ArrayList<Sentence>)session.getAttribute("sentenceSet");
	    
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
		
		session.setAttribute("voiceSet", voiceSet);
		session.setAttribute("emotionSet", emotionSet);
	    session.setAttribute("sentenceSet", sentenceSet);
	    request.setAttribute("voiceColorList", voiceColorList);
		request.setAttribute("emoticonNameList", emoticonNameList);
		request.setAttribute("opacityList", opacityList);
		
	    RequestDispatcher rd = request.getRequestDispatcher("/edit.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
