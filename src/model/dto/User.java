package model.dto;

public class User {
	private int user_id;
	private String user_login_id;
	private String user_login_pw;
	private String user_name;
	
	public User() {}
	
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	public void setUserLoginId(String user_login_id) {
		this.user_login_id = user_login_id;
	}
	public void setUserLoginPw(String user_login_pw) {
		this.user_login_pw = user_login_pw;
	}
	public void setUserName(String user_name) {
		this.user_name = user_name;
	}
	public int getUserId() {
		return this.user_id;
	}
	public int getVoiceId() {
		return this.user_id;
	}
	public String getUserLoginId() {
		return this.user_login_id;
	}
	public String getUserLoginPw() {
		return this.user_login_pw;
	}
	public String getUserName() {
		return this.user_name;
	}
}
