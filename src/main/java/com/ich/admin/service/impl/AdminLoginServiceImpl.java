package com.ich.admin.service.impl;

import com.ich.admin.dao.EmployeeMapper;
import com.ich.admin.dto.LocalEmployee;
import com.ich.admin.pojo.Employee;
import com.ich.admin.service.AdminLoginService;
import com.ich.admin.service.LocalEmployeeService;
import com.ich.core.base.ObjectHelper;
import com.ich.core.http.entity.HttpResponse;
import com.ich.core.security.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private LocalEmployeeService localEmployeeService;

    @Value("${ADMIN_LOGIN_ERROR}")
    private String ADMIN_LOGIN_ERROR;

    @Override
    public HttpResponse executeLogin(String sessionid,String logincode, String loginkey) {
        Employee employee = employeeMapper.selectByCode(logincode);
        if(ObjectHelper.isEmpty(employee)){//验证账号是否存在
            return new HttpResponse(HttpResponse.HTTP_ERROR, ADMIN_LOGIN_ERROR);
        }
        if(!employee.getStatus().equals(1)){//验证账号状态是否正确
            return new HttpResponse(HttpResponse.HTTP_ERROR, ADMIN_LOGIN_ERROR);
        }
        if(!MD5.decryption(employee.getLoginkey(), loginkey + employee.getKeyCode())){//验证账号密码是否正确
            return new HttpResponse(HttpResponse.HTTP_ERROR, ADMIN_LOGIN_ERROR);
        }
        localEmployeeService.createEmployeeDto(sessionid, employee);
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK,employee.getId());
    }

    @Override
    public HttpResponse editKey(String oldkey, String newkey) {
        LocalEmployee localEmployee = localEmployeeService.findLocalEmployee();
        if(ObjectHelper.isEmpty(localEmployee)) return new HttpResponse(HttpResponse.HTTP_INVALID,HttpResponse.HTTP_MSG_ERROR);
        Employee employee = employeeMapper.selectById(localEmployee.getEmployeeId());
        if(MD5.decryption(employee.getLoginkey(), oldkey + employee.getKeyCode())){
            Integer result = this.employeeMapper.updateKey(employee.getId(),MD5.encryption(newkey + employee.getKeyCode()));
            return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
        }
        return new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
    }
}
