package com.sist.dao;

public class MemberVO {

	private String id;
	private String admin;
	private String pwd;
	//세션저장해놓기!!
	
	private String name;
	private String mgs; //로그인 판단여부  => ok이면 session저장

	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMgs() {
		return mgs;
	}
	public void setMgs(String mgs) {
		this.mgs = mgs;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}

	
}
