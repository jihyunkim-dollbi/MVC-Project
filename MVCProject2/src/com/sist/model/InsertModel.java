package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class InsertModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub

		return "board/insert.jsp"; // 스프링과 같은 모양 => 실행을 한 후에 jsp파일 리턴!
	}

}

