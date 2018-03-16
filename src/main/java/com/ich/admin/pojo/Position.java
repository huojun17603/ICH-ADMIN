package com.ich.admin.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * 职位信息表
 * @author 霍俊
 *
 */
public class Position {
	
	/** ID */
	private String id;
	/** 职位名称 */
	private String name;
	/** 外键：组织机构ID */
	private String orgId;
	/** 可读取的数据权限组织结构 */
	private String permissionOrgId;
	
//	@Override
//	public Map<String, Object> verificationMsg() {
//		Map<String,Object> model = new HashMap<String, Object>();
//		if(ObjectHelper.isEmpty(name)&&name.length()<32){
//			model.put(HttpResponse.RETURN_STATUS, HttpResponse.HTTP_STATUS_CODE_ERROR);
//			model.put(HttpResponse.RETURN_MSG,"职位名称不可为空，且长度不超过32位！");
//		}else if(ObjectHelper.isEmpty(orgId)){
//			model.put(HttpResponse.RETURN_STATUS, HttpResponse.HTTP_STATUS_CODE_ERROR);
//			model.put(HttpResponse.RETURN_MSG,"组织机构不可为空！");
//		}else{
//			model.put(HttpResponse.RETURN_STATUS, HttpResponse.HTTP_STATUS_CODE_OK);
//		}
//		return model;
//	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getPermissionOrgId() {
		return permissionOrgId;
	}
	public void setPermissionOrgId(String permissionOrgId) {
		this.permissionOrgId = permissionOrgId;
	}
	

}
