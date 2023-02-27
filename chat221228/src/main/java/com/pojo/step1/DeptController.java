package com.pojo.step1;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

//SELECT deptno, dname, loc FROM dept;
public class DeptController implements Action {
	Logger logger = Logger.getLogger(DeptController.class);

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ActionForward af = new ActionForward();
		String[] upmu = (String[])req.getAttribute("upmu");
		DeptLogic deptLogic = new DeptLogic();
		String path = null;
		boolean isRedirect = true;
		
		// 너 부서목록 조회할거니?
		if ("getDeptList".equals(upmu[1])) {
			logger.info("deptInsert 호출");
			List<Map<String,Object>> deptList = deptLogic.getDeptList();
			
			req.setAttribute("deptList", deptList);
			path = "getDeptList.jsp";
			isRedirect = false;	// false는 forward이다 (요청을 유지 - 주소창은 그대로인데 페이지는 바뀜)
		}
		else if ("jsonDeptList".equals(upmu[1])) {
			logger.info("jsonDeptList 호출");
			String jsonDoc = deptLogic.jsonDeptList();
			
			req.setAttribute("jsonDoc", jsonDoc);
			path = "jsonDeptList.jsp";
			isRedirect = false;	// false는 forward이다 (요청을 유지 - 주소창은 그대로인데 페이지는 바뀜)
		}		
		// 부서 등록할거니?
		else if ("deptInsert".equals(upmu[1])) {
			logger.info("deptInsert 호출");
			// insert into dept(deptno, dname, loc) value (?, ?, ?)
			int result = deptLogic.deptInsert();
		}
		// 부서정보 수정할거니?
		else if ("deptUpdate".equals(upmu[1])) {
			logger.info("deptUpdate 호출");
			int result = deptLogic.deptUpdate();
		}
		// 너희 부서 없어졌다
		else if ("deptDelete".equals(upmu[1])) {
			logger.info("deptDelete 호출");
			int result = deptLogic.deptDelete();
		}
		
		af.setPath(path);
		af.setRedirect(isRedirect);
		
		return af;
	}

	public ActionForward getDeptList() {
		// res.sendRedirect()가 없으면 응답할 수 없다.
		
		return null;
	}
}
