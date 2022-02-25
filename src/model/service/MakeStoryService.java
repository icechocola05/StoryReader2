package model.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.Story;
import model.dto.Emotion;
import model.dto.Voice;

public class MakeStoryService {
	
	// ResultSet -> List<String>
		public static ArrayList<Story> changeResultSetToListStory(ResultSet rs) {
			ArrayList<Story> storyList = new ArrayList<Story>();
			
			try {
				// ResultSet의 내용을 카테고리 ArrayList로 변환
				while (rs.next()) {
					Story story = new Story();
					story.setStoryId(rs.getInt("story_id"));
					story.setStoryTitle(rs.getString("story_name"));
					story.setStoryUser(rs.getInt("user_id"));
					storyList.add(story);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}

			return storyList;
		}
		
	// ResultSet -> List<String>
	public static List<Voice> changeResultSetToListVoice(ResultSet rs) {
		List<Voice> voiceList = new ArrayList<Voice>();
		
		try {
			// ResultSet의 내용을 카테고리 ArrayList로 변환
			while (rs.next()) {
				Voice voice = new Voice();
				voice.setVoiceId(rs.getInt("voice_id"));
				voice.setVoiceName(rs.getString("voice_name"));
				voice.setVoiceNameKr(rs.getString("voice_name_kr"));
				voice.setVoiceColor(rs.getString("voice_color"));
				voiceList.add(voice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return voiceList;
	}
	
	// ResultSet -> List<String>
	public static List<Emotion> changeResultSetToListEmotion(ResultSet rs) {
		List<Emotion> emotionList = new ArrayList<Emotion>();
		
		try {
			// ResultSet의 내용을 카테고리 ArrayList로 변환
			while (rs.next()) {
				Emotion emotion = new Emotion();
				emotion.setEmotionId(rs.getInt("emotion_id"));
				emotion.setEmotionName(rs.getString("emotion_name"));
				emotion.setEmotionNameKr(rs.getString("emotion_name_kr"));
				emotionList.add(emotion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return emotionList;
	}
}
