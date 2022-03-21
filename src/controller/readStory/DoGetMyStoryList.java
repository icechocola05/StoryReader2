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
		int storySize = storyList.size();
		for (int i=0; i<storySize; i++){
			try {
				myStoryPages = (ArrayList<Page>)PageDAO.getStoryPage(con, storyList.get(i).getStoryId());
				// 제목만 저장하고 페이지 추가하지 않은 경우 Story 삭제
				if(myStoryPages.isEmpty()) {
					StoryDAO.deleteStory(con, storyList.get(i).getStoryId());					
				}
				else {
					myStoryImgUrl.add(myStoryPages.get(0).getPageImgUrl());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//Story 삭제 결정 후 다시 list setting하기
		storyList = StoryDAO.getUserStories(con, currUser.getUserId());
		
		session.setAttribute("myStoryList", storyList);
		session.setAttribute("myStoryListImgUrl", myStoryImgUrl);
		
		// 새로고침 시 데이터 적재 방지
		 response.sendRedirect("mypage.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
