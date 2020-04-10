<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
	
	margin: 0px auto;
	width: 350px;

}

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#id').focus();
	$('#checkBtn').click(function(){
		let id=$('#id').val(); //id가id인것의 값을 가져올때
		
		if(id.trim()=="")
			{
				$('#id').focus(); //아이디 입력 안되면포커스 되게
				return; 
			
			}
		//Membermodel에 id 전송!
		//결과값을 읽어서(idcheck_result.jsp로 결과값이 출력될 것) 처리를 여기서! 보내고 받고! 화면이 그대로 있는상태서 처리!
		//레스트컨트롤러? json을 보내고 파일을 보내고 결과값을 주고받을 수있는..?
		$.ajax({
			/*
				<초창기의 AJAX프로그램>
		  		시스템에 의해서 자동호출되는 함수 : 콜백함수 callback!
		  		1)AJAX
		  		   XMLHTTPREQUEST request=> 얻어온다(각 브라우저에 내장되어있다. ) 이것을 이용해 값을 주고받음!
		  		2) 연결
		  			request.open('post or get', '../member/idckeck_result.do', true)
		  																		====true(false동기화, true비동기화)
		  		3)결과값 가져오는 자동 호출 함수를 제작
		  		   request.onreadystatechange=sendMessege(정상완료 되면 자동으로 SENDMESSEGE()수행!)
		  		   
		  		4)전송할 데이터 설정
		  			request.send(id=amdin&pwd=1234)
		  		function sendMessege()
		  		{
		  			0 OPEN전 => 연결중
		  			1 OPEN후=> 연결완로!
		  			2 SEND준비
		  			3 SEND완료
		  			4 정상연결(call back), SEND완료 확인
		  			
		  			if(request.readystate==4)
		  				{
		  					if(request.status==200)// 서버 정상
		  						{
		  						
		  						}
		  						else
		  						{
		  							400,404,500
		  						
		  					}
		  				}
		  			
		  		}
		  		
		  		
			*/
		
			
			type:'post',
			url:'idckeck_result.do',//누구에게 보내? 여기로 결과(result)를 보내!
			//data:{"id":id,"pwd":"1234"}idckeck_result.do?id=id&pwd=1234
			data:{"id":id},//보내는 데이터, 앞 키 뒤 값
			success:function(result){ //정상수행(200), result(보내주는 형태)=>xml(getJSON으로 파싱하기), html, json, CSV파일! 
				
				let count=result.trim(); //읽어왔을때 문자열로 읽었기때문에 정수형으로 변환, Number or parseInt, 결과값을 정수형 0 or 1로만 받을 예정! db에서 count를 사용하여 개수를 받아올 것!
				// dckeck_result.do의 값이 0일때, 1일때!
				if(count==0) //일치하는 id 없음 => 사용 가능!
				{
					let msg='<font color=blue><b>'+id+'는(은) 사용 가능합니다</b></font>';
					$('#result').html(msg); //아래id가 result인 곳에 tag로 msg를 넣어라(출력)
					$('#ok').html(
						'<input type=button value=확인 class="btn btn-sm btn-success" onclick=ok()>'
							
					);
					
				}
				else //일치하는 id 있음 => 사용 불가능!
				{
					let msg='<font color=red><b>'+id+'는(은) 이미 사용중입니다</b></font>';//아래 span에서 출력할 예정!
					$('#result').html(msg); //아래id가 result인 곳에 text msg를 넣어라(출력)
				}
			},
			error:function(e){ //비정상수행(404,500)
				alert(e); //에러출력!
				
			}
		})
		
	})
	
});
function ok()
{
	let id=$('#id').val();
	parent.frm.id.value=id; // join parent.frm의 id value에 id를 대입해!
	parent.Shadowbox.close(); // 쉐도 박스를 오픈한 파일이 parent가 됨!join에서 띄움!
}
</script>

</head>
<body>
	<div class="container">
		<!-- <h2 class="text-center">아이디중복체크</h2>-->
		<div class="row">
			<table class="table">
				<tr>
					<td>
						입력:<input type=text id=id class="input-sm" size=15>
						<input type=button value="아이디체크" class="btn btn-sm btn-primary" id="checkBtn">
					</td>
				</tr>
				<tr>
					<td class="text-center">
						<span id="result"></span><!-- 여기에 결과값력 -->
					</td>
				</tr>
				<tr>
					<td class="text-center" id="ok"><!-- 불가능하면 버튼 안보이게 가능하면 버튼 보이게 -->
					
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>