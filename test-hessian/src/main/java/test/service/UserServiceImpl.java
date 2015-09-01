package test.service;

import org.springframework.stereotype.Service;

import test.dto.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Override
	public User addUser(User user) {
		user.setPassword("123");
		return user;
	}

}