package com.sist.temp;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StudentDAO dao=new StudentDAO();
		StudentVO vo=dao.studentInfoData(2);
		System.out.println("이름: "+vo.getName());
		System.out.println("국어: "+vo.getKor());
		System.out.println("영어: "+vo.getEng());
		System.out.println("수학: "+vo.getMath());
		System.out.println("넘버: "+vo.getNo());
		/*
		 * 이름: 김지현
			국어: 94
			영어: 85
			수학: 80

		 * 
		 * 
		 */
	}

}
