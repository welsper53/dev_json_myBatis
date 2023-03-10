package com.util;

import java.io.File;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

// 스프링 부트에서는 RequestParam이 대신 해준다, Model, ModelMap
// 사용자가 입력한 값을 Map에 담아 준다
// 담을 Map은 컨트롤계층에서 bind메소드 호출 시 파라미터를 이용해서 원본 주소번지를 받아온다
// 그리고 그 안에 담는다.
public class HashMapBinder {
	Logger logger = Logger.getLogger(HashMapBinder.class);
	// 표준 요청 객체
	HttpServletRequest req = null;	// 전역변수
	
	// cos.jar에서 제공하는 요청 객체임 
	// - 첨부파일로 post하면서 encType속성이 적용된 경우 사용할 것
	MultipartRequest multi = null;
	// 첨부파일의 업로드 물리적인 경로
	String realFolder = null;
	// 첨부파일 한글 처리
	String encType = "UTF-8";
	// 첨부파일 최대 크기
	int maxSize = 5*1024*1024;	// 5MB
	
	public HashMapBinder() {}
	// 생성자 파라미터에 요청객체(지역변수)가 필요한 이유는 뭐죠?
	public HashMapBinder(HttpServletRequest req) {
		logger.info("HashMapBinder 생성자 호출");
		// 생성자의 역할 -> 전역변수의 초기화
		this.req = req;
		realFolder = "D:\\workspace_java\\chat221228\\src\\main\\webapp\\pds";
	}
	
	public void multiBind(Map<String, Object> pMap) {
		logger.info("multiBind 호출");
		
		//컨트롤계층에서 생성한 맵 객체 비우기
		pMap.clear();
		try {
			multi = new MultipartRequest(req, realFolder, maxSize, encType
					, new DefaultFileRenamePolicy());
		}catch(Exception e){
			logger.info(e.toString()); //발생한 예외 이름 출력하기
		}
		Enumeration<String> en = multi.getParameterNames();
		
		while(en.hasMoreElements()) {
			String key = en.nextElement();
			logger.info(req.getParameter(key));
			pMap.put(key, multi.getParameter(key));
		}
		logger.info(pMap);
		
		// 첨부파일에 대한 정보 받아오기
		Enumeration<String> files = multi.getFileNames();	// n개 만큼
		
		if (files != null) {
			// 업로드 대상 파일을 객체로 만듦
			File file = null;	// 내용까지 복제되는건 아니다 - 파일명에 대해서만 객체화
			
			while(files.hasMoreElements()) {
				String fname = files.nextElement();
				String filename = multi.getFilesystemName(fname);
				
				pMap.put("bs_file", filename);
				
				if (filename != null && filename.length()>1) {
					file = new File(realFolder + "\\" + filename);
				}
				logger.info(file);
			} // end of while
			
			// 첨부파일에 크기를 담을 수 있는 변수 선언
			double size = 0;
			
			if (file != null) {
				size = file.length();	// 파일 크기를 byte 단위로 담음
				size = size/1024.0;		// kbyte로 변환
				
				pMap.put("bs_size", size);
			}
		} // end of if
		
	}
	
	// 어떤 페이지 어떤 상황에서 공통코드 재사용 가능하게 할 것인가?
	// 업무별 요청 클래스에서 주입 받을 객체를 정해서 메소드의 파라미터로 전달 받음
	// 전달 받은 주소번지 원본에 값을 담아준다
	public void bind(Map<String,Object> pMap) {
		logger.info("bind 호출");
		
		pMap.clear();
		Enumeration<String> en = req.getParameterNames();
		
		while(en.hasMoreElements()) {
			String key = en.nextElement();
			pMap.put(key, req.getParameter(key));
		}
		logger.info(pMap);
	}
}
