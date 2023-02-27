package com.pojo.step1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// SELECT empno, ename, job, hiredate FROM emp;
public class EmpController implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ActionForward af = new ActionForward();
		return af;
	}

}
