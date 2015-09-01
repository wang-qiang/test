package test.case4;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import test.pojo.Email;

/** 原始JMS消息监听器 */
@Component
public class Listener4 implements MessageListener {
	
	@Autowired
	private MessageConverter messageConverter;

	@Override
	public void onMessage(Message message) {
		ObjectMessage objMessage = (ObjectMessage) message;
		try {
			Email email = (Email) messageConverter.fromMessage(objMessage);
			System.out.println("Listener4收到：" + email);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
