package controller.makeStory;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        
      session.removeAttribute("currStory");
      session.removeAttribute("pageList");
      session.removeAttribute("pageIndex");
      session.removeAttribute("sentenceSet");
      
      RequestDispatcher rd = request.getRequestDispatcher("/DoGetMyStoryList");
      rd.forward(request, response);
      
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }


}