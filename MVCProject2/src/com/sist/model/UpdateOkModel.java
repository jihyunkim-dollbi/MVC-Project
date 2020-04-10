package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class UpdateOkModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String no=""; //try블락 밖에서 no사용 필요 => detail.do? no=.. 에서 no값이 넘어가야하기 때문에! 
		
		try{
				
			// request에 값을 담을 수 있는 이유는 JSP에 doget, dopost 에서 request와 response를 매개변수로 하는 메소드를 갖고 있기때문에
			//자바에서도 request와 response를 매개변수로 사용 가능하다!
			//JSP파일에서 사용자가 입력한 값을 HttpServletRequest request, HttpServletResponse response) 
			//위의 request와 response를 통해 받는다!
			
			request.setCharacterEncoding("UTF-8");
		
		
			//request에 담긴 no, name, subject, content 값들(사용자가 입력한 값)을 각각의 변수에 다시 담는다.
			
			no=request.getParameter("no"); //try블락 밖에서 no사용 필요 => detail.do? no=.. 에서 no값이 넘어가야하기 때문에! 
			String name=request.getParameter("name");
			String subject=request.getParameter("subject");
			String content=request.getParameter("content");
			String pwd=request.getParameter("pwd");
			
			BoardVO vo= new BoardVO();
			
			
			//위에서 담은 값들을 다시 vo객체에 담는다.
			vo.setNo(Integer.parseInt(no));
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			
			
			//위에서 새로운 값을 담은 vo를 boardUpdate()를 이용해 mapper에서 sql문장을수행하도록 한다! 
			BoardDAO.boardUpdate(vo);
			
			//request.setAttribute("vo", vo);
			
			//화면이동이 맞다!! 수정한 내용을 토대로 새로운 내용을 출력하는 새로운 화면으로 이동하므로!!
		//	response.sendRedirect("detail.jsp?no="+no);  //아래서 처리함!!
			
			
			
		}catch(Exception ex) {}
		
	
		return "redirect:detail.do?no="+no; 
		//try블락 밖에서 no사용 필요 => detail.do? no=.. 에서 no값이 넘어가야하기 때문에! 
		//redirect => sendRedirect예정!
	}

}
