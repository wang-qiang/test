package test.dubbo;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubooProvider {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext*.xml");
		context.start();
		System.out.println("按任意键退出");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}