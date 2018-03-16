package com.ich.admin.interceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ich.admin.dto.EmployeeMenuDto;
import com.ich.admin.dto.EmployeeWindowDto;
import com.ich.admin.dto.LocalEmployee;
import com.ich.config.pojo.IConfig;
import com.ich.core.base.JsonUtils;
import com.ich.core.base.ObjectHelper;
import com.ich.core.base.ThreadLocalUtil;
import com.ich.core.http.entity.HttpResponse;
import com.ich.core.listener.SystemConfig;
import com.ich.module.annotation.Link;
import com.ich.module.annotation.Window;
import com.ich.module.dao.MenuResourceMapper;
import com.ich.module.pojo.MenuResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class EmployeeInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private MenuResourceMapper menuResourceMapper;
	@Value("${ADMIN_LOGIN_URI}")
	private String ADMIN_LOGIN_URI;
	@Value("${ADMIN_MODULAR}")
	private String ADMIN_MODULAR;
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		//在添加WebSocket后handler对象的类型发生了变化 ？原因未知？
		if(!(handler instanceof HandlerMethod)) return true;//所有非HandlerMethod的都不做拦截
		if(ObjectHelper.isEmpty(ADMIN_MODULAR)) return true;//获取对应的系统标识，如果不存在则不处理
		String sesseionId = request.getSession().getId();
        HandlerMethod handlerMethod = (HandlerMethod)handler;
		Method useMethod = handlerMethod.getMethod();
		//**************** 链接请求 ***********************//
        if(useMethod.isAnnotationPresent(Link.class)){
        	Link link = useMethod.getAnnotation(Link.class);
        	if(!link.parent().equals(ADMIN_MODULAR)) {
				MenuResource presource = menuResourceMapper.selectResourceByCode(link.parent());
				if (!presource.getModular().equals(ADMIN_MODULAR)||ObjectHelper.isEmpty(presource)) return true;//系统标识，不匹配则不处理
			}
			/** 在员工登录后，每次请求都向线程中注入员工信息 */
			LocalEmployee employee = LocalEmployee.sessionDtoMap.get(sesseionId);
			if(ObjectHelper.isNotEmpty(employee)){
				LocalEmployee.thread2SessionMap.put(ThreadLocalUtil.getID(),sesseionId);
				List<Integer> list = LocalEmployee.session2ThreadMap.get(sesseionId);
				list.add(ThreadLocalUtil.getID());
			}
    		/** 以下菜单链接访问皆需求员工登录且拥有权限 */
    		if(ObjectHelper.isNotEmpty(employee)){
    			if(link.level() == Link.LEVEL_NONE) return true; //无需权限
    			if(link.level()==Link.LEVEL_READ) return true; //放开链接的读权限
    			if(employee.getEmployeeMenuMap().containsKey(link.parent())){
    				EmployeeMenuDto employeeMenuDto = employee.getEmployeeMenuMap().get(link.parent());
    				if(link.level()==Link.LEVEL_WRITE && employeeMenuDto.getIsWrite()){
            			return true;
    				}
					if(link.level()==Link.LEVEL_EDIT && employeeMenuDto.getIsEdit()){
            			return true;  					
					}
					if(link.level()==Link.LEVEL_AUDIT && employeeMenuDto.getIsAudit()){
            			return true;
					}
					if(link.level()==Link.LEVEL_DETELE && employeeMenuDto.getIsDelete()){
            			return true;
					}
    			}
    		}
    		return permissionNoPass(request,response);
        }
        /* 窗口请求 */
        if(useMethod.isAnnotationPresent(Window.class)){
        	Window window = useMethod.getAnnotation(Window.class);
			if(!window.modular().equals(ADMIN_MODULAR)) return true;//系统标识，不匹配则不处理
			/** 在员工登录后，每次请求都向线程中注入员工信息 */
			LocalEmployee employee = LocalEmployee.sessionDtoMap.get(sesseionId);
			if(ObjectHelper.isNotEmpty(employee)){
				LocalEmployee.thread2SessionMap.put(ThreadLocalUtil.getID(),sesseionId);
				List<Integer> list = LocalEmployee.session2ThreadMap.get(sesseionId);
				list.add(ThreadLocalUtil.getID());
			}
        	if(ObjectHelper.isNotEmpty(employee)){
        		if(employee.getEmployeeWindowMap().containsKey(window.code())){
        			EmployeeWindowDto employeeWindowDto = employee.getEmployeeWindowMap().get(window.code());
        			if(employeeWindowDto.getIsRead()) return true;
        		}
        	}
        	return permissionNoPass(request,response);
        }
       //**对于没有配置资源的，不做权限上的管理*//
       return true;
		
	}
	/* 处理所有无权限 */
	private Boolean permissionNoPass(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 /** 判断异步、同步 */
        String ajaxHeader = request.getHeader("x-requested-with");
        if(ObjectHelper.isEmpty(ajaxHeader)){
        	//同步
			response.sendRedirect("/"+ ADMIN_LOGIN_URI);
        }else{
        	//异步
            PrintWriter writer = response.getWriter();
			HttpResponse result = new HttpResponse(HttpResponse.HTTP_NO_POWER,HttpResponse.HTTP_MSG_ERROR);
            writer.println(JsonUtils.objectToJson(result));
            writer.close();
        }
		return false;
	}
}
