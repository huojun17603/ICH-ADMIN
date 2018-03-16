package com.ich.admin.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 员工信息表<br/>
 * 基本功能：员工列表、员工快速管理
 * @author 霍俊
 */
public class Employee {

	/** 员工禁用状态 */
	public static Integer STATUS_DISABLE = 0;
	/** 员工启用状态 */
	public static Integer STATUS_ABLE = 1;
	/** ID */
	private String id;
	/** 外键：职位信息 */
	private String positionId;
	/** 外键：机构信息 */
	private String orgId;
	/** 系统用户唯一编码，登录使用，为员工编码 */
	private String logincode;
	/** 密码 */
	private String loginkey;
	/** 密码混淆码 */
	private String keyCode;
	/** 员工名称 */
	private String name;
	/** 绑定手机：主要用于员工账号的管理-也可用于当做联系方式【唯一】 */
	private String phone;
	/** 绑定电子邮箱：主要用于员工账号的管理-也可用于当做联系方式【唯一】  */
	private String email;
	/** 状态：控制此员工是否可以登录平台；0-禁用；1-启用 */
	private Integer status;
	/** 注册时间 */
	private Date createTime;
	
//	@Override
//	public Map<String, Object> verificationMsg() {
//		Map<String,Object> model = new HashMap<String, Object>();
//		model.put(HttpResponse.RETURN_STATUS, HttpResponse.HTTP_STATUS_CODE_ERROR);
//		if(ObjectHelper.isEmpty(name)||name.length()>16){
//			model.put(HttpResponse.RETURN_MSG,"员工名称不可为空，且长度不可大于16！");
//		}else if(ObjectHelper.isEmpty(positionId)){
//			model.put(HttpResponse.RETURN_MSG,"员工职位信息不可为空！");
//		}else if(ObjectHelper.isEmpty(accountId)){
//			model.put(HttpResponse.RETURN_MSG,"员工登录账号不可为空！");
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

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getLogincode() {
		return logincode;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}

	public String getLoginkey() {
		return loginkey;
	}

	public void setLoginkey(String loginkey) {
		this.loginkey = loginkey;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
