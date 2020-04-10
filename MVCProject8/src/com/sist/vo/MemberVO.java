package com.sist.vo;
/*
 * ID       NOT NULL VARCHAR2(50)  
PWD      NOT NULL VARCHAR2(50)  
NAME     NOT NULL VARCHAR2(100) 
EMAIL             VARCHAR2(200) 
SEX               VARCHAR2(20)  
BIRTHDAY NOT NULL VARCHAR2(50)  
POST     NOT NULL VARCHAR2(10)  
ADDR1    NOT NULL VARCHAR2(500) 
ADDR2             VARCHAR2(500) 
TEL               VARCHAR2(50)  
CONTENT  NOT NULL CLOB          
REGDATE           DATE          
ADMIN             CHAR(1)       

 */

import java.util.Date;

public class MemberVO {

	private String id;
	private String pwd;
	private String name;
	private String email, sex, birthday, post;
	private String addr1;
	private String addr2;
	private String tel;
	private String content;
	private Date regdate;
	private String admin;
	private String msg;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	
	
	
}
