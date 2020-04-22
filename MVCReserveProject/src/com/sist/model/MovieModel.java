package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.MemberVO;
import com.sist.dao.MovieDAO;
import com.sist.dao.MovieVO;
import com.sist.dao.ReserveTheaterVO;
import com.sist.dao.ReserveVO;

import java.text.SimpleDateFormat;
import java.util.*;
@Controller
public class MovieModel {
	
	//영화예매를 클릭하면 => 시작하자마자 일단 전체를 다 뿌림 모든 목록을 다 보여줌=> but 다른 항목들은 비활성화!=> 디폴트 페이지 
	@RequestMapping("movie/reserve.do")
	public String movie_reserve(HttpServletRequest request, HttpServletResponse response)
	{
		
		
		return "reserve.jsp"; //같은 폴더안에 있는 다른 파일로 가는 경우 
	}
	
	@RequestMapping("movie/movie.do")
	public String movie_movie(HttpServletRequest request, HttpServletResponse response)
	{
		//db가져오기
		//static이기 때문에 따로 생성x
		List<MovieVO> list=MovieDAO.movieListData();
		request.setAttribute("mList", list);
		
		return "movie.jsp";
	}
	
	
	@RequestMapping("movie/theater.do")
	public String movie_theater(HttpServletRequest request, HttpServletResponse response)
	{
		String tno=request.getParameter("tno");
		
		//dao연결하기
		//결과값 theater.jsp에게 보내면 reserve.jsp에서 읽어감...?
		//ajax로 값을 가져오기 때문에 include와 같은 효과이다. 따라서 reserve에서 movie.jsp의 정보를 theater.jsp로 정보를 보낼수있다. 굳이 movie.jsp에서 theater.jsp로 직접 보낼필가 없다.
		//ajax는 소스 전체가 부모와 자식의 소스가 하나가 된다. 따라서 값을 어디서든 id나 class를 읽어서 어디서든 값을 보내고 받기가 쉬움!
		
		//tno가져오기
		StringTokenizer st=new StringTokenizer(tno, ",");
		//몇개인지 모르니까
		List<ReserveTheaterVO> list= new ArrayList<ReserveTheaterVO>();
		
		while(st.hasMoreTokens()) //개수만큼 돌아
		{
			ReserveTheaterVO vo=MovieDAO.movieTheaterData(Integer.parseInt(st.nextToken()));
			list.add(vo);	
		}
		
		request.setAttribute("tList", list);
		
		return "theater.jsp";
		
	}
	
	
	@RequestMapping("movie/date.do")
	public String movie_date(HttpServletRequest request, HttpServletResponse response)
	{
		String strYear=request.getParameter("year");
		String strMonth=request.getParameter("month");
	
		String reserve_date=request.getParameter("rdate");
		
		//디폴트 잡기
		String today=new SimpleDateFormat("yyyy-M-d").format(new Date());
		StringTokenizer st=new StringTokenizer(today,"-");
		System.out.println("st:"+st.toString());
		//오늘의 날짜 정보 받은 것들을 모두 잘라놓고 아래서 디폴트로 사용!
		String sy=st.nextToken();
		String sm=st.nextToken();
		String sd=st.nextToken();
		
		//디폴트 정보
		if(strYear==null)
			strYear=sy;
		if(strMonth==null)
			strMonth=sm;
		
		int year=Integer.parseInt(strYear);
		//System.out.println("year:"+year);
		int month=Integer.parseInt(strMonth);
		//System.out.println("strMonth:"+strMonth);
		int day=Integer.parseInt(sd);
		//System.out.println("day:"+day);
		
		String[] strWeek={"일","월","화","수","목","금","토"};
		
		//전년도까지 합
		
		int total=(year-1)*365
				+(year-1)/4
				-(year-1)/100
				+(year-1)/400;
		
		//System.out.println("total:"+total);
		//전달까지 합
		int[] lastDay={31,28,31,30,31,30,31,31,30,31,30,31};
		
		if((year%4==0 && year%100!=0) || (year%400==0))
			lastDay[1]=29;
		else 
			lastDay[1]=28;
		
		for(int i=0; i<month-1;i++)
		{
			total+=lastDay[i]; //전달까지 합!
			
		}
		
		total++; //4월 1일자부터 시작! => 그 날짜의 요일을 알아야함!
		
		int week=total%7; //7로 나눔!!
		
		
		//값을 일단 모델에서 받아서 경우의 수대로 다시 request값을 다시 반환 가능!
		//달력에서 색상변경위해
		int[] days=new int[32];

		if(reserve_date!=null){
			
			//1,2,4,6,7... tdate와 같은 날이면 색상이 변경되도록, 31개의 배열로 주기떄문에 값을 넘어갈 필요x
			StringTokenizer st1=new StringTokenizer(reserve_date,",");
			
			while(st1.hasMoreTokens()){
				
				int p=Integer.parseInt(st1.nextToken());
				
				if(p>=day)
				{	
					days[p-1]=p;
				}
				
			}
			
		}
		
		request.setAttribute("days", days);
		//날짜 정보 date.jsp로 보냄..
		request.setAttribute("lastday", lastDay[month-1]);
		request.setAttribute("week", week);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("strWeek", strWeek);
		
		return "date.jsp";
		
	}
	
	//tno날짜를 선택하면 토큰으로 잘라서 time.jsp로 토큰으로 잘라서 보내줌
	@RequestMapping("movie/time.jsp")
	public String movie_time(HttpServletRequest request, HttpServletResponse response)
	{
		String tno=request.getParameter("tno");
		String times=MovieDAO.movieTimeData(Integer.parseInt(tno));
		//여기에 들어간게 1,4,6,8,.. 이것에 대한 실제 시간대를 모두 받기
		StringTokenizer st=new StringTokenizer(times,",");
		
		List<String> list=new ArrayList<String>();
		
		while(st.hasMoreTokens()){
			
			String time=MovieDAO.movieTimeData2(Integer.parseInt(st.nextToken()));
			//하나식 자르며 실제 시간대를 가져옴
			list.add(time);			
		}
		request.setAttribute("tList", list);
		
		
		return "time.jsp";
	}
	
	
	
	@RequestMapping("movie/inwon.do")
	public String movie_inwon(HttpServletRequest request, HttpServletResponse response)
	{
		
		return "inwon.jsp";
	}
	
	
	@RequestMapping("movie/login.do")
	public String movie_login(HttpServletRequest request, HttpServletResponse response)
	{
		//창만띄우는 역할!
		return "login.jsp";
	}
	
	
	/*
	 * model: ~vo, ~dao, model =>model안에 일반 자바처리 모두!
	 * view(jsp)
	 * controller => 사용자 요청을 받아서=> 요청처리 내용을 전송!
	 * 				===============================
	 * 
	 * 사용자 값을 jsp에서 받아서 모델에서 request로 받아놓음!
	 * mapper에서 sql로 사용자값을 받는경우 처리될 수있도록 resulttype과 parameter를 정의
	 * =>매퍼에서 resulttype과 parameter에 따른 쿼리를 작성하고 (모든 처리를 한개의 쿼리문장으로 할 필요가 없이 여러문장을 작성후 dao혹은 모델에서 각각처리하거나 한번에 같이 처리할 수도있다.)
	 * dao에서도 동일한 패턴으로 리턴형을 작성한다.
	 * 하지만 dao의 매소드의 parameter는다른 개념이다. 사용자로부터 받은 request를 각각의 복수 매개변수로 받아 그것을 서로다른 쿼리에 저장하여 함께 결국 한개의 vo에 담을 수 있고
	 * 그 것은 매퍼의 리턴형과 동일해야하고, 각각의 매퍼 파라미터는 
	 *MemberVO mvo=session.selectOne("movieGetPwd",id);
	 *int count=session.selectOne("movieIdCount",id); 
	 * 
	 * select문에서 동일하게 적용한다.
	 * 
	 * 따라서, 각각의 request를 직접 매퍼에 적용해야하는 경우 attribute할 경우는 dao에서 처리를 하고
	 * 받은 request를 형변환 또는 그대로 적용하는 경우에도 모델에서 처리가능하다
	 * 결국 사용자로부터 전달받은 request를 어떻게 가공하여 매퍼에 전송하는지가 관건이다.
	 * 
	 */
	
	
	@RequestMapping("movie/login_ok.do")
	public String movie_login_ok(HttpServletRequest request, HttpServletResponse response)
	{
		
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		//dao
		MemberVO vo=MovieDAO.movieLogin(id, pwd);
		//결과값을 받은  vo에 담김!
		
		//여기서 해야할일
		//session에 저장하기
		if(vo.getMgs().equals("OK"))//session에 등록하기
		{
			//vo에 담긴 정보들을 각각 session에 저장하기
			HttpSession session=request.getSession();
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			session.setAttribute("admin", vo.getAdmin());
		}
		
		//admin이면 예매현황보기
		//admin이 아닌 경우 예매하기!
		//model에서 스크립트 작업을 할수없다. 여기서는 페이지 전화 혹은 request 전송/받기만 가능
		//controller는 jsp파일찾기, .do실행만 할뿐
		//두가지의 경우의 수였다면 admin처리도 여기서 리턴형으로가능했겠지만
		//history.back을 할수없기때문에.. 
		//sendRedirect(".do") , forward: .jsp
		
		
		
		//jsp로 보내기
		request.setAttribute("vo", vo);
		//dao에서 설정한대로 저장된 vo를 jsp로 보내기!
		
		
		return "login_ok.jsp";
	}
	
	
	@RequestMapping("movie/admin.do")
	public String movie_admin(HttpServletRequest request, HttpServletResponse response)
	{
		
	
		return "admin.jsp";
	}
	
	
	@RequestMapping("movie/mypage.do")
	public String movie_mypage(HttpServletRequest request, HttpServletResponse response)
	{
	
		return "mypage.jsp";
	}
	
	
	@RequestMapping("movie/reserve_ok.do")
	public String movie_reserve_ok(HttpServletRequest request, HttpServletResponse response)
	{
		ReserveVO vo=new ReserveVO();
		
		String mno=request.getParameter("mno");
		String tname=request.getParameter("tname");
		String rdate=request.getParameter("rdate");
		String rtime=request.getParameter("rtime");
		String rinwon=request.getParameter("rinwon");
		String rprice=request.getParameter("rprice");
	
		return "redirect:mypage.do";
	}
	
	
	
	
	
}
