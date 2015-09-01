package test.controller;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringListener implements ApplicationListener<ContextRefreshedEvent> {

	/** 第一次请求到达时才会调用 */
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		System.out.println("从Web服务器启动成功");
	}

}