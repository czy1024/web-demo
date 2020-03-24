package com.luna.web.user.controller;

import com.luna.web.user.dao.DepartmentDAO;
import com.luna.web.user.dao.UserDAO;
import com.luna.web.user.entity.DepartmentDO;
import com.luna.web.user.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Luna@win10
 * @date 2020/3/21 19:33
 */
@Controller
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private DepartmentDAO departmentDAO;

	/**
	 * 查询所有用户
	 *
	 * @return
	 */
	@GetMapping("/users")
	public String list(ModelMap map) {
		List<UserDO> all = userDAO.getAll();
		map.put("users", all);
		return "user/users";
	}

	/**
	 * 添加用户页面
	 *
	 * @param map
	 * @return
	 */
	@GetMapping("user")
	public String toAddPage(ModelMap map) {
		List<DepartmentDO> departments = departmentDAO.getDepartmentDOs();
		map.addAttribute("depts", departments);
		return "user/add";
	}

	/**
	 * 添加用户请求
	 * SpringMVC 自动将属性绑定  入参名和对象属性名一样
	 *
	 * @return
	 */
	@PostMapping("/user")
	public String addUser(UserDO userDO) {
		System.out.println("提交的用户信息" + userDO);
		userDAO.insert(userDO);
		//　TODO　添加完成后来到用户列表页面 redirect: 重定向地址  forward:转发地址
		return "redirect:/users";
	}

	/**
	 * 查出当前用户页面回显
	 *
	 * @return
	 */
	@GetMapping("/user/{id}")
	public String toUpdatePage(@PathVariable("id") Integer id, ModelMap map) {
		UserDO userDO = userDAO.get(id);
		map.put("user", userDO);
		List<DepartmentDO> departments = departmentDAO.getDepartmentDOs();
		map.addAttribute("depts", departments);
		return "user/update";
	}

	/**
	 * 用户修改
	 *
	 * @param userDO
	 * @return
	 */
	@PutMapping("/user")
	public String updateUser(UserDO userDO) {
		System.out.println("修改对象:" + userDO);
		return "redirect:/users";
	}

	/**
	 * 用户删除
	 * @param id
	 * @return
	 */
	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable("id") Integer id) {
		userDAO.delete(id);
		return "redirect:/users";
	}

}
