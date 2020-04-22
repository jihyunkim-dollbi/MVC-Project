<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">

$(function(){

	$('.theater').hover(function(){ //핸디커서 //갖다대었을때
	
		$(this).css("cursor","pointer");	
	
	},function(){
		
		$(this).css("cursor","none");		
	})
	//선택함수끝
	
	$('.theater').click(function(){ //클릭했다면,
		
		var data=$(this).text().trim(); //[${vo.tloc }]&nbsp;${vo.tname }  트림중요!
		var loc=data.substring(1,data.lastIndexOf(']'));
		var name=data.substring(data.indexOf(']')+2);
		//alert("loc: "+ loc+" \ name:"+name); //목동 CGV
		
		var theater=name+"("+loc+")";
		$('#movie-theater2').text(theater); //reserve에 예매정보란에 결과 출력해주기 ==> theater.jsp의 값을 클릭하는 순간 reserve.jsp로 보냄!
		
		
		//reserve로 결과값 보내기 ==> 보내고 hidden으로 값을 model로 보낼예정!
		$('#tname').val(theater); 
		
		
		//극장선택시
		//date에 값을 보내기
		var year= $('#year').val();
		var month=$('#month').val();
		var rdate=$(this).attr("data-date");
		
		$.ajax({
		
			type:'post',
			url:'date.do',
			data:{"year":year,"month":month,"rdate":rdate},
			success:function(res){
				
				$('#movie-date').html(res);
				
			}
			
		})
		//ajax끝!	
		
		
		
		
	})//클릭함수 끝!
	
})
//전체함수 끝!!



</script>
</head>
<body>
	<div class="row" style="width:350px; margin:0px auto;height:500px ">
		<table class="table">

			<c:forEach var="vo" items="${tList }">
			<tr>
				<td class="theater" data-date="${vo.tdate }"><!-- 클릭할때마다 date.jsp에서 가져가 씀  db연동 안하고 여기서 바로 줄예정!model에서 받아서 자를 예정! -->
					[${vo.tloc }]&nbsp;${vo.tname }
				</td>
			</tr>
			</c:forEach>

		</table>
	</div>

</body>
</html>