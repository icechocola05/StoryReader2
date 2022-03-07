package controller.readStory;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.User;


@WebServlet("/DoReadStory")
public class DoReadStory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
		User currUser = (User)session.getAttribute("currUser");
		int story_id = Integer.parseInt(request.getParameter("story_id"));
		
		//story 정보 가져오기 위해 DB 연결
		ServletContext sc = getServletContext();
	    Connection con = (Connection)sc.getAttribute("DBconnection");
	    
	    //Story 정보
	    
	    //Page 정보
	    
	    //Sentence 정보
	    
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
