package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.dao.RoleMapper;
import test.dao.UserMapper;
import test.entity.Role;
import test.entity.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	public void addUser(User user, Role role) {
		userMapper.addUser(user);
		roleMapper.addRole(role);
	}
}
