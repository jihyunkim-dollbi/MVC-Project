<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
$(function(){
		
	$('#pwd2').keyup(function(){ //한글자씩 씌워질떄마다 읽어줘
		
		var k=$(this).val();  // 그 값을 받아서 k에 넣어
		console.log("k:"+ k) // k를 한번 출력해봐
		var no=$('#no').val();  //id가 no인 것의 
		
		$.ajax({
		
			type:'post',
			url:'../reply/password_check.do',
			data:{"pwd":k,"no":no},
			success:function(res){//0또는 1의 값을 가지는 res/
				//@RequestMapping("reply/password_check.do") 여기서 res받음 
				console.log(res)
				var no=res.trim(); //
				
				if(no==1) // 일치했던 경우
				{
					
					$('#result').html("<font color=blue>비밀번호가 맞습니다. 수정할 수 있습니다.</font>")
					$('#updateBtn').attr('disabled', false)
			
				}
				else // 일치하지 않았던 경우
				{
			
					$('#result').html("<font color=red>비밀번호가 틀렸습니다.</font>")
					$('#updateBtn').attr('disabled', true)
					
				}
			
			}
			
		})
		
	})
	
})

</script>
<style type="text/css">

.row {
	margin: 0px auto; 
	width: 600px;	
}

h2 {

	text-align: center;
}

</style>
</head>
<body>
<div class="container">
		<h2>수정하기</h2>
		<div class="row">
			<form method=post action="../reply/update_ok.do"> 
		
			<table class="table table-hover">
				<tr>
					<th width=20% class="text-right default success" >이름</th>
					<td width=80%>
					<input type=text name=name size=15 required value="${vo.name }">
					<input type="hidden" value="${vo.no }" name=no id=no>
					</td>
				</tr>
				<tr>
					<th width=20% class="text-right default success" >제목</th>
					<td width=80%>
					<input type=text name=subject size=50 required value="${vo.subject }">
					</td>
				</tr>
				<tr> 
					<!-- input은 value에 넣고  textarea는 박에 놓기 -->
					<th width=20% class="text-right default success" >내용</th>
					<td width=80%>
					<textarea rows="8" cols="60" name=content required>${vo.content }</textarea>
					</td>
				</tr>
				<tr>
					<th width=20% class="text-right default success" >비밀번호</th>
					<td width=80%>
					<input type="password" name=pwd size=10 id=pwd2>
					
					<div id="result"></div>
					<!--제이쿼리부분! -->
					</td>
				</tr>
				
				<tr>
					<td class="text-center" colspan="2">
					<input type="submit" value="수정" class="btn btn-sm btn-primary"
					id="updateBtn" disabled><!-- 비활성화 시켜놓음! -->
					<input type="button" value="취소" class="btn btn-sm btn-danger"
					onclick="javascript:history.back()"> <!--취소버튼 눌렀을때  다시 돌아가기!-->
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>

</body>
</html>