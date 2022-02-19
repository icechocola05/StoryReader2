package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.dto.Sentence;

public class SentenceDAO {
	
	private final static String SQLST_INSERT_SENTENCE = "INSERT INTO sentence (sentence_txt, sentence_speaker, emotion_id, voice_id, intensity, sentence_wav_url, page_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
	
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

}
