package com.ich.admin.dao;

import com.ich.admin.dto.OrganizationDto;
import com.ich.admin.pojo.Organization;

import java.util.List;


public interface OrganizationMapper {

	public Integer insertOrganization(Organization organization);

	public Integer updateOrganization(Organization organization);

	public List<OrganizationDto> selectOrganizationListByParentId(String parentId);

	public void delete(String id);

	public Organization selectById(String id);

}
