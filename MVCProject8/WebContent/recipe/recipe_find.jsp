<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jqeury.js"></script>
<script type="text/javascript">
$(function(){
	
	$.ajax({ // 디폴트1 출력
		type:'post',   //post방식으로
		url:'../recipe/recipe_find_ok.do',  //ajax되는 곳에 이 파일을 올려둘 것.
		data:{"no1":1},  //db로 보낼 데이터
		success:function(res){
		
			$('#recipes').html(res); // db에서 가져온 값 res을 html형식으로 출력하여 id가 recipes 인곳에 뿌려줘..
		},
		error:function(e)
		{
			alert(e);
		
		}
	})
	
	$('.images').hover(function(){ //class가 "images" 인 곳을 선택시 호버 액션 부여
		
		$(this).css('cursor','pointer'); //this(recipe_find)에 css를 읽을 때 cursor를 pointer로 바꿔줘
	
		},
		function(){ // 호버하지 않은 경우 cursor를 none으로 해줘
	
			$(this).css('cursor','none');
		})
	
	$('.images').click(function(){ //클래스가 images인 곳을 클릭했을떄 액션 부여
		//아래 forEach를 이용해 1~57까지 만든 변수를  .attr("value")로 가져와 no변수에 넣음1
		//변수를 만든 이유는 아래 ajax에서  no를 사용하기 위함
		let no=$(this).attr("value"); //이 페이지에서 속성값이 value라는 이름을 가진 값을 no변수에 넣음 => value="${i }" i=1~57
		
		$.ajax({
			type:'post', //post방식으로 보냄!
			url:'../recipe/recipe_find_ok.do', //ajax될 부분에 이 파일을 넣을거야
			data:{"no1":no}, // var no에 해당하는 데이터를 넣을 예정
			success:function(res){ //이 function을 수행하여 res에 저장해
			
				$('#recipes').html(res); //no를 보내서 db에서 가져온 데이터를 html에 출력후 id가 recipes인 곳에 넣어!
			},
			error:function(e)
			{
				alert(e);
			}
			
		});
		
	})
	
})

</script>
</head>
<body>
	<div class="wrapper row2">
		<div id="services" class="clear">
			<div class="col-md-4">
				<c:forEach var="i" begin="1" end="57"><!-- for문을 줘서 변수i, 시작 1, 끝 57을 줌! -->
					<img src="image2/${i }.png" style="width:55px; height:55px" value="${i }" class="images"
					title="${data[i-1] }"
					><!-- class를 준 이유는? images를 클릭했을때  -->
				</c:forEach><!-- i를 no로 보내서 no와 string을 매칭시키기 위함 -->
			</div>
			<div class="col-md-8" id="recipes"><!--id="recipes", ajax로 출력될 예정! 따라서 div에 id를 주어 ajax에서 읽어가도록 한다! ajax는 id를 읽음! -->
				
			</div>
		</div>
	</div>	
</body>
</html>