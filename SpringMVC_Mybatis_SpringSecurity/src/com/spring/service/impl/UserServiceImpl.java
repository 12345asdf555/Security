package com.spring.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mapper.UserMapper;
import com.spring.model.User;
import com.spring.service.UserService;



@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper mapper;

	public boolean delete(int id) {
		
		return mapper.delete(id);
	}

	public List<User> findAll() {
		List<User> findAllList = mapper.findAll();
		return findAllList;
	}
	
	public List<User> findAllRole() {
		List<User> findAllRoleList = mapper.findAllRole();
		return findAllRoleList;
	}

	public User findById(Integer id) {

		User user = mapper.findById(id);
		
		return user;
	}
	
	public String findByRoleId(Integer id) {
		
		return mapper.findByRoleId(id);
	}

	public List<User> findRole(Integer id) {
		return mapper.findRole(id);
	}
	
	public void save(User user) {

		mapper.save(user);
	}
	
	public void saveRole(User user) {

		mapper.saveRole(user);
	}

	public boolean update(User user) {

		return mapper.update(user);
	}
	
	

}
