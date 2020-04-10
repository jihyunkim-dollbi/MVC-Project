package com.sist.recipe.model;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
/*
 * < 코딩순서 >
 * first -사용자의 요청
 * 	1. <a> tag
 * 	2. <button>
 * 
 * second -model gogo (@requestMapping)처리
 * 
 * third -mapper sql statement
 * 
 * forth -dao gogo 연결처리
 * 
 * fifth - comeback to model again
 * 
 * sixth - JSP
 *  
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.RecipeDAO;
import com.sist.vo.RecipeVO;

import java.sql.Array;
import java.util.*;
import com.sist.vo.*;

@Controller //메모리할당 , //@cont =>주석 걸어놓으면 메모리할당x
public class RecipeModel {

	@RequestMapping("recipe/recipe.do") //사용자가 요청한 위치(를 찾음 RM requestMapping이)
	public String recipe_recipe(HttpServletRequest request, HttpServletResponse response)
	{
		
		// List<RecipeVO> list=RecipeDAO.recipeListData(map);
		
		//페이지가져오기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map=new HashMap();
		map.put("start", start); //여기의 값이 #{start} 와 동일!=>키 명칭과 동일!
		map.put("end", end);
		
		List<RecipeVO> list=RecipeDAO.recipeListData(map);
		for(RecipeVO vo:list)
		{
			String title=vo.getTitle();
			if(title.length()>10)
			{
				title=title.substring(0, 10).concat("...");
				vo.setTitle(title);
			}
			
		}
		int totalpage=RecipeDAO.recipeTotalPage();
		
		//1페이지 후 11페이지가 됨! 21, 31, 41 
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		//다음 눌렀을때 10개씩 이동!!
		
		//맨 마지막 페이지 처리
		int allPage=totalpage;
		if(endPage>allPage) 
			endPage=allPage; 
		
		//보낼 값
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("BLOCK", BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("allPage", allPage);
		request.setAttribute("main_jsp", "../recipe/recipe.jsp");
		
		
		//요청==>처리 => dao
		return "../main/main.jsp";
			
	}
	
	@RequestMapping("recipe/chef.do")
	public String recipe_chef(HttpServletRequest request, HttpServletResponse response)
	{
		
		
		
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		int rowSize=30; //한페이지당 30개 리스트
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map=new HashMap();
		map.put("start", start); //여기의 값이 #{start} 와 동일!=>키 명칭과 동일!
		map.put("end", end);
		
		//dao에서 보낸 값 받기
		List<ChefVO> list=RecipeDAO.chefListData(map);
		int totalpage=RecipeDAO.chefTotalPage();
		
		
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		//다음 눌렀을때 10개씩 이동!!
		
		//맨 마지막 페이지 처리
		int allPage=totalpage;
		if(endPage>allPage) 
			endPage=allPage; 
		
		//보낼 값
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("BLOCK", BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("allPage", allPage);
		
		//인클루드 되는 아이들에게 주기
		request.setAttribute("main_jsp","../recipe/chef.jsp");
		
		return "../main/main.jsp";
	}
	
	@RequestMapping("recipe/recipe_detail.do") //상세보기 요청
	public String recipe_detail(HttpServletRequest request, HttpServletResponse response)
	{
		//from recipe.do 에서 no값을 실어서 보내줌!
		String no=request.getParameter("no"); 
		
		//dao연결하러 mapper gogo => id="recipeListData"에서 이미 no값으로 vo를 받아놨음! 
		//and then dao gogo!
		//cameback from dao
		int count=RecipeDAO.recipeCount(Integer.parseInt(no));
		if(count!=0)
		{
			RecipeDetailVO vo=RecipeDAO.recipeDetailData(Integer.parseInt(no));
			vo.setFoodmake(vo.getFoodmake().replace("\n", "@")); // 
			request.setAttribute("vo", vo);	
		}		
		request.setAttribute("count", count);
		request.setAttribute("main_jsp", "../recipe/recipe_detail.jsp"); //인클루드 되는 파일
		
		
		return "../main/main.jsp"; //인클루드 하는 곳1
	}
		
	@RequestMapping("recipe/chef_detail.do") //
	public String recipe_chef_detail(HttpServletRequest request, HttpServletResponse response)
	{
		//came back from chefDetailData mapper
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;

		String chef_name=request.getParameter("name"); // 보내준 값 받기 
		//<a href="../recipe/chef_detail.do?name=${vo.chef }"> ==> ?앞에 변수명 =name을 보내줬음!
		System.out.println("chef_name: "+chef_name);// chef_name: 조밍키♥ => 한글 안깨짐!ok get/post방식에 따라 한글 인코딩해줘야함! 여기서orserver.xml에서..
		//mapper, dao연결하러 고고!
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("chef", chef_name);
		
		List<RecipeVO> list=RecipeDAO.chefDetailData(map);
		//글자자르기
		for(RecipeVO vo:list)
		{
			String title=vo.getTitle();
			if(title.length()>10)
			{
				title=title.substring(0, 10).concat("...");
				vo.setTitle(title);
			}
			
		}
		int totalpage=RecipeDAO.chefDataTotalPage(chef_name);
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("main_jsp", "../recipe/chef_detail.jsp");
		//../recipe/chef_detail.jsp => 화면에 출력하는 것이다! =>include 되어
		//화면 출력이 안되는 아이들은 아래서 리턴할떄 직접 그파일로 보내고 따로 request로 보낼 필요x
		request.setAttribute("chef", chef_name);
		
		return "../main/main.jsp"; //출력은 main에서 실행은 chef_detail에서
	}
	
	
	@RequestMapping("recipe/recipe_find.do")//화면출력하는 아이
	public String recipe_find(HttpServletRequest request, HttpServletResponse response)
	{
		String[] data={//그림위에 커서될때 글자띄우기 위해 
				
				"전체","밑반찬","메인반찬","국/탕","찌개","초스피트",
				"손님접대","밥/죽/떡","술안주","면/만두","일상","빵",
				"다이어트","디저트","샐러드","양식","김치/젓갈","도시락",
				"간식","돼지고기","영양식","과자","양념소스","차/음료/술",
				"닭고기","야식","채소류","볶음","스프","소고기",
				"해물류","푸드스타일","육류","달걀/유제","조림",
				"이유식","무침","해장","명절","버섯류","가공식품류",
				"과일류","튀김","끓이기","찜","비빔","밀가루",
				"건어물류","절임","굽기","삶기","회","쌀",
				"콩/견과류","곡류","데치기","퓨전" //여기에 출력하는 것을 request와 response가 읽어갈 예정!
				
			};
		
		//List<String> nlist=Array.asList(data);
		//request.setAttribute("nlist", nlist);
	
		request.setAttribute("data", data); // 글자출력 데이터 jsp로 보냄
		request.setAttribute("main_jsp", "../recipe/recipe_find.jsp"); //인클루딩 작업
		
		return "../main/main.jsp"; //이걸 실행해, 실행하면 순간 인클루딩된 jsp가 출력
	}
	
	@RequestMapping("recipe/recipe_find_ok.do")
	public String recipe_find_ok(HttpServletRequest request, HttpServletResponse response)
	{
		

		String[] data={
				
				"전체","밑반찬","메인반찬","국/탕","찌개","초스피트",
				"손님접대","밥/죽/떡","술안주","면/만두","일상","빵",
				"다이어트","디저트","샐러드","양식","김치/젓갈","도시락",
				"간식","돼지고기","영양식","과자","양념소스","차/음료/술",
				"닭고기","야식","채소류","볶음","스프","소고기",
				"해물류","푸드스타일","육류","달걀/유제","조림",
				"이유식","무침","해장","명절","버섯류","가공식품류",
				"과일류","튀김","끓이기","찜","비빔","밀가루",
				"건어물류","절임","굽기","삶기","회","쌀",
				"콩/견과류","곡류","데치기","퓨전" //여기에 출력하는 것을 request와 response가 읽어갈 예정!
				
			};
		
		String no=request.getParameter("no1"); //사진(이름이 NO)을 클릭할때마다 번호를 넘긴값을 받음!!
		//받은 값에 대해 db연동 필요 => mapper gogo => dao gogo!
		
		//cameback from dao
		List<RecipeVO> list=RecipeDAO.recipeFindData(data[Integer.parseInt(no)-1].replace("/", "|")); // 자바에서는 배열이 0번부터 시작하기 때문에// 사진 명이 1번부터 시작하기때문에.
		//글자수 자르기
		for(RecipeVO vo:list)
		{
			String title=vo.getTitle();
			if(title.length()>10)
			{
				title=title.substring(0, 9).concat("...");
				vo.setTitle(title);
			}	
		}
		
		request.setAttribute("data", data);
		request.setAttribute("list", list);
		
		return "../recipe/recipe_find_ok.jsp";
	}
	
	
	
}
