package com.sist.dao;
import java.io.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//매번 doa에서 하던 작업을 여기서 한번에 로딩하고 불러서 가져다 쓰기 위함!

public class CreateSqlSessionFactory {
	
	private static SqlSessionFactory ssf;

	static{
		
		try{
			
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
			
			
			
		}catch(Exception ex){System.out.println(ex.getMessage());}
		
		
	}
	public static SqlSessionFactory getSsf() {
		return ssf;
	}

	
	
}
