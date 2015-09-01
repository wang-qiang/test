package test;

import java.io.IOException;

import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.atomikos.jms.AtomikosConnectionFactoryBean;

import fuc.common.spring.SpringBeanFactory;
import test.entity.Role;
import test.entity.User;
import test.service.UserService;

@Component
@Transactional
public class Test {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private UserService userService;
	
	//发送事务
	public void test() {
		try {
			//消息重发策略：1秒后开始重发，最大重发10次，每次重发间隔时间递增2倍，最大间隔60秒（计算得知10次重发间隔时间分别为1、2、4、8、16、32、60、60、60、60）
			AtomikosConnectionFactoryBean acfb = (AtomikosConnectionFactoryBean) jmsTemplate.getConnectionFactory();
			ActiveMQXAConnectionFactory fac = (ActiveMQXAConnectionFactory) acfb.getXaConnectionFactory();
			RedeliveryPolicy rp = fac.getRedeliveryPolicy();
			rp.setMaximumRedeliveries(10);					//最大重发次数，达到最大重发次数后抛出异常。为-1时不限制次数，为0时表示不进行重发
			rp.setUseExponentialBackOff(true);				//启用指数倍数递增的方式增加延迟时间
			rp.setBackOffMultiplier(2);						//重发时间间隔递增倍数，只有值大于1和启用useExponentialBackOff参数时才生效
			rp.setInitialRedeliveryDelay(0);				//初始重发延迟时间
			rp.setRedeliveryDelay(1000);					//重发延迟时间，当initialRedeliveryDelay=0时生效
			rp.setMaximumRedeliveryDelay(1000 * 60);		//最大重发延迟，只在useExponentialBackOff为true时有效，假设首次重发间隔为10ms，倍数为2，那么第二次重发时间间隔为20ms，第三次重发时间间隔为40ms，当重发时间间隔大的最大重发时间间隔时，以后每次重发时间间隔都为最大重连时间间隔
			
			userService.addUser(new User("fuc", 18), new Role("xx"));
			jmsTemplate.convertAndSend("xxx");
//			throw new RuntimeException();
		} catch (JmsException e) {
		}
	}
	
	//测试
	public static void main(String[] args) throws IOException {
		Test test = SpringBeanFactory.getBean("test", Test.class);
		test.test();
	}

}