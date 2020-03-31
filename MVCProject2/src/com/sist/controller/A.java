package com.sist.controller;
import java.util.*;

public class A {
	Map map=new HashMap();
	
	public A()
	{
		
		map.put("b", new B()); 
		
		//map에 저장되는 순간 하나로 정해짐!!
		//싱글톤 - 한개의 메모리를 갖고 여러개를 재사용!
		//싱글톤 패턴!
	}
	
	public static void main(String[] args) {
		
		A a = new A();
		B b =(B)a.map.get("b"); // B b = new b();
		
		System.out.println("b="+b);
		
	}
	
	
}

class B{
	
	public void display()
	{
		System.out.println("display call");
		
	}
	
}