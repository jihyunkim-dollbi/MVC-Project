package com.sist.user.model;
import com.sist.dao.*;
import com.sist.vo.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class MemberModel {

	@RequestMapping("member/join.do")
	public String member_join(HttpServletRequest request, HttpServletResponse response)
	{
		
		request.setAttribute("main_jsp", "../member/join.jsp"); // include 되는 파일 
		
		return "../main/main.jsp";
	}
	
	@RequestMapping("member/postfind.do")
	public String member_postfind(HttpServletRequest request, HttpServletResponse response)
	{
		
		
		
		return "../member/postfind.jsp";
	}
	
	//join.do  postfind.jsp postfind_result.jsp => join.do 위에 또 .do를 띄울 경우 데이터가 사라지기 때문에  새로운 창을 따로 작동하기 위해 ajax(비동기-동시처리)를 사용하여 
	//위에 띄워진 창은 ajax프로그램을 사용해  iframe으로  비동기 창을 띄워줌 - 두가지 프로그램을 동시수행! join.do와 postfind.jsp와... 
	@RequestMapping("member/postfind_result.do")
	public String member_postfind_result(HttpServletRequest request, HttpServletResponse response)
	{
		//db만들기
		try{
			
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex){}
		
		String dong=request.getParameter("dong");
		
		List<ZipcodeVO> list=MemberDAO.postfindData(dong);
		
		request.setAttribute("list", list);
		request.setAttribute("count", list.size());
		
		return "../member/postfind_result.jsp"; //자체내에서 처리해야한다. 매인으로 보내지 않고 //단독수행!
	}
	
	@RequestMapping("member/idcheck.do")
	public String member_idcheck(HttpServletRequest request, HttpServletResponse response)
	{
		//창만 보여주면됨!
		return "../member/idcheck.jsp";
	}
	
	@RequestMapping("member/idcheck_result.do")
	public String member_idcheck_result(HttpServletRequest request, HttpServletResponse response)
	{
		String id=request.getParameter("id");  //aja에서 보낸 id받기
		int count=MemberDAO.idcheckData(id);
		request.setAttribute("count", count); //아래 페이지에서 count받음 0 or 1 // 이 페이지에서 받은 카운트 출력만 할 것임!
		
		return "../member/idcheck_result.jsp"; //실행만 기켜놓고 ajax가 읽어갈 예정!
		
	}
	
	@RequestMapping("member/join_ok.do")
	public String member_join_ok(HttpServletRequest request, HttpServletResponse response)
	{
		try{
			
			request.setCharacterEncoding("UTF-8");
			
			
		}catch(Exception ex){}
		
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String birthday=request.getParameter("birthday");
		String post1=request.getParameter("post1");
		String post2=request.getParameter("post2");
		String addr1=request.getParameter("addr1");
		String addr2=request.getParameter("addr2");
		String tel1=request.getParameter("tel1");
		String tel2=request.getParameter("tel2");
		String tel3=request.getParameter("tel3");
		String email=request.getParameter("email");
		String content=request.getParameter("content");
		
		MemberVO vo=new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setEmail(email);
		vo.setSex(sex);
		vo.setBirthday(birthday);
		vo.setContent(content);
		vo.setPost(post1+"-"+post2);
		vo.setTel(tel1+"-"+tel2+"-"+tel3);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		
		//insert되는 부분
		//db연동! => mapper gogo!
		//cameback from dao!
		MemberDAO.memberInsert(vo); 
		
		return "redirect:../main/main.do"; //가져올 값없이 화면 이동!
	}
	
	
	
	
	/*		요청		 DB처리
	 *  사용자 =>  MODEL ==> DAO  ==> 결과값 MODEL전송 => JSP로 값 전송(setAttribute!)
	 * 				 
	 */
	
	
	
	/*이 모델에서는 회원의 로그인 처리를 한다.
	 * id와 pwd를 비교하고
	 * 
	 * 
	 */
	
	@RequestMapping("member/login.do")
	public String member_login(HttpServletRequest request, HttpServletResponse response)
	{
		//input에서 받은 값 가져오기
		String id=request.getParameter("id");  //getparameter는 name을 읽어옴!
		String pwd=request.getParameter("pwd");
		
		//DAO연결 => MAPPER로 고고1 ==> dao작성
		//결과값을 여기로 보내라고 return 하는 것!
		
		//came back from memberLogin(String id, String pwd)
		//dao연결하여 값 가져오기
		MemberVO vo=MemberDAO.memberLogin(id, pwd); //세션에 4개의 값을 저장하려고 vo에 저장하여 vo넘김!
		if(vo.getMsg().equals("OK")) //ok이면 세션에 등록하여 모든 Jsp에서 사용할 예정!
		{
			
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", vo.getName());
			session.setAttribute("admin", vo.getAdmin()); //admin을 가져와야 승인/삭제 처리 가능
		}
		
		request.setAttribute("msg", vo.getMsg()); //ok가 아닌 경우이기 때문에 msg만 필요!
		
		return "../member/login.jsp"; //id와 pwd값을 보내줘야함!
	}
	

	@RequestMapping("member/logout.do")
	public String member_logout(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		session.invalidate(); //세션에 저장했던 모든 내용을 지워줌! => 세션의 id값이 null로 바뀜.
				
		return "redirect:../main/main.do";
	}
	
	
}
