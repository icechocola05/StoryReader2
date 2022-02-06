package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBConnectionManager implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
    	Connection conn=(Connection) sce.getServletContext().getAttribute("DBconnection");
    	try {
    		conn.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public void contextInitialized(ServletContextEvent sce)  { 
    	//서버 시작 시 DB 연결
    	Connection conn = null;
		Properties connectionProps = new Properties();
		
		ServletContext sc = sce.getServletContext();
		String DBUrl = sc.getInitParameter("JDBCUrl");
		String DBuser = sc.getInitParameter("DBuser");
		String DBpasswd = sc.getInitParameter("DBpasswd");
		String DBTimeZone = sc.getInitParameter("DBTimeZone");
		
		connectionProps.put("user", DBuser);
		connectionProps.put("password", DBpasswd);
		connectionProps.put("serverTimezone", DBTimeZone);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DBUrl, connectionProps);
			System.out.println("success DB Connection!");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("failed DB Connection!");
			e.printStackTrace();
		}
		sc.setAttribute("DBconnection", conn);
    }
	
}
