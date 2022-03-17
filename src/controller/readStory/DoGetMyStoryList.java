package controller.readStory;

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
import model.dto.User;


@WebServlet("/DoGetMyStoryList")
public class DoGetMyStoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// for DB connection
		ServletContext sc = getServletContext();
		Connection con = (Connection)sc.getAttribute("DBconnection");

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
		User currUser = (User)session.getAttribute("currUser");
		
		System.out.println(currUser.getUserId());
		
		ArrayList<Story> storyList = StoryDAO.getUserStories(con, currUser.getUserId());
		ArrayList<Page> myStoryPages = new ArrayList<Page>();
		ArrayList<String> myStoryImgUrl = new ArrayList<String>();
		for (int i=0;i< storyList.size();i++){
			try {
				myStoryPages = (ArrayList<Page>)PageDAO.getStoryPage(con, storyList.get(i).getStoryId());
				myStoryImgUrl.add(myStoryPages.get(0).getPageImgUrl());
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		session.setAttribute("myStoryList", storyList);
		session.setAttribute("myStoryListImgUrl", myStoryImgUrl);
//		
		// 새로고침 시 데이터 적재 방지
		 response.sendRedirect("mypage.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
