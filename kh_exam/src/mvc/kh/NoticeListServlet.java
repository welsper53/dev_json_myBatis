package mvc.kh;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.vo.Notice;

public class NoticeListServlet extends HttpServlet{
	Logger logger = Logger.getLogger(NoticeListServlet.class);
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		logger.info("doGet 호출 성공");
		ArrayList<Notice> list = new NoticeService().selectList();
		list = null;
		req.setAttribute("list", list);
		String page = null;
		if(list !=null) {
			page = "/WEB-INF/views/notice/noticeList.jsp";
		}else {
			page = "/WEB-INF/views/common/errorPage.jsp";
			req.setAttribute("msg", "공지사항 조회에 실패하였습니다.");
		}
		req.getRequestDispatcher(page).forward(req, res);
	}	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		doGet(req,res);
	}	
}
