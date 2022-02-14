package model.dto;

public class Page {
	private int page_id;
	private int page_num;
	private String page_img_url;
	private String story_id;
	
	public Page() {}
	
	public void setPageId(int page_id) {
		this.page_id = page_id;
	}
	
	public void setPageNum(int page_num) {
		this.page_num = page_num;
	}
	
	public void setPageImgUrl(String page_img_url) {
		this.page_img_url = page_img_url;
	}
	
	public void setStoryId(String story_id) {
		this.story_id = story_id;
	}
	
	public int getPageId() {
		return this.page_id;
	}
	
	public int getPageNum() {
		return this.page_num;
	}
	
	public String getPageImgUrl() {
		return this.page_img_url;
	}
	
	public String getStoryId() {
		return this.story_id;
	}
}
