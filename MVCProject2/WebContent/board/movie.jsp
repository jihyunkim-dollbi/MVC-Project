<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*, java.util.*"%>
    
    <!-- attr()로 패키지명까지 읽어옴.... , 메모리할당!-->
    <jsp:useBean id="dao" class="com.sist.dao.MovieDAO"></jsp:useBean>
    
    <%
    	String strPage = request.getParameter("page");
    	if(strPage==null)
    		strPage="1";
    		
    	int curpage=Integer.parseInt(strPage);
    	
    	List<MovieBean> list= dao.movieListData(curpage);
    	
    	int totalpage = dao.movieTotalPage();
    	
    	final int BLOCK=5; //디테일 개수!
    	
    	
    	int startPage=((curpage-1)/BLOCK*BLOCK)+1; //한 페이지에 디테일 시작
    	int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK; // 한페이지에 디테일 끝
  
    	/*
    		curpage => 모든 페이지에 첫 데테일 의 넘버!
    		1/6 /11 /16 
    		
    		1페이지 1~10
    		2페이지 11~20
    		3페이지 21~30
    		
    		endPage+1 => 5+1 => 6~10
    		 
    	*/
    	
    	if(endPage>totalpage)
    		endPage=totalpage;
    	
    %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	<h1 class="text-center">영화목록</h1>
	<div class="row" style="text-align:left">
	
	<div>
		<div class="row" >
		
			<%
				for(MovieBean vo:list)
				{
					
			%>
					<div class="col-md-4">
					 <div class="panel panel-primary">
      					<div class="panel-heading">
      					<%
      						String temp=vo.getTitle();
      						if(temp.length()>22)
      						{
      							temp=temp.substring(0,22)+"...";
      						}
      					
      					%>
      					
      					<%=temp %><br>
      					</div>
      					<div class="panel-body">
      					<img src="<%= vo.getPoster() %>" width=320 height=200>
      					</div>
    				</div>
				</div>
			
			<%							
				}
			
			%>
		
		</div>
		<div class="row" style="text-align:center">
			<ul class="pagination">
					<li><a href="movie.jsp?page=1">&lt;&lt;</a></li> <!-- 첫페이지로 이동하기!! -->			
			<%
				if(curpage>BLOCK)
				{
			%>
					<li><a href="movie.jsp?page=<%=startPage-1%>">&lt;</a></li> <!-- -10 -->
			
			<%		
				}

			%>
			
			<%
			
				for(int i=startPage; i<=endPage; i++)
				{
					if(curpage==i)
					{
			%>
					<li class="active"><a href="movie.jsp?page=<%=i%>"><%= i %></a></li><!-- active 표시된 부분 컬러!-->
			
			<%		
					}
					else
					{
						
			%>
					<li><a href="movie.jsp?page=<%=i%>"><%= i %></a></li>
			
			<%				
					}
				}
			
			%>	
			<%
				if(endPage<totalpage)
				{
					
			%>
					<li><a href="movie.jsp?page=<%=endPage+1%>">&gt;</a></li> <!-- -10 -->
			<%		
				}
			
			%>	
				<li><a href="movie.jsp?page=<%=totalpage%>">&gt;&gt;</a></li> <!-- 끝페이지로 이동하기!! -->
				<li><a href="#"><%=curpage%> page / <%=totalpage %> pages</a></li>
			</ul>
		
		</div>
	
	</div>
</body>
</html>