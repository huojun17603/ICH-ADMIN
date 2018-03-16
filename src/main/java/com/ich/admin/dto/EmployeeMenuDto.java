package com.ich.admin.dto;

public class EmployeeMenuDto {
	
	/** 菜单唯一编码 */
	private String code;
	/** 菜单名称 */
	private String name;
	/** 菜单链接地址唯一  */
	private String url;
	/** 菜单父节点编码 */
	private String parent;
	/** 菜单类别  */
	private Integer type;
	/** 菜单级别   */
	private Integer level;
	/** 菜单图标信息 */
	private String icon;
	/** 菜单说明 */
	private String doc;
	/** 资源菜单指向地址 */
	private String view;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
}
