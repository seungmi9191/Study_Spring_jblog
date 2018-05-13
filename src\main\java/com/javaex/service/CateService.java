package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CateDao;
import com.javaex.vo.CateVo;

@Service
public class CateService {
	
	@Autowired
	private CateDao cateDao;

	//bc>cs-00.블로그 홈 - 카테고리 리스트 메인 출력
	public List<CateVo> getListMain(String id) {
		System.out.println("CateService: " + id);
		return cateDao.selectByCateIdInfo(id);
	}
	
	//acc>cs-01.카테고리 리스트 출력
	public List<CateVo> getList(String id) {
		return cateDao.getList(id);
	}
	
	
	//acc>cs-02.카테고리 추가
	public CateVo creation(CateVo cVo) {
		System.out.println("CateService creation 들어옴!");
		//insert로 값을 넣어줌
		int cateNo = cateDao.insert(cVo);
		System.out.println("CateService: "+ cateNo);
		
		//db에서 vo에 담긴 CateNo내용을 반환시켜 no에 넣음
		//입력한 글의 no가 담긴 번호를 db에 다시 보내서 값을 vo로 받아옴
		return cateDao.selectCategoryList(cateNo);
	}
	
	//acc>cs-03.카테고리 삭제
	public boolean delete(int cateNo) {
		System.out.println("CateService delete 들어옴!");
		System.out.println(cateNo);
		int count = cateDao.delete(cateNo);
		
		boolean flag;
		if(count>0) {
			System.out.println(count);
			flag = true;
			System.out.println("삭제성공");
		} else {
			System.out.println(count);
			flag = false;
			System.out.println("삭제실패");
		}
		
		return flag;
		
	}
}
