package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class DetailModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String no=request.getParameter("no");
		
		BoardVO vo=BoardDAO.boardDetailData(Integer.parseInt(no));
		
		//detail.jsp전송 vo를 넘겨줌!
		request.setAttribute("vo", vo);
		
		return "board/detail.jsp"; //forward!!
		
	}

}
