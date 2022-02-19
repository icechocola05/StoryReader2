package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.dto.Page;
import model.dto.Sentence;

public class PageDAO {
	
	private final static String SQLST_INSERT_PAGE = "INSERT INTO page (page_num, page_img_url, story_id) VALUES (?, ?, ?)";
			
	public static Page insertPage(Connection con, int page_num, String page_img_url, int story_id) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(SQLST_INSERT_PAGE, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, page_num);
			pstmt.setString(2, page_img_url);
			pstmt.setInt(3, story_id);
			
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
			page.setStoryId(story_id);
			
			return page;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {pstmt.close(); }
		}
		return null;
	}

}
