package com.sist.board.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.common.model.CommonData;
 
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.EmpDAO;
import com.sist.dao.EmpVO;

import java.util.*;
import com.sist.*;



@Controller
public class BoardModel {

	@RequestMapping("site/board/list.do") // cmd: site/board/list.do
	public String board_list(HttpServletRequest request, HttpServletResponse response)
	{
		//request.setAttribute("msg", "자유게시판");
		
		List<EmpVO> list=EmpDAO.empAllData();
		request.setAttribute("list", list);
		
		//첨부파일
		request.setAttribute("main_jsp", "board/list.jsp");
	
		CommonData.commonData(request, response);
		return "../main.jsp";
		
	}
	
}
