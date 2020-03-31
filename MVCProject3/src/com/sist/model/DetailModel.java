package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.FoodDAO;
import com.sist.dao.FoodVO;

public class DetailModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		
		String no=request.getParameter("no");
		FoodVO list=FoodDAO.detailData(Integer.parseInt(no));
		
		request.setAttribute("list", list);
		

		
		return "food/detail.jsp";
	}

}
