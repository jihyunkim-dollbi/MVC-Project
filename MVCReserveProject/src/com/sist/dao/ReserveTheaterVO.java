package com.sist.dao;
/*
 * TNAME    VARCHAR2(200) 
TLOC     VARCHAR2(200) 
TDATE    VARCHAR2(100) 
 * 
 * 
 */
public class ReserveTheaterVO {

	private int tno;
	private String tname, tloc, tdate;
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTloc() {
		return tloc;
	}
	public void setTloc(String tloc) {
		this.tloc = tloc;
	}
	public String getTdate() {
		return tdate;
	}
	public void setTdate(String tdate) {
		this.tdate = tdate;
	}
	
	
}
