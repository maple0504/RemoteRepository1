package model.test;

import java.util.List;

import model.ProductBean;
import model.dao.ProductDAO;
import model.dao.ProductDAOJdbc;

public class SelectAllDemo {

	public static void main(String[] args) {
		ProductDAO DAO = new ProductDAOJdbc();
		List<ProductBean> beans= DAO.select();
		for(ProductBean bean:beans){
		System.out.print(bean.getId()+" , "+bean.getName()+" , "+bean.getPrice()+" , "+bean.getMake()+" , "+bean.getExpire()+"\n");
		}	
	}

}
