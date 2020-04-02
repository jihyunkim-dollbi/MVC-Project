package com.sist.common.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;


public class CommonData {

	
	
	public static void commonData(HttpServletRequest request, HttpServletResponse response)
	{
		//공통모듈!
		request.setAttribute("side", "사이드 데이터");
		
		
	}
}
