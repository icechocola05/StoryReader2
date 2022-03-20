package controller.editStory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.SentenceDAO;
import model.dto.Emotion;
import model.dto.Sentence;
import model.dto.Voice;
import util.view.ViewProcessing;

/**
 * 사용자가 설정 후 저장한 페이지를 미리 보기
 */
@WebServlet("/DoPreviewSavePage")
public class DoPreviewSavePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DoPreviewSavePage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    
	    HttpSession session = request.getSession(true);
	    
	    //DB 연결
	  	ServletContext sc = getServletContext();
	  	Connection con = (Connection)sc.getAttribute("DBconnection");
	    
	    int page_id = Integer.parseInt(request.getParameter("selectedPageId"));
	    
	    ArrayList<Sentence> sentenceSet = new ArrayList<Sentence>();
	    
	    ArrayList<Voice> voiceSet = (ArrayList<Voice>)session.getAttribute("voiceSet");
		ArrayList<Emotion> emotionSet = (ArrayList<Emotion>)session.getAttribute("emotionSet");
		
		ArrayList<String> voiceColorList = new ArrayList<String>();
		ArrayList<String> emoticonNameList = new ArrayList<String>();
		ArrayList<String> opacityList = new ArrayList<String>();
		
		ViewProcessing vp = new ViewProcessing();
		
	    try {
			sentenceSet = (ArrayList<Sentence>)SentenceDAO.getPageSentence(con, page_id);//저장된 문장 설정값들 가져오기
			
			for(int i=0;i<sentenceSet.size();i++) {
				
				//각 문장의 voice_id와 맞는 voice_color를 리스트 형태로 저장
				voiceColorList.add(voiceSet.get(sentenceSet.get(i).getVoiceId()-1).getVoiceColor());
				
				//각 문장의 emotion_id에 적절한 emoticon의 이름을 저장
				emoticonNameList.add(vp.getEmotionName(sentenceSet.get(i)));
				
				//각 문장의 emotion_intensity를 적절한 opacity로 분류
				 opacityList.add(vp.getColorOpacityList(sentenceSet.get(i)));
			}
		} catch (SQLException e) {
			sentenceSet=null;
			e.printStackTrace();
		}
	    session.setAttribute("sentenceSet", sentenceSet);
	    request.setAttribute("voiceColorList", voiceColorList);
		request.setAttribute("emoticonNameList", emoticonNameList);
		request.setAttribute("opacityList", opacityList);
		session.setAttribute("isSaved", true);//설정값이 저장된 내용인지 변수 -> 이 변수에 따라 jsp페이지의 다음 경로가 달라짐
		
	    RequestDispatcher rd = request.getRequestDispatcher("/previewPage.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
