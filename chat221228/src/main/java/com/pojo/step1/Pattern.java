package com.pojo.step1;

public class Pattern {

	public static void main(String[] args) {
//		String url = "/chat221228/업무이름/페이지명 또는 요청명";
		String url = "/chat221228/dept/getDeptList.kh";
		String context = "chat221228/";	// 프로젝트명
		
		// 톰캣서버에 요청할 때 사용되는 주소값을 가지고 업무명과 업무에 필요한 이름으로
		// 분리시켜 사용자 요청에 따라 처리를 담당할 ~~~Controller객체를 주입하는데 사용한다
		String command = url.substring(context.length() + 1);
		System.out.println(command);	// dept/getDeptList.kh
		
		int end = command.lastIndexOf(".");
		System.out.println(end);		// 16
		
		command = command.substring(0, end);
		System.out.println(command);	// dept/getDeptList
		
		String upmu[] = null;
		upmu = command.split("/");
		for (String imsi : upmu) {
			System.out.println(imsi);	// dept		getDeptList
		}
	}
}
