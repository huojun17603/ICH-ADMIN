package com.ich.admin.dto;

public class EmployeeWindowDto {
	
	/** 唯一编码  由注解<Window>提供*/
	private String code;
	/** 窗口名称 */
	private String name;
	/** 窗口引入的JSP */
	private String include;
	/** 窗口的数据信息来源URL */
	private String url;
	/** 是否用户读取权限 */
	private Boolean isRead;

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
