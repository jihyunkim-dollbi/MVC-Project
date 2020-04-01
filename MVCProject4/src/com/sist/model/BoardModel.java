package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.temp.Controller;

@Controller
public class BoardModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		request.setAttribute("msg", "게시판입니다!");
		request.setAttribute("main_jsp", "../board/board.jsp");
		
		return "../main/main.jsp";
	}

}
