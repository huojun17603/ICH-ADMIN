package com.ich.admin.pojo;

public class PositionMenuResource {
	
	/** 联合主键：职位ID（外键）*/
	private String positionId;
	/** 联合主键：菜单编码（外键） */
	private String menuCode;
	
	/** 是否用户读取权限 */
	private Boolean isRead;
	/** 是否用户新增权限 */
	private Boolean isWrite;
	/** 是否用户修改权限 */
	private Boolean isEdit;
	/** 是否用户审核权限 */
	private Boolean isAudit;
	/** 是否用户删除权限 */
	private Boolean isDelete;
	
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	public Boolean getIsWrite() {
		return isWrite;
	}
	public void setIsWrite(Boolean isWrite) {
		this.isWrite = isWrite;
	}
	public Boolean getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}
	public Boolean getIsAudit() {
		return isAudit;
	}
	public void setIsAudit(Boolean isAudit) {
		this.isAudit = isAudit;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
}
