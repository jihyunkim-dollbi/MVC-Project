package com.sist.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.model.*;

import sun.print.DocumentPropertiesUI;

import javax.xml.parsers.*;
import org.w3c.dom.*;

//@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map clsMap=new HashMap();
	

	public void init(ServletConfig config) throws ServletException { // p.533 서블릿을 생성하고 초기화할때 호출되는 메소드 
	
		String path=config.getInitParameter("contextConfigLocation"); // 파일읽기
		System.out.println(path); // => web.xml에 등록시켜놓음
		// C:\Users\sist\git\repository12\MVCProject3\WebContent\WEB-INF\config\applicationContext.xml
		
		try{
			
			//xml 읽기
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(new File(path));
			Element beans=doc.getDocumentElement();
			System.out.println(beans.getTagName()); // beans
			
			//bean들 읽기
			NodeList list=beans.getElementsByTagName("bean");
			
			//현재 length 3개
			for(int i=0;i<list.getLength();i++)
			{
				Element bean=(Element)list.item(i);
				//System.out.println(bean.getTagName()); // bean bean bean
				
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");			
				
				Class clsName=Class.forName(cls); //클래스 읽기
				Object obj=clsName.newInstance(); //com.sist.model.MiddleModel@3269198b
				
				System.out.println("id="+id);
				System.out.println("model="+obj);
				/*
				 * id=category
				   model=com.sist.model.CategoryModel@36568b99
					id=middle
					model=com.sist.model.MiddleModel@3269198b
					id=detail
					model=com.sist.model.DetailModel@776b81a2

				 * 
				 */
				
				clsMap.put(id, obj); // 미리 생성한 clsMap(주소)- 싱글톤으로 만들어놓기 -> 아래서 사용1
				
			}
			
			
		}catch(Exception ex){}
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd=request.getRequestURI(); //http요청받ㄱ
		//http://localhost/MVCProject3/*.do
		//contextpath => /MVCProject3
		cmd=cmd.substring(request.getContextPath().length()+1,cmd.lastIndexOf(".")); //분석 => category, detail, middle 
		//사용자 요청한 위치
		
		//사용자 요청 처리하기 => 해당 model찾기
		Model model=(Model)clsMap.get(cmd); //요청위해 class찾음
		
		//요청 처리하기
		//model.handlerRequest(request); //처리된 결과를 request에 넣음
		
		//reqeust를 여기(jsp)로 보내
		String jsp=model.handlerRequest(request); 
		
		
		/*
		 * 각 모델마다 return하고 있다.
		 * return "파일명" =>forward
		 * return "redirect:" 
		 * 
		 * URI => ?뒤에 내용을 포함하지 않는다. (~.do까지) 
		 */
		//request 보낼거니, 화면만 이동할거니
		
		if(jsp.startsWith("redirect"))
		{
			//화면만 이동 시킴
			response.sendRedirect(jsp.substring(jsp.indexOf(":")+1)); // :뒤부터 자르기
		}
		else
		{
			//값 전송하기!
			RequestDispatcher rd=request.getRequestDispatcher(jsp); //getRequestDispatcher사용자가 request보낸것 받기
			rd.forward(request, response);			
		}
		
		
	}

}
