package com.sist.dao;

import java.util.Arrays;

//null에 insert하기 위해
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		for(int i=1;i<=31;i++) //극장개수만큼 돌리면서
		{
			String res=movieRandomData(15);
			System.out.println(i+":"+res);
			
			//MovieVO vo=new MovieVO();
			ReserveDateVO vo=new ReserveDateVO();
			vo.setTno(i);
			vo.setTime(res);

			MovieDAO.movieTimeUpdate(vo);;//데이터베이스에 값 넣기
			
	
		}
	}

	//영화당 영화관의 넘버를 임의로 넣어줌!!
	public static String movieRandomData(int count){
		
		String result="";
		
		//랜덤은 중복으로 들어올수도있다=> 아래서 중복처리 해줌!!
		//극장이 기본 디폴트 8개 => 9개~15개는 영화마다 다르게됨!!
		int no=(int)(Math.random()*4)+7; // 예약가능한 날 15일 주기 각 극장당!
		//기본으로 모든 영화는 8개의 영화관을 갖고
		
		
		
		int[] com=new int[no]; //몇개가 발행할 건지 확인?
		int rand=0;
		boolean bCheck=false;
		
		//랜덤이 중복으로 들어오면x =>중복없는 데이터 만들기! 
		//중복없는 랜덤 만들기
		for(int i=0;i<no;i++) //한개의 영화가 특정 영화관 수를 만날때
		{
			bCheck=true;
			while(bCheck) //중복없는 동안 
			{
				rand=(int)(Math.random()*29)+1;// 1~31일까지 랜덤 발생 / 1~29
				bCheck=false; //빠져나감
				for(int j=0; j<i; j++){ //중복제거 ,// i는 저장된 개수 , j<=i를 안한이유는 처음에 선택한 영화는 중복이 없기 때문에.
					
					if(com[j]==rand) //이 영화안에 영화관 넘버가 같다면
					{
						bCheck=true; //true되면서 random다시 발생하러 gogo! 중복이 아닐때까지 돈다.
						break;
					}
				}
			}
			com[i]=rand;
		}
		
		Arrays.sort(com); //결과값을 정렬해줌!
		
		
		for(int i=0;i<com.length;i++)
		{
			result+=com[i]+",";
			
		}
		result=result.substring(0,result.lastIndexOf(","));
		
		return result;
		/*
		 * 
1:1,2,3,4,6,7,8,9,10,11,12,13,14
2:1,2,3,4,5,6,7,8,9,10,12,13,14,15
3:1,2,3,4,6,7,8,9,10,11,12,13,14,15
4:1,2,4,5,9,10,11,13
5:1,2,3,4,5,6,7,10,11,12,13,15
6:1,3,4,5,6,7,8,9,10,11,12,13,14,15
7:1,2,4,5,6,7,8,9,10,11,12,13,14,15
8:1,2,3,5,6,8,10,15
9:1,3,5,6,8,10,11,12,13,14
10:4,6,7,10,12,13,14,15
11:5,6,7,8,10,12,13,15
12:1,3,4,5,6,7,8,9,14,15
13:1,3,4,6,9,10,12,14
14:1,2,3,4,5,6,7,8,9,10,11,12,13,14,15
15:1,3,4,5,6,7,8,9,11,14,15
16:2,4,5,7,9,11,13,14,15
17:1,2,3,4,5,6,7,9,10,11,13,14
18:2,3,4,5,6,7,8,9,10,11,12,13,14,15
19:1,3,4,7,8,10,11,12,14,15
20:1,2,3,4,6,7,8,9,10,11,12,13,14,15
21:1,2,3,5,6,7,8,9,11,14,15
22:1,2,4,5,6,7,8,9,10,11,12,13,14,15
23:2,3,5,7,10,11,13,14,15
24:1,2,3,5,6,8,9,14,15

		 * 
		 */
	}
	
}
