package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.model.Authority;
import com.spring.model.User;
import com.spring.service.AuthorityService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/authority",produces = { "text/json;charset=UTF-8" })
public class AuthorityController {
	@Autowired
	private AuthorityService authorityService;
	
	/**
	 * 获取所有用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/AllAuthority")
	public String AllAuthority(HttpServletRequest request){
		return "/allAuthority";
	}
	@RequestMapping("/getAllAuthority")
	@ResponseBody
	public String getAllAuthority(HttpServletRequest request){
		List<Authority> findAll = authorityService.findAll();
		request.setAttribute("authorityList", findAll);
		JSONObject json = new JSONObject();
		JSONArray ary = new JSONArray();
		JSONObject obj = new JSONObject();
		try{
			for(Authority authority:findAll){
				json.put("id", authority.getId());
				json.put("authorities_name", authority.getAuthorityName());
				json.put("authorities_desc", authority.getAuthorityDesc());
				json.put("status", authority.getStatus());
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
	@RequestMapping("/toAddAuthority")
	public String toAddUser(HttpServletRequest request){
		
		return "/addAuthority";
	}
	/**
	 * 添加用户并重定向
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/addAuthority")
	@ResponseBody
	public String addAuthority(Authority authority,HttpServletRequest request){
		authority.setStatus(Integer.parseInt(request.getParameter("status")));
        String str = request.getParameter("rid");
        String[] s = str.split(",");
        for (int i = 0; i < s.length; i++) {
            Integer id = Integer.parseInt(s[i]);
            authority.setResourceName(authorityService.findByResourceId(id));
            authorityService.saveResource(authority);
        }
		authorityService.save(authority);
		return "redirect:/authority/AllAuthority";
	}

	/**
	 *编辑用户
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateAuthority")
	@ResponseBody
	public String updateUser(Authority authority,HttpServletRequest request){
		
/*		if(userService.update(user)){
			user = userService.findById(user.getId());*/
/*		    authority.setUserPosition(request.getParameter("userPosition"));
		    authority.setStatus(Integer.parseInt(request.getParameter("status")));*/
		    authorityService.update(authority);
			return "redirect:/authority/AllAuthority";
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
	@RequestMapping("/getAuthority")
	public String getAuthority(@RequestParam int id,HttpServletRequest request){
		request.setAttribute("authority", authorityService.findById(new Integer(id)));
		return "/editAuthority";
	}
	/**
	 * 删除用户
	 * @param id
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping("/delAuthority")
	@ResponseBody
	public String delUser(@RequestParam int id){
		JSONObject obj = new JSONObject();
		try{
			Authority authority = authorityService.findById(new Integer(id));
			authorityService.delete(new Integer(authority.getId()));
			 obj.put("success", true);
		}catch(Exception e){
			obj.put("success", false);
			obj.put("errorMsg", e.getMessage());
		}
		return obj.toString();
	}
	@RequestMapping("/getAllResource")
	@ResponseBody
	public String getAllResource(HttpServletRequest request){
		
		List<Authority> findAllResource = authorityService.findAllResource();
		JSONObject json = new JSONObject();
		JSONArray ary = new JSONArray();
		JSONObject obj = new JSONObject();
		try{
			for(Authority authority:findAllResource){
				json.put("id", authority.getId());
				json.put("resources_name", authority.getResourceName());
				ary.add(json);
			}
		}catch(Exception e){
			e.getMessage();
		}
		obj.put("rows", ary);
		return obj.toString();
	}
	
	@RequestMapping("/getResource")
	@ResponseBody
	public String getResource(@RequestParam Integer id,HttpServletRequest request){
		
		List<Authority> findResource = authorityService.findResource(new Integer(id));
		JSONObject json = new JSONObject();
		JSONArray ary = new JSONArray();
		JSONObject obj = new JSONObject();
		try{
			for(Authority authority:findResource){
				json.put("id", authority.getId());
				json.put("resources_name", authority.getResourceName());
				ary.add(json);
			}
		}catch(Exception e){
			e.getMessage();
		}
		obj.put("rows", ary);
		return obj.toString();
	}
}
