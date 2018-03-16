package com.ich.admin.dto;

import com.ich.admin.pojo.PositionMenuResource;

import java.util.List;


public class PositionMenuResourceTree extends PositionMenuResource {
	
	private String menuName;
	private String menuType;
	
	private String state;		//节点的状态(open：打开;closed)
	private List<PositionMenuResourceTree> children; //其子内容
	private String text;
	
	
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<PositionMenuResourceTree> getChildren() {
		return children;
	}
	public void setChildren(List<PositionMenuResourceTree> children) {
		this.children = children;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
	

}
