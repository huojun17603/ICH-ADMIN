package com.ich.admin.dto;

public class EmployeeQueryDto {
	
	/** ID */
	private String id;
	/** 系统用户唯一编码，登录使用，为员工编码 */
	private String logincode;
	/** 员工名称 */
	private String name;
	/** 联系方式-手机 */
	private String phone;
	/** 联系方式-电子邮箱 */
	private String email;
	/** 状态 */
	private Integer status;
	/** 职位ID */
	private String positionId;
	/** 职位名称 */
	private String positionName;
	/** 部门ID */
	private String orgId;
	/** 部门名称 */
	private String orgName;
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}

	public String getLogincode() {
		return logincode;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


}
