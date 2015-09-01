package test;

import java.io.IOException;

import fuc.common.spring.SpringBeanFactory;
import test.entity.Role;
import test.entity.User;
import test.service.UserService;

public class Test {
	
	public static void main(String[] args) throws IOException {
		UserService service = SpringBeanFactory.getBean("userService", UserService.class);
		try {
			service.addUser(new User("fuc", 18), new Role("xx"));
		} catch (Exception e) {
		}
	}

}