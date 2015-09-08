package test.dao;

import java.util.ArrayList;
import java.util.List;

import test.entity.User;

/** 用户DAO */
public class UserDao {
	
	/** 查询用户 */
	public User getDatabase(String username) {
		for (User dbUser : users) {
			if (dbUser.getUsername().equals(username) == true) {
				return dbUser;
			}
		}
		throw new RuntimeException("用户不存在!");
	}
	
	
	
	//*******************************私有*******************************
	
	/** 模拟用户表 */
	private static final List<User> users;
	
	/** 用户表初始化 */
	static {
		users = new ArrayList<>();
		
		//admin用户
		User user = new User();
		user.setUsername("admin");
		user.setPassword("21232f297a57a5a743894a0e4a801fc3");	//"admin"经过MD5加密后
		user.setAccess(1);
		users.add(user);

		//user用户
		user = new User();
		user.setUsername("user");
		user.setPassword("ee11cbb19052e40b07aac0ca060c23ee");	//"user"经过MD5加密后
		user.setAccess(2);
		users.add(user);
	}
	
}