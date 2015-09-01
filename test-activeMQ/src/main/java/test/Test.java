package test;

import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnection;

public class Test {

	public static void main(String[] args) throws JMSException, Exception {
		Consumer consumer = new Consumer();
		Producer producer = new Producer();
		System.out.println(ActiveMQConnection.DEFAULT_BROKER_URL + "------------");
		consumer.consumeMessage();	//开始监听

		//延时500毫秒之后发送消息
		Thread.sleep(500);
		producer.produceMessage("Hello, world!");
		producer.close();

		//延时500毫秒之后停止接受消息
		Thread.sleep(500);
		consumer.close();
	}

}