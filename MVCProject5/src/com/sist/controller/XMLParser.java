package com.sist.controller;

/*
 * 패키지를 여러개 만들 경우?
 * 현재 package! =>  dao / vo / model 
 * 
 * 모델은 기능별로 클래스로 나누고
 * 한 클래스에 메소드를 모아둠
 * 
 * -------------------------------
 * 
 * SAX 파싱 => 읽기 전용!(수정, 삭제X)
 * 
 * (extends)DefaultHandler
 * 
 * startDocument
 * startElement
 * characters
 * endElement
 * endDocument
 * 
 * 
 * <?xml version="1.0" ?>    ==> startDocument => 한줄씩 읽을때마다 이 매소드가 실행됨!
 * <book> => startElement(태그 열린경우!)
 * 	<list> => startElement
 * 		<author> => startElement
 * 			sss   => characters
 * 		</author> => endElement
 * 		<title>  => startElement
 * 			aaa  =>  characters
 * 		</title>  =>  endElement
 * 	</list>  =>  endElement
 * </book>  =>  endElement
 * 				endDocument
 * 
 */
import java.util.*;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class XMLParser extends DefaultHandler { //SAX파싱하기! xml을 한줄씩 읽는 방식!!
	// 				   ======================상속 :xml을 한줄 읽을때마다 자동으로 매소드들(startElement)을 실행 시켜줌
	
	private List<String> list=new ArrayList<String>();
    
	@Override // defaultHandler를 상속했기때문에 override할 수 있다.
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		
		try{
			
			//  <context:component-scan base-package="com.sist.*"/>   ==> com.sist.* 를 가져오기 위함!
			if(qName.equals("context:component-scan")) // qName 열린테그에서 해당 tag를 찾을때!  context:component-scan 태그가 열렸으면!
			{ // qName: 태크명..
				
				String pack=attributes.getValue("base-package"); // context:component-scan 태그 안에서도 특정 속성값을 가져올떄!
				System.out.println(pack);
				list.add(pack);
				
			}
			
		}catch(Exception ex) {}
		
	}

	//패키지를 가져올때 리스트로 가져오려 list로 만듬!!
	/* <context:component-scan base-package="com.sist.*"/>
	 *  여러 개의 패키지를 넣기 때문에 list로 패키지를 가져올 예정! 
	 */
	public List<String> getList() {
		return list;
	}
	
	
	

}
