package controller.readStory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import model.dao.SettingDAO;
import model.dao.StoryDAO;
import model.dto.Emotion;
import model.dto.Page;
import model.dto.Sentence;
import model.dto.Story;
import model.dto.User;
import model.dto.Voice;


@WebServlet("/DoReadStory")
public class DoReadStory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
		User currUser = (User)session.getAttribute("currUser");
		
		
		//DB의 Emotion, Voice 가져오기 + session에 저장
	    ServletContext sc = getServletContext();
	    Connection con = (Connection)sc.getAttribute("DBconnection");
	    List<Voice> voiceSet = SettingDAO.getVoice(con);
	    List<Emotion> emotionSet = SettingDAO.getEmotion(con);
	    
	    session.setAttribute("voiceSet", voiceSet);
	    session.setAttribute("emotionSet", emotionSet);
		
	    ArrayList<String> voiceColorList = new ArrayList<String>();
		ArrayList<String> emoticonNameList = new ArrayList<String>();
		ArrayList<String> opacityList = new ArrayList<String>();
		ArrayList<Page> currPages =  new ArrayList<Page>();
	    ArrayList<Sentence> currSentences = new ArrayList<Sentence>();
		
		//story 정보 가져오기 위해 DB 연결
		//ServletContext sc = getServletContext();
	    //Connection con = (Connection)sc.getAttribute("DBconnection");
	    
	    if(request.getParameter("readPage")==null) {
	    	int story_id = Integer.parseInt(request.getParameter("story_id"));
		    //Story 정보
		    Story currStory = StoryDAO.getStoryById(con, story_id);
		    System.out.println("Story Id : "+story_id);
		    //Page 정보
		    try {
				currPages =  PageDAO.getStoryPage(con, story_id);
				System.out.println("Page Size : "+currPages.size());
				System.out.println("Page Id : "+currPages.get(0).getPageId());
				//첫 Page에 대한 Sentence 정보
				currSentences=SentenceDAO.getPageSentence(con, currPages.get(0).getPageId());
				System.out.println("Sentence Size : "+currSentences.size());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    
		    session.setAttribute("currStory", currStory);//현재 스토리
		    session.setAttribute("currPages", currPages);//현재 스토리에 대한 페이지 리스트
		    
		    request.setAttribute("currPage", currPages.get(0));
		    request.setAttribute("currSentences", currSentences);//첫 페이지에 대한 문장 리스트
	    }else {
	    	currPages = (ArrayList<Page>)session.getAttribute("currPages");
	    	int page_num = Integer.parseInt(request.getParameter("readPage"));
	    	try {
	    		System.out.println(page_num);
				currSentences=SentenceDAO.getPageSentence(con, currPages.get(page_num).getPageId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	
	    	request.setAttribute("currPage", currPages.get(page_num));
		    request.setAttribute("currSentences", currSentences);//첫 페이지에 대한 문장 리스트
	    }
	    
	    for(int i=0;i<currSentences.size();i++) {
			
			//각 문장의 voice_id와 맞는 voice_color를 리스트 형태로 저장
			voiceColorList.add(voiceSet.get(currSentences.get(i).getVoiceId()-1).getVoiceColor());
			
			//각 문장의 emotion_id에 적절한 emoticon의 이름을 저장
			String emoticon = "";
			switch(currSentences.get(i).getEmotionId()) {
			case 1://슬픔
				emoticon = "noto:neutral-face";
				break;
			case 2://기쁨
				emoticon = "noto:grinning-face-with-smiling-eyes";
				break;
			case 3:
				emoticon = "noto:angry-face";
				break;
			case 4:
				emoticon = "noto:crying-face";
				break;
			}
			emoticonNameList.add(emoticon);
			
			//각 문장의 emotion_intensity를 적절한 opacity로 분류
			float val = currSentences.get(i).getIntensity();
			String opacity ="";
			if(val>=(float)0.1&& val<=(float)0.3) {//0.1보다 크고 0.3보다 작은 경우
	             opacity = "20%";
	          }
	          else if(val>=(float)0.4&&val<=(float)0.7) {
	            opacity = "70%";
	          }
	          else if(val>=(float)0.8) {
	            opacity = "100%";
	         }
	          else {
	        	  opacity = Float.toString(val);
	          }
			 opacityList.add(opacity);
		}
	    
	    
		request.setAttribute("voiceColorList", voiceColorList);
		request.setAttribute("emoticonNameList", emoticonNameList);
		request.setAttribute("opacityList", opacityList);
	    
	    RequestDispatcher rd = request.getRequestDispatcher("readStory.jsp");
	    rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
