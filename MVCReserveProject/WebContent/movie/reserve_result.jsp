<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="row">
			<table class="table">
				<tr>
					<td width=30% class="text-center" rowspan="7">
						<img src="${vo.poster }" width=100%>
					</td>
					<td colspan="2">${vo.title }</td>
				</tr>
				<tr>
					<td class="text-right" width=20%>감독</td>
					<td class="text-left" width=50%>${vo.director }</td>
				</tr>
				<tr>
					<td class="text-right" width=20%>출연</td>
					<td class="text-left" width=50%>${vo.actor }</td>
				</tr>
				<tr>
					<td class="text-right" width=20%>장르</td>
					<td class="text-left" width=50%>${vo.genre }</td>
				</tr>
				<tr>
					<td class="text-right" width=20%>등급</td>
					<td class="text-left" width=50%>${vo.grade }</td>
				</tr>
				<tr>
					<td class="text-right" width=20%>시간</td>
					<td class="text-left" width=50%>${vo.time }</td>
				</tr>
				<tr>
					<td class="text-right" width=20%>상영일</td>
					<td class="text-left" width=50%>${vo.regdate }</td>
				</tr>
				<tr><td colspan="3" height="200" class="text-left" valign="top">${vo.story }</td></tr>
				
				
			</table>
		</div>

</body>
</html>