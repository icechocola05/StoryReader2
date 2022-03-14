package controller.makeStory;

import java.io.IOException;
import java.io.PrintWriter;
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

import model.dao.StoryDAO;
import model.dto.Story;
import util.text.TextProcessing;

@WebServlet("/doProcessText")
public class DoProcessText extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public DoProcessText() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
		//가공할 방식 가져오기
		String processMethod = request.getParameter("processing-type");
		String text = request.getParameter("pageText");
		ArrayList<String> sentence_list = new ArrayList<String>();
		System.out.println(text);
		
		System.out.println("servlet: " + processMethod);
		//가공 방식에 따른 값 변화
		switch(processMethod) {
		case "byEnter":
			sentence_list = TextProcessing.processByEnter(text);
			break;
		case "bySpeaker":
			sentence_list = TextProcessing.processBySpeaker(text);
			break;
		case "byMark":
			sentence_list = TextProcessing.processByMark(text);
		}
		
		for(int i=0; i<sentence_list.size(); i++) {
			System.out.println(i + ": " + sentence_list.get(i));
		}
		
		//가공한 텍스트 세션 저장
		session.setAttribute("sentence_list", sentence_list);
		session.setAttribute("pageText", text);
		session.setAttribute("processingMethod", processMethod);
		
//    	RequestDispatcher rd = request.getRequestDispatcher("/confirmImage.jsp");
//    	rd.forward(request, response);
		
		PrintWriter writer = response.getWriter(); 
		writer.println("<script> location.href='confirmImage.jsp'; </script>");
		writer.close();
		//response.sendRedirect("confirmImage.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
