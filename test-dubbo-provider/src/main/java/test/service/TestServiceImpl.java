package test.service;

import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Override
	public String sendMsg(String msg) {
		return "收到消息：" + msg;
	}

}
