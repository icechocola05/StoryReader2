package model.dto;

public class Story {
	private int story_id;
	private String story_name;
	private int user_id;
	
	public Story() {}
	
	public void setStoryId(int story_id) {
		this.story_id = story_id;
	}
	
	public void setStoryName(String story_name) {
		this.story_name = story_name;
	}
	
	public void setStoryUser(int user_id) {
		this.user_id=user_id;
	}
	
	public int getStoryId() {
		return this.story_id;
	}
	
	public String getStoryName() {
		return this.story_name;
	}
	
	public int getStoryUser() {
		return this.user_id;
	}

}
