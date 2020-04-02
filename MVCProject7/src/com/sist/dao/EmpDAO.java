package com.sist.dao;

import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class EmpDAO {

	private static SqlSessionFactory ssf;
	
	static{
		
		try{
		
			Reader reader=Resources.getResourceAsReader("Config.xml"); //파싱
			ssf=new SqlSessionFactoryBuilder().build(reader); //ssf에 파싱 내용 저장! build() =>sax 파싱!
			
		}catch(Exception ex)
		{ ex.printStackTrace(); }
		
	}
	
	
	public static List<EmpVO> empAllData()
	{
		SqlSession session=ssf.openSession();
		List<EmpVO> list=session.selectList("empAllData");//emp-mapper에서 찾아서 처리
		session.close();
		
		return list;
		
	}
}
