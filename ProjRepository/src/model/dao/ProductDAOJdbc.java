package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProductBean;

public class ProductDAOJdbc implements ProductDAO {

	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName = java";
	private static final String USER = "sa";
	private static final String PASS = "passw0rd";
	
	private static final String SELECT_BY_ID =
			"select * from product where id=?";
	@Override
	public ProductBean select(int id) {
		ProductBean result = null;
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				result = new ProductBean();
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("name"));
				result.setPrice(rs.getDouble("price"));
				result.setMake(rs.getDate("make"));
				result.setExpire(rs.getInt("expire"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
	
	private static final String SELECT_ALL =
			"select * from product";
	@Override
	public List<ProductBean> select() {
		List<ProductBean> result = null;
		ProductBean bean = null;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			
			result = new ArrayList<ProductBean>();
			
			while(rs.next()){
				bean = new ProductBean();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getDouble("price"));
				bean.setMake(rs.getDate("make"));
				bean.setExpire(rs.getInt("expire"));
				
				result.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
	
	private static final String INSERT =
			"insert into product (id, name, price, make, expire) values (?, ?, ?, ?, ?)";
	@Override
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		Connection conn = null;
		
		try{
			conn = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement pstmt = conn.prepareStatement(INSERT);
			
			pstmt.setInt(1 , bean.getId());
			pstmt.setString(2 , bean.getName());
			pstmt.setDouble(3 , bean.getPrice());
			pstmt.setDate(4 , (Date)bean.getMake());
			pstmt.setInt(5 , bean.getExpire());

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				result = new ProductBean();
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("name"));
				result.setPrice(rs.getDouble("price"));
				result.setMake(rs.getDate("make"));
				result.setExpire(rs.getInt("expire"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
			"update Product set name=?, price=?, make=?, expire=? where id=?";
	@Override
	public ProductBean update(String name, double price, java.util.Date make, int expire, int id) {
		ProductBean result = null;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, name);
			pstmt.setDouble(2, price);
			pstmt.setDate(3, (Date)make);
			pstmt.setInt(4, expire);
			pstmt.setInt(5, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				result = new ProductBean();
				result.setName(rs.getString("name"));
				result.setPrice(rs.getDouble("price"));
				result.setMake(rs.getDate("make"));
				result.setExpire(rs.getInt("expire"));
				result.setId(rs.getInt("id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
	
	private static final String DELETE =
			"delete from product where id=?";
	@Override
	public boolean delete(int id) {
		boolean result = false;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
	
	
	
}
