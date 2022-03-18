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

import model.dao.PageDAO;
import model.dao.StoryDAO;
import model.dto.Page;
import model.dto.Story;

@WebServlet("/doDeleteStory")
public class DoDeleteStory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public DoDeleteStory() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    HttpSession session = request.getSession(true);
	    
	    // for DB connection
	    ServletContext sc = getServletContext();
	    Connection con = (Connection)sc.getAttribute("DBconnection");
	    
	    //story_id 받아오기
	    int story_id = Integer.parseInt(request.getParameter("story_id"));
	    StoryDAO.deleteStory(con, story_id);
	    System.out.print("삭제 성공");
	    
//	    // 새로고침 시 데이터 적재 방지
//	    response.sendRedirect("mypage.jsp");
	    RequestDispatcher rd = request.getRequestDispatcher("/DoGetMyStoryList");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
