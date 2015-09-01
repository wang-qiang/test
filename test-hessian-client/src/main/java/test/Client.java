package test;

import test.dto.User;
import test.service.UserService;
import fuc.common.spring.SpringBeanFactory;

public class Client {

	public static void main(String[] args) {
		UserService userService = (UserService) SpringBeanFactory.getBean("userService");
		User user = new User();
		user.setUsername("fuchao");
		user = userService.addUser(user);
		System.out.println(user.getPassword());
	}
	
}