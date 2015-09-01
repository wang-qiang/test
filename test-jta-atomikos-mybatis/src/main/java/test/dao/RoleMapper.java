package test.dao;

import test.entity.Role;
import test.filter.Dsa2;
import test.filter.Dsi2;

@Dsa2
public interface RoleMapper extends Dsi2 {
	void addRole(Role role);
}
