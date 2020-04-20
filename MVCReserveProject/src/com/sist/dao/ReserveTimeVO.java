package com.sist.dao;
/*
 * ---- -- ------------ 
TNO     NUMBER       
TIME    VARCHAR2(50) 
 * 
 */
public class ReserveTimeVO {

	private int tno;
	private String time;
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
