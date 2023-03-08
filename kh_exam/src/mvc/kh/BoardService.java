package mvc.kh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import org.apache.log4j.Logger;

import com.util.JDBCTemplate;

public class BoardService {
	Logger logger = Logger.getLogger(BoardService.class);
	BoardDao boardDao = new BoardDao();
	
	public int insertBoard(Map<String, Object> pMap) {
		logger.info("insertBoard 호출 성공");
		
		int result = 0;
		
		result = boardDao.insertBoard(pMap);
		//result = 0;
		return result;
	}
	
	public int tesTinsertBoard(Map<String,Object> board) {
		logger.info("insertBoard 호출");
		int result = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		JDBCTemplate jdbcT = JDBCTemplate.getInstance();
		BoardDao boardDao = new BoardDao();
		
		try {
			con = jdbcT.getConnect();
			
			// INSERT 후 자동으로 COMMIT을 실행한다 (디폴트)
			// false : 자동 COMMIT 해제
			con.setAutoCommit(true);
			
			boardDao.insertBoard(con, board);
		} catch (Exception e) {
			logger.error("Exception : " + e.toString());
		} finally {
			jdbcT.close(con);
		}
		
		return result;
	}
}
