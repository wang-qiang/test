package test.case5;

import org.springframework.stereotype.Component;

import test.pojo.Email;

@Component
public class Listener5 {

	public void handleMessage(Email email) {
		System.out.println("Listener5收到：" + email);
	}

}