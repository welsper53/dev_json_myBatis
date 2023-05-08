package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;


@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;

	@Override
	public List<Map<String, Object>> memberList() {
		List<Map<String, Object>> rList = null;
		return rList;
	}

	@Override
	public Map<String, Object> memberLogin(Map<String, Object> pMap) {
		Map<String, Object> rMap = null;
		
		rMap = memberDao.memberLogin(pMap);
		
		return rMap;
	}
}
