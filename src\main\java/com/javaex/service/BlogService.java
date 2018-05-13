package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	//bc-00. id값 받아오기(주소)
	public BlogVo getId(String id) {
		System.out.println("blogService: " +id);
		return blogDao.selectByUserIdInfo(id);
	}

	//abc-02./{id} 기본정보수정-사진변경
	public int updateInfo(BlogVo blogVo, MultipartFile file) {
		
		//System.out.println(file);
		String saveName="";
		
		//파일의 값이 Empty가 아니면 아래를 실행해라.
		if(!file.isEmpty()) {
			
			String saveDir = "/Users/WOOSEUNGMI/Desktop/2018/javaStudy/upload";
			
			//원본 파일명(사용자 컴퓨터에 저장되어 있던 이름)
			String orgName = file.getOriginalFilename();
			System.out.println("orgName: "+orgName);
			
			//확장자 (파일의 원본 파일명의 .을 기준으로 잘라라)
			String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			System.out.println("exName: "+exName);
			
			//저장파일명
			saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			System.out.println("saveName: "+saveName);
			
			//파일패스
			String filePath = saveDir + "/" + saveName;
			System.out.println("filePath: "+filePath);
			
			//파일사이즈
			long fileSize = file.getSize();
			System.out.println(fileSize);
			
		
		//파일 서버에 복사
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream( saveDir + "/" + saveName);
			BufferedOutputStream bout = new BufferedOutputStream(out); //버퍼하나 끼워주면 빠름
			
			bout.write(fileData);
			
			if(bout != null) { //데이터랑 상관없이 연결이 있으면 닫아버령
				bout.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		} 
			//null이 아니면 새로운 파일을 서버에 복사해주고, null이면 아래만 실행!
			//파일경로 DB에 넣어주기
			blogVo.setLogoFile(saveName);
			System.out.println("update blogVo: "+blogVo.toString());
			
			int count = blogDao.updateByBlogInfo(blogVo);
			System.out.println(count);
			
			if(count>0) {
				System.out.println("정보저장성공!");
			} else {
				System.out.println("정보저장실패!");
			}
			
		return count;
	}
}
