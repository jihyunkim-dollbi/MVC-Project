package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/*
 * 	프로그램
 * 	======
 *  공통모듈 => 중복제거: 모든 프로그램에 적용되는 프로그램을 따로 만듬 => CreateSqlSessionFactory
 *  핵심 모듈 ===> 나머지 핵심기능만 코딩하게 만들기 ==>Spring!
 *  ===========================================> 이러한 프로그램: AOP
 * 
 */
import java.util.*;
import com.sist.vo.*;
public class ReplyBoardDAO {

	private static SqlSessionFactory ssf; 
	static{
		
		//파싱하여 데이터저장해놓는 ssf
		ssf=CreateSqlSessionFactory.getSsf(); 
		
	}
	
	public static List<BoardVO> replyListData(Map map)
	{
		List<BoardVO> list=new ArrayList<BoardVO>();
		SqlSession session=null;
		
		try{
			
			session=ssf.openSession(); //getConnection()!
			list=session.selectList("replyListData", map); //이 문장을 읽어서 
			/*
			 * 1)sql
			 * 2)preparedstatement => 채우기
			 * 		ps.setstring(1,"")
			 * 3)~vo
			 * 
			 * list=session.selectList("replyListData" => 키, map =>값);
			 * fs와 ss를 map으로 받아서 id가 replyListData인 것을 수행하여 값을 가져올때 BoardVO를 가져오겠어==> 아래와 같이 작성!
			 * <select id="replyFindData" ==> 키 resultType="BoardVO" parameterType="hashmap">
					SELECT no, subject, name, regdate, hit
					FROM replyBoard
					WHERE ${fs} LIKE '%'||#{ss}||'%'   ==>sql문장이 값!==> map으로 저장됨!!
				</select>
			 * 
			 * 프로그램이 작동할수있게 정보를 담아두는 파일 메타파일
			 * xml, 프로퍼티!
			 * xml파일이 더 많이 사용됨!xml파일을 사용위해 dtd를 만들어 tag와속성값의 가이드라인을 만듬!
			 */
			
		}catch(Exception ex) {
			
			System.out.println("replyListData(): " + ex.getMessage());
			
		}finally
		{
			if(session!=null)
				session.close();
				/*
				 * session안에  Connection, PreparedStatement 이 포함되어있다.	=> session안에! mapper에서 작성한 sql문장들이 들어감			 * 
				 * if(ps!=null) ps.close()
				 * if(conn!=null) conn.close() 와 동일!
				 */
		}
		return list;
		
	}
	
	public static int replyTotalPage()
	{
		int total=0;
		SqlSession session=null;
		
		try{
			session=ssf.openSession();
			total=session.selectOne("replyTotalPage"); //total에 db연결해서 가져온 값을 여기에 채워!
			//selectone/ list는 commit을 날리지 않음!
			
		}catch(Exception ex){
			
			System.out.println("replyTotalPage(): "+ex.getMessage());
			
		}finally{
			
			if(session!=null)
				session.close(); // 커넥션을 반환해야한다.
		}
		return total;
		
	}
	
	
	public static BoardVO replyDetailData(int no)
	{
		BoardVO vo= new BoardVO();
		SqlSession session=null;
		
		try{
			session=ssf.openSession();
			
			session.update("hitIncrement",no); 
			session.commit();
			
			vo=session.selectOne("replyDetailData",no); //total에 db연결해서 가져온 값을 여기에 채워!
			//selectone/ list는 commit을 날리지 않음!
			
		}catch(Exception ex){
			
			System.out.println("replyDetailData(): "+ex.getMessage());
			
		}finally{
			
			if(session!=null)
				session.close(); // 커넥션을 반환해야한다.
		}
		return vo;
		
	}
	
	public static void replyBoardInsert(BoardVO vo)
	{
		SqlSession session=null;
		
		try{
			session=ssf.openSession(true);
			
			session.insert("replyBoardInsert",vo); //total에 db연결해서 가져온 값을 여기에 채워!
			//selectone/ list는 commit을 날리지 않음!
	
			
		}catch(Exception ex){
			
			System.out.println("replyBoardInsert(): "+ex.getMessage());
			
		}finally{
			
			if(session!=null)
				session.close(); // 커넥션을 반환해야한다.
		}
	
	}
	
	
	public static BoardVO boardUpdateData(int no){
		
		SqlSession session=null;
		BoardVO vo=new BoardVO();
		
		try{
	
			session=ssf.openSession();
			vo=session.selectOne("replyDetailData",no); //total에 db연결해서 가져온 값을 여기에 채워!
			//selectone/ list는 commit을 날리지 않음!
			
		}catch(Exception ex){
			
			System.out.println("replyDetailData(): "+ex.getMessage());
			
		}finally{
			
			if(session!=null)
				session.close(); // 커넥션을 반환해야한다.
		}
		return vo;
	}
	
	public static void replyBoardUpdate(BoardVO vo)
	{
		SqlSession session=null;
		
		try{
	
			session=ssf.openSession(true);
			session.update("replyBoardUpdate",vo);
			
		}catch(Exception ex){
			
			System.out.println("replyDetailData(): "+ex.getMessage());
			
		}finally{
			
			if(session!=null)
				session.close(); // 커넥션을 반환해야한다.
		}

		
	}
	
	

	public static String replyGetPassword(int no){
		String pwd="";
		
		SqlSession session=null;
		
		try{
	
			session=ssf.openSession();
			pwd=session.selectOne("replyGetPassword",no); //total에 db연결해서 가져온 값을 여기에 채워!
		
			
		}catch(Exception ex){
			
			System.out.println("replyGetPassword(): "+ex.getMessage());
			
		}finally{
			
			if(session!=null)
				session.close(); // 커넥션을 반환해야한다.
		}
		return pwd;
	}
	
	
	
	
	
	
}
