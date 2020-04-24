package com.sist.dao;

import java.sql.ResultSet;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.BoardReplyVO;
import com.sist.vo.BoardVO;


public class FreeBoardReplyDAO {

	private static SqlSessionFactory ssf; //번역 파싱하는 아이
	
	static{
		
		ssf=CreateSqlSessionFactory.getSsf();
		
	}
	
	public static List<BoardReplyVO> replyListData(Map map) // 3개의 in을 넣고 pResult를 다시 이맵에 들어오고 => 한개의 map으로 값을 전송해서 다시 처리결과를 받아온다.
	{
		List<BoardReplyVO> list=new ArrayList<BoardReplyVO>();
		
		SqlSession session=null;
		
		try
		{
			session=ssf.openSession();
			//list로 받을수없다=> list로 값을 받아오는게 아니라=>map에 값이 들어가 있다. 따라서=> cursor가 들어가있기때문에
			//resultset으로 받는다
			session.update("replyListData2",map);  //프로시저 사용시 update를 항상 한다!select든 어떤 쿼리든.
			/*맵에 
			pStart NUMBER,
			pEnd NUMBER,
			pBno freeboard_reply.bno%TYPE, 들어가고

			*/
			
			list=(ArrayList<BoardReplyVO>)map.get("pResult"); //CURSOR!
			/*
			 * 마이바티스는 커서가 들어올때 LIST로 받기 때문에 
			 * 마이바티스를 이용하려면 ARRAYLIST로 받아와야한다.
			 */
			
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		finally{
			
			if(session!=null)
				session.close();
			
		}
		
		return list;
	}
	
	
	
	public static void replyInsert(Map map)//해시맵으로 받았기때문에  map을 넘겨줘야함 => 맵의 property="pBno"이런 키들로 넘어감!키네임을 이용해!
	{
		//모델에서 request값을 받아 dao여기 replyInsert에 맵으로 넣을 것!
		//맵에 vo를 하나씩 채워도 되지만 
		//여기서 사용자가 보내주는 값은 일반자바가 받을 수없기 때문에 항상 매개변수를 통해서 받음!
		//모델에서 매개변수로 request를 받고
		//그 request를 dao의 매개변수로 사용!
		
		SqlSession session=null;
		
		try{
			
			session=ssf.openSession();
			//insert도 select문장으로 쓰기때문에.. commit을 따로 할 필요x  =>프로시져 생성시 commit을 이미 함!
			session.update("replyInsert2",map); //프로시저 호출!
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			if(session!=null)
				session.close();
		}
		
	}
	
	
	// 매개변수가 => 클래스로 넘어가면 => 클래스의 값 변경, 추가가 가능하다 => call by reference!!!!!!
	
	public static int replyTotalPage(Map map)//해시맵으로 받았기때문에  map을 넘겨줘야함 => 맵의 property="pBno"이런 키들로 넘어감!키네임을 이용해!
	{
		//모델에서 request값을 받아 dao여기 replyInsert에 맵으로 넣을 것!
		//맵에 vo를 하나씩 채워도 되지만 
		//여기서 사용자가 보내주는 값은 일반자바가 받을 수없기 때문에 항상 매개변수를 통해서 받음!
		//모델에서 매개변수로 request를 받고
		//그 request를 dao의 매개변수로 사용!
		
		int total=0;
		SqlSession session=null;
		
		try{
			
			session=ssf.openSession();
			//insert도 select문장으로 쓰기때문에.. commit을 따로 할 필요x  =>프로시져 생성시 commit을 이미 함!
			session.update("replyTotalPage2",map); //프로시저 호출!
			
			//현재 맴안에 채워진 값이 있다!
			//가져온 값 꺼내기!pTotal(프로퍼티 설정! key name!)에 채워짐
			total=(int)map.get("pTotal");
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			if(session!=null)
				session.close();
		}
		return total;
	}
	
	
	public static void replyUpdate(Map map)//해시맵으로 받았기때문에  map을 넘겨줘야함 => 맵의 property="pBno"이런 키들로 넘어감!키네임을 이용해!
	{
				
		SqlSession session=null;
		
		try{
			
			session=ssf.openSession();
			//insert도 select문장으로 쓰기때문에.. commit을 따로 할 필요x  =>프로시져 생성시 commit을 이미 함!
			session.update("replyUpdate2",map); //프로시저 호출!
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			if(session!=null)
				session.close();
		}
		
	}
	
	
	
	public static void replyReplyInsert(Map map)//해시맵으로 받았기때문에  map을 넘겨줘야함 => 맵의 property="pBno"이런 키들로 넘어감!키네임을 이용해!
	{
				
		SqlSession session=null;
		
		try{
			
			session=ssf.openSession();
			//insert도 select문장으로 쓰기때문에.. commit을 따로 할 필요x  =>프로시져 생성시 commit을 이미 함!
			session.update("replyReplyInsert2",map); //프로시저 호출!
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			if(session!=null)
				session.close();
		}
		
	}
	
	
	public static void replyDelete2(Map map)//해시맵으로 받았기때문에  map을 넘겨줘야함 => 맵의 property="pBno"이런 키들로 넘어감!키네임을 이용해!
	{
				
		SqlSession session=null;
		
		try{
			
			session=ssf.openSession();
			//insert도 select문장으로 쓰기때문에.. commit을 따로 할 필요x  =>프로시져 생성시 commit을 이미 함!
			session.update("replyDelete2",map); //프로시저 호출!
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			if(session!=null)
				session.close();
		}
		
	}
	
	
	
	
	
}