package com.javaex.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CateVo;

@Repository
public class CateDao {

	@Autowired
	private SqlSession sqlSession;
	
	//bc>cs>cd-00.블로그 홈 - 카테고리 리스트 메인 출력
	//카테고리 블로그 메인에 불러오기
	public List<CateVo> selectByCateIdInfo(String id) {
		System.out.println("CateDao: "+id);
		return sqlSession.selectList("cate.selectByCateIdInfo", id);
	}
	
	//acc>cs>cd-01.카테고리 리스트 출력
	public List<CateVo> getList(String id) {
		return sqlSession.selectList("cate.listAll", id);
	}
	
	//acc>cs>dd-02.카테고리 추가
	public int insert(CateVo cVo) {
		System.out.println("CateDao: " + cVo);
		//db에 insert하기
		sqlSession.insert("cate.insert", cVo);
		System.out.println("Result cateInsert: " + cVo.toString());
		return cVo.getCateNo();
	}
	
	//acc>cs>dd-02.카테고리 추가_저장한 카테고리 리스트 부르기
	//저장한 카테고리 번호에 따른 목록을 vo에 담아서 반환시킴
	public CateVo selectCategoryList(int cateNo) {
		return sqlSession.selectOne("cate.selectCategoryList", cateNo);
	}
	
	//acc>cs>cd-03.카테고리 삭제
	public int delete(int cateNo) {
		return sqlSession.delete("cate.delete", cateNo);
	}
	
}
