package model.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.ProductBean;
import model.dao.ProductDAO;
import model.dao.ProductDAOJdbc;

public class UpdateDemo {

	public static void main(String[] args) {
		ProductBean bean = new ProductBean();
		ProductDAO DAO = new ProductDAOJdbc();
		
			bean.setId(100);
			bean.setName("TEA");
			bean.setPrice(20.0);
			bean.setMake(java.sql.Date.valueOf("2001-01-01"));
			bean.setExpire(20);
			
			
			DAO.update(bean.getName(),bean.getPrice(),bean.getMake(),bean.getExpire(),bean.getId());
		
		
		
		
		
	}

}
