package test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import test.dao.UserMapper;
import test.entity.Article;
import test.entity.User;

public class Test {
	
	public static void main(String[] args) throws IOException {
		Reader reader = Resources.getResourceAsReader("configuration.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sessionFactory.openSession();
		
		//只配xml，不写接口（通过命名空间调用，通过xml配置映射）
		User user = sqlSession.selectOne("test.dao.UserMapper.findById", 1);
		System.out.println(user.getName());
		
		//既配xml，又写接口（通过接口调用，通过xml配置映射）
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Map<String,Object> map = new HashMap<>();
		map.put("name", "fuc");
		map.put("age", "28");
		user = userMapper.findByMap(map);
		System.out.println(user.getName());
		
		//不配xml，只写接口（通过接口调用，通过注解配置映射）
		User example = new User();
		example.setAge(28);
		example.setName("fuc");
		user = userMapper.findByExample(example);
		System.out.println(user.getName());
		
		//批量查询
		List<User> us = userMapper.findAll();
		System.out.println(us.size());
		for (User u : us) {
			System.out.println(u.getName());
		}
		
		//新增
		User u = new User();
		u.setAge(18);
		u.setName("fuc");
		userMapper.addUser(u);
		sqlSession.commit();
		
		//关联映射
		List<Article> as = userMapper.getArticles(1);
		for (Article a : as) {
			System.out.println(a.getUser().getName());
		}
	}

}