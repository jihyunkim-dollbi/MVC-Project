<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		//그림을 띄워 놓기
		$.getJSON("weekly.json", function(data){ //data안에 파일 넣음
			//alert(data) //json: array[]로 이루어진 Object{}
			/*
				json 
				
				{"key","value"} => 1개의 객체
				 "value"==> 값을 줄때 [] 값을 배열로 넣을 수 있다. {"key",""}
				
				data
				datas1:[{},{},{},..],
				datas2:[{},{}],
				datas3:[]
			*/
			$.each(data["datas"],function(key,value){ //datas =>key, : ~ =>값인 블럭을 한개씩(총 8개) 가져올 예정!
			
			 $('#list').append(
			 '<div class="col-md-3">'
		   	 +'<div class="thumbnail">'
		     +'<img src="'+value.poster+'" alt="Lights" style="width:100%" onclick=detail('+value.no+')>'
		     +'<div class="caption">'
		     +'<p id="ppp">'+value.title+'</p>'
		     +'</div>'
		     +'</a>'
		     +'</div>'
		     +'</div>'
			 
			) 
			
		   });

		});
		
		//로딩이 안된상태이기 때문에 기능처리x => function으로 넣어야한다.
		//$('#ppp').click(function(){
		//	let p=$(this).text();
		//	alert(p);
		})
		
	});
	
	//JSON 파싱!
	function detail(no) //디테일 함수! 클릭했을때, 상세정보를 다이얼로그로 띄워주기 위한 함수!
	{
		$.getJSON("weekly.json", function(data){ //data안에 파일 넣음
			
			$.each(data["datas"],function(key,value){ //datas =>key, : ~ =>값인 블럭을 한개씩(총 8개) 가져올 예정!
			
			if(no==value.no)
			{
			 	$('#img').attr("src",value.poster) //poster를 여기에 입력해
				$('#title').text(value.title);
			 	$('#director').text(value.director);
			 	$('#actor').text(value.actor);
			 	$('#score').text(value.score);
			 	$('#genre').text(value.genre);
			 	$('#grade').text(value.title);
			 	$('#regdate').text(value.regdate);
			 	$('#story').text(value.story);
			 	return true; 
			 	//찾기 함수를 종료시켜줘야한다 like loop!!
				//함수 종료는 break가 아니고 return
			}
			
		   });

		});
		
		$('#dialog').dialog({
			width:600,
			height:500
		})
		
		
	}
</script>
<title>Insert title here</title>
</head>
<!-- <div class="col-md-4">
    <div class="thumbnail">
      <a href="/w3images/lights.jpg">
        <img src="/w3images/lights.jpg" alt="Lights" style="width:100%">
        <div class="caption">
          <p>Lorem ipsum...</p>
        </div>
      </a>
    </div>
  </div> -->
<body>
	<div class="container">
		<h1 class="text-center">영화목록</h1>
		<div class="row" id="list">
		<!-- 그림출력부분! -->
		</div>
		<div class="row">
			<div id="dialog" title="상세보기" style="display:none">
				<table class="table">
					<tr>
						<td width=30% class="text-center" rowspan="7">
							<img src="" width=100% id="img">
						</td>
						<td colspan="2" id="title"></td>
					</tr>
					<tr>
						<td width=10% class="text-right">감독</td>
						<td width=60% id="director"></td>
					</tr>
					<tr>
						<td width=10% class="text-right">배우</td>
						<td width=60% id="actor"></td>
					</tr>
					<tr>
						<td width=10% class="text-right">장르</td>
						<td width=60% id="genre"></td>
					</tr>
					<tr>
						<td width=10% class="text-right">등급</td>
						<td width=60% id="grade"></td>
					</tr>
					<tr>
						<td width=10% class="text-right">평점</td>
						<td width=60% id="score"></td>
					</tr>
					<tr>
						<td width=10% class="text-right">개봉일</td>
						<td width=60% id="regdate"></td>
					</tr>
					
					<tr>
						<td colspan="3" class="text-left" id="story"></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>