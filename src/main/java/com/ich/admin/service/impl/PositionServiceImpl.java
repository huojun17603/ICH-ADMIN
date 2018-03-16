package com.ich.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ich.admin.dao.EmployeeMapper;
import com.ich.admin.dao.PositionMapper;
import com.ich.admin.dao.PositionMenuResourceMapper;
import com.ich.admin.dao.PositionWindowResourceMapper;
import com.ich.admin.dto.PositionDto;
import com.ich.admin.dto.PositionMenuResourceTree;
import com.ich.admin.dto.PositionWindowResourceDto;
import com.ich.admin.pojo.Employee;
import com.ich.admin.pojo.Position;
import com.ich.admin.pojo.PositionMenuResource;
import com.ich.admin.pojo.PositionWindowResource;
import com.ich.admin.service.PositionService;
import com.ich.core.base.IDUtils;
import com.ich.core.base.ObjectHelper;
import com.ich.core.http.entity.HttpResponse;
import com.ich.core.http.entity.PageView;
import com.ich.module.annotation.Link;
import com.ich.module.dao.MenuResourceMapper;
import com.ich.module.dao.WindowResourceMapper;
import com.ich.module.pojo.MenuResource;
import com.ich.module.pojo.WindowResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PositionServiceImpl implements PositionService {
	
	@Autowired
	private PositionMapper positionDao;
	@Autowired
	private EmployeeMapper employeeDao;
	@Autowired
	private MenuResourceMapper menuResourceDao;
	@Autowired
	private WindowResourceMapper windowResourceDao;
	@Autowired
	private PositionMenuResourceMapper positionMenuResourceDao;
	@Autowired
	private PositionWindowResourceMapper positionWindowResourceDao;
	@Value("${ADMIN_MODULAR}")
	private String ADMIN_MODULAR;

	@Override
	public List<PositionDto> getPositionListByOrgId(String orgId) {
		if(ObjectHelper.isEmpty(orgId))return new ArrayList<PositionDto>();
		return this.positionDao.selectDtoByOrgId(orgId);
	}
	
	@Override
	public List<PositionDto> queryPositionListByOrgId(PageView pageView, String orgId) {
		Map<String,Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("orgId", orgId);
		PageHelper.startPage(pageView.getPage(), pageView.getRows());
		List<PositionDto> list = this.positionDao.queryByOrgId(paramsMap);
		PageInfo<PositionDto> pageInfo = new PageInfo<>(list);
		pageView.setRowCount(pageInfo.getTotal());
		return list;
	}
	
	@Override
	public HttpResponse insertPosition(Position position) {
		Integer result = 0;
		if(ObjectHelper.isEmpty(position.getId())){
			position.setId(IDUtils.createUUId());
			result = this.positionDao.insertPosition(position);//新增
		}else{
			result = this.positionDao.updatePosition(position);//修改
		}
		return result!=0?new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK,position):new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
	}

	@Override
	public HttpResponse deletePosition(String id) {
		List<Employee> list = this.employeeDao.selectByPosId(id);
		Integer result = 0;
		if(ObjectHelper.isEmpty(list)){//保证职位下无员工信息
			result = this.positionDao.deletePosition(id);//删除职位信息
			this.positionMenuResourceDao.clearByPosId(id);//清除与职位关联的菜单权限信息
			this.positionWindowResourceDao.clearByPosId(id);//清除与职位关联的窗口权限信息
		}
		return result!=0?new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK):new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
	}

	@Override
	public HttpResponse saveOrUpdatePositionMenuResource(PositionMenuResource positionMenuResource) {
		Position position = this.positionDao.selectById(positionMenuResource.getPositionId());
		MenuResource menuResource = this.menuResourceDao.selectResourceByCode(positionMenuResource.getMenuCode());
		if(ObjectHelper.isEmpty(position)||ObjectHelper.isEmpty(menuResource)) return null;//验证数据完成性
		if(menuResource.getType().equals(Link.TYPE_LINK))return null;//不能为其配置链接类型
		if(menuResource.getType().equals(Link.TYPE_CATALOG)){//目录类型，只能存在只读可配置
			positionMenuResource.setIsAudit(false);
			positionMenuResource.setIsWrite(false);
			positionMenuResource.setIsEdit(false);
			positionMenuResource.setIsDelete(false);
		}
		Map<String,Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("positionId", positionMenuResource.getPositionId());
		paramsMap.put("menuCode", positionMenuResource.getMenuCode());
		PositionMenuResource resource = this.positionMenuResourceDao.selectBy2PK(paramsMap); //验证权限数据是否存在
		Integer result = 0;
		if(ObjectHelper.isEmpty(resource)){
			result = this.positionMenuResourceDao.insertPositionMenuResource(positionMenuResource);//新增菜单权限数据
		}else{
			result = this.positionMenuResourceDao.updatePositionMenuResource(positionMenuResource);//修改菜单权限数据
		}
		return result!=0?new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK,positionMenuResource):new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
	}

	@Override
	public List<PositionMenuResourceTree> getPositionMenuResourceTree(String id) {
		Map<String,Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("positionId", id);
		paramsMap.put("menuCode", ADMIN_MODULAR);
		paramsMap.put("modular", ADMIN_MODULAR);
		List<PositionMenuResourceTree> list = this.positionMenuResourceDao.selectMenuResourceTree(paramsMap);
		if(ObjectHelper.isNotEmpty(list)){
			for(PositionMenuResourceTree resource : list){
				resource.setPositionId(id);
				pushTree(resource);
			}
			return list;
		}
		return null;
	}
	
	@Override
	public HttpResponse saveOrUpdatePositionWindowResource(PositionWindowResource positionWindowResource) {
		Position position = this.positionDao.selectById(positionWindowResource.getPositionId());
		WindowResource windowResource = this.windowResourceDao.selectResourceByCode(positionWindowResource.getWindowCode());
		if(ObjectHelper.isEmpty(position)||ObjectHelper.isEmpty(windowResource)) return null;//验证数据完成性
		Map<String,Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("positionId", positionWindowResource.getPositionId());
		paramsMap.put("windowResource", positionWindowResource.getWindowCode());
		PositionWindowResource resource = this.positionWindowResourceDao.selectBy2PK(paramsMap); //验证权限数据是否存在
		Integer result = 0;
		if(ObjectHelper.isEmpty(resource)){
			result = this.positionWindowResourceDao.insertPositionWindowResource(positionWindowResource);//新增菜单权限数据
		}else{
			result = this.positionWindowResourceDao.updatePositionWindowResource(positionWindowResource);//修改菜单权限数据
		}
		return result!=0?new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK,positionWindowResource):new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
	}

	@Override
	public List<PositionWindowResourceDto> getWindowPermissionList(String id) {
		return this.positionWindowResourceDao.selectPWRDtoByPositionId(id,ADMIN_MODULAR);
	}
	
	private void pushTree(PositionMenuResourceTree tree){
		Map<String,Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("positionId", tree.getPositionId());
		paramsMap.put("menuCode", tree.getMenuCode());
		paramsMap.put("modular", ADMIN_MODULAR);
		List<PositionMenuResourceTree> list = this.positionMenuResourceDao.selectMenuResourceTree(paramsMap);
		if(ObjectHelper.isNotEmpty(list)){
			for(PositionMenuResourceTree resource : list){
				resource.setPositionId(tree.getPositionId());
				pushTree(resource);
			}
			tree.setChildren(list);
			tree.setState("closed");
		}else{
			tree.setState("open");
		}
	}





}
