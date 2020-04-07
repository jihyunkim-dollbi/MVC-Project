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
	width: 500px;

}

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script><!-- 라이브러리 로드! 단독으로 이 화면에만 띄움 메인과 상관x -->
<script type="text/javascript">
//jquery는 항상 function이 있어야 동작=main함수!
// findBtn을 눌렀을때 dom을 보내줌
/*
 * 
 	//값을 읽을때 (getter)
 	<input type=text id="id"> ==> $('#id').val() => 입력한 값을 가져올떄
	<td>aaa</td>  ==> $('td').text() => td태그 사이에 값을 가져올때   ====> getter!
 	<td><span>bbb</span></td> ==> $('td').html() ==> <span>bbb</span> => tag까지 필요한 경우
 							  ==> $('td').text() ==> aaa  ==> 문자열만 필요한 경우
	//주소값 가져올때 							  
 	<a href="aaa"> ==> $('a').attr("href") ==>aaa
 	
 	//값을 줄떄(setter)
 	<input type=text id="id"> ==> #('#id').val('admin') => text에 admin이 들어감!  => setter! 
 
 	//값을 추가할때!
 	$('td').append() 
 
 */
$(function(){

	$('#findBtn').click(function(){
	
		var dong=$('#dong').val(); //여기에 입력한 값을 가져와..
		//console.log(dong);
		if(dong.trim()=="")
			{
				//console.log("여기까지 실행됐음");
				$('#dong').focus(); //빈칸일 경우 커서 다시 입력창에 놓기!
				return;
			}
		//alert(dong);   .do?dong=신촌 과 동일!
		$.ajax({//서버로 보내서 데이터 읽어오는 것을 동시에 할 예정!
			
			//get방식이니 post방식이니  
			type:'POST',
			url:'../member/postfind_result.do', //누구에게 보낼거니
			data:{"dong":dong}, //보낼 데이터가 있어? dong을 보내 db를 검색해 뿌려줘야...//json으로
			//데이터를 보냄!
			//정상수행 & 실패시
			success:function(result){
				console.log("여기까지 됐음");
				var div=$('#result').html(result); //#result ==> 검색결과를 id="result"에(postfind) 넣음!! => db에서 가져온 값 html(result)을 아래에 모두 첨부할 예정!
			},
			error:function(request,status,error)
			{
				alert("code = "+ request.status + " message = " + request.responseText + " error = " + error);
			}
			
		});
	});
});




</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td>
						입력:<input type=text id="dong" size=15 class="input-sm">
						<input type=button id="findBtn" class="btn btn-sm btn-danger" value="입력">
					</td>
				</tr>
				<tr>
					<td class="text-center">
						<sub style="color:red">※동/읍/면을 입력하세요</sub>
					</td>
				</tr>
			</table>
			<div id="result"></div>
		</div>
	</div>
</body>
</html>