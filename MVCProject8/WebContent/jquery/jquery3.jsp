<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, java.util.*"%>
    <!-- 키업 -->
    <%
    
    	try{
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		String url="jdbc:oracle:thin:@localhost:1521:XE";
    		Connection conn=DriverManager.getConnection(url,"hr", "happy");
    		String sql="SELECT rank, title, singer, poster FROM music_genie";
    					
    		PreparedStatement ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		
    %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"><!-- main.jsp에서 카피! -->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#keyword').keyup(function(){ //키보드에 한글입력될 때마다 글자 매칭 필터, 모음 매칭 etc...
	 	let k=$(this).val();
		//console.log(k);
		$('#user-table > tbody > tr').hide();
		
		let temp=$('#user-table > tbody > tr > td:nth-child(4n+3):contains("'+k+'")');// td안에 들어간 데이터를 가져올때(JS 함수)  ,내가입력한 값 k가 들어갔어?
		
		/*
			1 2 3 4
			5 6 7 8
			 . . 
			우리가 필요한 데이터는 3,7.. 에 있다. 3열에 있다. 
			따라서 4n+3 하여 3번째 자리수를 지정한다. 
			4 => 4칸 =td개수
			3 => 필요한 데이터 자리가 '3번째'
			
			==>우린 3번째 값인 '제목' 데이터가 필요하다!
		
		*/
		$(temp).parent().show(); // .parent() 를사용한 이유는 tr보다 상위인 tbody부터 show하고 싶기때문에.
		/*
			-가상 셀랙터 -
			<tr>
				<td> 
				</td>
			</tr>
			
			$('td').parent().show();
			$('tr').child().show();

		*/
		
	})
	
	
})


</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 class="text-center">뮤직 Top50</h1>
			<table class="table">
				<tr>
					<td>
						<input type=text size=20 class="input-sm" placeholder="검색어입력" id="keyword">
					</td>
				</tr>
			</table>
			
			<table class="table table-hover" id="user-table">
				<tr class="success">
				<th>순위</th>
				<th></th>
				<th>곡명</th>
				<th>가수명</th>
				</tr>
				<tbody>
				<%
					while(rs.next())
					{
				%>
				<tr>
					<td><%= rs.getInt(1) %></td>
					<td><img src="<%= rs.getString(4)%>" width=35 height=35></td>
					<td><%= rs.getString(2) %></td>
					<td><%= rs.getString(3) %></td>
				</tr>
				<%		
						
					}
				
				
				%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
<%
    	}catch(Exception ex) {}
	
%>