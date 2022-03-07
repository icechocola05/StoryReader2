package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dto.Story;
import model.service.MakeStoryService;

public class StoryDAO {

	//Story 삽입
	private final static String SQLST_INSERT_STORY = "INSERT INTO story (story_title, user_id) VALUES (?, ?)";
	//Story 찾기
	private final static String SQLST_SELECT_USER_STORY = "SELECT * FROM story WHERE user_id = ?";
	//Story 찾기(story id)
	private final static String SQLST_SELECT_STORY_BY_ID = "SELECT * FROM story WHERE story_id = ?";
	//Story title 수정
	private final static String SQLST_UPDATE_STORY_TITLE = "UPDATE story SET story_title = ? WHERE story_id = ?";
	//Story 삭제
	private final static String SQLST_DELETE_STORY = "DELETE FROM story WHERE story_id = ?";
	
	
	//Story 삽입 성공 시 story 객체 return
	public static Story insertStory(Connection con, String title, int user_id) throws SQLException {
		PreparedStatement pstmt = null;
		Story story = new Story();
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(SQLST_INSERT_STORY, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, title);
			pstmt.setInt(2, user_id);
			
			pstmt.executeUpdate();
			
			//넣은 데이터의 story_id 값 가져오기
			ResultSet rs = pstmt.getGeneratedKeys();  
			rs.next();  
			int id = rs.getInt(1);
			
			con.commit();
			con.setAutoCommit(true);
			
			story.setStoryId(id);
			story.setStoryTitle(title);
			story.setStoryUser(user_id);
			
			return story;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {pstmt.close(); }
		}
		return story;
	}
	//Story 찾기(user)
	public static ArrayList<Story> getUserStories(Connection con, int user_id) {
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(SQLST_SELECT_USER_STORY, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, user_id);
			
			ResultSet storyRS = pstmt.executeQuery();
			ArrayList<Story> storyList = MakeStoryService.changeResultSetToListStory(storyRS);
			
			return storyList;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//Story 찾기(story id)
	public static Story getStoryById(Connection con, int story_id) {
		PreparedStatement pstmt = null;
		Story story = new Story();
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(SQLST_SELECT_STORY_BY_ID, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, story_id);
			
			ResultSet rs = pstmt.executeQuery();
			con.commit();
			con.setAutoCommit(true);
			
			if(rs.next()) { //existing story
				story.setStoryId(rs.getInt(1));
				story.setStoryTitle(rs.getString(2));
				story.setStoryUser(rs.getInt(3));
				return story;
			}
			else { //invalid story
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return null;
		
	}
	
	//Story 제목 수정
	public static Story updateStoryTitle(Connection con, String story_title, int story_id, int user_id) throws SQLException {
		PreparedStatement pstmt = null;
		Story story = new Story();
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(SQLST_UPDATE_STORY_TITLE, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, story_title);
			pstmt.setInt(2, story_id);
			
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);
			
			story.setStoryId(story_id);
			story.setStoryTitle(story_title);
			story.setStoryUser(user_id);
			
			return story;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {pstmt.close(); }
		}
		return story;
	}

	//Story 삭제 -> CASCADE 기능을 이용하여 Story 삭제 시 문장도 함께 삭제 
	public static void deleteStory(Connection con, int story_id) {
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(SQLST_DELETE_STORY);
			pstmt.setInt(1, story_id);
			
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
		}
	}
	
	

}
