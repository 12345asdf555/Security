package com.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.model.Role;
import com.spring.model.User;
import com.spring.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/user",produces = { "text/json;charset=UTF-8" })
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 获取所有用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/AllUser")
	public String AllUser(HttpServletRequest request){
		return "/allUser";
	}
	@RequestMapping("/getAllUser")
	@ResponseBody
	public String getAllUser(HttpServletRequest request){
		List<User> findAll = userService.findAll();
		request.setAttribute("userList", findAll);
		JSONObject json = new JSONObject();
		JSONArray ary = new JSONArray();
		JSONObject obj = new JSONObject();
		try{
			for(User user:findAll){
				json.put("id", user.getId());
				json.put("users_name", user.getUserName());
				json.put("users_Login_Name", user.getUserLoginName());
				json.put("users_phone", user.getUserPhone());
				json.put("users_email",user.getUserEmail());
				json.put("users_insframework", user.getUserInsframework());
				json.put("users_position", user.getUserPosition());
				json.put("status", user.getStatus());
				ary.add(json);
			}
		}catch(Exception e){
			e.getMessage();
		}
		obj.put("rows", ary);
		return obj.toString();
	}
	
	/**
	 * 跳转到添加用户界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddUser")
	public String toAddUser(HttpServletRequest request){
		
		return "/addUser";
	}
	/**
	 * 添加用户并重定向
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/addUser")
	@ResponseBody
	public String addUser(User user,HttpServletRequest request){
        user.setUserPosition(request.getParameter("userPosition"));
        user.setStatus(Integer.parseInt(request.getParameter("status")));
 /*       Integer id = request.getParameter("rid")!= null && !request.getParameter("rid").equals("")?Integer.parseInt(request.getParameter("rid")) : null;
        String[] str = request.getParameterValues("rid")!= null && !request.getParameterValues("rid").equals("")?request.getParameterValues("rid") : null;*/    
/*         String str = request.getParameter("rid");
         JSONArray jsonArray = JSONArray.fromObject(str);
         Map<String,String> map = null;
            if(jsonArray.size()>0){
            	  for(int i=0;i<jsonArray.size();i++){
            	    JSONObject job = jsonArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象 
            	    map.put("id", (String) job.get("id"));
            	    Integer id = Integer.parseInt(map.get("id"));
                    user.setRoleName(userService.findByRoleId(id));
                    userService.saveRole(user);
            	  }
            }*/
/*        for(int i=0;i<str.length;i++)
        {
        Integer id = Integer.parseInt(str[i]);
        user.setRoleName(userService.findByRoleId(id));
        userService.saveRole(user);
        }*/
        String str = request.getParameter("rid");
        String[] s = str.split(",");
        for (int i = 0; i < s.length; i++) {
            Integer id = Integer.parseInt(s[i]);
            user.setRoleName(userService.findByRoleId(id));
            userService.saveRole(user);
        }
		userService.save(user);
		return "redirect:/user/AllUser";
	}
	
	/**
	 *编辑用户
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(User user,HttpServletRequest request){
		
/*		if(userService.update(user)){
			user = userService.findById(user.getId());*/
/*          user.setUserPosition(request.getParameter("userPosition"));
            user.setStatus(Integer.parseInt(request.getParameter("status")));*/
		    userService.update(user);
			return "redirect:/user/AllUser";
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
	@RequestMapping("/getUser")
	public String getUser(@RequestParam int id,HttpServletRequest request){
		request.setAttribute("user", userService.findById(new Integer(id)));
		return "/editUser";
	}
	/**
	 * 删除用户
	 * @param id
	 * @param request
	 * @param response
	 * @return 
	 */
	@RequestMapping("/delUser")
	@ResponseBody
	public String delUser(@RequestParam int id){

			JSONObject obj = new JSONObject();
			try{
				 User user = userService.findById(new Integer(id));
				 userService.delete(new Integer(user.getId()));
				 obj.put("success", true);
			}catch(Exception e){
				obj.put("success", false);
				obj.put("errorMsg", e.getMessage());
			}
			return obj.toString();
	}
	@RequestMapping("/getAllRole")
	@ResponseBody
	public String getAllRole(HttpServletRequest request){
		
		List<User> findAllRole = userService.findAllRole();
		JSONObject json = new JSONObject();
		JSONArray ary = new JSONArray();
		JSONObject obj = new JSONObject();
		try{
			for(User user:findAllRole){
				json.put("id", user.getId());
				json.put("roles_name", user.getRoleName());
				ary.add(json);
			}
		}catch(Exception e){
			e.getMessage();
		}
		obj.put("rows", ary);
		return obj.toString();
	}
	@RequestMapping("/getRole")
	@ResponseBody
	public String getRole(@RequestParam Integer id,HttpServletRequest request){
		
		List<User> findRole = userService.findRole(new Integer(id));
		JSONObject json = new JSONObject();
		JSONArray ary = new JSONArray();
		JSONObject obj = new JSONObject();
		try{
			for(User user:findRole){
				json.put("id", user.getId());
				json.put("roles_name", user.getRoleName());
				ary.add(json);
			}
		}catch(Exception e){
			e.getMessage();
		}
		obj.put("rows", ary);
		return obj.toString();
	}
}
