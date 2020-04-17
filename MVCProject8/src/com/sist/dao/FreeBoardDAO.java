package com.sist.dao;

import java.util.*;

import com.sist.vo.BoardVO;

import oracle.jdbc.OracleTypes;

import java.sql.*;

public class FreeBoardDAO {

	private Connection conn;
	private CallableStatement cs; //procedure 호출시에 전송 객체
	
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	
	public FreeBoardDAO(){
		
		try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
		}catch(Exception ex)
		{
			
		
				System.out.println(ex.getMessage());
		}
		
		
	}
		//sqlsession session=ssf.opensession()과정이 여기에 포함됨
		//연결하기
		public void getConnection(){
			
			try{
				
				conn=DriverManager.getConnection(URL,"hr", "happy");
				
			}catch(Exception ex)
			{
				
				
			}
			
		}
		
		
		//session.open(); => 포함됨!
		//연결해제
		public void disConnection()
		{
			try{
				
				
				if(cs!=null)cs.close();
				if(conn!=null) conn.close();
			}catch(Exception ex)
			{
				
				
			}
			
		}
		
		/*
		 * CREATE OR REPLACE PROCEDURE boardListData(
    
  			pStart NUMBER,
  			pEnd NUMBER,
  			pResult OUT SYS_REFCURSOR
		 * out변수/in변수 구분필요!
		 * 
		 */
		//session.selectList() 와 동일
		public List<BoardVO> freeboardListData(int page){
			
			List<BoardVO> list=new ArrayList<BoardVO>();
			
			try{
				
				getConnection();
				// http://
				int rowSize=10;
				
				int start=(rowSize*page)-(rowSize-1);
				int end=rowSize*page;
				
				String sql="{CALL boardListData(?,?,?)}"; //? 매개변수 3개!
				
				//위 문장을 전송객체에 담는다. cs에 sql문장이 저장됨!
				cs=conn.prepareCall(sql);  //프로시저를 호출하겠다!
				//?에 값을 채운다
				cs.setInt(1, start);
				cs.setInt(2, end);
				cs.registerOutParameter(3, OracleTypes.CURSOR); //넘어오는게 커서=> 커서로 저장해줘 레지스터(임시저장장소!)에
				// 나머지형들은 자바에 있는 형으로 받으면되고 String 이면 getString으로 받고.. 
				
				
				//값을 다 채웠으니 실행 요청
				cs.executeUpdate(); // boardListData(?,?,?) 호출
				
				//실행했으니 값을 커서에 채웠을것 ==> 이제 채운 커서값을 줘
				//자바에는 커서형이 없어서 오브젝트로 받음
				//Object 으로 받되 형변환 필요.
				ResultSet rs=(ResultSet)cs.getObject(3); //3번째 칸에 있는 커서를 가져와
				//CURSOR가 있으면 무조건 RESULTSET으로 받아야함!
				//resultset은 어디에 값이 담길지 모르기 때문에 순서대로 값을 넣어줘야한다.
				//ARRAYLIST로 받으면 X

				//이 과정은 VO에 저절로 채워주지 않는다. => 마이바티스와 다름 => 우리가 값을 가져와서 따로 값을 넣어주는 과정이 필요 => 그 과정이 RESULTSET이다!
				while(rs.next()){
					
					//SELECT no, subject, name, regdate, TO_CHAR(regdate,'YYYY'-MM-DD') as dbday,hit,num
					//select문장 순서대로 값 채우기..
					BoardVO vo=new BoardVO();
					vo.setNo(rs.getInt(1));
					vo.setSubject(rs.getString(2));
					vo.setName(rs.getString(3));
					vo.setRegdate(rs.getDate(4));
					vo.setDbday(rs.getString(5));
					vo.setHit(rs.getInt(6));
					
					list.add(vo);
					
				}rs.close();
				
				
			}catch(Exception ex){
				
				ex.printStackTrace();
				
			}finally{
				
				disConnection();
			}
			return list;
		}
		
		
		
		/*
		 * CREATE OR REPLACE PROCEDURE boardTotalPage(
    		ptotal OUT NUMBER
    		)
		 */
		
		//한페이지에 10개씩 출력=> 총페이지 구하기
		
		public int freeboardTotalPage()
		{
			int total=0;
			
			try{
				getConnection();
				//값을 먼저 받기
				String sql="{CALL boardTotalPage(?)}";
				
				//위 문장을 전송객체에 담는다. cs에 sql문장이 저장됨!
				//실행해
				cs=conn.prepareCall(sql);
				
				//첫번째 ?에 정수를 저장할 수있는 공간(메모리)을 만들어서 잠시 저장해서 여기로 가져와
				cs.registerOutParameter(1, OracleTypes.INTEGER); 
				
				//실행요청
				cs.executeUpdate();
				
				//메모리에서 결과값을 읽어온다.
				total=cs.getInt(1);
				//데이터 모두 읽음!!

				
			}catch(Exception ex){
				
				ex.printStackTrace();
			}finally{
				
				disConnection();
			}
			
			return total;
			
		}
		
		
		/*
		 * CREATE OR REPLACE PROCEDURE boardInsert(
    		pName board.name%TYPE,
    		pSubject board.subject%TYPE,
    		pContent board.content%TYPE,
    		pPwd board.pwd%TYPE
		 */

		//새글 완료시 확인누르고 insert하기
		public void freeboardInsert(BoardVO vo)
		{
			try{
				getConnection();
				String sql="{CALL boardInsert(?,?,?,?)}";
				
				//위 문장을 전송객체에 담는다. cs에 sql문장이 저장됨!
				cs=conn.prepareCall(sql);
				
				//cs에 형에 맞게 ?값을 채운다.
				cs.setString(1, vo.getName());
				cs.setString(2, vo.getSubject());
				cs.setString(3, vo.getContent());
				cs.setString(4, vo.getPwd());
				
				//cs에 문장에 변수정보를 실었으니 이것 이제 실행해줘 라고 지시!
				cs.executeUpdate();
				
				
				
			}catch(Exception ex){
				
				ex.printStackTrace();
				
			}finally
			{
				
				disConnection();
			}
			
		}
		
		//상세보기, 수정하기 동시에 할 예정
		/*
		 * CREATE OR REPLACE PROCEDURE boardInfoData(
 			pNo board.no%TYPE,  => in변수
 			pTYPE NUMBER,       => in변수!!
 			pResult OUT SYS_REFCURSOR  ==> out변수
		 */
		
		
		public BoardVO freeboardInfoData(int no, int type)
		{
			BoardVO vo=new BoardVO();
			
			try
			{
				getConnection();
				String sql="{CALL boardInfoData(?,?,?)}";
				
				cs=conn.prepareCall(sql);
				
				//매개변수로 직접 받음
				cs.setInt(1, no);
				cs.setInt(2, type);
				
				cs.registerOutParameter(3, OracleTypes.CURSOR); //커서의 크기만큼!!
				
				
				//실행
				cs.executeUpdate();
				
				
				//커서에 채워진 값들을 가져오자
				//커서의 크기만큼 ..
				ResultSet rs=(ResultSet)cs.getObject(3);
				
				rs.next();
				
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setRegdate(rs.getDate(5));
				vo.setHit(rs.getInt(6));
				
				rs.close();
				
				
			}catch(Exception ex)
			{
				ex.printStackTrace();
				
			}finally
			{
				disConnection();
				
			}
			return vo;
			
		}
		
		
		
}
