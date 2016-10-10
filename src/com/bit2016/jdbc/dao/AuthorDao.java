package com.bit2016.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bit2016.jdbc.vo.AuthorVo;

public class AuthorDao {
	
	public void insert( AuthorVo vo ) {
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
				"insert into author values( ?, ? )";	
			pstmt = conn.prepareStatement(sql);

			//4. 바인딩
			
			pstmt.setLong(1, vo.getNo() );
			pstmt.setString(2, vo.getName() );
			
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
