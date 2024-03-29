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
	    int isSaved = (int)session.getAttribute("isSaved");
	    int pageId = -1;
	    Sentence tempSent=new Sentence();
	    
	    //page에 미리보기 sentence 추가
	    String fullSentence = "";
	    String pageSentence = "";
	    for(int i=0; i<sentenceSet.size(); i++) {
	    	String sent = sentenceSet.get(i).getSentence();
	    	fullSentence = fullSentence + " " + sent;
	    }
	    
	    //100글자만 추리기
	    if(fullSentence.length() > 200)
	    	pageSentence = fullSentence.substring(0, 200) + "...";
	    else
	    	pageSentence = fullSentence;
	    
	    // 현재 페이지의 page_num 구하기 (제일 마지막에 추가된 페이지: 동일 story_id 중에서 가장 큰 page_num + 1 한 값임.)
		int page_num = 0;
		int story_id = currStory.getStoryId();
		page_num = PageDAO.getPageNum(con, story_id);
		page_num = page_num + 1;
		System.out.println(isSaved);
		if (isSaved==0) {//처음 설정 후 저장할 때
			Page currPage = PageDAO.insertPage(con, page_num, pageImgUrl, pageSentence, story_id);
			pageId = currPage.getPageId();
			
			for (int i=0;i<sentenceSet.size();i++) {
				tempSent = sentenceSet.get(i);
				SentenceDAO.insertSent(con, tempSent.getSentence(), tempSent.getSpeaker(), tempSent.getEmotionId(), tempSent.getVoiceId(), tempSent.getIntensity(), tempSent.getSentenceWavUrl(), pageId);
			}
		}else if(isSaved==1) {//수정 후 저장할 때
			pageId = (int)session.getAttribute("selectedPageId");
			PageDAO.updatePageSentence(con, pageId, pageSentence);
			ArrayList<Sentence> sentenceSetBefore = SentenceDAO.getPageSentence(con, pageId);
			for (int i=0;i<sentenceSet.size();i++) {
				tempSent = sentenceSet.get(i);
				SentenceDAO.updateSentence(con, sentenceSetBefore.get(i).getSentenceId(),tempSent.getSentence(), tempSent.getSpeaker(), tempSent.getEmotionId(), tempSent.getVoiceId(), tempSent.getIntensity(), tempSent.getSentenceWavUrl());
				System.out.println(sentenceSetBefore.get(i).getPageId()+"\n sen"+tempSent.getSentence()+"\n spe"+tempSent.getSpeaker()+"\n emo"+tempSent.getEmotionId()+"\n voi"+tempSent.getVoiceId()+"\n inte"+ tempSent.getIntensity()+"\n wav"+ tempSent.getSentenceWavUrl());
			}
		}
	    
	    session.removeAttribute("sentenceSet");
	    session.removeAttribute("currPageImg");
	    session.removeAttribute("isSaved");
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/doGetPageList");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
