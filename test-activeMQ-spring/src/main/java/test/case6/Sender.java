package test.case6;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import fuc.common.spring.SpringBeanFactory;

public class Sender {
	private static JmsTemplate jmsTemplate = SpringBeanFactory.getBean("jmsTemplate6", JmsTemplate.class);
	
	public static void main(String[] args) {
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("case6发送的消息");
			}
		});
	}

}