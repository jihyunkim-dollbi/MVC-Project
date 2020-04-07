<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){ //메인 개념과 동일! 단 한개만 존재해야! & 위에 src로 가져오고 function을 작성해야한다.
	//jquery는 json으로되어있다1!!!!!!!
	//css({"color":"red", "background-color":"green"})
	$('h1#h').css("color","red").css("background-color","green");
	$('h1.a').css("color","blue").css("background-color","pink"); //h1태그안의 a클래스만 처리해라.. 클래스 네임이 겹치지 않는다면 .a만 작성해도 ok!
	$('h2.a').css("color","black").css("background-color","pink");	
})


</script>
</head>
<body>
	<h1>Java</h1>
	<h1 id="h">Oracle</h1>
	<h1 class="a">Jsp</h1>
	<h1 class="a">Spring</h1>
	<h1>Kotlin</h1>
	<h2 class="a">korea</h2>
</body>
</html>