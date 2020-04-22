package com.sist.dao;
import java.util.*;



import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class MovieDAO {

	private static SqlSessionFactory ssf;
	
	static{
		
		
		try{
			
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader); //sax한줄씩 읽어서 데이터 가져옴
			
		}catch(Exception ex)
		{
			
			System.out.println(ex.getMessage()); //에러 프린팅
		}
	}
	
	
	//기능처리
	public static List<MovieVO> movieListData()
	{
		List<MovieVO> list=new ArrayList<MovieVO>();
		
		SqlSession session= null;
		
		try{
			
			session=ssf.openSession();
			list=session.selectList("movieListData"); //sql로 값 가져오기
			
		}catch(Exception ex){
			
			System.out.println("movieListData(): "+ex.getMessage());
		}finally{
			
			if(session!=null)
				session.close(); //dbcp구조 =>반환처리
		}
		return list;
	}
	
	//영화 선택시 극장  출력되는 부분
	public static void movieTheaterUpdate(MovieVO vo)
	{
		SqlSession session=null;
		
		try{
			
			session=ssf.openSession(true);
			session.update("movieTheaterUpdate", vo);
			
		}
		catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			if(session!=null)
				session.close();
			//main으로 가서 극장정보 첨부?
		}
		
	}
	
	
	//선택된 영화 의 영화관 뿌리기
	public static ReserveTheaterVO movieTheaterData(int tno)
	{
		ReserveTheaterVO vo= new ReserveTheaterVO();
		SqlSession session=null;
		
		try{
		
			session=ssf.openSession();
			vo=session.selectOne("movieTheaterData", tno);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			
		}finally{
			
			if(session!=null)
				session.close();
		}
		return vo;
	}
	
	
	
	public static void movieDataUpdate(ReserveTheaterVO vo)
	{
		SqlSession session=null;
		
		try{
			
			session=ssf.openSession(true);
			session.update("movieDataUpdate", vo);
			
		}
		catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			if(session!=null)
				session.close();
			//main으로 가서 극장정보 첨부?
		}
		
	}
	
	
	

	public static void movieTimeUpdate(ReserveDateVO vo)
	{
		SqlSession session=null;
		
		try{
			
			session=ssf.openSession(true);
			session.update("movieTimeUpdate", vo);
			
		}
		catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			if(session!=null)
				session.close();
		}
		
	}
	
	
	public static String movieTimeData(int tno)
	{
		
		
		String result="";
		SqlSession session=null;
		
		try{
			
			session=ssf.openSession();
			result=session.selectOne("movieTimeData", tno);
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			if(session!=null)
			session.close();
			
		}
		
		return result;
		
	}
		
		//List => forEach in()
		public static String movieTimeData2(int tno)
		{
			
			String result="";
			SqlSession session=null;
			
			try{
				
				session=ssf.openSession();
				result=session.selectOne("movieTimeData2", tno);
				
			}catch(Exception ex){
				
				ex.printStackTrace();
				
			}finally{
				
				if(session!=null)
				session.close();
				
			}
			
			return result;
				
		}
	
	
		//비번확인
		public static MemberVO movieLogin(String id, String pwd){
			
			
			MemberVO vo= new MemberVO();
			SqlSession session=null;
			
			try
			{
				
				session=ssf.openSession(); //커넥션의 주소를 가져옴..?
				 int count=session.selectOne("movieIdCount",id); //count리턴형 1 or 0
				 if(count==0){
					 
					 vo.setMgs("NOID"); //아이디 존재X
				 }
				 else
				 {
					 //아이디가 존재하는 경우
					 MemberVO mvo=session.selectOne("movieGetPwd",id); //리턴형 vo
					 
					 //비번확인
					 if(pwd.equals(mvo.getPwd()))
					 {
						 //비번일치
						vo.setMgs("OK");
						vo.setId(mvo.getId());
						vo.setAdmin(mvo.getAdmin());
						vo.setName(mvo.getName());
						//vo에 모두 실어서 넘김!
					 }
					 else
					 {
						 //비번x
						 vo.setMgs("NOPWD"); //메시지
						 
					 }
				 }
						 
						 
				vo=session.selectOne("movieGetPwd",pwd);
				
			}catch(Exception ex){
				
				ex.printStackTrace();
				
			}finally{
				
				if(session!=null)
					session.close();
			}
			return vo;
			
		}
		
		
		
	
}














