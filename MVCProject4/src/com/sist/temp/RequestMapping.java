package com.sist.temp;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface RequestMapping {
/*
 *  메소드 어노테이션!
 * 메소드마다 이름을 부여해야한다...
 * 메소드는 무엇일때 무엇인지를 확인해야한다. 사용자가 요청할때마다 사용을 할 것이기때문에..
 * @RequestMapping("main.do") main.do가 들어오면 아래 메소드를 실행하라! => main.do: value값을 가져온다. 
	FOR문을 돌려서 REQUESTMAPPING이 가진 VALUE값과 일치하는 메소드를 가져와라..
	
	   Spring에는 모든 시스템이 다 만들어져있다.
	=> 조건문 없이 찾을 수 있다, 메소드 이름이 어떤 것이 들어와도 찾을 수있다.   
 */
	//사용자가 보내주는 것이 requestURI이기 때문에.. 문자열로  설정한다.
	//해당 메소드 어노테이션의 문자열이 뭐였니?
	
	public String value();
	
}
