<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--pwd가 일치하는지 안하는지 --%>
<c:if test="${bCheck==true }">
	<c:redirect url="../freeboard/detail.do?no=${no }"></c:redirect>
</c:if>

<c:if test="${bCheck==false }">
	<script>
		alert("비밀번호가 틀립니다")
		history.back();
	</script>
</c:if>