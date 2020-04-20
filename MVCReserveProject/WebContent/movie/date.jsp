<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row" style="width:450px; margin:0px auto;">
		<h3 class="text-center">${year }년 ${month }월</h3>
		<table class="table">
			<tr>
				<td>
					<select name=year>
					<c:forEach var="i" begin="2020" end="2030" >
						<c:if test="${i==year }">
							<option selected>${i }</option>
						</c:if>
						<c:if test="${i!=year }">
							<option>${i }</option>
						</c:if>
					</c:forEach>
					</select>년도 &nbsp;
					<select name=month>
					<c:forEach var="i" begin="1" end="12" >
						<c:if test="${i==month }">
							<option selected>${i }</option>
						</c:if>
						<c:if test="${i!=month }">
							<option>${i }</option>
						</c:if>
					</c:forEach>
					</select>월
					
				</td>
			</tr>
		</table>
		<table class="table">
			<tr>
				<c:forEach var="sw" items="${strWeek }">
					<th class="text-center danger">
					${sw }
					</th>
				</c:forEach>
			</tr>
		
			<!-- week변수선언! => 토요일까지 가변 tr tag를 바꿔야하기 때문에! -->
			<c:set var="week" value="${week }"/>
			<%--
				week =3 수요일 , 4 목요일 => 
				따라서 ${week }을 변수로 줘서 계속 가다가 토요일에서 tr tag를 바꿔야함..
			 --%>
			<c:forEach var="i" begin="1" end="${lastday }">
				<c:if test="${i==1 }"> <%--if문은 공백 띈것만 함! --%>
					<tr>
						<c:forEach var="j" begin="1" end="${week }">
							<td>&nbsp;</td>
						</c:forEach>
				</c:if>
					 <td class="text-center">${i }</td>
					<c:set var="week" value="${week+1 }"/><%--week+1 == week++ --%>
					<c:if test="${week>6 }">
					<c:set var="week" value="0"/>
					</tr>
					<tr>
					</c:if>
			</c:forEach>
			</tr>
			
		</table>
	</div>
</body>
</html>