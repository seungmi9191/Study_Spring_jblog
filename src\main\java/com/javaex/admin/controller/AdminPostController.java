package com.javaex.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BlogService;
import com.javaex.service.CateService;
import com.javaex.service.PostService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;

@Controller
public class AdminPostController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CateService cateService;
	
	@Autowired
	private PostService postService;

	//apc-00. 글작성 페이지
	@RequestMapping(value="/{id}/admin/write", method=RequestMethod.GET)
	public String write(@PathVariable("id") String id, Model model) {
		
		//헤더에 id정보 표시위해 불러오기
		BlogVo bVo = new BlogVo();
		bVo = blogService.getId(id);
		System.out.println("AdminBlogCon: "+bVo);
		
		//카테고리 정보뿌리기
		List<CateVo> list = cateService.getList(id);
		System.out.println("카테고리"+list.toString());

		model.addAttribute("bVo", bVo);
		model.addAttribute("list", list);	
		
		return "blog/admin/blog-admin-write";
	}
	
	//apc-01. 글작성 등록
	@RequestMapping(value="/{id}/admin/write", method=RequestMethod.POST)
	public String writesucess(@PathVariable("id") String id, @ModelAttribute PostVo postVo, Model model) {
		
		//form에서 정보 받기
		boolean result = postService.write(postVo);
		System.out.println("postService result: " +result);
		model.addAttribute("pVo", postVo);
		
		return "redirect:/"+id+"/admin/write";
	}
	
}
