package com.ich.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ich.admin.dao.EmployeeMapper;
import com.ich.admin.dao.OrganizationMapper;
import com.ich.admin.dao.PositionMapper;
import com.ich.admin.dto.*;
import com.ich.admin.pojo.Employee;
import com.ich.admin.pojo.Position;
import com.ich.admin.service.LocalEmployeeService;
import com.ich.config.pojo.IConfig;
import com.ich.core.base.ObjectHelper;
import com.ich.core.base.ThreadLocalUtil;
import com.ich.module.annotation.Link;
import com.ich.module.dao.MenuResourceMapper;
import com.ich.module.dao.WindowResourceMapper;
import com.ich.module.pojo.MenuResource;
import com.ich.module.pojo.WindowResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * 名称: LocalEmployeeServiceImpl.java<br>
 * 描述: 当前员工信息实现类<br>
 * @since  2015-6-24
 * @author 霍俊
 */
@Service("localEmployeeService")
public class LocalEmployeeServiceImpl implements LocalEmployeeService {
	
	@Autowired
	private MenuResourceMapper menuResourceDao;
	@Autowired
	private WindowResourceMapper windowResourceDao;
	@Autowired
	private EmployeeMapper employeeDao;
	@Autowired
	private PositionMapper positionDao;
	@Autowired
	private OrganizationMapper organizationDao;

	@Value("${ADMIN_CODE}")
	private String ADMIN_CODE;
	@Value("${ADMIN_MODULAR}")
	private String ADMIN_MODULAR;
	/**
	 * 获取登录用户资源
	 */
	@Override
	public LocalEmployee findLocalEmployee(){
		String currentSession = LocalEmployee.thread2SessionMap.get(ThreadLocalUtil.getID());
		if (ObjectHelper.isNotEmpty(currentSession)) {
			return LocalEmployee.sessionDtoMap.get(currentSession);
		}
		return null;
	}

	/**
	 * 用户登录初始化用户信息
	 */
	@Override
	public void createEmployeeDto(String sessionid, Employee employee){
		
		//配置需要的员工信息
		LocalEmployee localEmployee = new LocalEmployee();
		localEmployee.setEmployeeId(employee.getId());
		localEmployee.setEmployeeCode(employee.getLogincode());
		localEmployee.setEmployeeName(employee.getName());
		
		//首先判断登录用户是否是超管
		List<EmployeeMenuDto> employeeMenuDtoList = new ArrayList<EmployeeMenuDto>();//用户目录信息
		Map<String,EmployeeMenuDto> employeeMenuMap = new HashMap<String, EmployeeMenuDto>();
		List<EmployeeWindowDto> employeeWindowDtoList = new ArrayList<EmployeeWindowDto>();//用户首页信息
		Map<String,EmployeeWindowDto> employeeWindowMap = new HashMap<String, EmployeeWindowDto>();
		
		//如果是超管、查出所有目录、菜单、并装在到制定的list中
		if(ADMIN_CODE.equals(localEmployee.getEmployeeCode())){
			List<MenuResource> resourceList = menuResourceDao.selectAllResource();
			for(MenuResource resource: resourceList){
				if(!resource.getType().equals(Link.TYPE_LINK)&&resource.getModular().equals(ADMIN_MODULAR)){//只取菜单及目录
					EmployeeMenuDto employeeMenuDto = new EmployeeMenuDto();
					employeeMenuDto.setCode(resource.getCode());//资源编码
					employeeMenuDto.setIcon(resource.getIcon());//资源图标
					employeeMenuDto.setName(resource.getName());//设置资源名字
					employeeMenuDto.setParent(resource.getParent());//父类资源code
					employeeMenuDto.setType(resource.getType());//设置资源类型
					employeeMenuDto.setUrl(resource.getUrl());//设置url
					employeeMenuDto.setDoc(resource.getDoc());
					employeeMenuDto.setLevel(resource.getLevel());
					employeeMenuDto.setView(resource.getView());
					employeeMenuDto.setIsAudit(true);
					employeeMenuDto.setIsWrite(true);
					employeeMenuDto.setIsDelete(true);
					employeeMenuDto.setIsEdit(true);
					employeeMenuDto.setIsRead(true);
					employeeMenuDtoList.add(employeeMenuDto);//装在到集合中
					employeeMenuMap.put(employeeMenuDto.getCode(), employeeMenuDto);
				}
			}
			List<WindowResource> homeList = windowResourceDao.selectAllResource();
			for(WindowResource resource: homeList){
				if(resource.getModular().equals(ADMIN_MODULAR)){
					EmployeeWindowDto employeeWindowDto = new EmployeeWindowDto();
					employeeWindowDto.setCode(resource.getCode());//资源编码
					employeeWindowDto.setName(resource.getName());//设置资源名字
					employeeWindowDto.setUrl(resource.getUrl());//设置url
					employeeWindowDto.setInclude(resource.getInclude());
					employeeWindowDto.setIsRead(true);
					employeeWindowDtoList.add(employeeWindowDto);
					employeeWindowMap.put(employeeWindowDto.getCode(), employeeWindowDto);
				}
			}
			localEmployee.setEmployeeMenuDtos(employeeMenuDtoList);
			localEmployee.setEmployeeWindowDtos(employeeWindowDtoList);
			localEmployee.setEmployeeMenuMap(employeeMenuMap);
			localEmployee.setEmployeeWindowMap(employeeWindowMap);
			//超级管理员不用配置数据权限语句
//			localEmployeeDto.setDataPower(employeeDto.getEmployeeId());//配置数据权限语句
		}else{
			//普通员工-配置资源信息
			employeeMenuDtoList = this.employeeDao.selectEmployeeMenuList(employee.getId());
			employeeWindowDtoList = this.employeeDao.selectEmployeeWindowList(employee.getId());
			for(EmployeeWindowDto employeeWindowDto : employeeWindowDtoList){
				employeeWindowMap.put(employeeWindowDto.getCode(), employeeWindowDto);
			}
			for(EmployeeMenuDto employeeMenuDto : employeeMenuDtoList){
				employeeMenuMap.put(employeeMenuDto.getCode(), employeeMenuDto);
			}
			localEmployee.setEmployeeMenuDtos(employeeMenuDtoList);
			localEmployee.setEmployeeWindowDtos(employeeWindowDtoList);
			localEmployee.setEmployeeMenuMap(employeeMenuMap);
			localEmployee.setEmployeeWindowMap(employeeWindowMap);
			Position position = positionDao.selectById(employee.getPositionId());
			if(ObjectHelper.isNotEmpty(position)&&ObjectHelper.isNotEmpty(position.getPermissionOrgId())){
				StringBuffer dataPower = new StringBuffer();
				StringBuffer dataPosiPower = new StringBuffer();
				StringBuffer dataOrgPower = new StringBuffer();
				String orgIds = getOrgIds(position.getPermissionOrgId());
				List<Employee> employees = this.employeeDao.selectByOrgIds(orgIds);
				for(Employee employeeo : employees){
					dataPower.append("'"+employeeo.getId()+"',");
				}
				List<Position> positions = this.positionDao.selectByOrgIds(orgIds);
				for(Position posi : positions){
					dataPosiPower.append("'"+posi.getId()+"',");
				}
				dataOrgPower.append(orgIds);
				dataPower.append("'"+employee.getId()+"'");//添加自身员工ID
				dataPosiPower.append("'"+employee.getPositionId()+"'");//添加自身职位ID
				dataOrgPower.append("'"+employee.getOrgId()+"'");//添加自身机构ID
				////加入本地员工数据///
				localEmployee.setDataPower(dataPower.toString());
				localEmployee.setDataOrgPower(dataOrgPower.toString());
				localEmployee.setDataPosiPower(dataPosiPower.toString());
			}else{
				localEmployee.setDataPower(employee.getId());
			}
				
		}
		
		LocalEmployee.sessionDtoMap.put(sessionid, localEmployee);
		LocalEmployee.dtoIdMap.put(localEmployee.getEmployeeId(), localEmployee);
		LocalEmployee.thread2SessionMap.put(ThreadLocalUtil.getID(),sessionid);
		List<Integer> list = new ArrayList<Integer>();
		list.add(ThreadLocalUtil.getID());
		LocalEmployee.session2ThreadMap.put(sessionid, list);
	}

	private String getOrgIds(String permissionOrgId) {
		StringBuffer orgIds = new StringBuffer("'"+permissionOrgId+"',");
		List<OrganizationDto> list = organizationDao.selectOrganizationListByParentId(permissionOrgId);
		if(ObjectHelper.isNotEmpty(list)){
			for(OrganizationDto dto : list){
				pushTree(orgIds,dto.getId());
				orgIds.append("'"+dto.getId()+"',");
			}
		}
		return orgIds.toString().substring(0, orgIds.toString().length()-1);
	}
	
	private void pushTree(StringBuffer orgIds,String permissionOrgId){
		List<OrganizationDto> result = organizationDao.selectOrganizationListByParentId(permissionOrgId);
		if(ObjectHelper.isNotEmpty(result)){
			for(OrganizationDto dto : result){
				pushTree(orgIds,dto.getId());
				orgIds.append("'"+dto.getId()+"',");
			}
		}
	}
	
}
