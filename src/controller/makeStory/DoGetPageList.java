package controller.makeStory;

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
import model.dao.SentenceDAO;
import model.dto.Page;
import model.dto.Story;

@WebServlet("/doGetPageList")
public class DoGetPageList extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public DoGetPageList() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    HttpSession session = request.getSession(true);
	    
	    // for DB connection
	    ServletContext sc = getServletContext();
	    Connection con = (Connection)sc.getAttribute("DBconnection");
	    
	    Story currStory = (Story) session.getAttribute("currStory");
	    int story_id = currStory.getStoryId();
	    
	    try {
	    	//한 story 안에 있는 모든 page를 list로 저장
	    	ArrayList<Page> pageList = new ArrayList<Page>();
	    	pageList = PageDAO.getStoryPage(con, story_id);
	    	session.setAttribute("pageList", pageList);
	    	
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
