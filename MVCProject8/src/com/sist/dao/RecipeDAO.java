package com.sist.dao;

import java.util.*;
import com.sist.vo.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
/*
 * 요청 ==>  리퀘스트 이용 => DS에서 받음!(SERVICE()호출!)=> 리퀘스트 전송 => MODEL(recipeListData(request))처리 <=====> DAO데이터연결!
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
		
		//id="recipeListData"에서 이미 no값으로 vo를 받아놨음!
		public static RecipeDetailVO recipeDetailData(int no){
			
			RecipeDetailVO vo= new RecipeDetailVO();
			
			SqlSession session=null;
			try{
				session=ssf.openSession();
				vo=session.selectOne("recipeDetailData", no); //vo한개 가져오므로 selectone()
				
			}catch(Exception ex)
			{
				System.out.println("recipeDetailData(): "+ ex.getMessage());
				
			}finally
			{
				if(session!=null)
					session.close();
			}
			return vo;
		}
		
		
		public static int recipeCount(int no)
		{
			
			SqlSession session=null;
			int total=0;
			
			try{
				
				session=ssf.openSession();
				total=session.selectOne("recipeCount2",no);
				
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
		
		
		
		public static List<RecipeVO> chefDetailData(Map map){ // mapper에서 chef 받음!
			
			SqlSession session=null;
			List<RecipeVO> list=new ArrayList<RecipeVO>();
			
			try{
				
				session=ssf.openSession();
				list=session.selectList("chefDetailData",map);
				
			}catch(Exception ex)
			{
				System.out.println("chefDetailData():" + ex.getMessage());
				
			}finally
			{
				if(session !=null)
					session.close();
			}
			
			
			return list;
		}

			public static int chefDataTotalPage(String chef)
			{
				
				SqlSession session=null;
				int total=0;
				

				try{
					
					session=ssf.openSession();
					total=session.selectOne("chefDataTotalPage", chef);
					
				}catch(Exception ex)
				{
					System.out.println("chefDataTotalPage():" + ex.getMessage());
					
				}finally
				{
					if(session !=null)
						session.close();
				}
				
				
				return total;

			}
		
		
			//레시피 검색하는 dao!
			//사용자로부터 fd를 받음!
			//mapper에서 사용되는 ss는 우리가 가진 컬럼! 
			public static List<RecipeVO> recipeFindData(String fd){ // parameterType="java.util.Map"
				
				SqlSession session=null;
				List<RecipeVO> list=new ArrayList<RecipeVO>();
				
				try{
					session=ssf.openSession();
					Map map=new HashMap();
					map.put("fd", fd);
					//fd를 넣은 map을 recipeFindData에 수행하여 list에 값을 넣음!!  
					list=session.selectList("recipeFindData",map);				
					
				}catch(Exception ex)
				{
					System.out.println("recipeFindData():" + ex.getMessage());
		
				}finally
				{
					if(session !=null)
						session.close();
				}
				return list;
			}
		
			
		
}
