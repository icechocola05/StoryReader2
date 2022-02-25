package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dto.Page;
import model.dto.Sentence;

public class PageDAO {
	
	private final static String SQLST_INSERT_PAGE = "INSERT INTO page (page_num, page_img_url, page_sentence, story_id) VALUES (?, ?, ?, ?)";
	private final static String SQLST_SELECT_PAGE = "SELECT * FROM page WHERE story_id = ?";
			
	public static Page insertPage(Connection con, int page_num, String page_img_url, String page_sentence, int story_id) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(SQLST_INSERT_PAGE, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, page_num);
			pstmt.setString(2, page_img_url);
			pstmt.setString(3, page_sentence);
			pstmt.setInt(4, story_id);
			
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			
			//insert page 후 page_id(AutoIncrement)값 가져오기
			int page_id=-1;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				page_id = rs.getInt(1); // 키값 초기화
				System.out.println("autoIncrement page_id : " + page_id); // 출력
			}
			
			Page page = new Page();
			page.setPageId(page_id);
			page.setPageNum(page_num);
			page.setPageImgUrl(page_img_url);
			page.setPageSentence(page_sentence);
			page.setStoryId(story_id);
			
			return page;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {pstmt.close(); }
		}
		return null;
	}
	
	public static ArrayList<Page> getStoryPage(Connection con, int story_id) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(SQLST_SELECT_PAGE, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, story_id);
			ResultSet rs = pstmt.executeQuery();
			con.commit();
			con.setAutoCommit(true);
			
			ArrayList<Page> pageList = new ArrayList<Page>();
			while(rs.next()) {
				Page page = new Page();
				page.setPageId(rs.getInt(1));
				page.setPageNum(rs.getInt(2));
				page.setPageImgUrl(rs.getString(3));
				page.setPageSentence(rs.getString(4));
				page.setStoryId(rs.getInt(5));
				
				pageList.add(page);
			}
			
			return pageList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {pstmt.close(); }
		}
		return null; //첫번째 삽입되는 페이지임을 나타냄
	}
	
	public static int getPageNum(Connection con, int story_id) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(SQLST_SELECT_PAGE, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, story_id);
			ResultSet rs = pstmt.executeQuery();
			con.commit();
			con.setAutoCommit(true);
			
			int pageNum[] = new int[1000];
			int pIndex = 0;
			while(rs.next()) {
				pageNum[pIndex] = rs.getInt(2);
				pIndex++;
			}
			
			int max = 0; //최대 page 값
			for(int i=0; i<pIndex; i++) {
				if(max > pageNum[i])
					max = pageNum[i];
			}
			
			return max;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {pstmt.close(); }
		}
		return 0; //첫번째 삽입되는 페이지임을 나타냄
	}

}
