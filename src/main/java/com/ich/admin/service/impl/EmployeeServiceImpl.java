package com.ich.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ich.admin.dao.EmployeeMapper;
import com.ich.admin.dao.PositionMapper;
import com.ich.admin.dto.EmployeeQueryDto;
import com.ich.admin.pojo.Employee;
import com.ich.admin.pojo.Position;
import com.ich.admin.service.EmployeeService;
import com.ich.admin.service.LocalEmployeeService;
import com.ich.core.base.IDUtils;
import com.ich.core.base.ObjectHelper;
import com.ich.core.base.RandomInt;
import com.ich.core.http.entity.HttpResponse;
import com.ich.core.http.entity.PageView;
import com.ich.core.security.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper employeeDao;
	@Autowired
	private PositionMapper positionDao;
	@Autowired
	private LocalEmployeeService localEmployeeService;
	@Value("${ADMIN_REST_KEY}")
	private String ADMIN_REST_KEY;
	@Override
	public HttpResponse updateEmployeeStatus(String id, Integer status) {
		Integer result = employeeDao.updateStatus(id,status);
		return result>=1? new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK):new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
	}

	@Override
	public List<EmployeeQueryDto> queryEmployeeList(PageView pageView, EmployeeQueryDto employeeQueryDto) {
		String powerSQL = localEmployeeService.findLocalEmployee().getDataPower();
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("dto", employeeQueryDto);
		map.put("power", powerSQL);
		PageHelper.startPage(pageView.getPage(), pageView.getRows());
		List<EmployeeQueryDto> result = employeeDao.queryEmployee(map);
		PageInfo<EmployeeQueryDto> pageInfo = new PageInfo<>(result);
		pageView.setRowCount(pageInfo.getTotal());
		return result;
	}

	@Override
	public Employee getEmployeeById(String id) {
		return this.employeeDao.selectById(id);
	}

	@Override
	public HttpResponse insertQuick(Employee employee) {
		Position position = this.positionDao.selectById(employee.getPositionId());
		if(ObjectHelper.isEmpty(position)){//验证职位信息
			return new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
		}
		if(ObjectHelper.isEmpty(employee.getLogincode())
				||ObjectHelper.isEmpty(employee.getLoginkey())
				||ObjectHelper.isEmpty(employee.getName())){
			return new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
		}
		employee.setId(IDUtils.createUUId());
		employee.setCreateTime(new Date());
		employee.setKeyCode(RandomInt.findNum(6));
		employee.setLoginkey(MD5.encryption(employee.getLoginkey() + employee.getKeyCode()));
		employee.setStatus(Employee.STATUS_DISABLE);
		employee.setOrgId(position.getOrgId());
		this.employeeDao.insertQuick(employee);
		return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
	}

	@Override
	public HttpResponse updateBase(Employee employee) {
		Employee entity = this.employeeDao.selectById(employee.getId());
		if(ObjectHelper.isEmpty(entity)) return new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
		Position position = this.positionDao.selectById(employee.getPositionId());
		if(ObjectHelper.isEmpty(position)){//验证职位信息
			return new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
		}
		entity.setPositionId(employee.getPositionId());
		entity.setOrgId(position.getOrgId());
		entity.setName(employee.getName());
		entity.setPhone(employee.getPhone());
		entity.setEmail(employee.getEmail());
		this.employeeDao.updateBase(entity);
		return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
	}

	@Override
	public HttpResponse updateRestkey(String id) {
		Employee employee = this.employeeDao.selectById(id);
		String loginkey = MD5.encryption( ADMIN_REST_KEY + employee.getKeyCode());
		this.employeeDao.updateKey(id,loginkey);
		return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
	}

}
