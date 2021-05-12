package com.logibeat.cloud.services;


import com.logibeat.cloud.common.vo.HistoryRouteVo;
import com.logibeat.cloud.common.vo.NetworkVo;
import com.logibeat.cloud.core.dto.EntNetworkDTO;
import com.logibeat.cloud.core.dto.TaskDto;
import com.logibeat.cloud.util.tools.pageMdl.PageResultDTO;

public interface EntNetworkService {


	/**
	 * 查找fu近的网点列表
	 *
	 * @param entNetworkDTO
	 * @return
	 */
	PageResultDTO<NetworkVo> findPageNear(EntNetworkDTO entNetworkDTO);

	/**
	 * 查找目的网点
	 *
	 * @param entNetworkDTO
	 * @return
	 */
	PageResultDTO<NetworkVo> findPageAll(EntNetworkDTO entNetworkDTO);

    /**
     * 获取网点
     * @param id
     * @return
     */
	NetworkVo isExist(String id);

	/**
	 * 判断行程历史记录是否存在
	 *
	 * @param taskDto
	 * @return
	 */
	HistoryRouteVo isExistHistoryRoute(TaskDto taskDto);


}
