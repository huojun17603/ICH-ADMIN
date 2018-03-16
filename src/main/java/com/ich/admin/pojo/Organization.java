package com.ich.admin.pojo;

public class Organization  {
	
	public static final String ROOT = "ROOT";
	//主要管理
	public static final Integer LEADER = 1;
	//次要管理
	public static final Integer DEPUTY_LEADER = 2;
	/** ID */
	private String id;
	/** 父ID */
	private String parentId;
	/** 机构名称 */
	private String orgName;
	/** 领导ID，现转移至Manager管理，此地留冗余 */
	private String leader;
	/** 副领导ID，现转移至Manager管理，此地留冗余*/
	private String deputyLeader;

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

	public String getDeputyLeader() {
		return deputyLeader;
	}

	public void setDeputyLeader(String deputyLeader) {
		this.deputyLeader = deputyLeader;
	}
}
