package com.ich.admin.service;

import com.ich.admin.dto.OrganizationDto;
import com.ich.admin.dto.OrganizationTreeDto;
import com.ich.admin.pojo.Organization;
import com.ich.core.http.entity.HttpResponse;

import java.util.List;

public interface OrganizationService {
	
	/**
	 * 根据提供的父机构ID，获取其下的一级子机构列表
	 * @param parentId 父机构ID【可为空】
	 * @return 子机构列表
	 */
	public List<OrganizationDto> getOrganizationList(String parentId);
	
	/**
	 * 提供组织机构的整个树形列表
	 * @return 机构树
	 */
	public List<OrganizationTreeDto> getOrganizationTree();
	
	/**
	 * 新增或修改组织机构
	 * @param organization 组织机构表单
	 * @return 组织机构信息
	 */
	public HttpResponse insertOrganization(Organization organization);
	
	/**
	 * 删除组织机构信息，保证其下没有职位信息
	 * @param id 信息ID
	 * @return 是否删除成功
	 */
	public HttpResponse deleteOrganization(String id);
	/**
	 * 根据ID查询组织机构信息
	 * @param orgId ID
	 * @return
	 */
	public Organization getOrganizationById(String orgId);

	

}
