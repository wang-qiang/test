package test.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import test.dao.UserDao;
import test.entity.User;

/** 用户Service */
@Service
public class UserService implements UserDetailsService {

	private UserDao userDAO = new UserDao();

	/** 根据用户名查询用户 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			//查询用户
			User dbUser = userDAO.getDatabase(username);
			
			//封装用户信息
			UserDetails user = new org.springframework.security.core.userdetails.User(
					dbUser.getUsername()					//用户名
					, dbUser.getPassword().toLowerCase()	//密码小写
					, true									//用户启用
					, true									//账户未过期
					, true									//证书未过期
					, true									//账户未锁定
					, getAuthorities(dbUser.getAccess()));	//用户拥有的角色
			return user;
		} catch (Exception e) {
			throw new UsernameNotFoundException("检索用户错误");
		}
	}

	/** 获得访问角色权限 */
	private Collection<GrantedAuthority> getAuthorities(Integer access) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));	//所有的用户默认拥有ROLE_USER权限
		if (access.compareTo(1) == 0) {							//如果参数access为1，则拥有ROLE_ADMIN权限
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return authList;
	}
}