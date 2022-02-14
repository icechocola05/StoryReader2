package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.dto.Emotion;
import model.dto.Voice;
import model.service.MakeStoryService;

public class SettingDAO {
	
	
	public static List<Voice> getVoice(Connection con) {
		try {
			Statement voiceSt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, Statement.RETURN_GENERATED_KEYS);
			voiceSt.execute("SELECT * FROM voice");
			ResultSet voiceRS = voiceSt.getResultSet();
			List<Voice> voiceList = MakeStoryService.changeResultSetToListVoice(voiceRS);
			return voiceList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static List<Emotion> getEmotion(Connection con) {
		try {
			Statement emotionSt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, Statement.RETURN_GENERATED_KEYS);
			emotionSt.execute("SELECT * FROM emotion");
			ResultSet emotionRS = emotionSt.getResultSet();
			List<Emotion> emotionList = MakeStoryService.changeResultSetToListEmotion(emotionRS);
			return emotionList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
