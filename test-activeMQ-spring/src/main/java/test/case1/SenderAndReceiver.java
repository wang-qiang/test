package test.case1;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import fuc.common.spring.SpringBeanFactory;

public class SenderAndReceiver {
	
	private static JmsTemplate jmsTemplate = SpringBeanFactory.getBean("jmsTemplate1", JmsTemplate.class);

	public static void main(String[] args) throws JMSException {
		send1();
		send2();
		
		//接收消息时，没有消息会阻塞等待，不会无限循环执行
		while (true) {
			receive1();
			receive2();
		}
	}
	
	/** 发送方式一：发送原生Message对象 */
	public static void send1() {
		System.out.println("send1发送：消息1");
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				message.setString("message", "消息1");
				return message;
			}
		});
	}
	
	/** 发送方式二：使用默认转换器SimpleMessageConverter发送普通对象 */
	public static void send2() {
		System.out.println("send2发送：消息2");
		Map<String, Object> map = new HashMap<>();
		map.put("message", "消息2");
		jmsTemplate.convertAndSend(map);
	}
	
	/** 接收方式一：接收原生Message对象 */
	public static void receive1() throws JMSException {
		MapMessage msg = (MapMessage) jmsTemplate.receive();
		System.out.println("receive1收到：" + msg.getString("message"));
	}
	
	/** 接收方式二：使用默认转换器SimpleMessageConverter接收普通对象 */
	@SuppressWarnings("unchecked")
	public static void receive2() {
		Map<String, Object> map = (Map<String, Object>) jmsTemplate.receiveAndConvert();
		System.out.println("receive2收到：" + map.get("message"));
	}
}