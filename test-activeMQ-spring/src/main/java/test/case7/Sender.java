package test.case7;

import org.springframework.jms.core.JmsTemplate;

import fuc.common.spring.SpringBeanFactory;

public class Sender {
	
	private static JmsTemplate jmsTemplate = SpringBeanFactory.getBean("jmsTemplate7", JmsTemplate.class);

	public static void main(String[] args) {
		String s = String.valueOf(System.currentTimeMillis());
		System.out.println("Sender发送消息：" + s);
		jmsTemplate.convertAndSend(s);
	}

}