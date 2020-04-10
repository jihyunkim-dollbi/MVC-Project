package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public interface Model {

	//다른 모델에서  사용예정!
	public String execute(HttpServletRequest request);
	
	
}
