package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;
//JSPMIDDLEPROJECT 5 => COM.SIST.MODEL => BoardModel.java에서 paste!!
public class InsertOkModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		
		try{
			
			
			request.setCharacterEncoding("UTF-8");
			
			String name=request.getParameter("name");
			String subject=request.getParameter("subject");  // setproperty 없다!
			String content=request.getParameter("content");
			String pwd=request.getParameter("pwd");
			// (#{no},#{name},#{subject},#{content},#{pwd},SYSDATE,0)
			BoardVO vo=new BoardVO();
			
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			
			BoardDAO.boardInsert(vo);
			
			
		//	response.sendRedirect("list.jsp");  //아래 처리되있음!
			//에러난다 try~ catch 해줘야
			//sendRedirect가 io exception을 갖고 있기 때문에.
			
			// 이 작업 후 ==> board-mapper에서 sql문장 insert작업하러 고고!!
			
			
			
			}catch(Exception ex) {}
		
		return "redirect:list.do"; //redirect => sendRedirect예정! 
	}

}
