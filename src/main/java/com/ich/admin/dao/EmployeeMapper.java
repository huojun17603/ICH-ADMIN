package com.ich.admin.dao;

import com.ich.admin.dto.EmployeeMenuDto;
import com.ich.admin.dto.EmployeeQueryDto;
import com.ich.admin.dto.EmployeeWindowDto;
import com.ich.admin.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface EmployeeMapper {


	public List<EmployeeQueryDto> queryEmployee(Map<Object, Object> map);



	public EmployeeQueryDto selectEmployeeQueryByAccountId(String accountId);

	public List<EmployeeMenuDto> selectEmployeeMenuList(String id);

	public List<EmployeeWindowDto> selectEmployeeWindowList(String id);

	public List<Employee> selectByPosId(String id);

	public List<Employee> selectByOrgIds(String orgIds);

	public Employee selectById(String id);

	public Employee selectByCode(String logincode);

	public void insertQuick(Employee employee);

	public void updateBase(Employee entity);

	public Integer updateStatus(@Param("id") String id, @Param("status") Integer status);

	public Integer updateKey(@Param("id") String id, @Param("loginkey") String loginkey);


}
