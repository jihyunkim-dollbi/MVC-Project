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
<div class="container">
		<h2>글쓰기</h2>
		<div class="row">
			<form method=post action="../reply/reply_ok.do">  
			<!-- _ok를 만들어 놓고 만들러 갈테지만 _ok.jsp파일은 모두 값을 받아 출력해주는 페이지!- 값을 받아서 db연동해주는 아이. 
				항상 자바를 먼저 수행하고 html 실행됨 => 따라서 데이터를 먼저 가져오고 뿌리는 것이다!
				dao를 만들어 놓지 않았으므로..
				만들러 gogo!!
			
			-->
			<table class="table table-hover">
				<tr>
					<th width=20% class="text-right default success" >이름</th>
					<td width=80%>
					<input type=text name=name size=15 required> <!-- required(not null로 설정해놓은 곳-공백이면 alert!넘어가지 않음!)는 5버전 이상 제공 -->
					<input type=hidden name=pno value="${pno }">
					
					<!--부모 no를 히든으로 보낸다.
					detail.jsp=>pno(request로)=>  reply.jsp =>pno(hidden으로) reply_ok.jsp(값만 받고 출력x)==>pno==> model로! ==> 모델 의reply_ok.do(처리)에서 결과적으로 처리됨
					-->
					</td>
				</tr>
				<tr>
					<th width=20% class="text-right default success" >제목</th>
					<td width=80%>
					<input type=text name=subject size=50 required>
					</td>
				</tr>		
			
				<tr>
					<th width=20% class="text-right default success" >내용</th>
					<td width=80%>
					<textarea rows="8" cols="60" name=content required></textarea>
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
					<input type="submit" value="답변" class="btn btn-sm btn-primary">
					<input type="button" value="취소" class="btn btn-sm btn-danger"
					onclick="javascript:history.back()"> <!--취소  버튼 눌렀을때  다시 돌아가기!-->
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>

</body>
</html>