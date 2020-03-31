package com.sist.model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 데이터형 Listmodel & Model 둘다 가능!
 * 
 * ListModel list=new ListModel();
 * Model model=new Model();
 * model=new InsertModel();
 * 
 * Model => execute()
 * 
 * class만들어서 ListModel implements Model{
 * 
 * 	execute(){}
 * 	list(){}
 * 
 * }
 * 
 * model model=new ListModel();
 * model.execute();
 * model.list(); => x 
 * 
 * 인터페이스의 사용은 추가 재정의 불가능! => 처음의 정의대로 사용해야한다. 
 * => 더 나은 서비스 => 2.5버전! 어노테이션! 
 * 
 * 
 */
import javax.servlet.http.HttpServletRequest;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class ListModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		//자바는 자바대로 html은html대로
		
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map=new HashMap();
		
		map.put("start", start);
		map.put("end", end);
		
		//데이터 가져오기
		List<BoardVO> list=BoardDAO.boardListData(map);
		
		//총페이지 가져오기 =>board-mapper.xml에서 작성 고고!
		int totalpage=BoardDAO.boardTotalPage();
		
		
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		/*
		 * 위의 한줄과 아래 3줄 코딩이 돌일한 코딩! 
		 * SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		 * 					===	  ================================= > 동일!!
		 * 
		 * Date date= new date();
		 * String today = sdf.format(date);
		 	
		 */
		
		//jsp로 결과값 전송
		//3개 
		// jsp는 받은 값을 출력만 하는 기능!
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("today", today);

		
		
		return "board/list.jsp"; //어디로 request 보낼지 여기서 정의.
		//Listmodel을 수행 execute하면 board/list.jsp를 보냄
		
		
		
		
		
	}

	
	
	
}
