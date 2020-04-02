package com.sist.dao;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import java.util.*;

public class FoodDAO {

		private static SqlSessionFactory ssf; //파싱된 데이터 저장공간
		
		static{
			
			try{
				
				//xml읽기
				Reader reader=Resources.getResourceAsReader("Config.xml");
				
				//xml 파싱 요청
				ssf=new SqlSessionFactoryBuilder().build(reader);
				//id를 읽고 sel~ 문장 처리함
				
				
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			
			
			
		}
		
		public static List<CategoryVO> categoryListData()
		{
			// connection갖고 있음
			SqlSession session=ssf.openSession();
			
			//데이터 가져오기
			List<CategoryVO> list=session.selectList("categoryListData");
			
			//커넥션 반환
			session.close();
			
			return list;
		
		}
		
		
		public static List<FoodVO> middleListData(int cno)
		{
			// connection갖고 있음
			SqlSession session=ssf.openSession();
			
			//데이터 가져오기
			List<FoodVO> list=session.selectList("middleListData", cno); // cno ==> #{cno}에 값이 채워짐
			
			//커넥션 반환
			session.close();
			
			return list;
		}
		
		
		public static FoodVO detailData(int no)
		{
			// connection갖고 있음
			SqlSession session=ssf.openSession();
			
			//데이터 가져오기
			FoodVO list=session.selectOne("detailData", no); // no ==> #{no}에 값이 채워짐
			
			//커넥션 반환
			session.close();
			
			return list;
		}
		
		
}
