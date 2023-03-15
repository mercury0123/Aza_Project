package com.aza.myapp.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aza.myapp.domain.CommunityVO;
import com.aza.myapp.domain.MemberVO;
import com.aza.myapp.domain.PagingVO;
import com.aza.myapp.handler.PagingHandler;
import com.aza.myapp.service.CommunityService;
import com.aza.myapp.service.MyPageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/myPage/*")
@Controller
public class MyPageController {
	
	@Inject
	private MyPageService mpsv;
	
	@Inject
	private CommunityService csv;
	
	@GetMapping("")
	public String myPageGet(PagingVO pvo, Model model, HttpServletRequest request) {
		String sessionID = ((MemberVO)request.getSession().getAttribute("session")).getId();
		pvo.setSessionID(sessionID);
		log.info(pvo.toString());
		List<CommunityVO> list = null;
		PagingHandler ph = null;
		
		String category = pvo.getCategory();
		if(category == null || category.equals("")) {
			category="community";
			pvo.setCategory("community");
		}
		// null : 마이페이지에 처음 들어와서 아무런 버튼을 누르지 않아서 자동으로 커뮤니티가 선택된 상태
		// "" : board, good, scrap 중 하나의 버튼을 눌러서 자동으로 커뮤니티가 선택된 상태 
		// community : 커뮤니티를 눌러서 선택된 상태
		String my = pvo.getMy();
		if(my == null) {
			my="board";
			pvo.setMy("board");
		}
		// null 위의 null과 같은 설명
		// board : board를 클릭한 상태
		
		// community
		if(category.equals("community")) {
			if(my.equals("board")) {
				list = mpsv.communityMyBoard(pvo);
				ph = new PagingHandler(pvo, mpsv.communityMyBoardCount(pvo));
			}else if(my.equals("good")) {
				list = mpsv.communityMyGood(pvo);
				ph = new PagingHandler(pvo, mpsv.communityMyGoodCount(pvo));
			}else if(my.equals("scrap")) {
				list = mpsv.communityMyScrap(pvo);
				ph = new PagingHandler(pvo, mpsv.communityMyScrapCount(pvo));
			}
		}

		// harmful food
		if(category.equals("harmful_food")) {
			if(my.equals("scrap")) {
				list = mpsv.harmfulFoodMyScrap(pvo);
				ph = new PagingHandler(pvo, mpsv.harmfulFoodMyScrapCount(pvo));
			}
		}
		
		// boast
		if(category.equals("boast")) {
			if(my.equals("board")) {
				list = mpsv.boastMyBoard(pvo);
				ph = new PagingHandler(pvo, mpsv.boastMyBoardCount(pvo));
			}else if(my.equals("good")) {
				list = mpsv.boastMyGood(pvo);
				ph = new PagingHandler(pvo, mpsv.boastMyGoodCount(pvo));
			}else if(my.equals("scrap")) {
				list = mpsv.boastMyScrap(pvo);
				ph = new PagingHandler(pvo, mpsv.boastMyScrapCount(pvo));
			}
		}
		
		// accom
		if(category.equals("accom")) {
			if(my.equals("board")) {
				list = mpsv.accomMyBoard(pvo);
				ph = new PagingHandler(pvo, mpsv.accomMyBoardCount(pvo));
			}else if(my.equals("good")) {
				list = mpsv.accomMyGood(pvo);
				ph = new PagingHandler(pvo, mpsv.accomMyGoodCount(pvo));
			}else if(my.equals("scrap")) {
				list = mpsv.accomMyScrap(pvo);
				ph = new PagingHandler(pvo, mpsv.accomMyScrapCount(pvo));
			}
		}
		
		// meeting
		if(category.equals("meeting")) {
			if(my.equals("board")) {
				list = mpsv.meetingMyBoard(pvo);
				ph = new PagingHandler(pvo, mpsv.meetingMyBoardCount(pvo));
			}else if(my.equals("good")) {
				list = mpsv.meetingMyGood(pvo);
				ph = new PagingHandler(pvo, mpsv.meetingMyGoodCount(pvo));
			}else if(my.equals("scrap")) {
				list = mpsv.meetingMyScrap(pvo);
				ph = new PagingHandler(pvo, mpsv.meetingMyScrapCount(pvo));
			}
		}
		
		// recipe
		if(category.equals("recipe")) {
			if(my.equals("board")) {
				list = mpsv.recipeMyBoard(pvo);
				ph = new PagingHandler(pvo, mpsv.recipeMyBoardCount(pvo));
			}else if(my.equals("good")) {
				list = mpsv.recipeMyGood(pvo);
				ph = new PagingHandler(pvo, mpsv.recipeMyGoodCount(pvo));
			}else if(my.equals("scrap")) {
				list = mpsv.recipeMyScrap(pvo);
				ph = new PagingHandler(pvo, mpsv.recipeMyScrapCount(pvo));
			}
		}
		
		// question
		if(category.equals("question")) {
			if(my.equals("board")) {
				list = mpsv.questionMyBoard(pvo);
				ph = new PagingHandler(pvo, mpsv.questionMyBoardCount(pvo));
			}
		}
		
		log.info(list.toString());
		log.info(ph.toString());
		
		model.addAttribute("list", list);
		model.addAttribute("ph", ph);
		
		return "/member/myPage";
	}
	

}
