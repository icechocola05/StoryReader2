package model.dto;

public class Emotion {
	private int emotion_id;
	private String emotion_name;
	private String emotion_name_kr;
	
	public Emotion() {}
	
	public void setEmotionId(int emotion_id) {
		this.emotion_id = emotion_id;
	}
	
	public void setEmotionName(String emotion_name) {
		this.emotion_name = emotion_name;
	}
	
	public void setEmotionNameKr(String emotion_name_kr) {
		this.emotion_name_kr = emotion_name_kr;
	}
	
	public int getEmotionId() {
		return this.emotion_id;
	}
	
	public String getEmotionName() {
		return this.emotion_name;
	}
	
	public String getEmotionNameKr() {
		return this.emotion_name_kr;
	}
}

