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
	
}














