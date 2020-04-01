package com.sist.controller;
/*
 * 서블렛 파일로 컨트롤러 생성!!
 * init(생성자역할!) & service(메모리할당!) 매소드만 추가! 
 */
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Controller를 부르면 list를 요청함
//Controller?cmd=list
//Controller?cmd=detail
//Controller?cmd=insert
//http://localhost/MVCProject1/Controller?cmd=detail
//							   =====================항상 호출은 컨트롤러 거쳐서!
import com.sist.model.*;

//@WebServlet("/Controller")
@WebServlet("*.do") //확장자 변경가능 => * => 앞에 어떤 단어여도 ok!
//list.do insert.do detail.do =>모두 이 해당 controller를 호출하기 위함!!

public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
	
	}

//1. controller => model 매칭 => model에서 dao연결 => 데이터 가져옴!  => controller에게 값 전달 =>controller가 jsp로 전달 => jsp에서 출력!!


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//톰캣이 사용자로부터 요청값을 받아서(service함수 자동호출) 여기 request값을 넣어줌.
	//톰캣 : 콜백함수 - 시스템에 의해 자동으로 실행되는 프로그램
	//따라서 request에 따라 화면을 보여주면됨.
	//service함수가 호출될 때마다 request의 메모리가 새로 생성됨 => model에 주소를 알려줌 =>model에서 같은 공간에 처리후 값을 실어줌(call by reference-주소에 값을채움)

		
		//방법1
	//	String cmd=request.getParameter("cmd"); //사용자로부터 요청받은 값?
		//자바에서 list.jsp(페이지)로 바로 값을 넘겨줬던 예전 방식과 달리 ==> mvc방식에서는model(자바)을 거치고 controller로 값을 최종적으로 보내줘야 jsp로 연결될 수있다.
		
		//방법2
		String uri=request.getRequestURI();
		// uri = /MVCProject1/*.do => 잘라와서 list, detail, insert를 구하기 위함!앞으로 아래와 같이 jsp로 찾지 않기 위함.
		//request.getContextPath() => /MVCProject1
		//요청받기
		//요청받기 => 처리 =>model(request.setttribute()로 값을 넣어주는 것 만들어놓음..) 한개 호출!
	//	String cmd=uri.substring(request.getContextPath().length()+1,uri.lastIndexOf("."));
		String cmd=uri.substring(uri.lastIndexOf("/")+1,uri.lastIndexOf(".")); // *
		String jsp=uri.substring(request.getContextPath().length(),uri.lastIndexOf("."));  // /*
		//										http://localhost.length()부터 ,  /MVCProject1/*
		
		//http://localhost/MVCProject1/*.do
		System.out.println("jsp: "+ jsp);  // /*
		jsp=jsp+".jsp"; // ==>  /*.jsp
		
		
	
		
		System.out.println("cmd: "+ cmd);
		
		
		/*http://localhost/MVCProject1/*.do
		 * 				  ================uri
		 * 
		 * 폴더를 읽어와서 파일까지 찾기 가능하게 하자...
		 * 
		 * http://localhost/MVCProject1/board/update.do 이렇게 두번째 방법으로 만듬!!
		 * 
		 */

		//model을 찾은 뒤에는 이 request를 어디로 보낼지도 각각의 model에서 설정해줌
		
		if(cmd.equals("list")){
			
			ListModel model=new ListModel();
			model.execute(request); //컨트롤러에서 해당 모델로 매칭해줌 => 그 후에 각자의 model마다 처리 과정을 설정해줌러 고고1!
			//jsp="member/list.jsp";
			
			
		}else if(cmd.equals("detail"))
		{
			
			DetailModel model = new DetailModel();
			model.execute(request); //request받아오기
		//	jsp="member/detail.jsp";
			
		}else if(cmd.equals("insert")){
			
			InsertModel model=new InsertModel();
			model.execute(request);
		//	jsp="member/insert.jsp"; 
			
		}else if(cmd.equals("update")){
			
			UpdateModel model=new UpdateModel();
			model.execute(request);
		//	jsp="board/update.jsp";
			
		}
			
		//보내기
		RequestDispatcher rd=request.getRequestDispatcher(jsp);  //request를 여기 jsp로 보내...
		rd.forward(request, response); //위 jsp에 req, res를 보내라..
		
		
		
		/*		맵에 등록할때마 객체 생성하여 저장해놓으면ok
		 * 		map.put(“list”, new ListModel());
		 * 		map.put(“insert”, new InsertModel());
		 * 		map.put(“update”, new UpdateModel());
		 * 		=> if문 없어짐!
		 */
		
		
	}

}
