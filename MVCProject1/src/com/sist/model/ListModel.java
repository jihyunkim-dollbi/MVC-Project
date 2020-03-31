package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
/*
 * 
 * 	Controller => 요청을 받는다, 처리 결과값을 jsp로 전송@!
 * 	Model => 요청에 대한 처리
 * 	View => 처리 결과값 출력
 * 
 * 	맨처음!
 * 				request 			request		   request			request(매개변수)		 
		사용자 요청 ======>  controller ======>  model ======> controller ======> view(jsp)
						 =>ListModel model=new Listmodel()
						   model.execute(request)
 * 							==> controller의 역할!자동으로 값을 model에 넘겨줌
 * 
 * 											**model
 * 											request.addAttribute()하여 처리 후 값을 request에 값을 실어 넘겨줌.
 * 
 * 																	 **sendRedirect(request값 reset!)
 * 																	   forward(request값 전송)
 */
public class ListModel {

	//request값을 받는 연습
	//anotation: request에 맞는 등록된 메소드를 찾아줌!
	//1model = 1 jsp
	public void execute(HttpServletRequest request){ //request => controller에서 받은 reqeust주소!
		
		List<String> list=new ArrayList<String>();
		list.add("홍길동");
		list.add("심청이");
		list.add("박문수");
		
		//controller로 전송하기 
		request.setAttribute("list",list); //controller가 갖고 있는 request로 값을 여러개 싣는다.
		
		
		//현재 여기는 model =>요청을 처리후, 결과값을 넘겨줌
		//controller에서 jsp로 forward시켜주거나.. etc..
		
	    	
		
		//사용자가 no=? 10을 보냄
		//request에 (no,10) 저장! 
		//model에서 해당 request의 공간을 이용해 처리
		
		
		
	}
	
	
}
