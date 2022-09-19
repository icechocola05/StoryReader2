package controller.makeStory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.StoryDAO;
import model.dto.Story;
import model.dto.User;

@WebServlet("/doSaveStoryTitle")
public class DoSaveStoryTitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DoSaveStoryTitle() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
		//user 정보 받아오기
		User currUser = (User)session.getAttribute("currUser");
		
		//기존 세션 정보 지우기
		session.removeAttribute("pageList");
		
		//Story 저장하기 위해 DB 연결
		ServletContext sc = getServletContext();
	    Connection con = (Connection)sc.getAttribute("DBconnection");
		
		//제목 가져오기
		String storyTitle = request.getParameter("storyTitle");
		
		
		Story currStory = StoryDAO.insertStory(con, storyTitle, currUser.getUserId()); // currUser.getUserId() 로 수정 필요
		session.setAttribute("currStory", currStory);
		
		
		//새로고침 시 데이터 적재 방지
		response.sendRedirect("makeStory.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
