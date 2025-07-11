package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* select문 수행하기 */
public class SelectTest {
	String url="jdbc:mysql://localhost:3306/dev";
	String user="java";
	String pass="1234";
	// Connection, PreparedStatement는 인터페이스... 접속 객체로부터 얻어와야 함
	Connection con=null; // finally에서 닫기 위해서는 try 문의 지역변수이면 안되기 때문..
	PreparedStatement pstmt=null; // query문 수행 객체: 쿼리문 마다 1:1 대응됨.. 쿼리 수행이 많아질 수록 만드는 개수 늘려야 함
	ResultSet rs=null;
	
	public SelectTest() {
		// mysql에서 버전 확인하기 : select version()
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Load Success !!");
			
			// 접속 (암기 사항) ; 다른 언어와 달리 Connection ㄱ개체는 접속이 성공되면, 메모리에 올라오는 결과물
			// 접속 정보를 가진 객체임... 접속 시도 객체가 아님
			con=DriverManager.getConnection(url, user, pass);
			
			if(con!=null) {
				System.out.println("접속 성공");
				// 쿼리문 날려보기
				String sql="select * from emp";
				pstmt=con.prepareStatement(sql); // pstmt 객체 생성
				
				// 쿼리 실행(DML=insert,update,delete / DDL=create,drop,alter)
				// DML,DDL : excuteUpdate() 통해 호출 / select문 : executeQuery()
				rs=pstmt.executeQuery();
				
				while(rs.next()) { // 커서 한 칸 전진
					// 최초 rs를 생성한 시점에는 커서가 첫 번째 레코드보다 위에 위치
					
					String ename=rs.getString("ename"); // 현재 커서가 위치한 곳에서의 ename 컬럼 값을 가져옴
					int sal=rs.getInt("sal");
					String job=rs.getString("job");
					Date hiredate=rs.getDate("hiredate");
					System.out.println("ename="+ename+ " sal="+sal+ " job="+job+ " hiredate="+hiredate);
				}
				
			}else {
				System.out.println("접속 실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();			
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public static void main(String[] args) {
		new SelectTest();
	}
}
