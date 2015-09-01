package test.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import test.entity.Role;
import test.entity.User;
import test.service.UserService;


/** 原始JMS消息监听器 */
@Component
@Transactional
public class Listener implements MessageListener {
	
	@Autowired
	private UserService userService;
	
	private static long time;
	
	//接收事务
	@Override
	public void onMessage(Message message) {
		//重发间隔时间
		long now = System.currentTimeMillis();
		if (time != 0) {
			System.out.println("间隔时间：" + ((now - time) / 1000));
		}
		time = now;
		
		TextMessage msg = (TextMessage) message;
		try {
			userService.addUser(new User(msg.getText(), 18), new Role(msg.getText()));
			throw new RuntimeException();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
