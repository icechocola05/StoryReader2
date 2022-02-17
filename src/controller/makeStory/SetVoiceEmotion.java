package controller.makeStory;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.Story;
import model.dto.User;

/*
 * 문장 설정 정보 가져오기
 * 설정 정보 : 문장, 화자, 음색, 감정, 감정세기
 */
@WebServlet("/SetVoiceEmotion")
public class SetVoiceEmotion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SetVoiceEmotion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // for DB connection
	      ServletContext sc = getServletContext();
	      Connection con = (Connection)sc.getAttribute("DBconnection");
	      

	      response.setContentType("text/html; charset=UTF-8");
	      request.setCharacterEncoding("UTF-8");
	      HttpSession session = request.getSession(true);
	      //현재 user, story, page 
	      User currUser = (User) session.getAttribute("currUser");
	      Story currStory = (Story) session.getAttribute("currStory");
	      Page currPage = (Page)session.getAttribute("currPage");
	      ArrayList<String> speaker = (ArrayList<String>) session.getAttribute("speaker_list");
	      List<Voice> voiceSet = (List<Voice>) session.getAttribute("voiceSet");
	      List<Emotion> emotionSet = (List<Emotion>) session.getAttribute("emotionSet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}