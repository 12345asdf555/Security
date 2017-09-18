package com.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.model.Role;
import com.spring.model.User;
import com.spring.service.RoleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	/**
	 * 获取所有用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/AllRole")
	public String AllUser(HttpServletRequest request){
		return "/allRole";
	}
	@RequestMapping("/getAllRole")
	@ResponseBody
	public String getAllRole(HttpServletRequest request){
		List<Role> findAll = roleService.findAll();
		request.setAttribute("roleList", findAll);
		JSONObject json = new JSONObject();
		JSONArray ary = new JSONArray();
		JSONObject obj = new JSONObject();
		try{
			for(Role role:findAll){
				json.put("id", role.getId());
				json.put("roles_name", role.getRoleName());
				json.put("roles_desc", role.getRoleDesc());
				json.put("status", role.getRoleStatus());
				ary.add(json);
			}
		}catch(Exception e){
			e.getMessage();
		}
//		obj.put("total", total);
		obj.put("rows", ary);
		return obj.toString();
//		return "/allUser";
	}
	
	/**
	 * 跳转到添加用户界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddRole")
	public String toAddRole(HttpServletRequest request){
		
		return "/addRole";
	}
	/**
	 * 添加用户并重定向
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/addRole")
	@ResponseBody
	public String addRole(Role role,HttpServletRequest request){
		role.setRoleStatus(Integer.parseInt(request.getParameter("status")));
        String str = request.getParameter("aid");
        String[] s = str.split(",");
        for (int i = 0; i < s.length; i++) {
            Integer id = Integer.parseInt(s[i]);
            role.setAuthorityName(roleService.findByAuthorityId(id));
            roleService.saveAuthority(role);
        }
        roleService.save(role);
		return "redirect:/role/AllRole";
	}
	
	/**
	 *编辑用户
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateRole")
	public String updateRole(Role role,HttpServletRequest request){
		
		
/*		if(roleService.update(role)){
			role = roleService.findById(role.getId());
			request.setAttribute("role", role);*/
			 roleService.update(role);
			return "redirect:/role/getAllRole";
/*		}else{
			return "/error";
		}*/
	}
	/**
	 * 根据id查询单个用户
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRole")
	public String getRole(@RequestParam int id,HttpServletRequest request){
		
		request.setAttribute("role", roleService.findById(new Integer(id)));
		return "/editRole";
	}
	/**
	 * 删除用户
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delRole")
	@ResponseBody
	public String delRole(@RequestParam int id){
		JSONObject obj = new JSONObject();
		try{
			 Role role = roleService.findById(new Integer(id));
			 roleService.delete(new Integer(role.getId()));
			 obj.put("success", true);
		}catch(Exception e){
			obj.put("success", false);
			obj.put("errorMsg", e.getMessage());
		}
		return obj.toString();
	}
	@RequestMapping("/getAllAuthority")
	@ResponseBody
	public String getAllAuthority(HttpServletRequest request){
		
		List<Role> findAllAuthority = roleService.findAllAuthority();
		JSONObject json = new JSONObject();
		JSONArray ary = new JSONArray();
		JSONObject obj = new JSONObject();
		try{
			for(Role role:findAllAuthority){
				json.put("id", role.getId());
				json.put("authorities_name", role.getAuthorityName());
				ary.add(json);
			}
		}catch(Exception e){
			e.getMessage();
		}
		obj.put("rows", ary);
		return obj.toString();
	}
	
	@RequestMapping("/getAuthority")
	@ResponseBody
	public String getAuthority(@RequestParam Integer id,HttpServletRequest request){
		
		List<Role> findAuthority = roleService.findAuthority(new Integer(id));
		JSONObject json = new JSONObject();
		JSONArray ary = new JSONArray();
		JSONObject obj = new JSONObject();
		try{
			for(Role role:findAuthority){
				json.put("id", role.getId());
				json.put("authorities_name", role.getAuthorityName());
				ary.add(json);
			}
		}catch(Exception e){
			e.getMessage();
		}
		obj.put("rows", ary);
		return obj.toString();
	}
}
