package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.service.TestService;

public class DubboConsumer {

	public static ClassPathXmlApplicationContext context;
	
	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("applicationContext*.xml");
		TestService testService = context.getBean("testService", TestService.class);
		String msg = testService.sendMsg("fuc");
		System.out.println(msg);
	}
	
}
