package com.sist.board.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.common.model.CommonData;
 
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class BoardModel {

	@RequestMapping("site/board/list.do") // cmd: site/board/list.do
	public String board_list(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("msg", "자유게시판");
		
		//첨부파일
		request.setAttribute("main_jsp", "board/list.jsp");
	
		CommonData.commonData(request, response);
		return "../main.jsp";
		
	}
	
}
