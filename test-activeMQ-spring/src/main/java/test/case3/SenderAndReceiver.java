package test.case3;

import org.springframework.jms.core.JmsTemplate;

import test.pojo.Email;
import fuc.common.spring.SpringBeanFactory;

public class SenderAndReceiver {
	
	private static JmsTemplate jmsTemplate = SpringBeanFactory.getBean("jmsTemplate3", JmsTemplate.class);

	public static void main(String[] args) {
		send();
		receive();
	}
	
	public static void send() {
		Email email = new Email("354447958@qq.com", "主题", "内容");
		jmsTemplate.convertAndSend(email);
		System.out.println("Sender发送：" + email);
	}

	public static void receive() {
		Email email = (Email) jmsTemplate.receiveAndConvert();
		System.out.println("Receiver收到：" + email);
	}
}