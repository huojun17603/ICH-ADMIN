package com.ich.admin.service;

import com.ich.admin.dto.PositionDto;
import com.ich.admin.dto.PositionMenuResourceTree;
import com.ich.admin.dto.PositionWindowResourceDto;
import com.ich.admin.pojo.Position;
import com.ich.admin.pojo.PositionMenuResource;
import com.ich.admin.pojo.PositionWindowResource;
import com.ich.core.http.entity.HttpResponse;
import com.ich.core.http.entity.PageView;

import java.util.List;


public interface PositionService {
	
	/**
	 * 根据组织机构ID，查询其下职位信息列表
	 * 如果组织机构ID为空则返回为空
	 * @param orgId 组织机构ID
	 * @return 职位信息列表
	 */
	public List<PositionDto> getPositionListByOrgId(String orgId);
	
	public List<PositionDto> queryPositionListByOrgId(PageView pageView, String orgId);
	
	/**
	 * 新增、修改职位信息
	 * @param position 职位信息表单
	 * @return 职位信息
	 */
	public HttpResponse insertPosition(Position position);
	
	/**
	 * 删除职位信息，并同时删除其权限信息
	 * 条件：其下没有员工
	 * @param id 职位ID
	 * @return 是否成功
	 */
	public HttpResponse deletePosition(String id);

	public HttpResponse saveOrUpdatePositionMenuResource(PositionMenuResource positionMenuResource);

	public List<PositionMenuResourceTree> getPositionMenuResourceTree(String id);

	public HttpResponse saveOrUpdatePositionWindowResource(PositionWindowResource positionWindowResource);

	public List<PositionWindowResourceDto> getWindowPermissionList(String id);

	

}
