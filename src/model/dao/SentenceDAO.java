package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import model.dto.Page;
import model.dto.Sentence;

public class SentenceDAO {
	
	private final static String SQLST_INSERT_SENTENCE = "INSERT INTO sentence (sentence_txt, sentence_speaker, emotion_id, voice_id, intensity, sentence_wav_url, page_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private final static String SQLST_SELECT_SENTENCE_BY_PAGEID = "SELECT * FROM sentence WHERE page_id = ?";
	private final static String SQLST_UPDATE_SENTENCE = "UPDATE sentence SET sentence_txt = ?, sentence_speaker=?, voice_id=?, emoticon_id=?, intensity=?, sentence_wav_url=? WHERE sentence_id = ?";
	public static Sentence insertSent(Connection con, String sentence_txt, String sentence_speaker, int emotionId, int voiceId, float intensity, String sentence_wav_url, int page_id) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(SQLST_INSERT_SENTENCE);
			pstmt.setString(1, sentence_txt);
			pstmt.setString(2, sentence_speaker);
			pstmt.setInt(3, emotionId);
			pstmt.setInt(4, voiceId);
			pstmt.setFloat(5, intensity);
			pstmt.setString(6, sentence_wav_url);
			pstmt.setInt(7, page_id);
			
			
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			
			Sentence sent = new Sentence();
			sent.setSentence(sentence_txt);
			sent.setSpeaker(sentence_speaker);
			sent.setEmotionId(emotionId);
			sent.setVoiceId(voiceId);
			sent.setIntensity(intensity);
			sent.setSentenceWavUrl(sentence_wav_url);
			sent.setPageId(page_id);
			return sent;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {pstmt.close(); }
		}
		return null;
	}
	
	public static ArrayList<Sentence> getPageSentence(Connection con, int page_id) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(SQLST_SELECT_SENTENCE_BY_PAGEID, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, page_id);
			ResultSet rs = pstmt.executeQuery();
			con.commit();
			con.setAutoCommit(true);
			
			ArrayList<Sentence> sentenceList = new ArrayList<Sentence>();
			while(rs.next()) {
				Sentence sentence = new Sentence();
				sentence.setSentenceId(rs.getInt(1));
				sentence.setSentence(rs.getString(2));
				sentence.setSpeaker(rs.getString(3));
				sentence.setVoiceId(rs.getInt(4));
				sentence.setEmotionId(rs.getInt(5));
				sentence.setIntensity(rs.getFloat(6));
				sentence.setSentenceWavUrl(rs.getString(7));
				sentence.setPageId(rs.getInt(8));
				sentenceList.add(sentence);
			}
			return sentenceList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {pstmt.close(); }
		}
		return null; //첫번째 삽입되는 페이지임을 나타냄
	}
	
	public static void updateSentence(Connection con,int sentence_id, String sentence_txt, String sentence_speaker, int emotionId, int voiceId, float intensity, String sentence_wav_url) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(SQLST_UPDATE_SENTENCE, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, sentence_txt);
			pstmt.setString(2, sentence_speaker);
			pstmt.setInt(3, voiceId);
			pstmt.setInt(4, emotionId);
			pstmt.setFloat(5, intensity);
			pstmt.setString(6, sentence_wav_url);
			pstmt.setInt(7, sentence_id);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {pstmt.close(); }
		}
	}

	
}
