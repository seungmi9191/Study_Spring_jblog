package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CateDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.CateVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	//tip! Autowired는 1:1로 생성
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CateDao cateDao;
	
	//uc>us-00-1.회원가입 처리
	public int join(UserVo userVo) {
		
		int count = userDao.insert(userVo);
		
		if(count>0) {
			System.out.println("가입처리성공!");
		}else {
			System.out.println("가입처리실패!");
		}
		
		//blog 생성하기
		String userId = userVo.getId();
		String blogTitle = userVo.getId() + "의 블로그 입니다.";
		String logoFile = "spring-logo.jpg";
		
		//blogDao에 값 넘겨주기
		blogDao.insert(userId, blogTitle, logoFile);
		
		//category 생성하기(map버전)
		//String cateName = "미분류";
		//String description = "기본으로 만들어지는 카테고리 입니다.";
		
		//category 생성하기(vo버전)
		CateVo cVo = new CateVo();
		cVo.setId(userVo.getId());
		cVo.setCateName("미분류");
		cVo.setDescription("기본으로 만들어지는 카테고리 입니다.");
		
		//cateDao에 값 넘겨주기
		cateDao.insert(cVo);

		return count;
	}
	
	//uc>us-00-2.회원가입 - (ajax) 중복 아이디 체크
	public boolean isCheckOk(String id) {
		
		int count = userDao.getCountById(id);
		
		boolean check;
		if(count>0) {
			check = true;
		} else {
			check = false;
		}
		
		System.out.println("Service: " + check);
		return check;
	}
	
	//uc>us-01&02.블로그 홈 & 블로그 메인({id})에서 로그인
	public UserVo login(String id, String password) {
		return userDao.getUser(id, password);
	}
}
