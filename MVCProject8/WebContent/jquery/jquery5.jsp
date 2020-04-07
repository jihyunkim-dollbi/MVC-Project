<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> <!-- 5버전을 사용 예정!! -->
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">

.row {
	margin: 0px auto; 
	width: 600px;	
}

h2 {

	text-align: center;
}

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
var index=2;
$(function(){
	$('#add').click(function(){
		//태그를 첨부할 수있다..
		$('#user-table').append( //추가해라..
			'<tr id="f'+index+'">'
			+'<td>'
			+'파일'+(index)+':'
			+'<input type=file size=20>'
			+'</td>'
			+'</tr>'
		)
		index=index+1;
	})
	$('#cancel').click(function(){
		if(index>1)
			{
				$('#f'+(index-1)).remove()
				index=index-1;
			}
		
	})
})

</script>
</head>
<body>
	<div class="container">
		<h2>글쓰기</h2>
		<div class="row">
			<form method=post action="insert_ok.do">  
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
					<th width=20% class="text-right success">첨부파일</th>	
					<td>
						<table class="table">
							<tr>
								<td class="text-right">
									<input type=button id="add" class="btn btn-sm btn-primary" value="추가">
									<input type=button id="cancel" class="btn btn-sm btn-danger" value="취소"> 
								</td>
							</tr>
						</table>
						<table class="table" id="user-table">
							<tr id=f1>
								<td>파일1:<input type=file size=15></td>
							</tr>
						</table>
						
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
					<input type="submit" value="글쓰기" class="btn btn-sm btn-primary">
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