<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 예약페이지의 껍데기 부분 => 전체 틀과 맨 오른쪽 결과물 표시 부분!==> 결과물은 계속 ajax로 변함! => 모두 선택하면 예맥하기 활성화됨!-->
<!-- 디폴트를 줄떄는 ajax부분된 페이지에 미리 데이터를 뿌려놓고 ajax로 화면에 붙이면된다. -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
  <script type="text/javascript">
  $(function(){
		//시작하자마자 출력
	  //영화정보를 디폴트로 출력해놓아야한다.
	  $.ajax({
		
		  type:'post',
		  url:'movie.do',
		  //data는 보낼게 없다
		  success:function(res) //res가 전체 실행한 데이터가져옴!
		  {
			  $('#movie-list').html(res)
			  
		  }
	  })
	  
	  
	  $.ajax({
		
		  type:'post',
		  url:'date.do',
		  success:function(res)
		  {
			  $('#movie-date').html(res);  
		  }
	  })
	  
	   
	  
 
  })  
  </script>
</head>
<body>
<div class="container-fluid">
	<h1 class="text-center">영화 예매</h1>
	<div class="row">
		<table class="table">
			<tr>
			
				<td width=20% height="500">
					<table class="table">
						<tr>
							<td bgcolor="#ccccff" class="text-center">영화 정보</td>
						</tr>
					</table>
					<div style="overflow-y:scroll; height:450px;" id="movie-list">
					</div>
				</td>0

			
				
				<td width=20% height="500">
					<table class="table">
						<tr>
							<td bgcolor="#ccccff" class="text-center">극장 선택</td>
						</tr>
					</table>
					<div style="overflow-y:scroll; height:450px;" id="movie-theater">
					</div>
				</td>
			
				<td width=30% height="500">
					<table class="table">
						<tr>
							<td bgcolor="#ccccff" class="text-center" >날짜 선택</td>
						</tr>
					</table>
					<div id="movie-date"></div>
				</td>
			
		
				<td width=30% rowspan="2">
					<table class="table">
						<tr>
							<td bgcolor="#ccccff" class="text-center">예매정보</td><!--ajax될때마다 예약정보 변경되는 것 표시됨! -->
						</tr>
					</table>
					<table class="table">
						<tr>
							<td class="text-center">
							<img src="def.png" width=300 height=350 id="movie-poster"> <!-- 영화변경할때마다 포스터 변경되는 자리,  -->
							</td>
						</tr>
						<tr>
							<td><!-- 선택할때마다..영화제목 출력됨 -->
								<b id="movie-title"></b>
							</td>
						</tr>
						<tr>
							<td>
								<span style="color:#999">별점</span>
								<span id="movie-score"></span>
							</td>
						</tr>
						<tr>
							<td>
								<span style="color:#999">극장</span>
								<span id="movie-theater2"></span>
							</td>
						</tr>
						<tr>
							<td>
								<span style="color:#999">날짜</span>
								<span id="movie-date2"></span>
							</td>
						</tr>
						<tr>
							<td>
								<span style="color:#999">시간</span>
								<span id="movie-time2"></span>
							</td>
						</tr>
						<tr>
							<td>
								<span style="color:#999">인원</span>
								<span id="movie-inwon2"></span>
							</td>
						</tr>
						<tr>
							<td>
								<span style="color:#999">금액</span>
								<span id="movie-price"></span>
							</td>
						</tr>
						<tr>
							<td class="text-center">
								<input type=button value="예매하기" class="btn btn-sm btn-danger" disabled id="resBtn">
							</td>
						</tr>
					</table>
				</td>
			<tr>
				<td colspan="2" height="200" >
					<table class="table">
						<tr>
							<td bgcolor="#ccccff" class="text-center">시간선택</td>
						</tr>
					</table>
					<div id="movie-time"></div>
				</td>
			
				
			
				<td width=20% height="200" >
					<table class="table">
						<tr>
							<td bgcolor="#ccccff" class="text-center">인원선택</td>
						</tr>
					</table>
					<div id="movie-inwon"></div>
				</td>
			</tr>
				
			</tr>
		</table>
	</div>
</div>
</body>
</html>