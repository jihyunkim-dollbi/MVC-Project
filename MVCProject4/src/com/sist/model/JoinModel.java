package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.temp.Controller;

@Controller
public class JoinModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		
		request.setAttribute("msg", "회원가입 메시지!!!");
		request.setAttribute("main_jsp", "../member/join.jsp");
		
		return "../main/main.jsp";
	}

}
