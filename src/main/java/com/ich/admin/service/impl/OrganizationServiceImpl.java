package com.ich.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ich.admin.dao.OrganizationMapper;
import com.ich.admin.dao.PositionMapper;
import com.ich.admin.dto.OrganizationDto;
import com.ich.admin.dto.OrganizationTreeDto;
import com.ich.admin.dto.PositionDto;
import com.ich.admin.pojo.Organization;
import com.ich.admin.service.OrganizationService;
import com.ich.core.base.IDUtils;
import com.ich.core.base.ObjectHelper;
import com.ich.core.http.entity.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationMapper organizationDao;
	@Autowired
	private PositionMapper positionDao;

	@Override
	public List<OrganizationDto> getOrganizationList(String id) {
		if(null==id) id= Organization.ROOT;//保证父ID值得存在
		return organizationDao.selectOrganizationListByParentId(id);
	}

	@Override
	public List<OrganizationTreeDto> getOrganizationTree() {
		List<OrganizationDto> result = organizationDao.selectOrganizationListByParentId("ROOT");
		if(ObjectHelper.isNotEmpty(result)){
			List<OrganizationTreeDto> list = new ArrayList<OrganizationTreeDto>();
			for(OrganizationDto dto : result){
				OrganizationTreeDto organizationTreeDto = new OrganizationTreeDto(dto);
				pushTree(organizationTreeDto);
				list.add(organizationTreeDto);
			}
			return list;
		}
		return null;
	}

	@Override
	public HttpResponse insertOrganization(Organization organization) {
		if(ObjectHelper.isEmpty(organization.getParentId())) organization.setParentId("ROOT");//保证父ID值得存在
		if(ObjectHelper.isEmpty(organization.getId())){//新增
			organization.setId(IDUtils.createUUId());
			organizationDao.insertOrganization(organization);
		}else{//修改
			organizationDao.updateOrganization(organization);
		}
		return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK,organization);
	}

	@Override
	public HttpResponse deleteOrganization(String id) {
		List<OrganizationDto> list = this.organizationDao.selectOrganizationListByParentId(id);//保证其下没有子级机构
		if(ObjectHelper.isNotEmpty(list)) new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
		List<PositionDto> position = this.positionDao.selectDtoByOrgId(id);
		if(ObjectHelper.isEmpty(position)){//保证其下没有职位信息
			this.organizationDao.delete(id);
			return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
		}
		return new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
	}

	@Override
	public Organization getOrganizationById(String orgId) {
		return this.organizationDao.selectById(orgId);
	}

	private void pushTree(OrganizationTreeDto tree){
		List<OrganizationDto> result = organizationDao.selectOrganizationListByParentId(tree.getId());
		if(ObjectHelper.isNotEmpty(result)){
			List<OrganizationTreeDto> list = new ArrayList<OrganizationTreeDto>();
			for(OrganizationDto dto : result){
				OrganizationTreeDto organizationTreeDto = new OrganizationTreeDto(dto);
				pushTree(organizationTreeDto);
				list.add(organizationTreeDto);
			}
			tree.setChildren(list);
		}else{
			tree.setState("open");
		}
	}



}
