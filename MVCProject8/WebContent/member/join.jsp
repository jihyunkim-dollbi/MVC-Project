<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> <!-- HTML 5 -->
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../shadow/css/shadowbox.css">
<script type="text/javascript" src="../shadow/js/shadowbox.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">

Shadowbox.init({
	
players:['iframe']

});
//jquery시작 => window.onload ==> 메인함수와 동일 
//$(document).ready(function(){}) => (document).ready 생략가능!
//var id=document.getELementById('id') => lib로서 동일문장!! => $('#id')
// <input type=text id=id size=10>
/*

 *	JavaScript, Jquery => 태그를 제어하는 프로그램(DOM)
  	selector : 동적으로 움직이게 하기위한 기능!
  	========
  		1) 태그를 읽을때 =>$('태그명') => $('tr')
  		2) ID를 읽을때 =>$('#id명')
  		3) CLASS를 읽을때 =>$('.class명')
  		4) 가상 =>자신, 내장 객체를 나타낼때 => $(this) 자신 , $(window) 내장객체, $(document)
 */
$(function(){
	//onclick="함수명" 과 동일!
	/*
		태그를 제어하는 방법: 
			1)CSS제어  : 클릭때마다 색변경됨
			2)이벤트 발생 : *** 중심설정
			
	*/
	//우편번호 검색을 눌렀을때 새도박스를 띄우려함 - content(어떤 파일을 띄울거니)), iframe: 인클루드하라
	$('#postBtn').click(function(){ // id가 postBtn을 눌렀다면 이 function을 수행하라..(따로 onclick함수를 만들필요x .click 하면 ok!)
		
		Shadowbox.open({
			
			content:'../member/postfind.do',
			title:'우편번호 검색',
			player:'iframe',
			width:500,
			height:400
			
		})
		
	})
	
});

 </script>

<style type="text/css">

.row {

	margin:0px auto;
	width: 700px;
}
input, select {

	display: inline-block;

}

.table, td {

	background-color: white;
}

</style>
</head>
<body>
	<div class="container">
		<h1 class="text-center">회원가입</h1>
		<div class="row">
			<form name="frm" action="join_ok.jsp" method="post"> <!--  "join_ok.jsp"으로 이동! post 데이터가 많아서. 데이터를 db로 보내는 jsp -->
			<table class="table table-hover">
				<tr>
					<th width=15% class="danger text-right">ID</th>
					<td width=85%>
						<input type=text name=id size=15 class="input-sm" readonly> <!-- 중복체크 후 새창에서 확인을 하고 확정값을 id칸에 넣을 예정! -->
						<input type="button" value="중복체크" class="btn btn-sm btn-danger" >
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right">비밀번호</th>
					<td width=85%>
						<input type=password name=pwd size=15 class="input-sm">&nbsp;
						재입력:<input type=password name=pwd1 size=15 class="input-sm"> <!-- JS에서 같은지 확인예정! -->
					
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right">이름</th>
					<td width=85%>
						<input type=text name=name size=15 class="input-sm" > 
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right">이메일</th>
					<td width=85%>
						<input type=text name=email size=50 class="input-sm" > 
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right">성별</th>
					<td width=85%>
						<input type="radio" name=sex value="남자" checked>남자
						<input type="radio" name=sex value="여자" >여자
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right">생년월일</th>
					<td width=85%>
						<input type=date name=birthday size=50 class="input-sm" > 
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right">우편번호</th>
					<td width=85%>
						<input type=text name=post1 size=5 class="input-sm" readonly> - 
						<input type=text name=post2 size=5 class="input-sm" readonly>
						<input type="button" class="btn btn-sm btn-primary" value="우편번호검색" id="postBtn"><!-- postBtn을 눌렀을때 액션! -->
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right">주소</th>
					<td width=85%>
						<input type=text name=addr1 size=50 class="input-sm" readonly> <!-- db에서 가져올 예정!!readonly --> 
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right">상세주소</th>
					<td width=85%>
						<input type=text name=addr2 size=50 class="input-sm" > 
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right">전화번호</th>
					<td width=85%>
					<select class="input-sm" name=tel1>
						<option>010</option>
						<option>011</option>
						<option>017</option>
					</select>
						<input type=text name=tel2 size=5 class="input-sm" > - 
						<input type=text name=tel3 size=7 class="input-sm" > 
					</td>
				</tr>
				
				<tr>
					<th width=15% class="danger text-right">소개</th>
					<td width=85%>
						<textarea rows="8" cols="60" name="content"></textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" class="text-center">
						<input type=button value="회원가입" class="btn btn-sm btn-info"
						onclick="join()"
						> <!--  button =submit(하면 화면 리셋됨!!) 클릭하면 join()함수 읽기!모든 데이터를 보냄! join_ok.jsp => 로 고고 !-->
						<input type=button value="취소" class="btn btn-sm btn-success"
							onclick="javascript:history.back()"
						>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>

</body>
</html>