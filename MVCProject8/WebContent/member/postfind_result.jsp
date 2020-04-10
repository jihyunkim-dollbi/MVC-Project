<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ajax 부분  postfind에서 버튼을 누르면  서버에 갔다가 여기에 출력한 것을 postfind에서 출력할 예정!!-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
td {

	font-size: 8pt;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>

<script type="text/javascript">
$(function(){
	$('.post_click').hover(function(){ // hover => 2개를 만들개 되면 if ~ else와 같은 의미!!
		$(this).css("cursor","pointer").css("background-color","#FC6");
		
		},function(){
		
		$(this).css("cursor","none").css("background-color","white");
		
	});
	
	$('.post_click').click(function(){
		
		var zip=$(this).attr('zip');
		var addr=$(this).attr('addr');
		
		parent.frm.post1.value=zip.substring(0,3);
		parent.frm.post2.value=zip.substring(4,7);
		parent.frm.addr1.value=addr;
		parent.Shadowbox.close();
		
	});
	
});
/*
function ok(zip, addr)
{	//join.jsp= parent로 값을 보내
	parent.frm.post1.value=zip.substring(0,3);
	parent.frm.post2.value=zip.substring(4,7);
	parent.frm.addr1.value=addr;
	parent.Shadowbox.close();
		
}
*/
</script>
</head>
<body>
	<c:if test="${count==0 }">
		<table class="table">
			<tr>
				<td class="text-center">
					<b>검색할 결과가 없습니다.</b>
				</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${count!=0 }">
		<table class="table">
			<tr class="success">
				<th class="text-center">우편번호</th>
				<th class="text-center">주소</th>
			</tr>
			<c:forEach var="vo" items="${list }">
				<tr class="post_click" zip="${vo.zipcode }" addr="${vo.address }">
					<td class="text-center"> ${vo.zipcode } </td>
					<td>${vo.address }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>