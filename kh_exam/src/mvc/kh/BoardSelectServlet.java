package mvc.kh;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.vo.Board;

public class BoardSelectServlet extends HttpServlet{
	Logger logger = Logger.getLogger(BoardDetailServlet.class);
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		logger.info("doGet 호출 성공");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, title, writer, content FROM khboard");
		
		List<Board> boardList = new ArrayList<>();
		
		try {
			dbMgr = DBConnectionMgr.getInstance();
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			Board board = null;
			
			while(rs.next()) {
				board = new Board();
				board.setId(rs.getInt("id"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getNString("content"));
				
				boardList.add(board);
			}
			logger.info(boardList);
			
			req.setAttribute("boardList", boardList);
			RequestDispatcher view = req.getRequestDispatcher("./boardList.jsp");
			
			view.forward(req, res);			
		} catch (Exception e) {
			logger.info("Exception : "+e.toString());
		} finally {
			dbMgr.freeConnection(con, pstmt, rs);
		}
/*		
		Board board = new Board();
		board.setId(1);
		board.setTitle("공지사항 제목1");
		board.setWriter("이순신");
		board.setContent("공지사항 내용 설명...");
		req.setAttribute("board", board);
*/		

	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		doGet(req,res);
	}	
}
