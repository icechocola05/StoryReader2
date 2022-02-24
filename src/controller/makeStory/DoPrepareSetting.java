package controller.makeStory;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.SettingDAO;
import model.dto.Emotion;
import model.dto.Story;
import model.dto.Voice;

@WebServlet("/doPrepareSetting")
public class DoPrepareSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DoPrepareSetting() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
		//DB의 Emotion, Voice 가져오기 + session에 저장
	    ServletContext sc = getServletContext();
	    Connection con = (Connection)sc.getAttribute("DBconnection");
	    List<Voice> voiceSet = SettingDAO.getVoice(con);
	    List<Emotion> emotionSet = SettingDAO.getEmotion(con);
	    
	    session.setAttribute("voiceSet", voiceSet);
	    session.setAttribute("emotionSet", emotionSet);
	    
		//문장 분리
		String rawTxt = request.getParameter("pageText");
		System.out.println("문장 그대로: " + rawTxt);   
	    String[] tempTxt = rawTxt.split("\n"); //가공1_문장 단위로 나누기
	    System.out.println("가공 전 문장: " + Arrays.toString(tempTxt));
	    
	    ArrayList<String> sentence_list = new ArrayList<String>(); //문장 목록
	    ArrayList<String> speaker_list = new ArrayList<String>(); //화자 목록 (해설만 들어있음)
	    int textLen = tempTxt.length; //페이지 속 문장의 총 개수
	    for(int i=0; i<textLen; i++) {
	    	if(tempTxt[i].contains("\"")) {
	    		speaker_list.add(""); //사용자가 직접 추가할 수 있도록 비워둠
	    		sentence_list.add(tempTxt[i]);
	    	}
	    	else {
	    		speaker_list.add("해설");
	    		sentence_list.add(tempTxt[i]);
	    	}
	    }
		
		// 페이지 이미지 경로 session 저장
	    String page_img_url = request.getParameter("pageImgUrl");
	    session.setAttribute("currPageImg", page_img_url);
//		rawTxt = rawTxt.replaceAll(System.getProperty("line.separator"), "");
//	    String[] tempTxt = rawTxt.split("\\.|\\!|\\?"); //가공 1_문장 단위로 나누기 (기준은 . , !, ?)
//	    System.out.println("가공 전 문장: " + Arrays.toString(tempTxt));
//	    int cntQuotes = 0;
//
//	    ArrayList<String> sentence_list = new ArrayList<String>(); //문장 목록
//	    ArrayList<String> speaker_list = new ArrayList<String>(); //화자 목록 (해설만 들어있음)
//	    int textLen = tempTxt.length; //페이지 속 문장의 총 개수
//	    for(int i=0; i<textLen; i++) {
//	    	tempTxt[i] = tempTxt[i].replaceAll(System.getProperty("line.separator"), ""); // 개행 문자 제거
//	    	tempTxt[i] = tempTxt[i].trim(); // 문장 앞뒤 공백 제거
//	    	
//	    	
//	    	if(tempTxt[i].contains("\"") && (cntQuotes%2 == 0) ) { //따옴표 시작: 발화 시작
//	    		cntQuotes++;
//	    		speaker_list.add(""); //사용자가 직접 추가할 수 있도록 비워둠
//	    		tempTxt[i] = tempTxt[i] + "\""; //문장 끝에 따옴표 추가
//	    		sentence_list.add(tempTxt[i]);
//	    	}
//	    	else if(cntQuotes%2 == 1) { //현재 발화 중일 때
//	    		speaker_list.add(""); //사용자가 직접 추가할 수 있도록 비워둠
//	    		tempTxt[i] = "\"" + tempTxt[i] + "\""; //문장 앞, 뒤에 따옴표 추가
//	    		sentence_list.add(tempTxt[i]);
//	    	}
//	    	else if(tempTxt[i].contains("\"") && cntQuotes%2 == 1){ //발화가 끝나고 다음 문장
//	    		int cnt = 0;
//	    		for(int j=0; j<tempTxt[i].length(); j++) { // 따옴표가 두 개 붙어서 등장할 때 대비하여 따옴표 개수 세기
//	    			if(tempTxt[i].equals("\"")) {
//	    				cntQuotes++;
//	    				cnt++;
//	    			}
//	    		}
//	    		if(cnt == 1) { // 따옴표가 한개만 존재 -> 일반 문장
//	    			System.out.println(cntQuotes);
//	    			tempTxt[i] = tempTxt[i].replaceAll("\"", ""); // 따옴표 제거
//	    			speaker_list.add("해설");
//	    			sentence_list.add(tempTxt[i]);
//	    		}
//	    		else if (cnt >= 2){ // 따옴표가 두개 이상 존재 -> 다음 문장도 발화 문장
//	    			tempTxt[i] = tempTxt[i].replaceAll("\"", ""); // 따옴표 제거
//	    			speaker_list.add(""); //사용자가 직접 추가할 수 있도록 비워둠
//	    			tempTxt[i] = "\"" + tempTxt[i] + "\""; //문장 앞, 뒤에 따옴표 추가
//		    		sentence_list.add(tempTxt[i]);
//	    		}
//	    		else if(cnt == 1 && tempTxt[i].length() <= 2) { // 따옴표만 존재. 다음 문장 없고 발화 문장으로 끝남
//	    			//아무 문장이 없으므로 삭제
//	    		}
//	    	}
//	    	else if(cntQuotes%2 == 0) { // 따옴표 없는 일반 문장
//	    		speaker_list.add("해설"); // 따옴표가 없다면 해설
//	    		sentence_list.add(tempTxt[i]);
//	    	}
//	    }
	    
	    
	    int i = 0;
	    while(i < sentence_list.size()) {
	    	System.out.print("화자: " + speaker_list.get(i));
	    	System.out.println("문장: " + sentence_list.get(i));
	    	i++;
	    }
	    //DummyData
	    Story currStory = new Story();
	    session.setAttribute("currStory", currStory);
	    session.setAttribute("sentence_list", sentence_list); //모든 문장 -> setting에서 수정함
	    session.setAttribute("speaker_list", speaker_list); //모든 화자 -> setting에서 수정함
	      
	    RequestDispatcher rd = request.getRequestDispatcher("/setting.jsp");
	    rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
