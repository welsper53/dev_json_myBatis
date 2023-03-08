package mvc.kh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
import java.sql.Connection;

import org.apache.log4j.Logger;

public class BoardDao {
	Logger logger = Logger.getLogger(BoardDao.class);
	
	public int insertBoard(Map<String, Object> pMap) {
		logger.info("insertBoard 호출 성공"+pMap);
		
		int result =0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO khboard(id,title,writer,content)");
		sql.append("VALUES(seq_khboard.nextval,?,?,?)");
		
		try {
			con = dbMgr.getConnection();
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, pMap.get("title").toString());
			pstmt.setString(2, pMap.get("writer").toString());
			pstmt.setString(3, pMap.get("content").toString());
			
			result = pstmt.executeUpdate();
			if (result==1) con.commit();
		} catch (Exception e) {
			logger.info("Exception : "+e.toString());
		} finally {
			dbMgr.freeConnection(con, pstmt);
		}
		
		return result;
	}
	
	public int insertBoard(Connection conn, Map<String, Object> board) {
		logger.info("insertBoard 호출 성공" + board);
		
		int result =0;
		
		Connection con = conn;
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO khboard(BOARD_NUM,BOARD_WRITER,BOARD_TITLE,BOARD_CONTENT)");
		sql.append("VALUES(seq_khboard.nextval,?,?,?)");
		logger.info(sql.toString());
		
		try {
			// INSERT 후 자동으로 COMMIT을 실행한다 (디폴트)
			// false : 자동 COMMIT 해제
			con.setAutoCommit(true);
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, board.get("bwriter").toString());
			pstmt.setString(2, board.get("btitle").toString());
			pstmt.setString(3, board.get("bcontent").toString());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			logger.info("Exception : "+e.toString());
		} finally {
			try {
				if(pstmt !=null) pstmt.close();
			} catch (Exception fe) {
				logger.info("Exception : " + fe.toString());
			}
		}
		
		logger.info("sql결과 : " + result);
		return result;
	}
}
