package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import com.sist.dao.*;
import java.util.*;
public class CategoryModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {

		List<CategoryVO> list=FoodDAO.categoryListData();
		
		request.setAttribute("list", list); //list를 싣고
		
		//category.jsp로 값을 보냄.
		return "food/category.jsp"; //=> 폴더/파일명 
		// forward or sendRedirect(+redirect)?
		
	}

}
