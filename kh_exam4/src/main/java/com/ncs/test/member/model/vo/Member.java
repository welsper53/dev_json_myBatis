package com.ncs.test.member.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
	private String memberId;
	private String memberPwd;
	private String memberName;
	private Date memberEnrollDate; // (import : java.sql.Date)
}
