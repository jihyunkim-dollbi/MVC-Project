package com.sist.temp;
import java.util.*;
import java.io.*;
/*
 * 패키지 단위로 메모리 할당을 하려하는데....데이터형(+vo) & 클래스 & 인터페이서 구분 
 * 
 * 우리가 구분하는 이유는 싱글톤을 만들어 한번의 메모리를 할당하여 그 주소로 계속 사용하기 위함이다.
 * 따라서 vo, interface와 dao,model과 같은 기능을 가진 클래스들은 등록(싱글톤으로 만들어,한번의 메모리할당!)하여 사용함
 * 
 * 메모리  할당을 해놓고 싱글톤으로 만들지 말아야하는  것! interface!(인터페이스: 미완성클래스)! , VO(메모리할당X , 데이터형으로 사용!)
 * 메모리 할당을 해놓고 싱글톤으로 만들어놓고 사용해야하는 것! model classes!
 * 
 * 
 * 따라서 한 동일한 패키지안에서 interface는 걸러야한다. 어떻게 해결할까?
 * 어노테이션으로 각 클래스를 구분하여준 후에 !
 * 어노테이션은 구분자!
 * 
 * 어노테이션 올라간 것만 메모리할당해줘!
 * 메모리 할당 할 것들에만 어노테이션하기! => Spring 시스템!
 * (if문과 동일한 과정 => 매칭하기!)
 * 
 * 우리가 해야할 두가지!!
 * 
 * 1)메소드 찾는 어노테이션 만들기
 * 2)메모리 할당(싱글톤)대상인지 구분하는 어노테이션 만들기
 * 
 * p264, 266.....
 * 
 *  스프링 코딩은 new 연산자를 사용하지 않는다.(매번 메모리할당하지 않고(싱글톤으로 만들어) 결합된 클래스들의 영향력을 줄인다.)
 *  이유는 참조하는 클래스들의 에러방지! 영향력 낮춘다.
 *  따라서 중간에 debs?를 한두개 주어 결합성을 낮춘다.(스프링- 클래스 관리자)
 *  
 * 
 * 	따라서 패키지명을 주어 클래스찾아 자동으로 메모리할당 하기 위함!! => 생성자에서 맵에 넣어 싱글톤으로 사용하기 위함!
 *  <bean id="main/home" class="com.sist.model.HomeModel"/>
 *  							===========================
 *  
 */
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		componentScan("com.sist.model"); // com.sist.model 패키지 안의 클래스들을 메모리할당해줘!
		/*
		 * 패키지 하나를 넘기고 그 안에 클래스를 로딩되게 만들기.
		 * 따라서 패키지 하나만 등록하면 끝!
		 * 그 안에 모델들은 메모리 할당 ok!
		 * 할때마다 bean 태그 등록할 필요x
		 * 
		 * 
		 *  BoardModel.java
			HomeModel.java
			JoinModel.java
			MainModel.java
			Model.java
			MovieModel.java
			MypageModel.java
			ReserveModel.java

		 * 
		 */
	}

	public static List<String> componentScan(String pack) // 패키지 명을 pack에 입력하면 안에 파일들을 읽어들이기.
	{
		//********************
		// 클래스 한 개의 일반적인 파일 경로
		// C:\Users\sist\git\repository13\MVCProject4\src\com\sist\model\BoardModel.java
		// \ 한개로 되어있다.
		
		List<String> list=new ArrayList<String>();
		
		try{
			
			//src경로
			String path="C:\\Users\\sist\\git\\repository13\\MVCProject4\\src";
			System.out.println("path1: "+path); // => 프린팅으로 확인하니 \\ 에서 \ 한 개로 출력됨!
			//path1: C:\Users\sist\git\repository13\MVCProject4\src
			
			path=path+"\\"+pack.replace(".","\\");
			System.out.println("path2: " + path); 
			//C:\Users\sist\git\repository13\MVCProject4\src\com\sist\model
			//												================추가됨!
			
			File dir=new File(path); // 파일 객체 생성 , path경로를 찾아 path경로(model 패키지!)에 있는 것을  dir에 넣음 
			System.out.println("dir: "+ dir);
			// dir: C:\Users\sist\git\repository13\MVCProject4\src\com\sist\model => model 패키지!
			
			File[] files=dir.listFiles(); //listFiles(); 폴더 안(모델 패키지 dir)에 파일 객체(모델 클래스들)를 배열(files)로 받는다.
			
			
			for(File f:files){ // 모델 패키지(files)안에 모델 클래스들(f)을 읽기

				String ext=f.getName().substring(f.getName().lastIndexOf(".")+1); // f에서 뒤의 .까지 자름!
				
				System.out.println("f: "+f);
				//f: C:\Users\sist\git\repository13\MVCProject4\src\com\sist\model\BoardModel.java
				//f: C:\Users\sist\git\repository13\MVCProject4\src\com\sist\model\HomeModel.java
				//f: C:\Users\sist\git\repository13\MVCProject4\src\com\sist\model\JoinModel.java
				// 모델 패키지에 있는 파일들을 한개씩 읽음.
				
				
				System.out.println("f.getName(): " +f.getName()); // getName() => 해당 경로의 파일이나 폴더 네임을 준다.
				//f.getName(): MypageModel.java
				
				
				
				System.out.println("ext: "+ ext); 
				//ext: java
			
				if(!ext.equals("java"))
					continue;
					//자바가 아닌 파일이 있으면 메모리할당을 안하고 자바가 있는 파일만 메모리 할당을 할 예정!
					//.java로 끝나는 클래스명이 아니라면 메모리할당 하지마.
					//.java로 끝나면 메로리 할당 하게됨.
				
				
				String p=pack+"."+f.getName().substring(0,f.getName().lastIndexOf("."));
				
				
				//위에서 매개변수로 넣어준 값!=> componentScan("com.sist.model"); 
				System.out.println("pack: " + pack);
				//pack: com.sist.model
				
				//  pack+"." ==> com.sist.model.
				
				System.out.println("???: " + f.getName().substring(0,f.getName().lastIndexOf(".")));
				//C:\Users\sist\git\repository13\MVCProject4\src\com\sist\model\JoinModel.java
				//???: JoinModel
				
				
				System.out.println("f: " + f);
				//f: C:\Users\sist\git\repository13\MVCProject4\src\com\sist\model\MypageModel.java
				System.out.println("f.getName(): "+f.getName()); 
				//f.getName(): MypageModel.java
				
				//pack : com.sist.model
				// .
				 
				System.out.println("p: "+p); 
				//p: com.sist.model.ReserveModel
				//클래스이름을 메모리 할당할 수있도록 만듬!
				//따라서 패키지명을 주어 클래스찾아 자동으로 메모리할당 하기 위함!! 
				//  <bean id="main/home" class="com.sist.model.HomeModel"/>
				//  							===========================
				
				
				/*
				 * 클래스를 bean tag로 등록할 필요x
				 * 루프를 돌리면 자동으로 메모리 할당 가능!
BoardModel.java
com.sist.model.BoardModel
HomeModel.java
com.sist.model.HomeModel
JoinModel.java
com.sist.model.JoinModel
MainModel.java
com.sist.model.MainModel
Model.java
com.sist.model.Model
MovieModel.java
com.sist.model.MovieModel
MypageModel.java
com.sist.model.MypageModel
ReserveModel.java
com.sist.model.ReserveModel

				 */
			}
			
			
			
		}catch(Exception ex) {}
		
		return list;
		
	}
	
	
}
