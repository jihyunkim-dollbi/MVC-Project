/*
 * 한 화면에서 
 * 한개의 파일에만 값을 보내지 않고
 * 한번에 여러개의 파일로 값을 보내는 모델을 만들 예정!!
 * 
 * 웹 시스템은 한부분을 클릭하면 전체 화면이 동작하고 있는 것이다. 따라서 내가 클릭하지 않은 부분도 계속 값을 보내주어야한다.
 * 같은 값을 계속 유지하게 하여 움직이지 않는 것처럼 보임!
 * 
 * 공통모듈로 만들기!
 * 
 * pojo방식!
 * NO 상속, NO 인터페이스 등  클래스의 영향력을 줄임!
 * 일반 클래스로 만들어 자유롭게 사용! 어노테이션사용하여!!
 * 
 * 
 * ------------------------------------------------------
 * 
 * 
 * Spring 동작 과정을 실제로 만들어보기!
 * 
 * 첫번째 동작
 * 1.applicationContext.xml 일기
 * 	========================안에 
 *  package=> 읽기: XMLParser에서!
 * 
 * 2) ComponentScan => Class.forName(class명)
 * 	
 * 3) 클래스 관리자 => handlerMapping (메모리할당된 클래스들 갖고 있음!)
 * 
 * 4) DispatcherServlet <=> handlerMapping
 * 
 * 5) ViewResolver : JSP 파일 찾기
 * 
 * 6) Model을 통해서 데이터를 JSP로 전송
 * 
 * 7) forward/sendRedirect
 * 
 * -------------------------------------------------
 * 
 *  <Controller in Spring>
 *  
 *  A. 부모 컨트롤러(aka Front Controller!)
 *  DispatcherServlet
 *  
 *  B. 자식 컨트롤러s(우리가 만드는 모델들)
 *  controller1(BoardModel)
 *  controller2(MainModel)
 *  controller3(JoinModel)
 *  
 *  ------------------------------
 *  
 *  HADOOP(데이터수집분석) &  달빅 가상머신
 *  
 *	------------------------------
 * 
 *  
 * 	
 * 
 * 
 */
public class main {

}
