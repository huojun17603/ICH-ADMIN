package com.ich.admin.service;

import com.ich.admin.dto.LocalEmployee;
import com.ich.admin.pojo.Employee;
/**
 * 
 * 名称: LocalEmployeeService.java<br>
 * 描述: 取当前操作员工信息<br>
 * @since  2015-6-24
 * @author 霍俊
 */
public interface LocalEmployeeService {
	/**
	 * 请求当前操作员工信息
	 * @return LocalEmployee 员工信息
	 */
	public LocalEmployee findLocalEmployee();
	/**
	 * 请在员工登录成功后，使用此方法创建静态员工信息
	 * @param sessionid
	 * @param employee 登录员工信息
	 */
	public void createEmployeeDto(String sessionid, Employee employee);
}
