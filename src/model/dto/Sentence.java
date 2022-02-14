package model.dto;

public class Sentence {
	private int sentence_id;
	private String sentence;
	private String speaker;
	private int page_id;
	private int voice_id;
	private int emotion_id;
	private float intensity;
	private String sentence_wav_url;

	public Sentence() {this.sentence_id=-1;}
	
	public void setSentenceId(int sentence_id) {
		this.sentence_id = sentence_id;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}
	public void setPageId(int page_id) {
		this.page_id = page_id;
	}
	public void setVoiceId(int voice_id) {
		this.voice_id = voice_id;
	}
	public void setEmotionId(int emotion_id) {
		this.emotion_id = emotion_id;
	}
	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}
	public void setSentenceWavUrl(String sentence_wav_url) {
		this.sentence_wav_url = sentence_wav_url;
	}

	public int getSentenceId() {
		return sentence_id;
	}
	
	public String getSentence() {
		return sentence;
	}
	
	public String getSpeaker() {
		return speaker;
	}
	
	public int getPageId() {
		return page_id;
	}
	
	public int getVoiceId() {
		return voice_id;
	}
	
	public int getEmotionId() {
		return emotion_id;
	}
	
	public float getIntensity() {
		return intensity;
	}
	
	public String getSentenceWavUrl() {
		return sentence_wav_url;
	}
	

}
