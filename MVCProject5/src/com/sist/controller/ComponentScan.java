package com.sist.controller;
//패키지에서 클래스를 찾아오는 기능!!
import java.util.*;
import java.io.*;
public class ComponentScan {

	// 자바로 만든 리스트
	public List<String> getClassNameRead(String pack, String path){ //패키지명과 path를 받아
		
		List<String> list=new ArrayList<String>(); //
		
		//di(xml에 값을 주고 읽어가게끔 만들어놓은 작업.= 스프링이 xml을 읽어가도록 만들어 놓은 것. 키,값 설정), 
		//aop(자동호출기능! 소스 간결, 반복작업x), 
		//mvc 
		try{

		//src 경로는 defaultPath로 변경하여 그때그때 받아올 예정!
		//String path="C:\\Users\\sist\\git\\repository13\\MVCProject5\\src";
			
		path=path+"\\"+pack.replace(".","\\"); //pack => C:\Users\sist\git\repository13\MVCProject5\src\com\sist\controller
		//스트링																							====================
		
		//확장자가 .java인 것만 가져오기!
		//가져간 파일들을 D에서 @찾아서 처리
		
		File dir=new File(path); //파일에 directory읽음 => 스트링을 파일객체로 바꾸고
		
		File[] files=dir.listFiles();//전체파일 읽음(클래스파일)
		for(File f:files)
		{
			
			String name=f.getName();
			if(name.endsWith("java"))
			{
				String s=pack+"."+name.substring(0, name.indexOf("."));
				//f.getName(); => BoardModel.java
				//s="com.sist.model" + . + BoardModel
				// com.sist.model.BoardModel ==> class.forName에 넣어 메모리할당할 예정!
				
				list.add(s); //핸들러매핑 고고
				
			}
			
		}
		
		
		
		
		}catch(Exception ex) {}
		
		
		
		
		
		return list;
	}
	
	
	
	
}
