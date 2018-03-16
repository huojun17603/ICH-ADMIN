package com.ich.admin.dao;

import com.ich.admin.dto.PositionWindowResourceDto;
import com.ich.admin.pojo.PositionWindowResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PositionWindowResourceMapper {

	public void clearByPosId(String positionId);

	/**
	 * 查询所有窗口，并通过职位ID匹配已经配置好的职位窗口信息
	 * @param positionId 职位ID
	 * @return
	 */
	public List<PositionWindowResourceDto> selectPWRDtoByPositionId(@Param("positionId") String positionId, @Param("modular") String modular);

	public PositionWindowResource selectBy2PK(Map<String, Object> paramsMap);

	public Integer insertPositionWindowResource(PositionWindowResource positionWindowResource);

	public Integer updatePositionWindowResource(PositionWindowResource positionWindowResource);

}
