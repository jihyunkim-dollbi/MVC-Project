package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.*;
import com.sist.vo.*;

public class MainDAO {

	private static SqlSessionFactory ssf;
	
	static{
		
		ssf=CreateSqlSessionFactory.getSsf();
		
	}
	
	public static List<CategoryVO> mainImageData()
	{
		
		SqlSession session=null;
		List<CategoryVO> list=new ArrayList<CategoryVO>();
		
		try{
			
			session=ssf.openSession();
			list=session.selectList("mainImageData");
			
		}catch(Exception ex){
			
			System.out.println("mainImageData(): "+ex.getMessage());
			
		}finally
		{
			if(session!=null)
				session.close();
		}
		
		return list;
	}
	
	
	public static int recipeCount()
	{
		int count=0;
		SqlSession session=null;
		
		try{
			session=ssf.openSession();   //getConnection() 주소 찾는 부분
			count=session.selectOne("recipeCount");
			
		}catch(Exception ex)
		{
			System.out.println("recipeCount(): "+ex.getMessage());
			
		}finally{
			
			if(session != null)
				session.close();
		}
		
		return count;

	}
	

	public static List<RecipeVO> mainRecipeData(){
		
		
		SqlSession session=null;
		List<RecipeVO> rlist=new ArrayList<RecipeVO>();
		
		try{
			
			session=ssf.openSession();
			rlist=session.selectList("mainRecipeData");
			
		}catch(Exception ex)
		{
			System.out.println("mainRecipeData: "+ex.getMessage());
			
		}finally
		{
			if(session != null)
			session.close();
		}
		
		return rlist;
	}
	
	
	public static FoodVO mainFoodDetailData(int no)
	{
		SqlSession session=null;
		FoodVO vo= new FoodVO();
		
		try{
			
			session=ssf.openSession();
			vo=session.selectOne("mainFoodDetailData", no);
			
		}catch(Exception ex)
		{
			System.out.println("mainFoodDetailData(): "+ex.getMessage());
			
		}finally
		{
			if(session !=null)
			session.close();
		}
		
		return vo;
	}
	
	
	
	
	
	
}
