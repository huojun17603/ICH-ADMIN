package com.ich.admin.dao;

import com.ich.admin.dto.PositionMenuResourceTree;
import com.ich.admin.pojo.PositionMenuResource;

import java.util.List;
import java.util.Map;

public interface PositionMenuResourceMapper {

	/** 只允许查询目录和菜单类别 */
	public List<PositionMenuResourceTree> selectMenuResourceTree(Map<String, Object> paramsMap);

	public void clearByPosId(String positionId);

	public Integer insertPositionMenuResource(PositionMenuResource positionMenuResource);

	public Integer updatePositionMenuResource(PositionMenuResource positionMenuResource);

	public PositionMenuResource selectBy2PK(Map<String, Object> paramsMap);

}
