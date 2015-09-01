package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.entity.User;
import test.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Transactional
	@RequestMapping("add")
	public String add() {
		userService.addUser(new User("fuc", 29));
		userService.addUser(new User("jm", 26));
		return "success";
	}
	
}
