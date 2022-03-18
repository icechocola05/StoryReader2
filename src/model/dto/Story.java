package model.dto;

public class Story {
	private int story_id;
	private String story_title;
	private int user_id;
	
	public Story() {}
	
	public void setStoryId(int story_id) {
		this.story_id = story_id;
	}
	
	public void setStoryTitle(String story_title) {
		this.story_title = story_title;
	}
	
	public void setStoryUser(int user_id) {
		this.user_id=user_id;
	}
	
	public int getStoryId() {
		return this.story_id;
	}
	
	public String getStoryTitle() {
		return this.story_title;
	}
	
	public int getStoryUser() {
		return this.user_id;
	}

}
