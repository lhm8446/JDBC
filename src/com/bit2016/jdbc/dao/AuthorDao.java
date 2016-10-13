package com.bit2016.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2016.jdbc.vo.AuthorVo;

public class AuthorDao {
	
	private Connection getConnetion() throws SQLException{
		
		Connection conn =null;
		
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection( url, "bitdb", "bitdb" );

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : "+ e);
		}
		return conn;
	}
	
	public boolean update(AuthorVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try{
			conn = getConnetion();
			
			String sql = "update author set name = ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setLong(2, vo.getNo());
			
			result =  pstmt.executeUpdate();
		}catch(SQLException e){
			System.out.println("Error : " + e);
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException e){
				System.out.println("Error : " + e);
			}
		}
		return result == 1;
	}
	public boolean delete(Long no){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try{
			conn = getConnetion();
			
			String sql = "delete from author where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			result =  pstmt.executeUpdate();
		}catch(SQLException e){
			System.out.println("Error : " + e);
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException e){
				System.out.println("Error : " + e);
			}
		}
		return result == 1;
	}
	
	public List<AuthorVo> getList(){
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection( url, "bitdb", "bitdb" );
			
			stmt = conn.createStatement();
			String sql = "select no,name from author order by no asc";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				
				AuthorVo vo = new AuthorVo();
				vo.setNo(no);
				vo.setName(name);
				
				list.add(vo);
			}
		}catch(ClassNotFoundException e){
			System.out.println("드라이버 로딩 실패 : "+ e);
		}catch(SQLException e){
			System.out.println("Error : " + e);
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(stmt != null){
					stmt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException e){
				System.out.println("Error : " + e);
			}
		}
		return list;
	}
	
	public boolean insert( AuthorVo vo ) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			//1. JDBC 드라이버 ( Oracle ) 로딩
			conn = getConnetion();
			
			//3. Statement 준비
			String sql = 
				"insert into author values( author_seq.nextval, ? )";	
			pstmt = conn.prepareStatement(sql);

			//4. 바인딩
			pstmt.setString(1, vo.getName() );
			
			//5. SQL 실행
			result  = pstmt.executeUpdate();
	
			
		}catch ( SQLException e ) {
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
		return result == 1;
	}
}
