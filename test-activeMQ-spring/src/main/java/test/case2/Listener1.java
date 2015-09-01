package test.case2;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/** 原始JMS消息监听器 */
@Component
public class Listener1 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage mm = (TextMessage) message;
		try {
			System.out.println("Listener1，负责监听队列1：");
			System.out.println("收到：" + mm.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
