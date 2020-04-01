package com.sist.temp;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface Controller {
/*
 * source, class 프로그램 종료시 메모리 삭제!

runtime 프로그램 종료시까지 메모리를 저장해

@target! 어떤 것을 구분할거니? 
type => class 
class를 구분하기!!


 * 클래스에 @Controller있으면 메모리할당o 
 * 클래스에 @Controller없으면 메모리할당x
 * 
 * 어노테이션 만들때,
 * 메소드를 구분하는 경우는 구분문자열이 필요하지만
 * 클래스의 구분은 있냐 없냐만 판단!
 * 
 */
	
}
