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
			 * 2)preparedstatement => ps에 채워서 sql로 감!!
			 * 	 ps.setstring(1,"")
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
				session.close(); //  커넥션을 반환해야한다.
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
	
	

	public static String replyGetPassword(int no)
	{
		
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
	
	
	// DML 여러개 사용으로 트랜젝션 프로그램 사용!
	public static void replyReplyInsert(int pno,BoardVO vo)
	{
		
			SqlSession session=null;
		
		try{
	
			session=ssf.openSession(); //커넥해
			
			//상위것 가져오기 ==> replyParentInfoData 먼저 가져와야 아래모두 실행 가능
			BoardVO pvo=session.selectOne("replyParentInfoData", pno);
			
			//step증가 시킴
			session.update("replyGroupStepIncrement", pvo);
			
			//insert 하기 => replyReplyInsert
			//사용자가 보내준 int vo사용하기
			vo.setGroup_id(pvo.getGroup_id()); //부모의 id사용!
			vo.setGroup_step(pvo.getGroup_step()+1);
			vo.setGroup_tab(pvo.getGroup_tab()+1);
			
			//?
			vo.setRoot(pno);
			
			//대댓글!
			session.insert("replyReplyInsert", vo);
			
			// root 증가 => //pno에 대해 증가해라
			session.update("replyDepthIncrement", pno); 
			
			session.commit(); //모두 정상수행하면 commit해.. 
			
		}
		catch(Exception ex)
		{
			
			System.out.println("replyReplyInsert(): "+ex.getMessage());
			session.rollback(); // 하나라도 실패하면 모두 실행을 안하겠다 => 그래서 위에서 autocommit을 하지 않았다..
			
			
		}
		finally
		{
			
			if(session!=null)
				session.close(); // 커넥션을 반환해야한다.
		}
		
		
	}
	
	//DML 여러개 사용으로 트랜젝션 프로그램 사용!
	public static boolean replyDelete(int no, String pwd)
	{
		
			boolean bCheck=false;
			SqlSession session=null;
			
			try
			{
				session=ssf.openSession();
				String db_pwd=session.selectOne("replyGetPassword", no);
				
				//비번 맞는 경우
				if(db_pwd.equals(pwd))
				{
					bCheck=true;
					BoardVO vo=session.selectOne("replyDeleteInfoData", no); //값을 가져옴 db로부터 ...!!!!
					if(vo.getDepth()==0)
					{
						//댓글이 없다
						session.delete("replyDelete",no);
						
					}
					else
					{
						//댓글 있는 상태
						vo.setSubject("관리자가 삭제한 게시물 입니다.");
						vo.setContent("관리자가 삭제한 게시물 입니다.");
						vo.setNo(no);
						session.update("replySubjectUpdate",vo);//해당 sql에서 3개를 가져왔으므로 위에서 3개 모두 채움
					}
					session.update("replyDepthDecrement", vo.getRoot()); //상위 번호(vo.getRoot())에..depth를 감소시켜라...	
					
				}else
				{
					//비번 틀린경우
					bCheck=false;
					
				}
				session.commit();
				
				
			}catch(Exception ex)
			{
			
				System.out.println("replyDelete(): "+ ex.getMessage());
				
			}finally
			{
				if(session!=null)
					session.close();
			}
			
			return bCheck;
	}
	
	
	
}
