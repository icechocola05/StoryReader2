package controller.makeStory;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/doChangeStoryTitle")
public class DoChangeStoryTitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DoChangeStoryTitle() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
		//storyTitle 수정하기 위해 DB 연결
		ServletContext sc = getServletContext();
	    Connection con = (Connection)sc.getAttribute("DBconnection");
		
		//바뀐 제목 가져오기
		String storyTitle = request.getParameter("storyTitle");
		
		//현재 Story 객체 가져오기
		Story currStory = (Story)session.getAttribute("currStory");
		int storyId = currStory.getStoryId();
		int userId = currStory.getStoryUser();
		
		//DB에 있던 Story 수정
		try {
			currStory = StoryDAO.updateStoryTitle(con, storyTitle, storyId, userId); // currUser.getUserId() 로 수정 필요
			session.setAttribute("currStory", currStory);
			System.out.println(currStory.getStoryTitle());
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		
		PrintWriter writer = response.getWriter(); 
		writer.println("<script>location.href='makeStory.jsp';</script>");
		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
