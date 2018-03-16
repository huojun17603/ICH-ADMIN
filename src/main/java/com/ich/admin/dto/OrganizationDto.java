package com.ich.admin.dto;
/**
 * 组织机构列表DTO
 * @since  2015-12-1
 * @author 霍俊
 */
public class OrganizationDto {
	
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
	/** 节点的状态(open：打开;closed) */
	private String state;		
	
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
}
