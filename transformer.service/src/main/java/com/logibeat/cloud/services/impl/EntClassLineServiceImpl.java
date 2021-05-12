package com.logibeat.cloud.services.impl;

import com.logibeat.cloud.common.valide.TValide;
import com.logibeat.cloud.common.vo.EntClassLineHistoryVo;
import com.logibeat.cloud.errorenum.ClasslineErrorEnums;
import com.logibeat.cloud.errorenum.EntNetworkErrorEnums;
import com.logibeat.cloud.errorenum.EnterpriseErrorEnums;
import com.logibeat.cloud.persistent.dao.*;
import com.logibeat.cloud.persistent.entity.EntClassLineEntity;
import com.logibeat.cloud.persistent.entity.EnterpriseInfoEntity;
import com.logibeat.cloud.persistent.entity.NetworkEntity;
import com.logibeat.cloud.services.EntClassLineService;
import com.logibeat.cloud.util.tools.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntClassLineServiceImpl implements EntClassLineService {

	@Autowired
	private NetworkDao networkDao;

	@Autowired
	private EnterpriseInfoDao enterpriseInfoDao;

	@Autowired
	private EntClassLineDao entClassLineDao;

	@Override
	public List<EntClassLineHistoryVo> getHistoryLine(String entId, String baseUserId) {
		TValide.notNull(baseUserId, ClasslineErrorEnums.ClasslineError.BASE_USER_ID_IS_NOT_NULL);
		List<EntClassLineEntity> list = entClassLineDao.getHistoryLine(entId, baseUserId);
		List<EntClassLineHistoryVo> listVo = new ArrayList<>();
		if (list != null && list.size() > 0) {
			// 附近路线去重
			for (int i = 0; i < list.size() - 1; i++) {
				for (int j = list.size() - 1; j > i; j--) {
					if (networkDao.select(list.get(j).getStartNetworkId()).getNetworkName()
							.equals(networkDao.select(list.get(i).getStartNetworkId()).getNetworkName())
							&& networkDao.select(list.get(j).getEndNetworkId()).getNetworkName()
									.equals(networkDao.select(list.get(i).getEndNetworkId()).getNetworkName())) {
						list.remove(j);
					}
				}
			}
			for (EntClassLineEntity entity : list) {
				EntClassLineHistoryVo vo = new EntClassLineHistoryVo();
				// 显示起始点信息
				if (StringUtils.isNotBlank(entity.getStartNetworkId())
						&& StringUtils.isNotBlank(entity.getEndNetworkId())) {
					vo.setStartNetworkGuid(entity.getStartNetworkId());
					NetworkEntity netStart = networkDao.select(entity.getStartNetworkId());
					if (netStart == null)
						throw new ServiceException(EntNetworkErrorEnums.START_NETID_IS_NOT_EXIST);
					vo.setStartNetworkName(netStart.getNetworkName());
					// 显示结束点信息
					NetworkEntity netEnd = networkDao.select(entity.getEndNetworkId());
					if (netEnd == null)
						throw new ServiceException(EntNetworkErrorEnums.END_NETID_IS_NOT_EXIST);
					vo.setEndNetworkGuid(entity.getEndNetworkId());
					vo.setEndNetworkName(netEnd.getNetworkName());
					vo.setCreateTime(String.valueOf(entity.getAddTime()));
					if (StringUtils.isNotBlank(entity.getEntId())) {
						vo.setEntId(entity.getEntId());
						EnterpriseInfoEntity entityInfo = enterpriseInfoDao.select(entity.getEntId());
						if (entityInfo == null)
							throw new ServiceException(EnterpriseErrorEnums.MESSAGE_ENT_NOT_EXIST);
						vo.setEntName(entityInfo.getName());
					}
				}
				listVo.add(vo);
			}
		}
		return listVo;
	}

}

