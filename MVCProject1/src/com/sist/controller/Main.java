package com.sist.controller;

/*

2020 03 30

MVC  구조란?

사용자 request => Controller []=> model1, model2, model3,  … =>  DAO

사용자가 model1을 요청함! 
controller가 model1을 찾음!
그에 해당하는 데이터를 controller에 갖다놓음!
model1에서 request값을 담는다.. (set,,)
dao갔다옴
controller에서 가져온 request값을 view로 데려감!  

controller가 중간에서 값을 전달해줌!

MODEL&DAO - JAVA
VIEW - JSP(보안X)
CONTROLLER - 데이터  & 모델-뷰 연결 기능! - SERVLET!(데이터보안기능!)

서블릿 & JSP => reuqeust와 response를 가져올 수 있다.
연결    &  출력(퍼블리셔- TAG형으로 출력!JSTL,EL)


controller -> model -> controller => view! 

request
session

service안에 들어가는 내용! JSP 

 
서블릿을 JSP로 바꾼 이유:

CONTROLLER 역할: 서블릿(service:req,res) 연결기능! (이미 만들어져있다..) => 지정한 파일명과 설정만 해주면 ok!(XML) => VIEW와 MODEL 매칭시키기!


JSP(req, res):출력만 담당


서블릿과 jsp는 하나의 클래스이다. 모두 같은 메소드service(req,res) & 다른 매개변수..
req,res를 전송하는 기능이 controller. 

forward기능! => 매개변수를 통해 화면이 전환될때마다 req,res값을 전송!
& sendRedirect!

jsp는 메모리 할당x안됨 => get/post방식 
url통해 값을 전송 => 매개변수 전송! => get/post! 

session(전역변수로 만들어 공유) & request!

자바는 메소드에 매개변수를 넣어 값을 전송 -  인자 전송법
웹 전송 - url을 통해 값을 전송! & forward를 사용 가능!(값의 사용 허용!) 
 
값을 넘기는 경우(내용보기, 가입버튼 누르는 경우) => forward기법 사용하기
넘길 필요가 없는 경우(새글, 회원가입 창띄우기, 입력창 띄우기) ->forward사용필요x



 

//응집성 => 하나의 클래스 안에 여러 매소드를 모아놓고 사용가능!
	       여러 기능을 한군데에 모아라! 
// 결합성은 낮게 해야하고


1 controller

+1 model

⇒ 모든 model에 에러가 나지 않고 한개의 model에만 각각 에러가 발생하도록(결합성이 낮게) => c와m 사이에 interface를 두어 영향력을 줄인다. 


스트링은 인터페이스 기반 - 모델클래스를 한개로 통합하여 인터페이스 사용!
		MAP이용! => ID를 주면 값(주소)을 받을수있게. 
		map.put(“list”, new ListModel());
어노테이션 기반 - 자동호출
XML 기반! 





 * 
 * 
 */
public class Main {

}
