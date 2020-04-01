package com.sist.temp;
import java.lang.reflect.Method;
import java.util.*;

import com.sun.swing.internal.plaf.metal.resources.metal;


class A
{
	@RequestMapping("a.do")
	public void aaa()
	{
		System.out.println("A:aaa() call...");
	
	}
	@RequestMapping("b.do")
	public void bbb()
	{
		System.out.println("A:bbb() call...");
	
	}
	
	//복수의 어노테이션을 올려놓을 수있다.. 
//  @aaa
	@RequestMapping("c.do") // => getAnnotation(RequestMapping.class); => RequestMapping 어노테이션의 정보를 줘!!
	public void ccc()
	{
		System.out.println("A:ccc() call...");
	
	}
	
	@RequestMapping("d.do")
	public void ddd()
	{
		System.out.println("A:ddd() call...");
	
	}
	
	
}



public class MainClass2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan=new Scanner(System.in);
		//a.do ==> aaa
		//b.do ===> bbb수행하는지...
		
		//메소드 어노테이션을 만들어 아래와 같은 노동을 안하기 위함!!
		
		System.out.println("URI 주소 입력: ");
		String uri=scan.next();
	/*	
		A a=new A();
		
		if(uri.equals("a.do"))
		{
			a.aaa();
			
		}
		if(uri.equals("b.do"))
		{
			a.bbb();
			
		}
		if(uri.equals("c.do"))
		{
			a.ccc();
			
		}
		if(uri.equals("d.do"))
		{
			
			a.ddd();
		}
		
	*/
		
		try{
			
			//1.메모리할당
			Class clsName=Class.forName("com.sist.temp.A"); //클래스 네임 읽어서 정보를 clsName에 넣음
			Object obj=clsName.newInstance();// 해당 클래스 메모리 할당 == A a=new A();와  동일!
			
			 Method[] methods=clsName.getDeclaredMethods(); //해당 클래스에서 메소드 정보 모두 가져옴
			 
			 for(Method m:methods){
				 
				// System.out.println(m.getName());
				 /*	but, 순서대로 저장된 것은 아님! 배열 첨자로 출력은 불가능!
				  * ddd
					ccc
					aaa
					bbb

				  */
				 
				 RequestMapping rm=m.getAnnotation(RequestMapping.class); //어노테이션 중에 RequestMapping.class를 줘
				 
				 if(rm.value().equals(uri)) //RequestMapping 이 갖고 있는 값들 중에 입력값과 같은지..
				 {
					 m.invoke(obj, null);
					 //따라서 이렇게 코딩할 경우 메소드 이름을 몰라도 검색이 가능하다.
					 //모델에 여러 메소드가 존재할 것.= > 어노테이션 사용!
					 
				 }
				 
			 }
			
			
			
		}catch(Exception ex) {}
		
		
		
		
	}

}
