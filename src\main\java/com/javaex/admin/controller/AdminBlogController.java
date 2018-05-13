package com.javaex.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class AdminBlogController {
	
	@Autowired
	private BlogService blogService;
	
	//abc-02./{id} 기본정보
	@RequestMapping(value="/{id}/admin/basic", method=RequestMethod.GET)
	public String adminblog(@PathVariable("id") String id, Model model) {
		
		//헤더에 id정보 표시위해 불러오기
		BlogVo bVo = blogService.getId(id);
		System.out.println("AdminBlogCon: "+bVo);
		model.addAttribute("bVo", bVo);
		return "blog/admin/blog-admin-basic";
	}
	
	//abc-02./{id} 기본정보수정
	@RequestMapping(value="/{id}/admin/basic", method=RequestMethod.POST)
	public String adminblogprocess(@RequestParam("file") MultipartFile file, @ModelAttribute BlogVo blogVo) {
		System.out.println("AdminBlogCon: "+file+blogVo);
		//업데이트 된 blog 정보를 BlogVo에 넣어주기
		int count = blogService.updateInfo(blogVo,file);
		System.out.println("AdminBlogConCount: " + count);
		
		return "redirect:/{id}/admin/basic";
	}
}
