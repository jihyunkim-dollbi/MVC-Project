package com.sist.member.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class MemberModel {

	@RequestMapping("site/member/join.do")
	public String join_page(HttpServletRequest request, HttpServletResponse response)
	{
		
		request.setAttribute("msg", "회원가입");
		
		
		return "../main.jsp";
		
	}
	
}
