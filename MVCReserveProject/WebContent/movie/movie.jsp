<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
  $(function(){
	  
	  $('.movie_title').hover(function(){
		
		  $(this).css('cursor','pointer');
		  
	  },function(){
		  
		  $(this).css('cursor','none');
	  })
	  
	  //데이터 읽어서 영화결과물 란에 뿌리기
	  $('.movie_title').click(function(){
		
		  var poster=$(this).attr("data-poster");
		  
		  $('#0').attr("src",poster); // 부모 페이지에 여기의 scr정보(src="def.png")를 여기의 poster로 보내!! 
		  $('#movie-title').text($(this).text());
		  $('#movie-score').text($(this).attr('data-score'));
		
		  
		//영화제목 선택했을때 극장정보 jsp를 ajax로 가져오기 위함! 
		  	var tno=$(this).attr("data-theater");
			 
			$.ajax({
			
				type:'post',
				url:'theater.do',
				data:{"tno":tno}, //tno는 ajax로 movie파일에 있지만 부모페이지에 표시되기 때문에 여기서도 줄수있다.
				success:function(res)
				{
					$('#movie-theater').html(res);
								
				}
			})
			//ajax끝!!
		  
	  })
	  //영화선택 버튼 클릭 함수 끝!!

	
	  
  })
  //함수끝!
  
</script>
</head>
<body>
	<div class="row" style="width:350px; margin:0px auto;height:500px ">
		<table class="table">
			<c:forEach var="vo" items="${mlist }">
			<tr>
				<td>
					<img src="${vo.poster }" width=35 height=35>
				</td>
				<td class="movie_title" data-poster="${vo.poster }" data-score="${vo.score }" data-theater="${vo.theaterNo }">${vo.title }</td><!-- data-poster를 보내기 -->
				<!--  이미 가지고 있는 정보를 페이지로 보낼수있다.. 스크립트로 정보를 감추고 있다가 보내기=> reserve에서 읽음 -->
			</tr>
			</c:forEach>
		
		</table>
	
	</div>
</body>
</html>