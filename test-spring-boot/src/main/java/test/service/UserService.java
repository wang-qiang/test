package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.dao.UserMapper;
import test.entity.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Transactional
	public void addUser() {
		userMapper.addUser(new User("fc", 29));
		userMapper.addUser(new User("jm", 26));
	}
}
