package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//apc>ps>pd-01. 글작성 등록
	public int insert(PostVo postVo) {
		int count = sqlSession.insert("post.insert", postVo);
		System.out.println("--------------");
		System.out.println("postDao: " + postVo);
		return count;
	}

}
