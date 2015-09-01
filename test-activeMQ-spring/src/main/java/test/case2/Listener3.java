package test.case2;

import java.util.Map;

import javax.jms.ObjectMessage;

import org.springframework.stereotype.Component;

@Component
public class Listener3 {

	public String handleMessage1(String message) {
		System.out.println("Listener3，负责监听队列3，并返回消息发送到队列2：");
		System.out.println("收到：" + message);
		System.out.println("回复：消息2");
		System.out.println("=============================================");
		return "消息2";
	}
 
	public void handleMessage1(byte[] message) {
		System.out.println("收到一个BytesMessage，内容是：" + message);
	}
	
	public void handleMessage3(Map<String, Object> message) {
		System.out.println("收到一个MapMessage，内容是：" + message);
	}
	
	public void handleMessage4(ObjectMessage message) {
		System.out.println("收到一个ObjectMessage，内容是：" + message);
	}

}