package com.ncs.test.member.model.service;

import java.util.List;
import java.util.Map;

public interface MemberService {
	public List<Map<String,Object>> memberList();
	public int login(Map<String,Object> pMap);
}
