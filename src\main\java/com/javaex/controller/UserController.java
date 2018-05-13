package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user") //value에만 해당됨
public class UserController {
	
	String url;
	
	@Autowired
	private UserService userService;
	
	//uc-00.회원가입 폼 보여주기
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinform() {
		System.out.println(">>enter /joinform");
		return "user/joinForm";
	}
	
	//uc-00-1.회원가입 처리
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinsucess(@ModelAttribute UserVo userVo) {
		System.out.println(">>enter /joinProcessing");
		System.out.println(userVo.toString());
		userService.join(userVo);
		return "user/joinSuccess";
	}

	//uc-01.블로그 홈에서 로그인
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginform() {
		System.out.println(">>enter /loginform");
		return "user/loginForm";
	}
	
	//uc-01-1.블로그 홈에서 로그인 처리
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("id") String id, @RequestParam("password") String password, HttpSession session) {
		
		UserVo authUser = userService.login(id,password);
		
		//session이 null이 아니면 로그인 가능,
		if(authUser != null) {
			session.setAttribute("authUser", authUser);
			System.out.println(">>checkOk /loginsuccess");
			url = "redirect:/";
		} else {
			url = "redirect:/user/login?result=fail";
			System.out.println(">>checkFail go-/login");
		}
		
		return url;
	}
	
	//uc-02./{id} 블로그 메인에서 로그인
	@RequestMapping(value="/login_blog", method=RequestMethod.GET)
	public String loginform_blog() {
		System.out.println(">>enter /loginform_blog");
		return "user/loginForm_blog";
	}
	
	//uc-02-1. /{id} 블로그 메인에서로그인 처리
	@RequestMapping(value="/login_blog", method=RequestMethod.POST)
	public String login_blog(@RequestParam("id") String id, @RequestParam("password") String password, HttpSession session) {
		
		UserVo authUser = userService.login(id,password);
		
		//session이 null이 아니면 로그인 가능,
		if(authUser != null) {
			session.setAttribute("authUser", authUser);
			System.out.println(">>checkOk /loginsuccess");
			url = "redirect:/"+id;
		} else {
			url = "redirect:/user/login?result=fail";
			System.out.println(">>checkFail go-/login");
		}
		
		return url;
	}
	
	//uc-01-2.블로그 홈에서 로그아웃
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		System.out.println(">>processOk /logout");
		
		return "redirect:/";
	}
	
	//uc-02-2./{id} 블로그 메인에서 로그아웃
	@RequestMapping(value="/logout_blog", method=RequestMethod.GET)
	public String logoutblog(@RequestParam("id") String id, HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		System.out.println(">>processOk /logout");
		
		return "redirect:/"+id;
	}
	
	//uc-00-2.회원가입 - (ajax) 중복 아이디 체크
	@ResponseBody
	@RequestMapping(value="/idcheck", method=RequestMethod.POST)
	public boolean isCheckOk(@RequestParam("id") String id) {
		System.out.println("ajax id check: " + id);
		boolean isCheckOk = userService.isCheckOk(id);
		System.out.println("Controller Request DB: " + isCheckOk);
		
		return isCheckOk;
	}
}
