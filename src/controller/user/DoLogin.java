package controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.dto.User;

@WebServlet("/doLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public DoLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    HttpSession session = request.getSession(true);
		String user_input_id = request.getParameter("user_input_id");
		String user_input_pw = request.getParameter("user_input_pw");
		
		//for DB connection
		ServletContext sc = getServletContext();
		Connection conn = (Connection)sc.getAttribute("DBconnection");
		
		//try login
		User currUser = new User();
		try {
			currUser = UserDAO.findUser(conn, user_input_id, user_input_pw);
			if(currUser == null) { //login failed
				PrintWriter out = response.getWriter();
				out.println("<script>alert('회원 정보를 확인해주세요.'); location.href='../StoryReader2/login.jsp';</script>");
				out.flush();
			}
			else { //login success
				PrintWriter out = response.getWriter();
				out.println("<script>alert('로그인 성공'); location.href='../StoryReader2/index.jsp';</script>");
				session.setAttribute("currUser", currUser);
				out.flush();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
