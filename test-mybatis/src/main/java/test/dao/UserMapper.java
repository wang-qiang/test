package test.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import test.entity.Article;
import test.entity.User;

public interface UserMapper {
	User findByMap(Map<String, Object> map);
	
	@Select("select * from user where name=#{name} and age=#{age}")
	User findByExample(User example);
	
	@Select("select * from user")
	List<User> findAll();
	
	@Insert("insert into user(name,age) values(#{name},#{age})")
	void addUser(User user);
	
	List<Article> getArticles(Integer userId);
}