package model.dto;

public class Voice {
	private int voice_id;
	private String voice_name;
	private String voice_name_kr;
	private String voice_color;
	
	public Voice() {}
	
	public void setVoiceId(int voice_id) {
		this.voice_id = voice_id;
	}
	
	public void setVoiceName(String voice_name) {
		this.voice_name = voice_name;
	}
	
	public void setVoiceNameKr(String voice_name_kr) {
		this.voice_name_kr = voice_name_kr;
	}
	public void setVoiceColor(String voice_color) {
		this.voice_color = voice_color;
	}
	
	public int getVoiceId() {
		return this.voice_id;
	}
	
	public String getVoiceName() {
		return this.voice_name;
	}
	
	public String getVoiceNameKr() {
		return this.voice_name_kr;
	}
	
	public String getVoiceColor() {
		return this.voice_color;
	}
}