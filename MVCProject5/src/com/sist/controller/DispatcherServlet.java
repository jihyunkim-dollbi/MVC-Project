package com.sist.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


//@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<String> list=new ArrayList<String>();
	
	public void init(ServletConfig config) throws ServletException {
		
		//path읽기 web.xml에서 aap.xml의 path
		//경로명 읽어서 핸들러매핑한테 보내주기-path
		
		String path=config.getInitParameter("contextConfigLocation");//경로 읽음! 
		String defaultPath=config.getInitParameter("defaultPath");//경로 읽음!
		System.out.println("defaultPath: "+defaultPath);
		HandlerMapping hm=new HandlerMapping(path, defaultPath);//파싱 끝!
		
		list=hm.getList(); // 패키지명+클래스명 리스트들
		
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//conponent에서 클래스 찾기위한 작업한 것을 가져와서
		//여기서 메모리할당!
		String cmd=request.getRequestURI(); //  main/main.do 		new1.do 
		
		cmd=cmd.substring(request.getContextPath().length()+1);
		System.out.println("cmd: "+cmd); //*.do
		
		
		try{
			
			//list
			for(String cls:list) //com.sist.model.BoardModel........
			{	//clsName 클래스 모든 정보 가짐
				Class clsName=Class.forName(cls);
				
				if(clsName.isAnnotationPresent(Controller.class)==false)
					continue;
				
				Object obj=clsName.newInstance();//메모리 할당
				
				//메소드 찾아서 호출
				Method[] methods=clsName.getDeclaredMethods();
					
				//메소드 어노테이션 구분
				//@requestMapping("main/list.do")
				for(Method m:methods) //m이 메소드 한개
				{
					//사용자가 보내준 cmd랑 main/list.do랑 같아?
					RequestMapping rm=m.getAnnotation(RequestMapping.class); //RequestMapping.class=> annotation 이름! 
					if(rm.value().equals(cmd)) //rm.value()=>main/list.do
					{
						//m 메소드를 실행해 => invoke()=> ()에 값을 넣으면 채워줌!
						
						String jsp=(String)m.invoke(obj, request, response); // return "../main.jsp";
						System.out.println("jsp: "+jsp); //jsp: list.jsp
						
						if(jsp.startsWith("redirect"))
						{
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1)); // return "redirect:list.do" => :뒤에서 잘라와..
							
							
						}else //forward
						{
							//request.setAttribute있는 경우.
							//값을 보낸다는 의미는?
							RequestDispatcher rd=request.getRequestDispatcher(jsp);
							rd.forward(request, response);	
						}
						
						//for문을 멈춰, 사용자 요청때마다 찾고 멈춤!
						return;
					}
					
				}
		
			}
			
		}catch(Exception ex) {}
		
		
	}

}
