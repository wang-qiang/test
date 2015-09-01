package test;

import test.dto.Result;
import test.service.TestService;
import fuc.common.spring.SpringBeanFactory;

public class Client {

	public static void main(String[] args) {
		TestService testService = (TestService) SpringBeanFactory.getBean("testService");
		Result s = testService.test(9999, null);
		System.out.println(s.getData());
	}
	
}