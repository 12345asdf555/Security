package com.spring.service;

import java.util.List;

import com.spring.model.Role;
import com.spring.model.User;


public interface RoleService {
	void save(Role role);
	void saveAuthority(Role role);
	boolean update(Role role);
	boolean delete(int id);
	Role findById(int id);
	String findByAuthorityId(Integer id);
	List<Role> findAll();
	List<Role> findAllAuthority();
	List<Role> findAuthority(Integer id);
}
