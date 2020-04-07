<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, java.util.*"%>
    <%
    	try{
    		
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		String url="jdbc:oracle:thin:@localhost:1521:XE";
    		Connection conn=DriverManager.getConnection(url,"hr", "happy");
    		String sql="SELECT poster, rownum FROM movie "
    					+"WHERE rownum<=9";
    		PreparedStatement ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){ 
	
	$('img').hover(function(){ //img를 갖다 대었다면
		
		//if
		$(this).css("opacity",0.3);
	
	},function(){
	
		//else
		$(this).css("opacity",1.0);
	})	
})
</script>
</head>
<body>
	<center>
<%
	//출력하기 떄문에 body안에서
	while(rs.next())
	{
		String poster=rs.getString(1);
	
%>
		<img src="<%= poster%>" width="200" height="300">

<%		
    }
	
%>
</center>
</body>
</html>
<%		//body안에서 출력하기 때문에 여기까지 try!
    	}catch(Exception ex) {}

%>