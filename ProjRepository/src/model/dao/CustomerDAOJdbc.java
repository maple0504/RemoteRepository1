package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CustomerBean;

public class CustomerDAOJdbc implements CustomerDAO {
	
	public static void main(String[] args){
		CustomerDAO dao = new CustomerDAOJdbc();
		CustomerBean bean = dao.select("Carol");
		System.out.print("select = "+bean);
		System.out.print("\n");
		System.out.print(dao.update("E".getBytes(), "ellen@lab.com", new java.sql.Date(0), "ellen"));
	}
	
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName = java";
	private static final String USER = "sa";
	private static final String PASS = "passw0rd";

	private static final String SELECT_BY_CUSTID = "select * from customer where custid=?";
	@Override
	public CustomerBean select(String custid) {
		CustomerBean result = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = DriverManager.getConnection(URL , USER , PASS);
			stmt = conn.prepareStatement(SELECT_BY_CUSTID);
			stmt.setString(1,custid);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				result = new CustomerBean();
				result.setCustid(rs.getString("custid"));
				result.setPassword(rs.getBytes("password"));
				result.setEmail(rs.getString("email"));
				result.setBirth(rs.getDate("birth"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	private static final String UPDATE =
			"update customer set password=?, email=?, birth=? where custid=?";
	@Override
	public boolean update(byte[] password, String email, java.util.Date birth, String custid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.prepareStatement(UPDATE);
			
			stmt.setBytes(1 , password);
			stmt.setString(2 , email);
			
//			stmt.setDate(3 , (Date)birth);
			if(birth!=null){
				long time = birth.getTime();
				stmt.setDate(3 , new java.sql.Date(time));
			}else{
				stmt.setDate(3 , null);
			}
			
			stmt.setString(4, custid);
			int i  = stmt.executeUpdate();
			
			if(i!=0){
				return true;
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
