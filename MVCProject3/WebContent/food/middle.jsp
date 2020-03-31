<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<c:forEach var="vo" items="${list}">
				<div class="thumbnail">
				<a href="detail.do?no=${vo.no }" target="_blank"> 
					<img src="${vo.image }" alt="Lights" style="width: 100%">
					<div>${vo.title }</div></a>
					<div>${vo.address }</div>
					<div>${vo.tel }</div>
					<div>${vo.score }</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>