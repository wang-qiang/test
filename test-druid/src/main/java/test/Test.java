package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fuc.common.spring.SpringBeanFactory;

import test.dao.UserMapper;
import test.entity.User;
import test.service.UserService;

public class Test {
	
	public static void main(String[] args) throws IOException {
		UserMapper userMapper = SpringBeanFactory.getBean("userMapper", UserMapper.class);
		
		//既配xml，又写接口（通过接口调用，通过xml配置映射）
		Map<String,Object> map = new HashMap<>();
		map.put("name", "fuc");
		map.put("age", "28");
		User user = userMapper.findByMap(map);
		System.out.println(user.getName());
		
		//不配xml，只写接口（通过接口调用，通过注解配置映射）
		User example = new User();
		example.setAge(28);
		example.setName("fuc");
		user = userMapper.findByExample(example);
		System.out.println(user.getName());
		
		//事务
		User u = new User();
		u.setAge(18);
		u.setName("fuc");
		UserService service = SpringBeanFactory.getBean("userService", UserService.class);
		try {
			service.addUser(user);
		} catch (Exception e) {
		}
	}

}