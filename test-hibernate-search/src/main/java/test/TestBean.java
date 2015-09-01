package test;

import org.springframework.stereotype.Component;

import fuc.common.spring.SpringBeanFactory;

@Component
public class TestBean {

	public static void main(String[] args) {
		TestBean bean = SpringBeanFactory.getBean("testBean", TestBean.class);
		System.out.println(bean);
	}
	
}