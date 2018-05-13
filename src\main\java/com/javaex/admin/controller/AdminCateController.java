package com.javaex.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.BlogService;
import com.javaex.service.CateService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;

@Controller
public class AdminCateController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CateService cateService;
	
	//ajax 주소 형식
	//acc-00.카테고리 관리 진입 
	@RequestMapping(value="/{id}/admin/category", method=RequestMethod.GET)
	public String category(@PathVariable("id") String id, Model model) {
		
		//헤더 정보 불러오기 위해 호출
		BlogVo bVo = blogService.getId(id);
		model.addAttribute("bVo", bVo);
		
		System.out.println("CateController 진입!");
		return "blog/admin/blog-admin-cate";
	}
	
	//acc-01.카테고리 리스트 출력
	@ResponseBody
	@RequestMapping(value="/{id}/admin/category/list", method=RequestMethod.POST)
	public List<CateVo> list(@PathVariable("id") String id) {
		
		System.out.println("cate ajax list");
		
		//리스트를 불러 파일을 연결
		List<CateVo> list = cateService.getList(id);
		System.out.println("cate ajax list check: " + list.toString());
		
		return list;
	}
	
	//acc-02.카테고리 추가
	@ResponseBody
	@RequestMapping(value="/{id}/admin/category/add", method=RequestMethod.POST)
	public CateVo insert(@PathVariable("id") String id, @RequestBody CateVo cVo) {
		
		System.out.println("cate ajax insert");
		System.out.println(cVo.toString());
		CateVo cVoAdd = cateService.creation(cVo);
		System.out.println("cate ajax Con: " + cVoAdd.toString());		
		return cVoAdd;
	}
	
	//acc-03.카테고리 삭제
	@ResponseBody
	@RequestMapping(value="/{id}/admin/category/delete", method=RequestMethod.POST)
	public boolean delete(@PathVariable("id") String id, @RequestParam("cateNo") int cateNo) {
		
		System.out.println("adminCateCon 처음진입: " + cateNo);
		boolean result = cateService.delete(cateNo);
		
		return result;
	}

}
