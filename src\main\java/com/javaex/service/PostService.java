package com.javaex.service;

import java.util.List;

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
	
	//bc>ps-00.블로그 홈 - 포스트 리스트 출력
	public List<PostVo> getListPost(int cateNo) {
		System.out.println("ff"+cateNo);
		
		return postDao.getListPost(cateNo);
		
	}
	
//	//bc>ps-00.블로그 홈 - 포스트 내용 출력
//	public List<PostVo> getPost(int postNo) {
//		System.out.println(postNo);
//		
//		List<PostVo> listCheckPost = postDao.getListPost(postNo);
//		
//		if(listCheckPost.isEmpty()) {
//			if(postNo==-1) {
//				listCheckPost = postDao.getListPost(listCheckPost.get(0).getPostNo());
//				return listCheckPost;
//			}
//		}
//		return listCheckPost;
//	}
	
}
