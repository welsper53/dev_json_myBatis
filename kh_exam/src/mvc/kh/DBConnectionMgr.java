package mvc.kh;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*****************************************************************
 * 데이터의 영속성을 보장할 수 있는 데이터베이스 서버에 접속할 때 
 * 사용하는 공통 코드 작성해 보기
 * @author user2
 *
 ****************************************************************/
public class DBConnectionMgr {
	//물리적으로 떨어져 있는 오라클 서버에 연결통로를 확보
	Connection con = null;
	//자바코드에서 작성한 select문을 전달함
	Statement  stmt = null;
	//프로시저를 전담하는 인터페이스 선언
	CallableStatement cstmt = null;
	//오라클에게 전달된 select문을 처리한 결과를 꺼내기 위해서 커서를 조작해야 하는데
	//그 때 커서를 이동하는데 필요한 메소드를 선언한 인터페이스
	ResultSet  rs   = null;
	public static final String _DRIVER = "oracle.jdbc.driver.OracleDriver";//ojdbc6.jar  의존관계, 의존성 주입 제조사
	//서버측의 아이피, 서버측의 포트번호, 서버측의 오라클 SID가 필요하다.
	public static final String _URL    = "jdbc:oracle:thin:@127.0.0.1:1521:orcl11";
	public static final String _USER   = "scott";
	public static final String _PW     = "tiger";
	private static DBConnectionMgr dbMgr = null;
	private DBConnectionMgr() {}
	//싱글톤 패턴
	/* 고려사항: 동시성(Concurrency)문제를 고려해서 설계해야 함.
	 * 싱글턴 패턴 공통적인 특징
	 * private constructor를 가진다는 것 - 직접 인스턴스화를 못하도록 하기 위해서
	 * static method를 사용하는 것이다.
	 * Eager Initialization - 이른 초기화 - Thread-safe
	 * :static의 특징을 이용해서 클래스 로더가 초기화 하는 시점에서 정적 바인딩
	 * 즉 컴파일 시점에서 성격이 결정됨 을 통해 해당 인스턴스를 메모리에 등록해서
	 * 사용하는 것이다.
	 * 싱글턴 구현시 중요한 점은 멀티스레딩 환경에서도 동작 가능하게 구현하기
	 */
	public static DBConnectionMgr getInstance() {
		if(dbMgr == null) {
			dbMgr = new DBConnectionMgr();
		}
		return dbMgr;
	}
	public Connection getConnection() {
		try {
			Class.forName(_DRIVER);
			con = DriverManager.getConnection(_URL, _USER, _PW);
		} catch (Exception e) {
			System.out.println("드라이버 클래스를 찾을 수 없습니다.");
			System.out.println(e.toString());
		}
		return con;
	}
/*
 * 사용한 자원 반납하기 
 * 사용한 자원 즉 Connection, PreparedStatement, Statement, ResultSet은 
 * 반드시 반납하도록 한다.
 * 동시 접속자 수가 많은 시스템에서 자원사용은 곧 메모리랑 직결되므로 서버가 다운되거나
 * 시스템 장애 발생의 원인이 된다.
 * 자원을 반납하는 순서는 생성된 역순으로 할것.
 */
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {//select일때
		try {
			if(rs !=null) rs.close();
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//PreparedStatement 동적쿼리에서 사용(권장사항) SELECT * FROM member WHERE id=?
	public void freeConnection(Connection con, PreparedStatement pstmt) {//INSERT|UPDATE|DELETE
		try {
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//CallableStatement 프로시저에 사용됨.
	public void freeConnection(Connection con, CallableStatement cstmt) {//INSERT|UPDATE|DELETE
		try {
			if(cstmt !=null) cstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//Statement 는 정적쿼리 처리할 때 사용
	public void freeConnection(Connection con, Statement stmt) {//INSERT|UPDATE|DELETE
		try {
			if(stmt !=null) stmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
