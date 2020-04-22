<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 

	 JSP(list.do) ==> DispatcherServlet = MODEL(@REQUEST~(req,res)<=>DAO)=> 
	 
	 REQUEST.ADDATTRIBUTE()처리내용 전송! =>DS => REQUEST전송!(FORWARD(~.JSP)를 이용해서 사용자한테 전송)
	 sendRedirect는 사용자한테 값을 보내지 않고 화면만 이동할 경우...

 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<c:choose>
		<c:when test="${vo.mgs=='NOID' }">
		<!-- id가 없는 상태 -->
		<script>
			alert("ID가 존재하지 않습니다.");
			history.back();
		
		</script>
		</c:when>
		<c:when test="${vo.mgs=='NOPWD' }">
		<!-- id있고 pwd가 틀린상태 -->
			<script>
			alert("비밀번호가 틀립니다.");
			history.back();
		
		</script>
		</c:when>
		
		<!-- 둘다 맞는데.. -->
		<c:otherwise>
			<c:if test="${vo.admin=='y' }">
			<!-- admin인 경우! session에 저보를 저장했기때문에 forward보다 redirec.. -->
			<c:redirect url="admin.do"/>
			</c:if>
			
			<c:if test="${vo.admin!='y' }">
			<!-- 일반회원인 경우 -->
			<c:redirect url="reserve.do"/>
			</c:if>
		</c:otherwise>
	
	
	
	
	</c:choose>