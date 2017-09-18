package com.spring.mapper;

import java.util.List;

import com.spring.model.User;

public interface UserMapper {
	void save(User user);
	void saveRole(User user);
	boolean update(User user);
	boolean delete(int id);
	User findById(Integer id);
	String findByRoleId(Integer id);
	List<User> findAll();
	List<User> findRole(Integer id);
	List<User> findAllRole();
}
