package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//uc>us>ud-00-1.회원가입 처리
	public int insert(UserVo userVo) {
		System.out.println("insert Dao 진입!");
		return sqlSession.insert("user.insert", userVo);
	}
	
	//uc>us>ud-00-2.회원가입 - (ajax) 중복 아이디 체크
	public int getCountById(String id) {
		System.out.println("idCheck Dao 진입!");
		System.out.println("Dao: " + id);
		return sqlSession.selectOne("user.selectUserByIdCheck", id);
	}
	
	//uc>us>ud-01&02.블로그 홈 & 블로그 메인({id})에서 로그인
	public UserVo getUser(String id, String password) {
		//map에 id랑 password 넣기
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("id", id);
		userMap.put("password", password);
		System.out.println("userDao에서 map: " + userMap);
		return sqlSession.selectOne("user.selectUserByIdPw", userMap);
	}
}
