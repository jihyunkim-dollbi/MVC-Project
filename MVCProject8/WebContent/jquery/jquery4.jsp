<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){ //생성자와 같은 개념!
	
	//가상 셀렉터!
	//$('h1').css("color","blue") //시작하자 마자 실행
	//$('h1').css("color","orange")
	//$('h1:even').css("color","pink")
	//$('h1:odd').css("color","magenta")
	
	//$('h1').eq(4).css("background-color","yellow")
	// eq는 0부터 인덱스
	//h1:eq(4) => h1테그의 4번째 값만 가져와라 => 0,1,2,3,4! 
	
	//가장 첫번째 h1태그의 값에 적용
	$('h1').first().css("background-color","green")
	
	//가장 마지막 h1태그의 값에 적용
	$('h1').last().css("background-color","red")
	
})
/*
 	1)
 	window.onload=function(){ //시작하자 마자 실행 -js
		
	} 
 	2)
	window.onload=()=>{
		
	}
	3)
	$(document=화면).ready(function(){
	  ===================> 	(document=화면).ready =>생략하여 사용가능!!
	})
 
 */
</script>
</head>
<body>
	<h1>Java</h1>
	<h1>Oracle</h1>
	<h1>Jsp</h1>
	<h1>Spring</h1>
	<h1>Kotlin</h1>
</body>
</html>