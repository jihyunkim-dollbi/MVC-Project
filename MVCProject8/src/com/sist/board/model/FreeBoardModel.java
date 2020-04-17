package com.sist.board.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.BoardVO;
/*
 * 
 * 사용자 페이지요청 => 모델(dao포함됨)을 찾음 이유는 매핑 찾기위해=> 매칭에는 사용자가 요청한 url주소가 있기때문에 .do
 * ==> jsp 출력
 * 
 * model=> dao, service(비지니스로직..)(사용자가 요청한대로 값을 넘기고 받아주는 기능!) , vo 포함됨!!
 * 
 */
@Controller
public class FreeBoardModel {

	
	@RequestMapping("freeboard/list.do")
	public String freeboard_list(HttpServletRequest request, HttpServletResponse response)
	{
		
		//사용자가준 페이지 받기
		String page=request.getParameter("page");
			if(page==null)
				page="1";
		//요청한 페이지,현재페이지 curpage로 !
		int curpage=Integer.parseInt(page);
		
		FreeBoardDAO dao=new FreeBoardDAO();
		List<BoardVO> list=dao.freeboardListData(curpage);
		
		int totalpage=dao.freeboardTotalPage();
		
		
		request.setAttribute("curpage",	curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		//vo안에 dbday와 오늘날짜를 비교하기위함
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		request.setAttribute("main_jsp", "../freeboard/list.jsp");
		
		return "../main/main.jsp";
	}
	
	
	//새글 눌렀을때 빈 화면 띄우는 매핑!
	@RequestMapping("freeboard/insert.do") //DS 에서 .do를 찾으라고 정의해놓음! 사용자위치
	public String freeboard_insert(HttpServletRequest request, HttpServletResponse response)
	{

		request.setAttribute("main_jsp", "../freeboard/insert.jsp");
		
		return "../main/main.jsp";
	}
	
	
	//새글 입력하고 확인 눌렀을때 처리_ok
	@RequestMapping("freeboard/insert_ok.do")
	public String freeboard_insert_ok(HttpServletRequest request, HttpServletResponse response)
	{
		//메소드 => 매개변수가 3개 이상이면 클래스로 묶어서 전송
		//						 ===========>~VO(구조체:변수를 묶어주는 역할 수행=> 자바에선 이제 클래스로)
		//사용자가 보낸데이터를 받는다
		//DTO DATA TRANSPER OBJECT? , VO VALUE OBJECT(값만 가지고 있는 클래스)
		try{
			
			//송신: 인코딩, 
			//수신: 디코딩!!
			request.setCharacterEncoding("UTF-8"); //디코딩 작업
		//인코딩/디코딩, post/get, 시맨택 tag(누구나다 알기쉽게 tagName을 정의된 tag 5버전부터!!) 
		
		}catch(Exception ex){}
		
		
		//포함클래스(그대로 사용!), 상속(확장)
		//오버로딩, 오버라이딩!!
		//sendredirect/forward , session/cookie , MVC(인터페이스 기반, 인터페이스,컬렉션 , 예외처리) 장점, 프로토타입(복제- 객체를 여러개 생성=> BUT 다른 메모리에 생성)쓰는 목적/싱글톤(메모리 하나를 갖고 사용-> 메모리 SAVE!!) 쓰는 목적!!
		//시험, 자격증(오픽!), 기술면접, 포트폴리오, 프로젝트경험(헙업!)
		//만든것을 보여줘야함!!
		
		//요청값 받기 => input name의 값들이다!!
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		//dao로 전송 =>dao에서 오라클로 보내준다
		FreeBoardDAO dao=new FreeBoardDAO();
		
		//insert요청!!
		dao.freeboardInsert(vo);
		
		return "redirect:../freeboard/list.do"; //데이터가 필요없으면 sendredirect!
	}			// DS에서 앞을 자르고  ../freeboard/list.do 을 찾으면 MODEL의 list.do를 찾을 것임!! 그리고 이것을 수행함!!
				//list.do에는 리스트를 출력하는 기능 => 새글을 읽었으므로 curpage와 start, end등등 페이지 정의 할것!
	
	
	
	@RequestMapping("freeboard/detail.do")
	public String freeboard_detail(HttpServletRequest request, HttpServletResponse response)
	{
		
		String no=request.getParameter("no");
		//사용자는 no를 보냇고 vo를 받을 것
		
		//doa에서 
		FreeBoardDAO dao=new FreeBoardDAO();
		
		
		//vo 받기
		BoardVO vo=dao.freeboardInfoData(Integer.parseInt(no), 1); //임이의 1을 주어 프로시저에서 조건건대로 hit에 +1을 해준다 => 조회수 증가 => 수정에서는 2를 주어 조호수 증가x
		
		
		//jsp 보내기
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/detail.jsp");
		
		return "../main/main.jsp"; //전체툴은 메인에 첨부되고 첨부되는 내용은 request로 값으로보낸다.
	}
	
	
	
	@RequestMapping("freeboard/update.do")
	public String freeboard_update(HttpServletRequest request, HttpServletResponse response)
	{
	
		// no? 페이지에서 넘긴것?
		String no=request.getParameter("no");
		//사용자는 no를 보냇고 vo를 받을 것
		
		//doa에서 
		FreeBoardDAO dao=new FreeBoardDAO();
		
		
		//vo 받기
		BoardVO vo=dao.freeboardInfoData(Integer.parseInt(no), 2); //임이의 1을 주어 프로시저에서 조건건대로 hit에 +1을 해준다 => 조회수 증가 => 수정에서는 2를 주어 조호수 증가x
		
		
		//jsp 보내기
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/update.jsp");
		
		return "../main/main.jsp"; //전체툴은 메인에 첨부되고 첨부되는 내용은 request로 값으로보낸다.
	}
	
	
	
	@RequestMapping("freeboard/update_ok.do")
	public String freeboard_update_ok(HttpServletRequest request, HttpServletResponse response)
	{
		//메소드 => 매개변수가 3개 이상이면 클래스로 묶어서 전송
		//						 ===========>~VO(구조체:변수를 묶어주는 역할 수행=> 자바에선 이제 클래스로)
		//사용자가 보낸데이터를 받는다
		//DTO DATA TRANSPER OBJECT? , VO VALUE OBJECT(값만 가지고 있는 클래스)
		try{
			
			//송신: 인코딩, 
			//수신: 디코딩!!
			request.setCharacterEncoding("UTF-8"); //디코딩 작업
		//인코딩/디코딩, post/get, 시맨택 tag(누구나다 알기쉽게 tagName을 정의된 tag 5버전부터!!) 
		
		}catch(Exception ex){}
		
		
		//포함클래스(그대로 사용!), 상속(확장)
		//오버로딩, 오버라이딩!!
		//sendredirect/forward , session/cookie , MVC(인터페이스 기반, 인터페이스,컬렉션 , 예외처리) 장점, 프로토타입(복제- 객체를 여러개 생성=> BUT 다른 메모리에 생성)쓰는 목적/싱글톤(메모리 하나를 갖고 사용-> 메모리 SAVE!!) 쓰는 목적!!
		//시험, 자격증(오픽!), 기술면접, 포트폴리오, 프로젝트경험(헙업!)
		//만든것을 보여줘야함!!
		
		//요청값 받기 => input name의 값들이다!!
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		String no=request.getParameter("no"); //hidden으로  감춰서 넘어온값!!? pwd?
		
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		vo.setNo(Integer.parseInt(no));
		
		//dao로 전송 =>dao에서 오라클로 보내준다
		FreeBoardDAO dao=new FreeBoardDAO();
		
		//update요청!!
		boolean bCheck=dao.freeboardUpdate(vo);
		request.setAttribute("bCheck", bCheck); //맞는지 안맞는지 보냄!!
		request.setAttribute("no", no); //디테일로 가야하기 때문에..
		
		return "../freeboard/update_ok.jsp"; // ==> ajax를 쓰면 여기로 안보내도 되지만 , ajax를 하지 않는 경우에는 jsp를 만들어서 여기서 비번확인을 하는 작업을 여기서 해서 1이면 ok 0이면 업데이트 x
						
	}
	
	
	@RequestMapping("freeboard/delete.do")
	public String freeboard_delete(HttpServletRequest request, HttpServletResponse response)
	{
		//detail
		// <a href="../freeboard/delete.do?no=${vo.no} " class="btn btn-xs btn-info">삭제</a>
		//delete
		//<input type="hidden" name=no value="${no }">
		//delete에서 hidden으로 보내준 no값은 모델에서 보냄!!
		/*
		 * 사용자
		 * (디테일에서 a tag에서 화면이동으로 no를 보내면 delete.jsp에서 사용가능하며 그 받은 값을 다시 hidden으로 no를 model로 받아서 request로 받아서 set 으로 보냄)delete.do?no=10 ==> model(사용자가 보내준 값을 받는다)
		 * ==> form 에서 action의 의미는 거기서 이것을 처리하라는 것인데 그럼 보낸값도 거기서 받는 건가??
		 * 
		 */
		
		String no=request.getParameter("no");
		
		
		
		
		request.setAttribute("main_jsp", "../freeboard/delete.jsp"); //비번 입력창 보여주기
		request.setAttribute("no", no);
		return "../main/main.jsp";
	}
	
	
	@RequestMapping("freeboard/delete_ok.do")
	public String freeboard_delete_ok(HttpServletRequest request, HttpServletResponse response)
	{
		//변수를 받을때 2개면 각각 넘기고 3개 이상이면 아까처럼 vo로 넘긴다.
		//no, pwd받기 =>누가 준것?
		String no=request.getParameter("no");
		String pwd=request.getParameter("pwd");
		
		FreeBoardDAO dao= new FreeBoardDAO();
		//프로시져 만들러 고곡!!
		
		
		
		boolean bCheck=dao.freeboardDelete(Integer.parseInt(no), pwd);
		request.setAttribute("bCheck", bCheck);
	
		return "../freeboard/delete_ok.jsp";
	}
	
	
	
	
}
