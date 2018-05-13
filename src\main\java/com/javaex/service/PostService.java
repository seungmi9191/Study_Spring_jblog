package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	//apc>ps-01. 글작성 등록
	public boolean write(PostVo postVo) {
		
		int count = postDao.insert(postVo);
		System.out.println("--------------");
		System.out.println("postService: " + postVo);
		
		boolean result;
		if(count>0) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}

}
