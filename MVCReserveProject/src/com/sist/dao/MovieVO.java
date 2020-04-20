package com.sist.dao;
/*
 * MNO      NOT NULL NUMBER(4)      
TITLE    NOT NULL VARCHAR2(1000) 
POSTER   NOT NULL VARCHAR2(2000) 
SCORE             NUMBER(4,2)    
GENRE    NOT NULL VARCHAR2(200)  
REGDATE           VARCHAR2(200)  
TIME              VARCHAR2(50)   
GRADE             VARCHAR2(200)  
DIRECTOR          VARCHAR2(200)  
ACTOR             VARCHAR2(500)  
STORY             CLOB           
TYPE              NUMBER     
 * 
 */
public class MovieVO {

	private int mno, type;
	private String title, poster, genre, regdate, time, grade, director, actor, story;
	private double score;
	private String theaterNo;
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getTheaterNo() {
		return theaterNo;
	}
	public void setTheaterNo(String theaterNo) {
		this.theaterNo = theaterNo;
	}
	
	
	
}
