package test.case7;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class Listener7 implements SessionAwareMessageListener<TextMessage> {

	@Autowired
	private Destination queue9;
	
	@Override
	public void onMessage(TextMessage message, Session session) throws JMSException {
		String s = String.valueOf(System.currentTimeMillis());
		System.out.println("Listener7收到消息：" + message.getText());
		System.out.println("Listener7回复消息：" + s);
		
        MessageProducer producer = session.createProducer(queue9);
        Message textMessage = session.createTextMessage(s);
        producer.send(textMessage);
        
        //抛出异常后会回滚
//        if ("".equals("")) {  
//        	throw new RuntimeException("Error");  
//        }
	}
}