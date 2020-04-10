package com.sist.board.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;

@Controller //DS가 메모리할당!
public class ReplyBoardModel {

	@RequestMapping("reply/list.do") // 구분자이기떄문에 폴더명과 파일명 중복되지 않도록
	public String reply_list(HttpServletRequest request, HttpServletResponse response)
	{
		//인클루드 시키는 파일로 값을 보냄!
		//요청한 데이터를 가지고 온다 ==>dao고고!
		String page=request.getParameter("page");
			if(page==null)
				page="1";
			
		//현재페이지 넘기기
		int curpage=Integer.parseInt(page); //정수값으로 변환!
		Map map =new HashMap();
		
		int rowSize=15; //리스트 개수
		//시작하는 위치
		int start=(rowSize*curpage)-(rowSize-1);
		
		//끝페이지
		int end=(rowSize*curpage);
		
		map.put("start", start);
		map.put("end", end);
		//이 값을 dao로 보내러 gogo!
		
		//cameback from dao
		List<BoardVO> list=ReplyBoardDAO.replyListData(map);
		int totalpage=ReplyBoardDAO.replyTotalPage();
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		/*
		 * 스프링 코딩!
		 * 
		 * class Model(=request)
		 * {
		 * 
		 * 	HttpServletRequest request;
		 * 		public Model(HttpServletRequest request)
		 * 		{
		 * 			this.request=request;
		 * 		}	
		 * 		public void addAttribute(String key, Object obj)
		 * 		{
		 * 			request.setAttribute(key, obj);
		 * 		}
		 * }
		 * 
		 * 		Model model=new Model(request);
		 * 		model.addAttribute("list", list); 얘가 호출됨과 동시에 request에 값이 들어감!
		 * 		//addAttrubute() 안에 request.setAttribute(key, obj);들어가 있기 때문에!!
		 * 
		 * 1) DispatcherServlet => Front Controller
		 * 2) Model(우리가 만드는 모델) => Controller
		 * 3) View => JSP
		 * 4) Model => request
		 * 
		 * 
		 * 
		 */
		
		request.setAttribute("main_jsp", "../reply/list.jsp");
		
		//메인안에 boardlist가 인클루드
		return "../main/main.jsp"; //list.do가 밖으로 가가서 main폴더로 들어가 main.jsp를 찾음
	}

	@RequestMapping("reply/detail.do")
	public String reply_detail(HttpServletRequest request, HttpServletResponse response)
	{
		//사용자가 보내준 no값 받기!
		String no=request.getParameter("no");
		BoardVO vo=ReplyBoardDAO.replyDetailData(Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../reply/detail.jsp");
		
		return "../main/main.jsp";
	}
	
	@RequestMapping("reply/insert.do")
	public String reply_insert(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "../reply/insert.jsp");
		return "../main/main.jsp";
	}
	
	
	@RequestMapping("reply/insert_ok.do")
	public String reply_insert_ok(HttpServletRequest request, HttpServletResponse response)
	{
		try{
			
			//입력글 변환하기
			request.setCharacterEncoding("UTF-8");
			
		}catch(Exception ex){}
		
		//사용자로 부터 받은 값
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		//받은 값을 vo에 실어서 jsp로 번송!!
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		////////////////////////////////////////
		
		ReplyBoardDAO.replyBoardInsert(vo);

		return "redirect:../reply/list.do";
	}
	
	
	@RequestMapping("reply/update.do")
	public String reply_update(HttpServletRequest request, HttpServletResponse response)
	{
		String no=request.getParameter("no");
		BoardVO vo=ReplyBoardDAO.boardUpdateData(Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../reply/update.jsp");
		
		return "../main/main.jsp";
	}
	
	
	
	
	/* 수정하기 - UPDATE 프로그램 로직 쩜쩜쩜...
	 * 생각해보자!
	 * 사용자 요청이 이루어지는 곳에서부터 모든 프로그램은 시작 된다! => detail.jsp에서 사용자는 "수정" 버튼을 누를 것이다.
	 * 수정 버튼을 누르는 순간 DispatcherServlet은 버튼에 정의된 여행 프로그램을 따라 먼 바다로 떠나게 될 것이다.
	 * 
	 * 먼저 상상을 해보자 사용자가 수정 버튼을 눌렀을 경우 나타나게될 화면 첫번째는 내가 입력했던 화면 
	 *  
	 * 
	 * 
	 * 
	 */
	
	@RequestMapping("reply/password_check.do") 
	public String reply_password_check(HttpServletRequest request, HttpServletResponse response){
		
		String no=request.getParameter("no");// jsp에서 받은 no
		String pwd=request.getParameter("pwd"); //jsp에서 받은 no
		
		//jsp로부터 받은 no를 매개변수로replyGetPassword()를 이용해 해당 no에 해당하는 pwd를 db에서 가져와서 db_pwd에 넣음!
		String db_pwd=ReplyBoardDAO.replyGetPassword(Integer.parseInt(no));
		
		//받은 pwd가 db에서 가져온 db_pwd와 일치하는지 구별하기 위해 int변수 res를 선언 초기화함
		// 1이면 동일
		// 0이면 틀림으로 할 예정!!
		int res=0;
		
		if(db_pwd.equals(pwd)) //만약 두 비번이 일치한다면!
		{
			res=1; //res에 1을 저장해
			
		}else{ //만약 두 비번이 일치하지 않는다면!
			
			res=0; //res에 0을 대입해!
		}
		request.setAttribute("result", res); // 0또는 1의 값을 가지는 res를 값으로,키네임을 result로 jsp로 다시 보냄!!
				
		return "../reply/password_check.jsp";
		
	}
	
	@RequestMapping("reply/update_ok.do")
	public String reply_update_ok(HttpServletRequest request, HttpServletResponse response)
	{
		
		try{
			
			request.setCharacterEncoding("UTF-8");
			
		}catch(Exception ex){}
		
		// 사용자가 보내준 값..
		String no=request.getParameter("no");
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		BoardVO vo=new BoardVO();
		
		vo.setNo(Integer.parseInt(no));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		ReplyBoardDAO.replyBoardUpdate(vo);
		
		return "redirect:../reply/detail.do?no="+no;
	}
	
	
	

	
	
}
