package com.sist.user.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;


@Controller
public class MainModel {

	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request, HttpServletResponse response)
	{
		List<CategoryVO> list=MainDAO.mainImageData();
		
		List<RecipeVO> rlist=MainDAO.mainRecipeData();
		
		int count=MainDAO.recipeCount();
		
		int no=(int)(Math.random()*119)+1;
		FoodVO fvo=MainDAO.mainFoodDetailData(no);
		
		
		request.setAttribute("main_jsp", "home.jsp");
		request.setAttribute("clist", list); //이미지리스트!	
		request.setAttribute("rlist", rlist);
	
		request.setAttribute("count", count);
		request.setAttribute("fvo", fvo);
	
		return "../main/main.jsp"; //  기준이 되는 폴더는 어디로 이동하던, 항상 webContent까지 나온 후에 => 목적지로 이동 한다.!
	}
}
