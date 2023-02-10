<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%!	// !: 전역변수 선언
	int x = 1;

	public String newsItem(String[] news) {
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<news.length; i++) {
			if (i == (x-1)) {
				sb.append("<table width='500px' border='1px'>");
				sb.append("<tr><td>" + news[i] + "</td></tr>");		
				sb.append("</table>");
			} // end of if
		} // end of for
		String choice = sb.toString();
		
		return choice;
	} // end of newsItem
	
	public String autoReload() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("console.log('autoReload 호출');");
		
		return sb.toString();
	}
%>

<%	// 스크립틀릿
	String[] news = {"[속보] '도이치모터스 주가조작' 권오수 유죄…집행유예"
			, "이재명 '민생에 무심한 정권, 정적 죽이기에 칼춤'…서울중앙지"
			, "AI 산학계 '챗GPT 사용 규제 필요해'"
			, "尹대통령 지지율 32%…부정평가 59%[한국갤럽]"
			, "인천 편의점 업주 살해 용의자, 부천 모텔서 검거"};
	String data = "";

	switch (x) {
		case 1: {
			data = newsItem(news);
			x++;
			break;
		}
		case 2: {
			data = newsItem(news);
			x++;
			break;
		}
		case 3: {
			data = newsItem(news);
			x++;
			break;
		}
		case 4: {
			data = newsItem(news);
			x++;
			break;
		}
		case 5: {
			data = newsItem(news);
			x = 1;
			break;
		}
	} // end of switch
	
	out.clear();	// 기존에 읽어온 기사 정보 지우기
	out.print(data);
%>