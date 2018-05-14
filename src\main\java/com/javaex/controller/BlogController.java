package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BlogService;
import com.javaex.service.CateService;
import com.javaex.service.PostService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CateService cateService;
	
	@Autowired
	private PostService postService;
	
	//bc-00.블로그 홈
	//id값 받아와서 주소 계속 변경되도록 path값 받아오기
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String blog(@PathVariable("id") String id, Model model, 
			           @RequestParam(value="cateNo" , required=false, defaultValue="-1") int cateNo,
			           @RequestParam(value="postNo", required=false, defaultValue="-1") int postNo) {
		
		//헤더 불러오기
		BlogVo bVo = new BlogVo();
		bVo = blogService.getId(id);
		
		//카테고리서비스
		List<CateVo> list = cateService.getListMain(id);	
		System.out.println("리스트확인 "+list.toString());
		
		//포스트 리스트
		System.out.println("카테고리번호 "+cateNo);
		List<PostVo> listPost = postService.getListPost(cateNo);  
		System.out.println("리스트포스트: "+listPost.toString());
		System.out.println(cateNo);
		
//		//포스트 내용
//		List<PostVo> listPostContent = postService.getPost(postNo);
//		System.out.println("포스트내용: "+listPostContent.toString());

		
		model.addAttribute("bVo", bVo);
		model.addAttribute("list", list);
		model.addAttribute("listPost", listPost);
		//model.addAttribute("listPostContent", listPostContent);
		System.out.println("CategoryController에서 받아온 값: " + list.toString());
		System.out.println("BlogController에서 받아온 값: " + bVo.toString());
		
		return "blog/blog-main";
	}

}
