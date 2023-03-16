package com.pojo.step3;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ViewResolver {
	Logger logger = Logger.getLogger(ViewResolver.class);
	
	public ViewResolver() {}
	public ViewResolver(HttpServletRequest req, HttpServletResponse res, String[] pageMove) 
			throws ServletException, IOException {
		logger.info("ViewResolver 호출");
		logger.info("pageMove : " + pageMove[0] + ", " + pageMove[1]);
		//String path = pageMove[1];
		
		String path = "";
		if (pageMove[1]==null) {
			path = pageMove[0];
		}
		else {
			path = pageMove[1];
		}
		logger.info(path);
		logger.info("pageMove : " + pageMove[0] + " + " + pageMove[1]);
		
		// webapp 바라본다
		if ("redirect".equals(pageMove[0])) {
			logger.info("redirect");
			
			res.sendRedirect(path);
		} else if ("forward".equals(pageMove[0])) {
			logger.info("forward");
			RequestDispatcher view = req.getRequestDispatcher("/" + path + ".jsp");
			logger.info(view);
			
			view.forward(req, res);
		} 
		// setViewName(~~);
		// WEB-INF/views/(~~).jsp
		else {
			logger.info("else");
			
			if (pageMove[0] != null && pageMove[0].length()>0) {
				path = pageMove[0] + "/" + pageMove[1];
			} else {
				path = pageMove[1];
			}
			logger.info(path);
			
			RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/" + path + ".jsp");
			view.forward(req, res);
		}
	} // end of ViewResolver(req, res, pageMove)
} // end of ViewResolver
