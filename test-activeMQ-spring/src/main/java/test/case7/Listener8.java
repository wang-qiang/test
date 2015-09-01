package test.case7;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/** 原始JMS消息监听器 */
@Component
public class Listener8 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage mm = (TextMessage) message;
		try {
			System.out.println("Listener8收到消息：" + mm.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
