package com.ich.admin.pojo;

public class PositionWindowResource {
	
	/** 联合主键：职位ID（外键）*/
	private String positionId;
	/** 联合主键：窗口编码（外键） */
	private String windowCode;
	/** 是否用户读取权限 */
	private Boolean isRead;
	
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getWindowCode() {
		return windowCode;
	}
	public void setWindowCode(String windowCode) {
		this.windowCode = windowCode;
	}
	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

}
