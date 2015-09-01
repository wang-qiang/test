package test.dao;

import test.entity.User;
import test.filter.Dsa1;
import test.filter.Dsi1;

@Dsa1
public interface UserMapper extends Dsi1 {
	void addUser(User user);
}