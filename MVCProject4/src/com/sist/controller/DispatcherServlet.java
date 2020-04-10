
package com.sist.controller;
// 1.노테이션을 만들어 놓고
// 2.할당할 클래스들에 어노테이션 올려놓고
// 3.클래스들에서 어노테이션을 찾아내어
// 4. 있으면 할당 처리
// 5. 없으면 할당 처리x
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
import com.sist.temp.Controller;

import sun.print.DocumentPropertiesUI;

import javax.xml.parsers.*;
import org.w3c.dom.*;
/*
 * 어노테이션 사용 법
 * 
 * @ => TYPE, 클래스 구분 => 클래스 찾기 ==>@Controller, @Repository, @Component , @Service => 4개가 올라가면 메모리할당을 하겠다!
 * 									모델 구분		 dao구분	           일반 클래스 구분		manager구분(외부자료)\
 * 
 * 이렇게  구분해 놓아야 찾을때 쉽다.
 * 
 * public class A
 * {
 * 		@ => 'FIELD' 필드(맴버변수구분:저장하고 있던(MAP에 저장해놓아..) 메모리주소를 전송한다.)  => @Autowired(맴버변수위에 자동 메모리할당!)
 * 		private B b;
 * 		
 * 		public void setB(@ B b) => 'PARAMETER'(매개변수 앞) => @Resource => 여기에 값을 채우거나 값을 가져올때 , 매개변수 확인가능!
 * 										메소드 자동호출을 할텐데 어떤 매개변수가 들어가는지 알수 없기때문에...
 * 		{
 * 			this.b=b
 * 	     
 * 			@ => CONSTRUCT(생성자구분)
 * 			public A()
 * 			{
 * 
 * 			}
 * 
 * 			@ => METHOD구분
 * 			public void display()
 * 			{
 * 				public void aaa(String a, int b){}
 * 				public void bbb(String a){}
 * 				public void ccc(String a, double d){}
 * 
 *	 		}
 *			
 *			매개변수를 찾기위해 아래와 같은 행위를 하지 않아도 된다.....
 *
 *			사용자가 a를 입력하면 aaa를 출력하게 만들고
 *			사용자가 b를 입력하면 bbb를 //
 *			사용자가 c를 입력하면 ccc를 //
 *
 *			A aa=new A();
 *			if(input=='a')
 *			 aa.aaa("",10);
 *			else if(input=='b')
 *			aa.bbb("");
 *			.
 *			.
 *			.
 *
 *			
 	  	}	
 * 	
 * }
 * 
 * 
 * 
 */



//@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map clsMap=new HashMap();
	

	public void init(ServletConfig config) throws ServletException { // p.533 서블릿을 생성하고 초기화할때 호출되는 메소드 
	
		String path=config.getInitParameter("contextConfigLocation"); // 파일읽기
		System.out.println(path); // => web.xml에 등록시켜놓음
		//C:\Users\sist\git\repository12\MVCProject3\WebContent\WEB-INF\config\applicationContext.xml
		
		try{
			
			//xml 읽기  - DOM 파싱!
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
				
				 // id=category
				 // model=com.sist.model.CategoryModel@36568b99
				//	id=middle
				//	model=com.sist.model.MiddleModel@3269198b
				//	id=detail
				//	model=com.sist.model.DetailModel@776b81a2
				// . . .
				
				
				//모델클래스들 clsName에서 어노테이션 정보의 검색 결과를 컨트롤러 con객체에 저장함!
				//Controller con=(Controller)clsName.getAnnotation(Controller.class); //getAnnotation() 어노테이션 정보 찾기!
				//지운 이유는?
				
				
				
				if(clsName.isAnnotationPresent(Controller.class)==false)
					continue; // 어노테이션 없으면 메모리 할당에서 제외!됨
			
				System.out.println("id="+id);
				System.out.println("model="+obj);
				/*
				 * 현재 7개의 모델 클래스 중에 4개만 클래스 어노테이션을 해 놓은 상태이기 때문에 4개만 검색됨!
				 * id=main/home
					model=com.sist.model.HomeModel@4be88179
					id=main/main
					model=com.sist.model.MainModel@451c45d1
					id=member/join
					model=com.sist.model.JoinModel@68bbfa54
					id=board/board		
					model=com.sist.model.BoardModel@40ca8c19

				 */
				clsMap.put(id, obj); // 미리 생성한 clsMap(주소)- 싱글톤으로 만들어놓기 -> 아래서 사용1
				
			}
			
			
		}catch(Exception ex){}
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd=request.getRequestURI(); 
		//모든 jsp는 request와 response를 갖고 있어서 사용자가 특정 페이지에서 특정 요청을 한 경우 사용자 정보가 request를 통해 넘겨지며, 
		//getRequestURI()를 이용해 request안에 있는 http 주소정보를 읽는다.
		//http://localhost/MVCProject4/member/join.do
		//contextpath => /MVCProject3
		System.out.println("cmd는 패키지 명일까?"+cmd);  // nope! cmd ==>  /MVCProject4/*.do
		cmd=cmd.substring(request.getContextPath().length()+1,cmd.lastIndexOf(".")); //분석 => category, detail, middle 
		//사용자 요청한 위치
		System.out.println("가공한 cmd?"+cmd); // *
		
		
		//사용자 요청 처리하기 =>사용자uri에  해당하는 model찾기
		Model model=(Model)clsMap.get(cmd); //요청위해 modelclass찾음=>여기서 찾았다면 model&dao처리 완료한 것!?????????????????????????????????????
		
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
			RequestDispatcher rd=request.getRequestDispatcher(jsp); //getRequestDispatcher: 사용자가 request보낸것 받기
			rd.forward(request, response);			
		}
		
		
	}

}

