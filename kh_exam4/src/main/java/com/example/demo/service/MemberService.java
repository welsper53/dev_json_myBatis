package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface MemberService {
	public List<Map<String,Object>> memberList();
	public Map<String, Object> memberLogin(Map<String,Object> pMap);
}
