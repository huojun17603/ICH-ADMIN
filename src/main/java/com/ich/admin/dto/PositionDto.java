package com.ich.admin.dto;


import com.ich.admin.pojo.Position;

public class PositionDto extends Position {

	/** 机构名称 */
	private String orgName;
	/** 可读取的数据权限组织结构名称 */
	private String permissionOrgName;
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getPermissionOrgName() {
		return permissionOrgName;
	}
	public void setPermissionOrgName(String permissionOrgName) {
		this.permissionOrgName = permissionOrgName;
	}
	
}
