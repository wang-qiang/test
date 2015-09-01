package test;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {

	private String queueName = "FUC_TEST";								//队列名
	private String userName = ActiveMQConnection.DEFAULT_USER;			//消息中间件用户名
	private String password = ActiveMQConnection.DEFAULT_PASSWORD;		//消息中间件密码
	private String brokerURL = ActiveMQConnection.DEFAULT_BROKER_URL;	//消息中间件URL
	private Connection connection = null;								//连接
	private Session session = null;										//会话(单线程上下文)
	private Destination destination = null;								//目的地，指定生产消息的目标及消费消息的来源，支持两种消息模式：点对点、发布/订阅
	private MessageProducer producer = null;							//创建生产者

	// 初始化
	private void initialize() throws JMSException, Exception {
		ActiveMQConnectionFactory connFac = new ActiveMQConnectionFactory(userName, password, brokerURL);
		connection = connFac.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createQueue(queueName);
		producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);			//设置发送模式
	}

	// 发送消息
	public void produceMessage(String message) throws JMSException, Exception {
		initialize();
		TextMessage msg = session.createTextMessage(message);
		connection.start();
		System.out.println("Producer:->Sending message: " + message);
		producer.send(msg);
		System.out.println("Producer:->Message sent complete!");
	}

	// 关闭连接
	public void close() throws JMSException {
		System.out.println("Producer:->Closing connection");
		if (producer != null)
			producer.close();
		if (session != null)
			session.close();
		if (connection != null)
			connection.close();
	}
}