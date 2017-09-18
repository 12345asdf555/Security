package com.spring.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mapper.RoleMapper;
import com.spring.model.Role;
import com.spring.model.User;
import com.spring.service.RoleService;




@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleMapper mapper;

	public boolean delete(int id) {
		
		return mapper.delete(id);
	}

	public List<Role> findAll() {
		List<Role> findAllList = mapper.findAll();
		return findAllList;
	}

	public Role findById(int id) {

		Role role = mapper.findById(id);
		
		return role;
	}
	
	public String findByAuthorityId(Integer id) {
		
		return mapper.findByAuthorityId(id);
	}

	public void save(Role Role) {

		mapper.save(Role);
	}
	
	public void saveAuthority(Role Role) {

		mapper.saveAuthority(Role);
	}

	public boolean update(Role role) {

		return mapper.update(role);
	}
	
	public List<Role> findAllAuthority() {
		List<Role> findAllAuthorityList = mapper.findAllAuthority();
		return findAllAuthorityList;
	}
	
	public List<Role> findAuthority(Integer id) {
		return mapper.findAuthority(id);
	}
	

}
