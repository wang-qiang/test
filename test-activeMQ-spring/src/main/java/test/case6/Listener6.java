package test.case6;

import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component
public class Listener6 {

	public void handleMessage(String message) {
		System.out.println("Listener6收到：" + message);
	}
	
	public void handleMessage(TextMessage message) {
		System.out.println("Listener6收到：" + message);
	}
 
}