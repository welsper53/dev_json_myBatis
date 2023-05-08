package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.MemberServiceImpl;


@Controller
@RequestMapping("/member/*")
public class MemberController {
	Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private MemberServiceImpl memberServiceImpl = null;
	
	@GetMapping("memberList")
	public String memberList(HttpSession session) {
		logger.info("memberList 호출");
		session.setAttribute("loginUser", "나신입");
		
		
		return "forward:memberList.jsp";
	}

	@PostMapping("memberLogin")
	public String memberLogin(@RequestBody Map<String,Object> pMap, HttpSession session) {
		logger.info("memberLogin 호출");
		logger.info(pMap.toString());
		Map<String,Object> rMap = null;
		
		rMap = memberServiceImpl.memberLogin(pMap);
		
		if (rMap.get("mem_name") != null) {
			session.setAttribute("loginUser", rMap.get("mem_name").toString());
			return "redirect:/member/loginSuccess.jsp";
		}
		
		return "redirect:/member/loginFail.jsp";
	}
}