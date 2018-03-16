package com.ich.admin.service;

import java.util.List;

import com.ich.admin.dto.EmployeeQueryDto;
import com.ich.admin.pojo.Employee;
import com.ich.core.http.entity.HttpResponse;
import com.ich.core.http.entity.PageView;


public interface EmployeeService {

	/**
	 * 根据ID查询员工信息
	 * @param id
	 * @return 员工信息
	 */
	public Employee getEmployeeById(String id);
	/**
	 * 修改员工状态
	 * @param id 员工ID
	 * @param status 状态值
	 * @return 是否修改成功
	 */
	public HttpResponse updateEmployeeStatus(String id, Integer status);
	/**
	 * 员工分页查询
	 * @param pageView 分页数据
	 * @param employeeQueryDto 查询表单
	 * @return 员工列表
	 */
	public List<EmployeeQueryDto> queryEmployeeList(PageView pageView, EmployeeQueryDto employeeQueryDto);

	/**
	 * 员工信息快速注册及修改
	 * @param employee 员工表单
	 * @return 完成后的员工信息
	 */
	public HttpResponse insertQuick(Employee employee);

	/**
	 * 修改员工基本信息
	 * @param employee 员工表单
	 * @return 是否修改成功
	 */
	public HttpResponse updateBase(Employee employee);

	/**
	 * 重置员工密码
	 * @param id 员工ID
	 * @return 是否修改成功
	 */
	public HttpResponse updateRestkey(String id);


}
