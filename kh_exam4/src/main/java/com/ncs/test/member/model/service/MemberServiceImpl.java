package com.ncs.test.member.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.test.member.model.dao.MemberDao;

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
	public int login(Map<String, Object> pMap) {
		int result = 0;
		
		result = memberDao.login(pMap);
		
		return result;
	}
}
