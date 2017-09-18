package com.spring.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mapper.AuthorityMapper;
import com.spring.mapper.UserMapper;
import com.spring.model.Authority;
import com.spring.model.User;
import com.spring.service.AuthorityService;

@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class AuthorityServiceImpl implements AuthorityService {
	
	@Resource
	private AuthorityMapper mapper;

	public boolean delete(int id) {
		
		return mapper.delete(id);
	}

	public List<Authority> findAll() {
		List<Authority> findAllList = mapper.findAll();
		return findAllList;
	}
	
	public List<Authority> findAllResource() {
		List<Authority> findAllResourceList = mapper.findAllResource();
		return findAllResourceList;
	}
	
	public List<Authority> findResource(Integer id) {
		
		return mapper.findResource(id);
	}

	public Authority findById(Integer id) {

		Authority authority = mapper.findById(id);
		
		return authority;
	}

	public String findByResourceId(Integer id) {
		
		return mapper.findByResourceId(id);
	}
	
	public void save(Authority authority) {

		mapper.save(authority);
	}
	
	public void saveResource(Authority authority) {

		mapper.saveResource(authority);
	}

	public boolean update(Authority authority) {

		return mapper.update(authority);
	}

}
