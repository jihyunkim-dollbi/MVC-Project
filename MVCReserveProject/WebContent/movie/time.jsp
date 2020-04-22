<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	
	$('.times').hover(function(){
	
		
		$(this).css("cursor","pointer");
		$(this).attr("class","times success");
	},function(){
		
		$(this).css("cursor","none");
		$(this).attr("class","times");
		
	})
	//hover끝!
	
	
	
	$('.times').click(function(){
	
		var time=$(this).text();
		var h=time.substring(0,time.indexOf(":"));
		var m=time.substring(time.indexOf(":")+1);
		
		var t=h+"시간"+m+"분";
		$('#movie-time2').text(t); //t값을 여기로 보내!!
		
		
		//reserve로 정보 보내기
		$('#rtime').val(t);
		
		
		$.ajax({
		
			type:'post',
			url:'inwon.do',
			success:function(res){
				
				$('#movie-inwon').text(res);
			
		}//ajax끝
		
	})//클릭함수 끝
	
	
})
//스크립트 끝!!

</script>
</head>
<body>
	<table class="table">
		<tr>
			<c:forEach var="time" items="${tList }">
				<td class="times">${time }</td>
			</c:forEach>
		
		</tr>
	</table>
</body>
</html>