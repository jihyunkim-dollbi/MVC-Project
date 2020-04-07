package com.sist.user.model;
import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class MemberModel {

	@RequestMapping("member/join.do")
	public String member_join(HttpServletRequest request, HttpServletResponse response)
	{
		
		request.setAttribute("main_jsp", "../member/join.jsp"); // include 되는 파일 
		
		return "../main/main.jsp";
	}
	
	@RequestMapping("member/postfind.do")
	public String member_postfind(HttpServletRequest request, HttpServletResponse response)
	{
		
		
		
		return "../member/postfind.jsp";
	}
	
	//join.do  postfind.jsp postfind_result.jsp => join.do 위에 또 .do를 띄울 경우 데이터가 사라지기 때문에  새로운 창을 따로 작동하기 위해 ajax(비동기-동시처리)를 사용하여 
	//위에 띄워진 창은 ajax프로그램을 사용해  iframe으로  비동기 창을 띄워줌 - 두가지 프로그램을 동시수행! join.do와 postfind.jsp와... 
	@RequestMapping("member/postfind_result.do")
	public String member_postfind_result(HttpServletRequest request, HttpServletResponse response)
	{
		//db만들기
		try{
			
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex){}
		
		String dong=request.getParameter("dong");
		
		List<ZipcodeVO> list=MemberDAO.postfindData(dong);
		
		request.setAttribute("list", list);
		request.setAttribute("count", list.size());
		
		return "../member/postfind_result.jsp"; //자체내에서 처리해야한다. 매인으로 보내지 않고 //단독수행!
	}
	
}
