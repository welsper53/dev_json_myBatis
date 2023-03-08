package mvc.kh;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class BoardUpdateServlet extends HttpServlet{
	Logger logger = Logger.getLogger(BoardUpdateServlet.class);
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		
		logger.info(title+","+writer+","+content);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("update khboard set title=?");
		sql.append("                ,content=?");
		sql.append(" where id=?");
		int result = 0;
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, id);
			
			result = pstmt.executeUpdate();
			
			logger.info("result : "+result);
			
			res.sendRedirect("./boardList.bo");
		} catch (Exception e) {
			logger.info("Exception : "+ e.toString());
		} finally {
			dbMgr.freeConnection(con, pstmt);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		doGet(req,res);
	}	
}
