<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	
	//업데이트처리
	var u=0; //화면이 바뀌기 전까지 0 => 수행하면 변화!
	var i=0;
	$(function(){
		
	
		//메인함수 => has Onload 기능!
		$('.upBtn').click(function(){ // 특정 댓글 수정을 클릭했다면..
			
			//그전에 열었던것을 닫아놓고 시작1! //내가 클릭한것만 보여주기...(대댓글 확인/수정도 동일!!)
			$('.reply_update').hide(); 
			$('.reply_insert').hide();
			var no=$(this).attr("data-no");
			
			if(u==0){
				$('#m'+no).show();
				u=1;
				
			}else{
				$('#m'+no).hide();
				u=0;	
			}
		})
		
		
		$(function(){
		//메인함수 => has Onload 기능!
		$('.inBtn').click(function(){ // 특정 댓글 수정을 클릭했다면..
			
			//그전에 열었던것을 닫아놓고 시작1! //내가 클릭한것만 보여주기...(대댓글 확인/수정도 동일!!)
			$('.reply_update').hide(); 
			$('.reply_insert').hide();
			var no=$(this).attr("data-no");
			
			if(i==0){
				
				//
				$('#i'+no).show();
				i=1;
				
			}else{
				$('#i'+no).hide();
				i=0;	
			}
		})
		//함수 끝!
		
	})
	//스크립트 끝
</script>

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
						<a href="../freeboard/update.do?no=${vo.no}" class="btn btn-xs btn-danger">수정</a> 
						<a href="../freeboard/delete.do?no=${vo.no} " class="btn btn-xs btn-info">삭제</a>
						<a href="../freeboard/list.do" class="btn btn-xs btn-success">목록</a>
					</td>
				</tr>
			</table>
			
			<div style="height:20px"></div>
			
			<table class="table">
				<c:forEach var="rvo" items="${list }">
					<tr>
						<td class="text-left">
						<c:if test="${rvo.group_tab >0 }">
							<c:forEach var="i" begin=1 end="${rvo.group_tab }">
							&nbsp;&nbsp;		
							</c:forEach>
							<img src="icon_reply.gif"/>
						</c:if>
							${rvo.name }&nbsp;<span style="color:#999">(${rvo.dbday })</span>
						</td>
						<td class="text-right">
						<c:if test="${rvo.msg!='관리자가 삭제한 댓글입니다.' }">
						  <c:if test="${sessionScope.id==rvo.id }">
							<span class="btn btn-xs btn-primary upBtn" data-no="${rvo.no }">수정</span><!-- 댓글번호 => "m${rvo.no }"를 찾게 만듬! <tr>수정창 뜨게만듬!! -->
							<a href="../freeboard/reply_delete.do?no=${rvo.no }&bno=${vo.no}" class="btn btn-xs btn-danger">삭제</a>
						    </c:if>
						    <span class="btn btn-xs btn-success inBtn" data-no="${rvo.no }">댓글</span><!-- span이지만 버튼모양을 주었기때문에 버튼모양!! -->	
						</c:if>    
							
						</td>
					</tr>
					<tr>
						
						<td colspan="2" class="text-left" valign="top">
							<pre style="white-space: pre-wrap;">${rvo.msg }</pre>
						</td>
					</tr>
					<tr id="m${rvo.no }" style="display:none" class="reply_update">	<!-- 창을 보여주는 기능! -->
					<td colspan="2">
					<form action="../freeboard/reply_update.do" method="post">
						<input type="hidden" name=bno value="${vo.no }" ><!--bno는 다시 데테일로 넘어봐야해서 , vo.no=게시물번호  -->
						<input type="hidden" name=no value="${rvo.no }"><!-- 다시 돌아와야함 => rvo.no에 대해서 수정하려면! 댓글번호 넘겨줘야 -->
						<textarea rows="5" cols="125" name="msg" style="float: left">${rvo.msg }</textarea><!-- msg 입력한 결과값넘김 -->
						<input type="submit" class="btn btn-sm btn-primary" style="height:100px; float:left" value="수정하기">
					</form>
					</td>
				</tr>
				
				<!-- 대댓글기능! - 상위클래스 정보 필요! -->
				<tr id="i${rvo.no }" style="display:none" class="reply_insert">
					<td colspan="2">
					<form action="../freeboard/reply_reply_insert.do" method="post">
						<input type="hidden" name=bno value="${vo.no }" ><!--bno는 다시 데테일로 넘어봐야해서 , vo.no=게시물번호  -->
						<input type="hidden" name=pno value="${rvo.no }"><!-- 상위번호: 상위의 정보를 가져와야 depth와 tab등등 변경!-->
						<textarea rows="5" cols="125" name="msg" style="float: left"></textarea><!-- msg 입력한 결과값넘김 -->
						<input type="submit" class="btn btn-sm btn-primary" style="height:100px; float:left" value="댓글쓰기">
					</form>
					</td>
				</tr>
				
				</c:forEach>
					<tr>
						<td class="text-center" colspan="2">
							<a href="#" class="btn btn-xs btn-danger">이전</a>
							${curpage } page / ${totalpage } pages
							<a href="#" class="btn btn-xs btn-danger">다음</a> 
						</td>
					</tr>
			</table>
			<table class="table">
				<tr>
					<td>
					<form action="../freeboard/reply_insert.do" method="post">
						<input type="hidden" name=bno value="${vo.no }" ><!-- vo.no 게시물번호  -->
						<textarea rows="5" cols="125" name="msg" style="float: left"></textarea><!-- msg 입력한 결과값넘김 -->
						<input type="submit" class="btn btn-sm btn-primary" style="height:100px; float:left" value="댓글쓰기">
					</form>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>	
</body>
</html>