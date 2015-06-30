package model.test;

import model.ProductBean;
import model.dao.ProductDAO;
import model.dao.ProductDAOJdbc;

public class SelectByDemo {

	public static void main(String[] args) {
		ProductDAO DAO = new ProductDAOJdbc();
		ProductBean bean = DAO.select(10);
		System.out.println(bean.getId()+" , "+bean.getName()+" , "+bean.getPrice()+" , "+bean.getMake()+" , "+bean.getExpire());
		
		
	}

}
