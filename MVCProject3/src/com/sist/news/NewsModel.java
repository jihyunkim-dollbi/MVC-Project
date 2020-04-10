package com.sist.news;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.sist.model.Model;

public class NewsModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {

		try{
			//한글  받을 준비
			request.setCharacterEncoding("UTF-8");
			
			
		}catch(Exception ex) {}
		
		//사용자가 넘겨준값 받기
		String fd=request.getParameter("fd");
		
		if(fd==null || fd.equals(""))
		{
			fd="맛집";
			
			
		}
		
		NewsManager mgr=new NewsManager();
		
		List<Item> list=mgr.newsAllData(fd);
		
		request.setAttribute("list", list);
		
		return "news/news.jsp";
	}

}
