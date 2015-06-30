package model.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.ProductBean;
import model.dao.ProductDAO;
import model.dao.ProductDAOJdbc;

public class InsertDemo {

	public static void main(String[] args) {
		
		ProductBean bean = new ProductBean();

			ProductDAO DAO = new ProductDAOJdbc();
			
			bean.setId(100);
			bean.setName("GREEN TEA");
			bean.setPrice(30.5);
			bean.setMake(java.sql.Date.valueOf("2015-06-07"));
			bean.setExpire(10);
			ProductBean count = DAO.insert(bean);
			
			System.out.println(count.getId()+count.getName()+count.getPrice()+count.getMake()+count.getExpire());
		

		
	}

}
