package com.luna.web.dao;

import com.luna.web.entity.DepartmentDO;
import com.luna.web.entity.UserDO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.DateUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Luna@win10
 * @date 2020/3/21 19:40
 */
@Repository
public class UserDAO {

	private static Map<Integer, UserDO> users = null;

	@Autowired
	private DepartmentDAO departmentDao;

	static {
		users = new HashMap<Integer, UserDO>();

		users.put(1001, new UserDO(1001, "E-AA", 1, DateUtils.create("1999", "11", "07").getTime(), new DepartmentDO(101, "D-AA")));
		users.put(1002, new UserDO(1002, "E-BB", 1, DateUtils.create("1999", "11", "07").getTime(), new DepartmentDO(102, "D-BB")));
		users.put(1003, new UserDO(1003, "E-CC", 1, DateUtils.create("1999", "11", "07").getTime(), new DepartmentDO(103, "D-CC")));
		users.put(1004, new UserDO(1004, "E-DD", 1, DateUtils.create("1999", "11", "07").getTime(), new DepartmentDO(104, "D-DD")));
		users.put(1005, new UserDO(1005, "E-EE", 0, DateUtils.create("1999", "11", "07").getTime(), new DepartmentDO(105, "D-EE")));
	}

	private static Integer initId = 1006;

	/**
	 * 查询所有员工
	 *
	 * @return
	 */
	public List<UserDO> getAll() {
		List<UserDO> list = new ArrayList<>();
		list.addAll(users.values());
		return list;
	}

	public void insert(UserDO userDO) {
		if (userDO == null) {
			userDO.setId(initId++);
		}
		int id = userDO.getDepartment().getId();
		userDO.setDepartment(departmentDao.getDepartmentDO(id));
		users.put(initId, userDO);
		System.out.println("存入数据库");
	}

	public UserDO get(Integer id) {
		return users.get(id);
	}

	public void delete(Integer id) {
		users.remove(id);
	}

}
