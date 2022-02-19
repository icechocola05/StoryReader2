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
import model.dto.Sentence;
import model.dto.Story;
import model.dto.User;


/*
 * 페이지 저장
 * DB 저장 - page, sentence
 * */

@WebServlet("/DoConfirmPage")
public class DoConfirmPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DoConfirmPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    HttpSession session = request.getSession(true);
	    
	    // for DB connection
	    ServletContext sc = getServletContext();
	    Connection con = (Connection)sc.getAttribute("DBconnection");
	    
	    // 현재 user, story, page 
	    User currUser = (User) session.getAttribute("currUser");
	    Story currStory = (Story) session.getAttribute("currStory");
	    ArrayList<Sentence> sentenceSet = (ArrayList<Sentence>)session.getAttribute("sentenceSet");
	    ArrayList<Sentence> sentenceResultSet = new ArrayList<Sentence>();
	    String pageImgUrl = (String)session.getAttribute("currPageImg");
	    int pageId = -1;
	    Sentence tempSent=new Sentence();
	    
	    try {
			Page currPage = PageDAO.insertPage(con, 1, pageImgUrl, 1);
			pageId = currPage.getPageId();
			
			for (int i=0;i<sentenceSet.size();i++) {
				tempSent = sentenceSet.get(i);
				SentenceDAO.insertSent(con, tempSent.getSentence(), tempSent.getSpeaker(), tempSent.getEmotionId(), tempSent.getVoiceId(), tempSent.getIntensity(), tempSent.getSentenceWavUrl(), pageId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
