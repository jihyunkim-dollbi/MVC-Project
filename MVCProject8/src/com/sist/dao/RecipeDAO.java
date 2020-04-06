package com.sist.dao;

import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
/*
 * 요청 ==> 리퀘스트 이용 => DS에서 받음!(SERVICE()호출!)=> 리퀘스트 전송 => MODEL(recipeListData(request))처리 <=====> DAO데이터연결!
 * 
 * DAO에서 받은 값을 리퀘스트에 추가(ADDATTRIBUTE)==>MODEL ==>  DS ====> RESOLVER => 해당 JSP로 REQUEST전송(forward(request)) => 메모리에 출력=> 사용자가 브라우저에서 읽음!
 * request.setAttribute()
 * 
 * addAttribute!!
 * 
 */
public class RecipeDAO {

	private static SqlSessionFactory ssf;
	
	static{
		
		ssf=CreateSqlSessionFactory.getSsf();
		
	}
	
	
	public static List<RecipeVO> recipeListData(Map map){
		
		SqlSession session=null;
		List<RecipeVO> list=new ArrayList<RecipeVO>();
		
		try{
			
			session=ssf.openSession();
			list=session.selectList("recipeListData", map);
			
		}catch(Exception ex)
		{
			System.out.println("recipeListData():" + ex.getMessage());
			
		}finally
		{
			if(session !=null)
				session.close();
		}
		
		
		return list;
	}
	
	
	public static int recipeTotalPage()
	{
		
		SqlSession session=null;
		int total=0;
		

		try{
			
			session=ssf.openSession();
			total=session.selectOne("recipeTotalPage");
			
		}catch(Exception ex)
		{
			System.out.println("recipeTotalPage():" + ex.getMessage());
			
		}finally
		{
			if(session !=null)
				session.close();
		}
		
		
		return total;

	}

		public static List<ChefVO> chefListData(Map map){
		
		SqlSession session=null;
		List<ChefVO> list=new ArrayList<ChefVO>();
		
		try{
			
			session=ssf.openSession();
			list=session.selectList("chefListData", map);
			
		}catch(Exception ex)
		{
			System.out.println("chefListData():" + ex.getMessage());
			
		}finally
		{
			if(session !=null)
				session.close();
		}
		
		
		return list;
	}

		public static int chefTotalPage()
		{
			
			SqlSession session=null;
			int total=0;
			

			try{
				
				session=ssf.openSession();
				total=session.selectOne("chefTotalPage");
				
			}catch(Exception ex)
			{
				System.out.println("chefTotalPage():" + ex.getMessage());
				
			}finally
			{
				if(session !=null)
					session.close();
			}
			
			
			return total;

		}
		
		
}
