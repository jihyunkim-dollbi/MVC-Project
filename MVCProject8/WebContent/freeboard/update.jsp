<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<div class="wrapper row2">
  <div id="services" class="clear"> 
    <div class="row text-center">
     	<img src="../freeboard/jsp_board2.png" style="width:900px; height:300px;" >
    </div>
  		<div class="row">
			<form method=post action="../freeboard/update_ok.do">  
			<table class="table table-hover">
				<tr>
					<th width=20% class="text-right default success" >이름</th>
					<td width=80%>
					<input type=text name=name size=15 required value="${vo.name }"> <!-- required(not null로 설정해놓은 곳-공백이면 alert!넘어가지 않음!)는 5버전 이상 제공 -->
					</td>
				</tr>
				<tr>
					<th width=20% class="text-right default success" >제목</th>
					<td width=80%>
					<input type=text name=subject size=50 required value="${vo.subject }"><!-- 수정 눌렀을때 이전에 작성한 값이 띄워져 있어야하기 때문에 -->
					<input type=hidden name=no value="${vo.no }">
					</td>
				</tr>		
			
				<tr>
					<th width=20% class="text-right default success" >내용</th>
					<td width=80%>
					<textarea rows="8" cols="60" name=content required >${vo.content }</textarea>
					</td>
				</tr>
					
				<tr>
					<th width=20% class="text-right default success" >비밀번호</th>
					<td width=80%>
					<input type="password" name=pwd size=10 required>
					</td>
				</tr>
				
				<tr>
					<td class="text-center" colspan="2">
					<input type="submit" value="수정" class="btn btn-sm btn-primary">
					<input type="button" value="취소" class="btn btn-sm btn-danger"
					onclick="javascript:history.back()"> <!--취소  버튼 눌렀을때  다시 돌아가기!-->
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</div>
</body>
</html>