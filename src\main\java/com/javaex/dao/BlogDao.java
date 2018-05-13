package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	//us>bd-00-1.회원가입 처리-블로그 생성
	public int insert(String id, String blogTitle, String logoFile) {
		
		System.out.println("blogDao에 도착한 id: " +id);
		//map에 id랑 password 넣기
		Map<String, String> blogMap = new HashMap<String, String>();
		blogMap.put("id",id);
		blogMap.put("blogTitle", blogTitle);
		blogMap.put("logoFile", logoFile);
		return sqlSession.insert("blog.insert",blogMap);
	}
	
	//bc>bc>bd-00.블로그 홈,id값
	public BlogVo selectByUserIdInfo(String id) {
		System.out.println("BlogDao: " + id);
		return sqlSession.selectOne("blog.selectByUserIdInfo", id);
	}
	
	//abc>bc>bd-02./{id} 기본정보수정
	public int updateByBlogInfo(BlogVo blogVo) {
		int count = sqlSession.update("blog.updateByBlogInfo", blogVo);
		System.out.println("blogdao vo: "+count);
		return count;
	}
}
