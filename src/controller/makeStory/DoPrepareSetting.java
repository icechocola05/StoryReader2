package controller.makeStory;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.SettingDAO;
import model.dto.Emotion;
import model.dto.Story;
import model.dto.Voice;

@WebServlet("/doPrepareSetting")
public class DoPrepareSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DoPrepareSetting() {
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
	    
	    session.setAttribute("voiceSet", voiceSet);
	    session.setAttribute("emotionSet", emotionSet);
	    
	    ArrayList<String> sentence_list = (ArrayList<String>)session.getAttribute("sentence_list");
	    ArrayList<String> speaker_list = new ArrayList<String>(); //화자 목록 (해설만 들어있음)
	    int textLen = sentence_list.size(); //페이지 속 문장의 총 개수
	    for(int i=0; i<textLen; i++) {
	    	if(sentence_list.get(i).contains("\"") || sentence_list.get(i).contains("\'")) {
	    		speaker_list.add(""); //사용자가 직접 추가할 수 있도록 비워둠
	    	}
	    	else {
	    		speaker_list.add("해설");
	    	}
	    }
	    session.setAttribute("speaker_list", speaker_list); //모든 화자 -> setting에서 수정함
		
		// 페이지 이미지 경로 session 저장
	    String page_img_url = request.getParameter("pageImgUrl");
	    session.setAttribute("currPageImg", page_img_url); //이미지 확인 한 뒤 세션에 저장
	    
	      
	    RequestDispatcher rd = request.getRequestDispatcher("setting.jsp");
	    rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
