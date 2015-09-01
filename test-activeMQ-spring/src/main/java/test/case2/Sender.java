package test.case2;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import fuc.common.spring.SpringBeanFactory;

public class Sender {
	
	private static JmsTemplate jmsTemplate = SpringBeanFactory.getBean("jmsTemplate2", JmsTemplate.class);
	private static final Destination destination = SpringBeanFactory.getBean("queue2", Destination.class);

	public static void main(String[] args) {
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage("消息3");
				message.setJMSReplyTo(destination);	//指定回复目的地，MessageListenerAdapter配置defaultResponseDestination也可指定回复目的地，但优先级要低一些
				
				System.out.println("Sender类，负责发送消息，目的地为队列3：");
				System.out.println("发送：消息3");
				System.out.println("=============================================");
				return message;
			}
		});
	}

}