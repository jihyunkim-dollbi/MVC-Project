package com.sist.vo;
/*
 * ---------- -------- ------------- 
NO         NOT NULL NUMBER        
BNO                 NUMBER        
ID                  VARCHAR2(50)  
NAME       NOT NULL VARCHAR2(100) 
MSG        NOT NULL CLOB          
REGDATE             DATE          
GROUP_ID            NUMBER        
GROUP_STEP          NUMBER        
GROUP_TAB           NUMBER        
ROOT                NUMBER        
DEPTH               NUMBER   

 */

import java.util.Date;

public class BoardReplyVO {

	private String id, name, msg;
	private int no, bno, group_id, group_step, group_tab,root, depth;
	private Date regdate;
	
	//입력한 날짜
	private String dbday;
	
	//regdate는 현재시간 sysdate를 사용하기 때문에 입력한 날짜는 to_char필요!!
	//따라서 입력한 날짜- 댓글에서 는 오라클에서 입력한 날짜를 저장해놓아야한다.
	//따라서 문자열을 받을수있는 db day가 필요!
	
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public int getGroup_step() {
		return group_step;
	}
	public void setGroup_step(int group_step) {
		this.group_step = group_step;
	}
	public int getGroup_tab() {
		return group_tab;
	}
	public void setGroup_tab(int group_tab) {
		this.group_tab = group_tab;
	}
	public int getRoot() {
		return root;
	}
	public void setRoot(int root) {
		this.root = root;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
	
}
