package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.User;

public class UserDAO {
		// 로그인
	   private final static String SQLST_SELECT_USER_BY_ID = "SELECT user_id, user_login_id, user_login_pw, user_name FROM user WHERE user_login_id = ?";
	   //회원가입
	   private final static String SQLST_INSERT_USER = "INSERT INTO user(user_login_id, user_login_pw, user_name) VALUES(?, ?, ?)";
	   // 아이디 중복 확인
	   private final static String SQLST_SELECT_USER_ID = "SELECT user_login_id FROM user WHERE user_login_id = ?";
	   
	   /* 로그인 - user의 id로 pw 정보를  가져오기
	    * 
	    * 가져온 pw가 파라미터 pw와 같을 때 return user id
	    * 가져온 pw가 파라미터 pw와 다를 때 return null
	    * 가져올 수 있는 pw가 없을 때(user가 없을 때) return null
	    */
	   public static User findUser(Connection con, String user_input_id, String user_input_pw) throws SQLException {
			PreparedStatement pstmt = null;
			User user = new User();
			try {
				pstmt = con.prepareStatement(SQLST_SELECT_USER_BY_ID);
				pstmt.setString(1, user_input_id);
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) { //existing user
					String user_login_pw = rs.getString(3);
					if(user_login_pw.equals(user_input_pw)) { //login success
						//fill user model
						user.setUserId(rs.getInt(1));
						user.setUserLoginId(rs.getString(2));
						user.setUserLoginPw(rs.getString(3));
						user.setUserName(rs.getString(4));
						return user;
					}
					else { //login failed
						return null;
					}
				}
				else { //invalid user
					return null;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				 if(pstmt != null) {
			        pstmt.close(); 
			     }
			}
			return null;
		}
	   

	   
	   /* 주어진 id가 DB내 존재하는지 확인(아이디 중복확인)
	    * 
	    * 주어진 id가 DB내 존재하지 않을 경우 return true
	    * 주어진 id가 DB내 존재하거나 쿼리 실행 실패 시 return false
	    */
	   public static boolean selectID(Connection con, String id) throws SQLException{
	      PreparedStatement pstmt=null;
	      try {
	         pstmt = con.prepareStatement(SQLST_SELECT_USER_ID);
	         pstmt.setString(1, id);
	         ResultSet rs = pstmt.executeQuery();
	         if (rs.next()) {
	            return false;
	         } else {
	            return true; // 사용가능한 아이디(email)
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	         return false;
	      } finally {
	         if(pstmt != null) {
	            pstmt.close(); 
	         }
	      }
	   }
	   
	   /* 주어진 사용자 정보를 DB에 삽입(회원가입)
	    * 
	    * 성공 시 return true
	    * 실패 시 return false
	    */
	   public static boolean insertUser(Connection con, User user) throws SQLException{
	      PreparedStatement pstmt=null;
	      try {
	         pstmt = con.prepareStatement(SQLST_INSERT_USER);
	         pstmt.setString(1, user.getUserLoginId());
	         pstmt.setString(2, user.getUserLoginPw());
	         pstmt.setString(3, user.getUserName());
	         int insertCount = pstmt.executeUpdate();
	         if (insertCount == 1) { // 회원정보 삽입에 성공한다면
	            return true;
	         } else {
	            return false;
	         } 
	      }catch (Exception e) {
	         e.printStackTrace();
	         return false;
	      }finally {
	         if(pstmt != null) {
	            pstmt.close(); 
	         }
	      }
	   }
}
