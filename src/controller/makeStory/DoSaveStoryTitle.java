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
		
		//Story 저장하기 위해 DB 연결
		ServletContext sc = getServletContext();
	    Connection con = (Connection)sc.getAttribute("DBconnection");
		
		//제목 가져오기
		String storyTitle = request.getParameter("storyTitle");
		
		//DB에 Story 저장
		try {
			Story currStory = StoryDAO.insertStory(con, storyTitle, 1); // currUser.getUserId() 로 수정 필요
			session.setAttribute("currStory", currStory);
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		
		RequestDispatcher rd = request.getRequestDispatcher("/makeStory.jsp");
	    rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
