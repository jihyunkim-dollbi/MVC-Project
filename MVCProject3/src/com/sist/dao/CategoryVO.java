package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

/*
 * CATENO  NOT NULL NUMBER        
TITLE   NOT NULL VARCHAR2(500) 
SUBJECT NOT NULL VARCHAR2(500) 
POSTER  NOT NULL VARCHAR2(500) 
LINK    NOT NULL VARCHAR2(500) 

 * 
 * 
 */
@Getter
@Setter
public class CategoryVO {

	private int cateno;
	private String title;
	private String subject;
	private String poster;
	private String link;
	
}
