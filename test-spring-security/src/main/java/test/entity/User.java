package test.entity;

/** 用户 */
public class User {

	/** 用户名 */
	private String username;

	/** 密码 */
	private String password;

	/** 访问权限，对应一组角色（一个用户有多个角色） */
	private Integer access;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAccess() {
		return access;
	}
	public void setAccess(Integer access) {
		this.access = access;
	}

}