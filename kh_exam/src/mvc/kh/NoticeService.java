package mvc.kh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import org.apache.log4j.Logger;

import model.vo.Board;
import model.vo.Notice;

public class NoticeService {
	Logger logger = Logger.getLogger(NoticeService.class);
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	public ArrayList<Notice> selectList() {
		logger.info("selectList 호출 성공");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, title, writer, content FROM khnotice");
		ArrayList<Notice> list = new ArrayList<>();
		try {
			dbMgr = DBConnectionMgr.getInstance();
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			Notice notice = null;
			while(rs.next()) {
				notice = new Notice();
				notice.setId(rs.getInt("id"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getNString("content"));
				list.add(notice);
			}		
		} catch (Exception e) {
			logger.info("Exception : "+e.toString());
		} finally {
			dbMgr.freeConnection(con, pstmt, rs);
		}		
		return list;
	}
	
}
