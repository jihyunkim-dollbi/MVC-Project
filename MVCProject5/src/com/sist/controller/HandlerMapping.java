package com.sist.controller;
//viewResolver : jsp 파일 찾는 기능

import java.io.*;
import java.util.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class HandlerMapping { //handlerMapping : 모델 찾는 기능!

	//xmlparer에서 패키지명을 가져와
	//전달 받은 패키지에서 클래스네임을 읽고 list로 담아서 dispatcher로 보낼 예정! => D 여기서 싱글톤으로 메모리 할당하여 저장해놓고 사용할 예정!!
	private List<String> list=new ArrayList<String>(); //리스트 공간 만들고. =>아래서 패키지에서 찾은 크래스들을 여기에넣을얘정!
	
	
	public HandlerMapping(String path, String defaultPath) //XMLPARSER가 XML파싱후 패키지 경로path를 주면..
	{
		
		try{

			SAXParserFactory spf=SAXParserFactory.newInstance();
			//sax파서기

			SAXParser sp=spf.newSAXParser();
			XMLParser xp=new XMLParser();
			sp.parse(new File(path),xp); 
			List<String> pList=xp.getList(); //xml 파일에서 패키지 리스트 읽어서 pList에 넣음!
			//pList에는 패키지명칭들이 들어가있음! =>compo에게 넘겨
			
			ComponentScan cs=new ComponentScan();//가져옴
			for(String pack:pList)
			{
				List<String> fNames=cs.getClassNameRead(pack,defaultPath); //1패키지 개수대로 
				
				for(String name:fNames) //2.클래스네임들 읽어들임
				{
					System.out.println("name"+name);
					//핸들러매핑 list에 넣을 예정
					list.add(name);	
					
				}
			}

		}catch(Exception ex) {}
	}
	
		
		public List<String> getList(){ 
			
		return list; //패키지명칭 넘겨줌! 
	}
	
}
