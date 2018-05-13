package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BlogService;
import com.javaex.service.CateService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CateService cateService;
	
	//bc-00.블로그 홈
	//id값 받아와서 주소 계속 변경되도록 path값 받아오기
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String blog(@PathVariable("id") String id, Model model) {
		
		BlogVo bVo = new BlogVo();
		bVo = blogService.getId(id);
		
		//카테고리서비스
		List<CateVo> list = cateService.getListMain(id);
		
		model.addAttribute("bVo", bVo);
		model.addAttribute("list", list);
		System.out.println("CategoryController에서 받아온 값: " + list.toString());
		System.out.println("BlogController에서 받아온 값: " + bVo.toString());
		
		return "blog/blog-main";
	}
	
	
	
}
