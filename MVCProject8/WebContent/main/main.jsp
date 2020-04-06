<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<title>SIST Seoul Travel</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link href="../main/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all"><!-- 이 css파일들은 layout폴더에 있기때문에  layout폴더에서 나와서(../)=>main폴더로 가라 main/ -->
<style type="text/css">
body{
  font-family: 맑은 고딕;
}
</style>
</head>
<body id="top">

<div class="wrapper row1">
  <header id="header" class="clear"> 
    <!-- ################################################################################################ -->
    <div id="logo" class="fl_left">
      <h1><a href="index.html">SIST Recipe & Food</a></h1>
    </div>
    <!-- ################################################################################################ -->
    <nav id="mainav" class="fl_right">
      <ul class="clear">
        <li class="active"><a href="../main/main.do">Home</a></li>
        <li><a class="drop" href="#">회원</a>
          <ul>
            <li><a href="pages/gallery.html"></a></li>
            <li><a href="pages/full-width.html">아이디찾기</a></li>
          	<li><a href="pages/gallery.html">비밀번호찾기</a></li>
            <li><a href="pages/full-width.html">회원탈퇴</a></li>
          </ul>
        </li>
        <li><a class="drop" href="#">레시피</a>
          <ul>
            <li><a href="../recipe/recipe.do">레시피</a></li>
            <li><a class="drop" href="#">쉐프</a>
              <ul>
                <li><a href="../recipe/chef.do">쉐프  목록</a></li>
                <li><a href="#">레시피 찾기</a></li>
              </ul>
            </li>
          </ul>
        </li>
        <li><a class="drop" href="#">맛집</a>
          <ul>
            <li><a href="#">추천코스</a></li>
            <li><a href="#">명소</a></li>
            <li><a href="#">쇼핑</a></li>
            <li><a href="#">숙박</a></li>
            <li><a href="#">음식점</a></li>
          </ul>
        </li>
        <li><a class="drop" href="#">예약하기</a>
          <ul>
            <li><a href="#">호텔예약</a></li>
            <li><a href="#">맛집예약</a></li>
          </ul>
        </li>
        <li><a class="drop" href="#">커뮤니티</a>
          <ul>
            <li><a href="#">자유게시판</a></li>
            <li><a href="#">묻고답하기</a></li>
            <li><a href="#">자료실</a></li>
          </ul>
        </li>
        <li><a href="#">공지사항</a></li>
        <li><a href="#">마이페이지</a></li>
        
      <!--   
        <li><input type=text size=8 class="input-sm" placeholder="ID" style="display: inline-block;"></li>
        <li><input type=password size=8 class="input-sm" placeholder="Password" style="display: inline-block;"></li>
        <li><input type=button class="btn btn-sm btn-info" value="Login"></li> -->
        
      </ul>
    </nav>
    <!-- ################################################################################################ -->
 </header>
 </div>
  <div class="row1">
  	<div id="clear" style="margin-left: 1250px">
 	 	<input type=text size=10 class="input-sm" placeholder="ID" style="display: inline-block;">
 	 	&nbsp;
 	 	<input type=password size=10 class="input-sm" placeholder="Password" style="display: inline-block;">
 	 	<input type=button class="btn btn-sm btn-info" value="로그인">
  	</div>
</div>
<div style="height: 30px"></div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->

<jsp:include page="${main_jsp }"></jsp:include>

<div class="wrapper row4">
  <footer id="footer" class="clear"> 
    <!-- ################################################################################################ -->
    <div class="one_quarter first">
      <h6 class="title">공지사항</h6>
      <ul class="nospace linklist">
        <li><a href="#">좌석을 변경할 수 있나요?</a></li>
        <li><a href="#">예약은 어떻게 취소하나요?</a></li>
        <li><a href="#">예약을 일부만 취소할 수 있나요? </a></li>
        <li><a href="#">티켓은 어디서 받나요?</a></li>
        <li><a href="#">관광시간이 긴데 줄일 수 없나요?</a></li>
      </ul>
    </div>
    <div class="one_quarter">
      <h6 class="title">인기게시물</h6>
      <ul class="nospace linklist">
        <li><a href="#">좌석을 변경할 수 있나요?</a></li>
        <li><a href="#">예약은 어떻게 취소하나요?</a></li>
        <li><a href="#">예약을 일부만 취소할 수 있나요? </a></li>
        <li><a href="#">티켓은 어디서 받나요?</a></li>
        <li><a href="#">관광시간이 긴데 줄일 수 없나요?</a></li>
      </ul>
    </div>
    <div class="one_quarter">
      <h6 class="title">이벤트</h6>
      <ul class="nospace linklist">
        <li><a href="#">좌석을 변경할 수 있나요?</a></li>
        <li><a href="#">예약은 어떻게 취소하나요?</a></li>
        <li><a href="#">예약을 일부만 취소할 수 있나요? </a></li>
        <li><a href="#">티켓은 어디서 받나요?</a></li>
        <li><a href="#">관광시간이 긴데 줄일 수 없나요?</a></li>
      </ul>
    </div>
    <div class="one_quarter">
      <h6 class="title">인기검색어</h6>
      <ul class="nospace linklist">
        <li><a href="#">좌석을 변경할 수 있나요?</a></li>
        <li><a href="#">예약은 어떻게 취소하나요?</a></li>
        <li><a href="#">예약을 일부만 취소할 수 있나요? </a></li>
        <li><a href="#">티켓은 어디서 받나요?</a></li>
        <li><a href="#">관광시간이 긴데 줄일 수 없나요?</a></li>
      </ul>
    </div>
    <!-- ################################################################################################ -->
  </footer>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->




<div class="wrapper row5">
  <div id="copyright" class="clear"> 
    <!-- ################################################################################################ -->
    <p class="fl_left"><a href="#">SIST 쌍용 강북 교육센터</a></p>
    <p class="fl_right"><a target="_blank" href="#" title="Free Website Templates">G강의장</a></p>
    <!-- ################################################################################################ -->
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a> 
<!-- JAVASCRIPTS -->
<script src="../main/layout/scripts/jquery.min.js"></script>
<script src="../main/layout/scripts/jquery.backtotop.js"></script>
<script src="../main/layout/scripts/jquery.mobilemenu.js"></script>
<script src="../main/layout/scripts/jquery.flexslider-min.js"></script>
</body>
</html>