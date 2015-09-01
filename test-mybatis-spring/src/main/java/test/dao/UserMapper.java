package test.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import test.entity.User;

public interface UserMapper {
	User findByMap(Map<String, Object> map);
	
	@Select("select * from user where name=#{name} and age=#{age}")
	User findByExample(User example);
	
	@Insert("insert into user(name,age) values(#{name},#{age})")
	void addUser(User user);
}