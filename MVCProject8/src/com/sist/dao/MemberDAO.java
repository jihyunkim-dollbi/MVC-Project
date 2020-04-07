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
	
}
