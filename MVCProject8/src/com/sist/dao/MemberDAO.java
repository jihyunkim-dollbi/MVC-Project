package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;

public class MemberDAO {

	private static SqlSessionFactory ssf;
	static{
		
		ssf=CreateSqlSessionFactory.getSsf();
		
	}
	
	public static List<ZipcodeVO> postfindData(String dong)
	{
		
		List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
		
		SqlSession session=null;
		
		try{
			session=ssf.openSession(); //오라클 연결
			list=session.selectList("postfindData", dong); // 실행한 결과 list에 넣어줘!
			
		}catch(Exception ex)
		{
			System.out.println("postfindData(): " + ex.getMessage());
			
		}finally{
			
			if(session != null)
				session.close(); //닫지 않으면 8번후에 먹통됨..
		}
		
		return list;
		
	}
	
	//from member-mapper
	public static int idcheckData(String id)
	{
		int count=0;
		SqlSession session=null;
		
		try{
			
			session=ssf.openSession();
			count=session.selectOne("idcheckData",id); //sql문장을 읽어서 실행된 결과를 보내줄 것 => 어디로? 이 값을 memberModel에서 받음!!
			
		}catch(Exception ex)
		{
			System.out.println("idcheckData(): "+ ex.getMessage());
			
		}finally{
			
			if(session!=null)
				session.close();
			
		}
		
		return count;

	}

	public static void memberInsert(MemberVO vo)
	{
		SqlSession session=null;
		
		try{
			
			session=ssf.openSession(true); //insert! commit!
			session.insert("memberInsert", vo);
			
		}catch(Exception ex){
			
			System.out.println("memberInsert(): " + ex.getMessage());
		}finally
		{
			if(session != null)
				session.close();
		}
		
	}
	
	//cameback  from mapper id="idCount", getPwd
	public static MemberVO memberLogin(String id, String pwd)
	{
		  MemberVO vo=new MemberVO();
		  SqlSession session=null;
		  try
		  {
			  session=ssf.openSession();
			   int count=session.selectOne("idCount", id);
			  if(count==0)
			  {
				  vo.setMsg("NOID"); //3가지 값을 모두 확인하기 위해 MSG만듬
				  
			  }else //id존재
			  {
				  MemberVO mvo=session.selectOne("getPwd", id);
				  if(pwd.equals(mvo.getPwd())) // 일치
				  {
					  vo.setMsg("OK");
					  vo.setAdmin(mvo.getAdmin());
					  vo.setName(mvo.getName());
					  
				  }else//불일치
				  {
					  vo.setMsg("NOPWD");
					  
				  }
				  
			  }
			  
		  }catch(Exception ex)
		  {
			  System.out.println("memberLogin(): " + ex.getMessage());
			  
		  }finally
		  {
			  if(session!=null)
				  session.close();
			  
		  }
		
		  return vo;
	}
	
}
