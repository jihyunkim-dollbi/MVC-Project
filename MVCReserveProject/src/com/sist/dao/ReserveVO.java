package com.sist.dao;
/*
 * 
 * RNO          NUMBER        
ID           VARCHAR2(20)  
MNO          NUMBER        
TNAME        VARCHAR2(200) 
RDATE        VARCHAR2(200) 
RTIME        VARCHAR2(100) 
RINWON       VARCHAR2(50)  
RPRICE       VARCHAR2(20)  
ISRESERVE    NUMBER        

 */
public class ReserveVO {

	private int rno, mno, isReserve;
	private String id, tname, rdate, rtime, rinwon, rprice;
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getIsReserve() {
		return isReserve;
	}
	public void setIsReserve(int isReserve) {
		this.isReserve = isReserve;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public String getRtime() {
		return rtime;
	}
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
	public String getRinwon() {
		return rinwon;
	}
	public void setRinwon(String rinwon) {
		this.rinwon = rinwon;
	}
	public String getRprice() {
		return rprice;
	}
	public void setRprice(String rprice) {
		this.rprice = rprice;
	}
	
	
	
}
