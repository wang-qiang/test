package test.case2;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

/** Spring提供的消息监听器，收到消息后可回复一个消息 */
@Component
public class Listener2 implements SessionAwareMessageListener<TextMessage> {

	@Autowired
	private Destination queue1;
	
	@Override
	public void onMessage(TextMessage message, Session session) throws JMSException {
		System.out.println("Listener2，负责监听队列2，并返回消息发送到队列1：");
		System.out.println("收到：" + message.getText());
		System.out.println("回复：消息1");
		System.out.println("=============================================");
			
        MessageProducer producer = session.createProducer(queue1);
        Message textMessage = session.createTextMessage("消息1");
        producer.send(textMessage);
	}

}