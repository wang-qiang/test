package test;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer implements MessageListener {
	private String queueName = "FUC_TEST";
	private String userName = ActiveMQConnection.DEFAULT_USER;
	private String password = ActiveMQConnection.DEFAULT_PASSWORD;
	private String brokerURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageConsumer consumer = null;		//消费者

	// 初始化
	private void initialize() throws JMSException, Exception {
		ActiveMQConnectionFactory connFac = new ActiveMQConnectionFactory(userName, password, brokerURL);
		connection = connFac.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createQueue(queueName);							
		consumer = session.createConsumer(destination);

	}

	// 消费消息
	public void consumeMessage() throws JMSException, Exception {
		initialize();
		connection.start();
		System.out.println("Consumer:->Begin listening...");
		consumer.setMessageListener(this);			//开始监听
		//Message message = consumer.receive();	//主动接收
	}

	// 关闭连接
	public void close() throws JMSException {
		System.out.println("Consumer:->Closing connection");
		if (consumer != null)
			consumer.close();
		if (session != null)
			session.close();
		if (connection != null)
			connection.close();
	}

	//消息处理函数
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				TextMessage txtMsg = (TextMessage) message;
				String msg = txtMsg.getText();
				System.out.println("Consumer:->Received: " + msg);
			} else {
				System.out.println("Consumer:->Received: " + message);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}