package model.test;

import model.dao.ProductDAO;
import model.dao.ProductDAOJdbc;

public class DeleteDemo {

	public static void main(String[] args) {
		int id = 100;
		ProductDAO DAO = new ProductDAOJdbc();
		boolean bool = DAO.delete(id);
		
		
		
	}

}
