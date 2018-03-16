package com.ich.admin.listener;

import java.util.List;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ich.admin.dto.LocalEmployee;

public class EmployeeSessionListener implements HttpSessionListener {
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	/**
	 * 当用户Session失效后，清理用户的静态信息
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		//Session 销毁方法
		String sesseionId = event.getSession().getId();
		//判断此session中是否存在信息，没有则不用执行
		if(LocalEmployee.sessionDtoMap.containsKey(sesseionId)){
			LocalEmployee dto = LocalEmployee.sessionDtoMap.get(sesseionId);
			//移除静态信息，保证内存使用
			LocalEmployee.sessionDtoMap.remove(sesseionId);
			LocalEmployee.dtoIdMap.remove(dto.getEmployeeId());
			if(LocalEmployee.session2ThreadMap.containsKey(sesseionId)){
				List<Integer> list = LocalEmployee.session2ThreadMap.get(sesseionId);
				for(Integer tlid : list){
					LocalEmployee.thread2SessionMap.remove(tlid);
				}
				LocalEmployee.session2ThreadMap.remove(sesseionId);
			}
		}
	}

}
