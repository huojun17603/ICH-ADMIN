package com.ich.admin.dao;

import com.ich.admin.dto.PositionDto;
import com.ich.admin.pojo.Position;

import java.util.List;
import java.util.Map;

public interface PositionMapper {


	public List<PositionDto> selectDtoByOrgId(String orgId);

	public Integer insertPosition(Position position);

	public Integer updatePosition(Position position);

	public Integer deletePosition(String positionId);

	public Position selectById(String positionId);

	public List<PositionDto> queryByOrgId(Map<String, Object> paramsMap);

	public List<Position> selectByOrgIds(String orgIds);

}
