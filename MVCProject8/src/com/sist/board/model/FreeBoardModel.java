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
	
		// no?
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
	
	
	
	
	
	
	
	
	
	
}
