package com.sist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.model.*;
import java.util.*;
// 1. controller => 해당 model과 매칭 => model에서 dao연결 => (데이터처리)데이터 가져옴!  => controller에게 값 전달 =>controller가 jsp로 전달 => jsp에서 출력!!
// 따라서, controller 호출과정과 ==> .do를 주어 controller호출하겠다.  => web.xml 에서 지정함!
// 		 controller와 jsp연결 과정이 중요하다(결과:화면이동) ==> request-값 버림->sendRedirect!, request값 유지->forward!, session 으로 받기
									
//@WebServlet("/Controller")
public class Controller extends HttpServlet {

	// <bean id="list" class=""com.sist.model.ListModel"/> ==> 키와 클래스를 주고 데이터값으로 사용 => 해당 클래스(model) 메모리 할당함!
	private static final long serialVersionUID = 1L;
	
	private String[] strCmd={"list","insert","insert_ok","detail","update","update_ok","movie"}; // 6개의 키
	private String[] strCls={
			//strCmd와 strCls를 매핑 예정
			//if문 사용x
			"com.sist.model.ListModel",
			"com.sist.model.InsertModel",
			"com.sist.model.InsertOkModel",
			"com.sist.model.DetailModel",
			"com.sist.model.UpdateModel",
			"com.sist.model.UpdateOkModel",
			"com.sist.model.MovieModel"
	};
	
	private Map clsMap=new HashMap();
	/*
	 * map.put("list",new ListModel());  
	 * 				================ 100 
	 * 
	 * 호출되는 순서
	 * 1. 메모리할당  Controller con=new Controller(); 
	 * 2. con.init()
	 * {
	 * 		초기 => 생성자개념! 단 한번만 생성!=> 메모리 할당 여기서 고고!
	 * 			  싱글톤으로 사용 => 맵에 저장하여 사용!
	 * }
	 * 3. con.service()  
	 * {
	 * 		사용자 요청때마다 여러번 생성ok! 
	 * }
	 * con.destroy()
	 * { System.gc(); 
	 * }
	 *
	 */
	
	
	public void init(ServletConfig config) throws ServletException {

		try{
			
			//for문으로 자동 메모리활당
			for(int i=0; i<strCls.length;i++)
			{
				Class clsName=Class.forName(strCls[i]);
				Object obj=clsName.newInstance(); //메모리할당!
				//따라서 클래스네임만 알고있으면 메모리할당이 쉬워진다.
				
				System.out.println(obj);
				/*com.sist.model.ListModel@65e83dac
				  com.sist.model.InsertModel@61d7a5c9
				  com.sist.model.InsertOkModel@24d53797
				  com.sist.model.DetailModel@319a7fb2
				  com.sist.model.UpdateModel@153b3170
				  com.sist.model.UpdateOkModel@499e27f3
				 * 
				 * 
				 */
				clsMap.put(strCmd[i], obj);
				
			}
			
			
		}catch(Exception ex) {}
		
		
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//위에서 등록된 키와 값을 가져와 맵에 등록된 model을 자동으로 매칭 시켜줌.
		
		String cmd=request.getRequestURI(); // /MVCProject2/detail.do
		//System.out.println(cmd);
		//detail.do?no=1 이렇게 넘어감!  => http://localhost/MVCProject2/detail.do?no=17
		//우리가 필요한것 값에 해당하는 키! list, insert 이렇게 자를 예정
		cmd=cmd.substring(request.getContextPath().length()+1,cmd.lastIndexOf(".")); // list로 만듬!
		
		Model model=(Model)clsMap.get(cmd); //오브젝트 리턴하여(model) 형변환해줌
		//System.out.println(model); 
		//com.sist.model.ListModel@15931593
		
		String jsp=model.execute(request); //model에서 execute()한 뒤의 return값을 jsp에 넣어놓음!
		//System.out.println(jsp);
		//board/list.jsp
		
		if(jsp.startsWith("redirect"))
		{
			//sendRedirect로 !
			response.sendRedirect(jsp.substring(jsp.indexOf(":")+1)); //detail.do 로 자를 예정!
			
		}else
		{
			//jsp파일이 들어오면 forward
			RequestDispatcher rd=request.getRequestDispatcher(jsp);
			rd.forward(request, response);	
		}
		
		
		
		
		

	}

}
