package com.ich.admin.dto;

import java.util.List;

public class OrganizationTreeDto {
	
	/** ID */
	private String id;
	/** 父ID */
	private String parentId;
	/** 机构名称 */
	private String orgName;
	/** 领导ID */
	private String leader;
	/** 领导名称 */
	private String leaderName;
	/** 副领导ID */
	private String deputyLeader;
	/** 领导名称 */
	private String deputyLeaderName;
	//树特有数据
	private String state;		//节点的状态(open：打开;closed)
	private List<OrganizationTreeDto> children; //其子内容
	private String text;
	
	public OrganizationTreeDto(){}
	
	public OrganizationTreeDto(OrganizationDto dto) {
		this.id = dto.getId();
		this.parentId = dto.getParentId();
		this.orgName = dto.getOrgName();
		this.leader = dto.getLeader();
		this.leaderName = dto.getLeaderName();
		this.deputyLeader = dto.getDeputyLeader();
		this.deputyLeaderName = dto.getDeputyLeaderName();
		this.text = dto.getOrgName();
		this.state = "closed";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getDeputyLeader() {
		return deputyLeader;
	}
	public void setDeputyLeader(String deputyLeader) {
		this.deputyLeader = deputyLeader;
	}
	public String getDeputyLeaderName() {
		return deputyLeaderName;
	}
	public void setDeputyLeaderName(String deputyLeaderName) {
		this.deputyLeaderName = deputyLeaderName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<OrganizationTreeDto> getChildren() {
		return children;
	}
	public void setChildren(List<OrganizationTreeDto> children) {
		this.children = children;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}


	
}
