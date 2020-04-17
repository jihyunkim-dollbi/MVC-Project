package com.sist.temp;
import java.util.*;

import oracle.jdbc.OracleTypes;

import java.sql.*;

public class StudentDAO {

	private Connection conn;
	private CallableStatement cs; //프로시저 사용할때 statement사용x
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public StudentDAO(){
		
		try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			
		}	
	}
	
	
	
	public void getConnection(){
		
		try{
			
			conn=DriverManager.getConnection(URL,"hr","happy");
			
		}catch(Exception ex)
		{}
		
	}
	
	
	public void disConnection(){
		
		try{
			
			
			if(cs!=null) cs.close();
			if(conn!=null) conn.close();
			
		}catch(Exception ex){}
		
	}
	
	
	
	//오라클에서 만든 프로시져  pro_test_select 를 자바에서 사용해보기
	//5개의 매개변수가 있고
	//no변수는 프로시져를 만들때 IN변수로 만들어져있고
	//나머지 4개의 변수는 OUT변수로 만들어져있다
	//==> 이 프로시져의 구조와 자바에서 사용하는 구조가 동일하다
	//IN변수는 자바에서 매개변수로 사용되고
	//OUT변수는 자바에서 리턴형으로 사용된다
	//따라서, no변수는 사용자한테 받은 값의의미로 setInt로값을 넣어주고 => cs에 저장!
	//나머지, 4개의 변수는 레지스터에 저장하라는 명령을 해야한다. => cs.registerOutParameter(4, OracleTypes.INTEGER);
	//4번째 ?를 오라클타입의 integer로 레지스터에 저장해. => register는 오라클에서 가져온 값을 자바에 저장하는 장소! 그리고 cs에 저장해!cs는 sql을 실행한 문장을 커넥션하여 수행한 결과값들!
	//모든 변수를 cs에 저장한 후에는 cs를 실행한다.
	
	
	public StudentVO studentInfoData(int no){
		
		StudentVO vo=new StudentVO();
		try{
			
			getConnection();//매개변수 총 5개를 모두 채워줘야한다.
			
			String sql="{CALL pro_test_select(?,?,?,?,?)}";
			
			cs=conn.prepareCall(sql); //실행됨!
			
			//첨부하는 변수
			//실행전에 값 채워야한다. 
			//set은 사용자한테 받은 것을 보낼때
			cs.setInt(1, no); //사용자한테 받은값
			
			//데이터를 받을 변수 설정!
			//받아오는 변수는 무조건 registerOutParamenter ==>  (2,:pname,:pkor,:peng,:pkor); :pname과 동일!!
			//register에 저장해 ?를! ==> register순간 값이 저장됨!!
			cs.registerOutParameter(2, OracleTypes.VARCHAR); //oracle.jdbc로 가져오기 // VARCHAR => 자바에서 읽는 문자열
			cs.registerOutParameter(3, OracleTypes.INTEGER);
			cs.registerOutParameter(4, OracleTypes.INTEGER);
			cs.registerOutParameter(5, OracleTypes.INTEGER);
			
			//OracleTypes.CURSOR => Resultset
			
			//VARCHAR, INTEGER, CURSOR ETC
			
			//실행하기
			//무조건 모든 sql문장은 executeUpdate(); 해야한다.
			cs.executeUpdate();
			
			
			//위에서 저장한 값을 읽어오기
			// getString(2); 값을 가져오기
			String name=cs.getString(2);
			int kor=cs.getInt(3);
			int eng=cs.getInt(4);
			int math=cs.getInt(5);
			
			//값을 여기에 채우는 
			vo.setEng(eng);
			vo.setKor(kor);
			vo.setMath(math);
			vo.setName(name);
			
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally
		{
			disConnection();
			
		}
		return vo;
		
	}
	
	
	
	
}





























