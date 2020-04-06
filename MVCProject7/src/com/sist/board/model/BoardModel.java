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

	@RequestMapping("site/board/list.do") // cmd: 7/site/board/list.do
	public String board_list(HttpServletRequest request, HttpServletResponse response)
	{
		//request.setAttribute("msg", "자유게시판");
		
		List<EmpVO> list=EmpDAO.empAllData();
		request.setAttribute("list", list);
		
		//첨부파일
		request.setAttribute("main_jsp", "board/list.jsp");// 값이 적용될 키가 있는 곳이 기준이됨, 그 기준이 되는 파일에서 바라볼때 list.jsp까지 가지위한 경로만주면됨1
	
		CommonData.commonData(request, response);
		return "../main.jsp"; // 스트링을 리턴하는데 , 인클루드된 곳으로 리턴해야함 .. 인클루드/포워드!
		//기준은  site/board/(list.do)  메인에 포워딩해라! ==> .. 밖으로 나갈때, 들어갈때는 폴더명 주기!!
		
	}
	
}
