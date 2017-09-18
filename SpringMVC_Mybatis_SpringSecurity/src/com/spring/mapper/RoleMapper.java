package com.spring.mapper;


	import java.util.List;

import com.spring.model.Role;

	public interface RoleMapper {
		void saveAuthority(Role role);
		void save(Role role);
		boolean update(Role role);
		boolean delete(int id);
		Role findById(int id);
		String findByAuthorityId(Integer id);
		List<Role> findAll();
		List<Role> findAllAuthority();
		List<Role> findAuthority(Integer id);
	}
