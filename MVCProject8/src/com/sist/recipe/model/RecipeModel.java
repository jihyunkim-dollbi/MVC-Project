package com.sist.recipe.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.RecipeDAO;
import com.sist.vo.RecipeVO;
import java.util.*;
import com.sist.vo.*;

@Controller //메모리할당 , //@cont =>주석 걸어놓으면 메모리할당x
public class RecipeModel {

	@RequestMapping("recipe/recipe.do") //사용자가 요청한 위치(를 찾음 RM requestMapping이)
	public String recipe_recipe(HttpServletRequest request, HttpServletResponse response)
	{
		
		//List<RecipeVO> list=RecipeDAO.recipeListData(map);
		
		//페이지가져오기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map=new HashMap();
		map.put("start", start); //여기의 값이 #{start} 와 동일!=>키 명칭과 동일!
		map.put("end", end);
		
		List<RecipeVO> list=RecipeDAO.recipeListData(map);
		for(RecipeVO vo:list)
		{
			String title=vo.getTitle();
			if(title.length()>10)
			{
				title=title.substring(0, 10).concat("...");
				vo.setTitle(title);
			}
			
		}
		int totalpage=RecipeDAO.recipeTotalPage();
		
		//1페이지 후 11페이지가 됨! 21, 31, 41 
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		//다음 눌렀을때 10개씩 이동!!
		
		//맨 마지막 페이지 처리
		int allPage=totalpage;
		if(endPage>allPage) 
			endPage=allPage; 
		
		//보낼 값
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("BLOCK", BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("allPage", allPage);
		request.setAttribute("main_jsp", "../recipe/recipe.jsp");
		
		
		//요청==>처리 => dao
		return "../main/main.jsp";
			
	}
	
	@RequestMapping("recipe/chef.do")
	public String recipe_chef(HttpServletRequest request, HttpServletResponse response)
	{
		
		
		
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		int rowSize=30; //한페이지당 30개 리스트
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map=new HashMap();
		map.put("start", start); //여기의 값이 #{start} 와 동일!=>키 명칭과 동일!
		map.put("end", end);
		
		//dao에서 보낸 값 받기
		List<ChefVO> list=RecipeDAO.chefListData(map);
		int totalpage=RecipeDAO.chefTotalPage();
		
		
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		//다음 눌렀을때 10개씩 이동!!
		
		//맨 마지막 페이지 처리
		int allPage=totalpage;
		if(endPage>allPage) 
			endPage=allPage; 
		
		//보낼 값
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("BLOCK", BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("allPage", allPage);
		
		//인클루드 되는 아이들에게 주기
		request.setAttribute("main_jsp","../recipe/chef.jsp");
		
		return "../main/main.jsp";
	}
	
	
		
	
}
