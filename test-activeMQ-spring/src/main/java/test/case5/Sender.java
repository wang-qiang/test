package test.case5;

import org.springframework.jms.core.JmsTemplate;

import test.pojo.Email;
import fuc.common.spring.SpringBeanFactory;

public class Sender {

	private static JmsTemplate jmsTemplate = SpringBeanFactory.getBean("jmsTemplate5", JmsTemplate.class);
	
	public static void main(String[] args) {
		Email email = new Email("354447958@qq.com", "主题", "内容");
		System.out.println("Sender发送：" + email);
		jmsTemplate.convertAndSend(email);
	}

}