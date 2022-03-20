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
import model.dto.Emotion;
import model.dto.Page;
import model.dto.Sentence;
import model.dto.Story;
import model.dto.Voice;

@WebServlet("/doMakeFullStory")
public class DoMakeFullStory extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    public DoMakeFullStory() {
        super();
    }
    
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        int page_index =  (int) session.getAttribute("pageIndex");
        int page_id = 0;
      
      Story currStory = (Story) session.getAttribute("currStory");
       int story_id = currStory.getStoryId();
       ArrayList<Page> pageList = (ArrayList<Page>)session.getAttribute("pageList");
         
      session.removeAttribute("currStory");
      session.removeAttribute("pageList");
       session.removeAttribute("sentenceSet");
      
      RequestDispatcher rd = request.getRequestDispatcher("/DoGetMyStoryList");
      rd.forward(request, response);
      
      
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }


}
