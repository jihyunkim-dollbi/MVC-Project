package com.sist.model;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import com.sist.dao.CategoryVO;
import com.sist.dao.FoodDAO;
import com.sist.dao.FoodVO;

public class MiddleModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
	
		
	
		String cno=request.getParameter("cno"); //"key"
		List<FoodVO> list=FoodDAO.middleListData(Integer.parseInt(cno));
		
		request.setAttribute("list", list);
		
		
		
		return "food/middle.jsp";
	}

}
