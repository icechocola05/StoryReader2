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
		
		try {//Connection timeout 오류 해결용 코드
			UserDAO.throwConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ServletContext sc_tmp = null;
		Connection conn_tmp = null;
		
		//try login
		User currUser = new User();
		sc_tmp = getServletContext();
		conn_tmp = (Connection)sc_tmp.getAttribute("DBconnection");
		
		currUser = UserDAO.findUser(conn_tmp, user_input_id, user_input_pw);
		if(currUser == null) {
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
