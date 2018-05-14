package com.javaex.dao;

import java.util.List;

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

	//bc>ps>pd-00.블로그 홈 - 포스트 리스트 출력
	public List<PostVo> getListPost(int cateNo) {
		System.out.println("포스트다오: " + cateNo);
		return sqlSession.selectList("post.selectByPostList", cateNo);
	}
	
//	//bc>ps>pd-00.블로그 홈 - 포스트 내용 출력
//	public List<PostVo> getPost(int postNo) {
//		System.out.println("포스트내용 다오: "+postNo);
//		return sqlSession.selectOne("post.selectByPostContent", postNo);
//	}
}
