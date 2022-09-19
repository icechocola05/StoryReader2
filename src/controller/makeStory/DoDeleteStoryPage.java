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
import model.dto.Page;
import model.dto.Story;

@WebServlet("/doDeleteStoryPage")
public class DoDeleteStoryPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DoDeleteStoryPage() {
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
	    int flag = 0;
	    int pageId = 0;
	    
	    for(int i=1; i<=pageSize; i++) {
			n = Integer.toString(i);
			pageId = Integer.parseInt(request.getParameter("pageId"+n)); // pageId
			flag = Integer.parseInt(request.getParameter("deleteFlag"+n));
			
			if(flag == 1) { //flag 값이 1이면 삭제할 pageId
				PageDAO.deletePage(con, pageId);
			}
		}
		
		pageList = PageDAO.getStoryPage(con, storyId);
		
		session.setAttribute("pageList", pageList);
		    
		 // 새로고침 시 데이터 적재 방지
		   response.sendRedirect("makeStory.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
