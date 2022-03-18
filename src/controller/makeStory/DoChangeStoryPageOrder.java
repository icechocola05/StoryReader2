package controller.makeStory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.PageDAO;
import model.dto.*;

@WebServlet("/doChangeStoryPageOrder")
public class DoChangeStoryPageOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public DoChangeStoryPageOrder() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
		//page_num 수정하기 위해 DB 연결
		ServletContext sc = getServletContext();
	    Connection con = (Connection)sc.getAttribute("DBconnection");
	    
	    ArrayList<Page> pageList = (ArrayList<Page>)session.getAttribute("pageList");
	    Story currStory = (Story)session.getAttribute("currStory");
	    int storyId = currStory.getStoryId();
	    int pageSize = pageList.size();
	    String n = "";
	    int pageId = 0;
	    int pageOrder = 0;
	    
	    try {
	    	
	    for(int i=1; i<=pageSize; i++) {
	    	n = Integer.toString(i);
	    	pageId = Integer.parseInt(request.getParameter("pageId"+n)); // pageId
	    	pageOrder = Integer.parseInt(request.getParameter("changedNum"+n)); //바뀐 순서
	    	
	    	PageDAO.updatePageOrder(con, pageId, pageOrder);
	    }
	    
	    pageList = PageDAO.getStoryPage(con, storyId);
    	
    	session.setAttribute("pageList", pageList);
    	
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
