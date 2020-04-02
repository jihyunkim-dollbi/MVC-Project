package com.sist.controller;

// request 초기화!
// 세션은 서버안에 자리가 존재하므로 여기에 저장되지 않고 서버에 저장되므로 사라지지 않는다.
// 쿠키는 내 컴퓨터에 저장되므로 가져올 수 있다.
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Test extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher rd=request.getRequestDispatcher("a.jsp"); //rd 에 a.jsp값을 보냄
		rd.forward(request, response); //rd에 forward하여 request, response값을 보내고있음
		//forward는 service함수를 갖고 있으므로 req,res값을 전달하는데 사용!
		
		response.sendRedirect("a.jsp"); // 화면만 이동한 것뿐, req,res값은 넘어가지 않음 a.jsp는 매소드를 갖고있지 x
		
		//데이터가 필요한 경우 - forward로 보내준 데이터를 또 사용할 경우 - _ok에서 작업
		//데이터가 필요없는 경우 - sendRedirect로 화면만 전환 - _ok에서 
		
		
		
		
	}

}
