<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>



</head>
<body>
	<div class="wrapper row2">
 	 <div id="services" class="clear"> 
  	  <div class="row text-center">
   	<img src="../freeboard/jsp_board2.png" style="width:900px; height:300px;" >
   	 </div>
   	 <div class="row">
			<table class="table">
				<tr>
					<th class="text-center danger" width=20%>게시물번호</th>
					<td class="text-center" width=30%>${vo.no }</td>
					<th class="text-center danger" width=20%>작성일</th>
					<td class="text-center" width=30%>
					<fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/><!-- 변화해서 출력해줘 -->
					
					</td>
				</tr>
				<tr>
					<th class="text-center danger" width=20%>이름</th>
					<td class="text-center" width=30%>${vo.name }</td>
					<th class="text-center danger" width=20%>조회수</th>
					<td class="text-center" width=30%>${vo.hit }</td>
				</tr>
				<tr>
					<th class="text-center danger" width=20%>제목</th>
					<td class="text-letf" colspan="3">${vo.subject }</td>
				</tr>
				<tr>
					<td class="text-center" colspan="4" valign="top" height="200">${vo.content }</td>
				</tr>
				<tr>
					<td class="text-right" colspan="4">
						<a href="../freeboard/update.do?no=${vo.no }" class="btn btn-xs btn-danger">수정</a> 
						<a href="../freeboard/delete.do?no=${vo.no} " class="btn btn-xs btn-info">삭제</a>
						<a href="../freeboard/list.do" class="btn btn-xs btn-success">목록</a>
					</td>
				</tr>
			</table>
			
		
		</div>
	</div>
</div>	
</body>
</html>