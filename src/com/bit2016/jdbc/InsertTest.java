package com.bit2016.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//1. JDBC 드라이버 ( Oracle ) 로딩
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
			
			//2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection( url, "bitdb", "bitdb" );
			
			//3. Statement 준비
			String sql = 
				"insert into book values( ?, ?, sysdate, ?, ?)";	
			pstmt = conn.prepareStatement(sql);

			//4. 바인딩
			Long no = 12L;
			String title = "토지12";
			//String date = "2016-10-10";
			String state= "대여가능";
			Long authorNo = 1L;
			
			pstmt.setLong(1, no);
			pstmt.setString(2, title);
			pstmt.setString(3, state );
			pstmt.setLong(4, authorNo );
			
			//5. SQL 실행
			int count = pstmt.executeUpdate();
			System.out.println( count );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "드라이버 로딩 실패 : " + e );
		} catch ( SQLException e ) {
			System.out.println( "error:" + e );
		} finally {
			try {
				//3. 자원정리
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
