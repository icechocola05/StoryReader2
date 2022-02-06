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

import model.dao.UserDAO;
import model.dto.User;

@WebServlet("/doRegister")
public class DoRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DoRegister() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    
	    String user_input_name = request.getParameter("user_input_name");
	    String user_input_id = request.getParameter("user_input_id");
		String user_input_pw = request.getParameter("user_input_pw");
		String user_input_pw_check = request.getParameter("user_input_pw_check");
		
		//빈 칸 확인
		if (user_input_name.equals("")) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이름을 입력해주세요.'); location.href='../StoryReader2/register.jsp';</script>");
			out.flush();
		}else if (user_input_id.equals("")) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아이디를 입력해주세요.'); location.href='../StoryReader2/register.jsp';</script>");
			out.flush();
		}else if(user_input_pw.equals("") ){
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호를 입력해주세요.'); location.href='../StoryReader2/register.jsp';</script>");
			out.flush();
		}
		//비밀번호 확인
		if(!user_input_pw.equals(user_input_pw_check)) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호를 확인해주세요.'); location.href='../StoryReader2/register.jsp';</script>");
			out.flush();
		}
		
		//for DB connection
		ServletContext sc = getServletContext();
		Connection conn = (Connection)sc.getAttribute("DBconnection");
		
		User user = new User();
		user.setUserLoginId(user_input_id);
		user.setUserLoginPw(user_input_pw);
		user.setUserName(user_input_name);
		
		boolean check_result = false;
		boolean join_result = false;
		
		//try join
		try {
			check_result = UserDAO.selectID(conn, user_input_id);
			//아이디 중복
			if (check_result==false) {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('중복된 아이디입니다.'); location.href='../StoryReader2/join.jsp';</script>");
				out.flush();
			}
			//아이디 미중복
			else {
				
				join_result = UserDAO.insertUser(conn, user);
				//가입 실패
				if(join_result == false) {
					PrintWriter out = response.getWriter();
					out.println("<script>alert('회원 정보를 확인해주세요.'); location.href='../StoryReader2/join.jsp';</script>");
					out.flush();
				}
				//가입 성공
				else { 
					PrintWriter out = response.getWriter();
					out.println("<script>alert('회원가입 성공'); location.href='../StoryReader2/login.jsp';</script>");
					out.flush();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
