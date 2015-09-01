package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.dao.UserMapper;
import test.entity.User;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public void addUser(User user) {
		userMapper.addUser(user);
		throw new RuntimeException();
	}
}
