<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
	<h1 class="text-center">로그인</h1>
		<div class="row" style="margin:0xp auto; width:350px">
		<form action="post" action="login_ok.do">
			<table class="table">
				<tr>
					<th class="text-center danger" width="25%">ID</th>
					<td width="75%" class="text-left">
						<input type="text" name=id size=15 class="input-sm" required placeholder="아이디">
					</td>
				</tr>
				
				<tr>
					<th class="text-center danger" width="25%">PASSWORD</th>
					<td width="75%" class="text-left">
						<input type=password name=pwd size=15 class="input-sm" required placeholder="패스워드">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type=submit value="로그인" class="btn btn-sm btn-primary">
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>