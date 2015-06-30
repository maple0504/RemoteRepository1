package model.service;

import java.util.Arrays;

import model.CustomerBean;
import model.dao.CustomerDAO;
import model.dao.CustomerDAOJdbc;

public class CustomerService {
	CustomerDAO customerDao = new CustomerDAOJdbc();
	public CustomerBean login(String userName , String password){
		CustomerBean result = null;
		CustomerBean bean = customerDao.select(userName);
		if(bean!=null){
			if(password!=null&&password.length()!=0){
				byte[] pass = bean.getPassword();
				byte[] temp = password.getBytes();
				if(Arrays.equals(pass , temp)){
					return bean;
				}
			}
		}
		return result;
	}
	
	public boolean changePassword(String userName , String oldPassword , String newPassword){
		CustomerBean bean = this.login(userName, oldPassword);
		if(bean!=null){
			byte[] temp = newPassword.getBytes();
			return customerDao.update(temp, bean.getEmail(), bean.getBirth(), userName);
		}
		
		
		return false;
	}
	
	
	public static void main(String[] args){
		CustomerService cs = new CustomerService();
		CustomerBean bean = cs.login("Alex", "A");
		System.out.print(bean);
		
		boolean result = cs.changePassword("Ellen", "E", "EEE");
		System.out.println("result="+result);	
		
	}
	
	
	
}
