package com.logibeat.cloud.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.logibeat.cloud.common.enumtype.CoopType;
import com.logibeat.cloud.common.valide.TValide;
import com.logibeat.cloud.common.vo.EntNetWorkVo;
import com.logibeat.cloud.common.vo.HistoryRouteVo;
import com.logibeat.cloud.common.vo.NetworkVo;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.dto.EntNetworkDTO;
import com.logibeat.cloud.core.dto.TaskDto;
import com.logibeat.cloud.errorenum.EntNetworkErrorEnums;
import com.logibeat.cloud.errorenum.EnterpriseCooperatePerErrorEnums;
import com.logibeat.cloud.errorenum.EnterpriseErrorEnums;
import com.logibeat.cloud.persistent.dao.EnterpriseCooperateCarDao;
import com.logibeat.cloud.persistent.dao.EnterpriseCooperatePerDao;
import com.logibeat.cloud.persistent.dao.EnterpriseInfoDao;
import com.logibeat.cloud.persistent.dao.NetworkDao;
import com.logibeat.cloud.persistent.entity.EnterpriseCooperateCarEntity;
import com.logibeat.cloud.persistent.entity.EnterpriseCooperatePerEntity;
import com.logibeat.cloud.persistent.entity.EnterpriseInfoEntity;
import com.logibeat.cloud.persistent.entity.NetworkEntity;
import com.logibeat.cloud.services.EntNetworkService;
import com.logibeat.cloud.util.tools.LatLngUtil;
import com.logibeat.cloud.util.tools.exception.ServiceException;
import com.logibeat.cloud.util.tools.pageMdl.Page;
import com.logibeat.cloud.util.tools.pageMdl.PageResultDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EntNetworkServiceImpl implements EntNetworkService {

    @Autowired
    private EnterpriseInfoDao enterpriseInfoDao;

    @Autowired
    private NetworkDao networkDao;

    @Autowired
    private EnterpriseCooperatePerDao enterpriseCooperatePerDao;
    
    @Autowired
    private EnterpriseCooperateCarDao enterpriseCooperateCarDao;

    @Override
    public PageResultDTO<NetworkVo> findPageNear(EntNetworkDTO entNetworkDTO) {
        Page page = entNetworkDTO;
        String entId = entNetworkDTO.getEntId();
        String personId = entNetworkDTO.getBaseUserId();
        String networkNameLike = entNetworkDTO.getNetworkNameLike();

        if(StringUtils.isBlank(entId)){
            return null;
        }

        EnterpriseCooperatePerEntity entity = enterpriseCooperatePerDao.getCoopByEntIdAndPersonId(entId, personId);
        if (entity == null){
            return null;
            //throw new ServiceException(EntNetworkErrorEnums.ENT_DRIVER_RELATION_IS_NOT_EXIST);
        }
        if(!CoopType.SelfDriver.getValue().equals(entity.getCoopType())) {
            return null;
            //throw new ServiceException(EntNetworkErrorEnums.ENT_IS_NOT_SELF_DRIVER);
        }

        List<NetworkEntity> networkList = networkDao.getEntityByType(entId,ConstantUtil.String_CODE_ONE, networkNameLike);

        List<NetworkVo> networkVoList = new ArrayList<>();
        for (NetworkEntity networkEntity:networkList){
            networkVoList.add(setNetworkNear(networkEntity,entNetworkDTO.getLng(), entNetworkDTO.getLat()));
        }

        List<NetworkVo> resultList = networkVoList.parallelStream()
                .sorted(Comparator.comparing(NetworkVo::getNear))
                .limit(entNetworkDTO.getEnd()).skip(entNetworkDTO.getStart()).collect(Collectors.toList());

        Integer maxDistance = entNetworkDTO.getMaxDistance();
        if(null != maxDistance && maxDistance !=0){
            resultList = resultList.parallelStream().filter(p->p.getNear()<=maxDistance).collect(Collectors.toList());
        }
        return new PageResultDTO<>(resultList, page);
    }

    /**
     * 查找目的地的网点信息
     *
     * @param entNetworkDTO
     * @return
     */
    @Override
    public PageResultDTO<NetworkVo> findPageAll(EntNetworkDTO entNetworkDTO) {
        Page page =  entNetworkDTO;
        String entId = entNetworkDTO.getEntId();
        String personId = entNetworkDTO.getBaseUserId();
        String networkNameLike = entNetworkDTO.getNetworkNameLike();

        if(StringUtils.isBlank(entId)){
            return null;
        }

        EnterpriseCooperatePerEntity entity = enterpriseCooperatePerDao.getCoopByEntIdAndPersonId(entId, personId);
        if (entity == null){
            return null;
            //throw new ServiceException(EntNetworkErrorEnums.ENT_DRIVER_RELATION_IS_NOT_EXIST);
        }
        if(!CoopType.SelfDriver.getValue().equals(entity.getCoopType())) {
            return null;
            //throw new ServiceException(EntNetworkErrorEnums .ENT_IS_NOT_SELF_DRIVER);
        }

        List<NetworkVo> networkVoList = new ArrayList<>();

        PageHelper.startPage(page.getPageIndex(),page.getPageSize());
        List<NetworkEntity> networkEntityList = networkDao.getEntityByType(entId, ConstantUtil.String_CODE_ONE, networkNameLike);
        for (NetworkEntity networkEntity : networkEntityList){
           networkVoList.add(setNetworkNear(networkEntity, null, null));
        }

        PageInfo pageInfo = new PageInfo(networkVoList);
        page.setTotalCount(pageInfo.getTotal());
        return new PageResultDTO<>(networkVoList, page);
    }

    @Override
    public NetworkVo isExist(String id){
        NetworkEntity networkEntity = networkDao.select(id);
        if(networkEntity != null){
            return setNetworkNear(networkEntity, null, null);
        }
        return null;
    }

    /**
     * 判断行程历史线路是否存在,如果没有企业id，起始点的信息也要返回
     *
     * @param taskDto
     * @return
     */
    @Override
    public HistoryRouteVo isExistHistoryRoute(TaskDto taskDto) {
        TValide.notNull(taskDto.getBaseUserId(), EntNetworkErrorEnums.BASEUSER_ID_IS_NOT_EXIST);
        HistoryRouteVo historyRouteVo = new HistoryRouteVo();
        List<NetworkVo> networkVoList = new ArrayList<>();

        if (StringUtils.isNotBlank(taskDto.getStartNetId())) {
            NetworkEntity networkEntity = networkDao.select(taskDto.getStartNetId());
            if (networkEntity == null){
                throw new ServiceException(EntNetworkErrorEnums.START_NET_IS_NOT_EXIST);
            }
            NetworkVo networkVo = new NetworkVo();
            EntNetWorkVo entNetworkVo = setNetWork(networkEntity);
            networkVo.setNetwork(entNetworkVo);
            networkVo.setSortNum(0);
            networkVoList.add(networkVo);
        }

        if (StringUtils.isNotBlank(taskDto.getEndNetId())) {
            NetworkEntity networkEntity = networkDao.select(taskDto.getEndNetId());
            if (networkEntity == null){
                throw new ServiceException(EntNetworkErrorEnums.END_NET_IS_NOT_EXIST);
            }

            NetworkVo networkVo = new NetworkVo();
            EntNetWorkVo entNetworkVo = setNetWork(networkEntity);
            networkVo.setNetwork(entNetworkVo);
            networkVo.setSortNum(1);
            networkVoList.add(networkVo);
        }

        historyRouteVo.setNetworkVoList(networkVoList);

        if (StringUtils.isNotBlank(taskDto.getEntId())) {
            EnterpriseCooperatePerEntity entity = enterpriseCooperatePerDao.getCoopByEntIdAndPersonId(taskDto.getEntId(),
                    taskDto.getBaseUserId());
            EnterpriseCooperateCarEntity entCar = enterpriseCooperateCarDao.getCarsByEntIdAndPersonId(taskDto.getEntId(), 
            		taskDto.getBaseUserId());
            if (entity == null && entCar == null){
                throw new ServiceException(EnterpriseCooperatePerErrorEnums.IS_NOT_SELF_DRIVER);
            }
//            if (entity != null && entity.getInviteState() == InviteState.Stop.getValue()){
//                throw new ServiceException(EnterpriseCooperatePerErrorEnums.IS_NOT_SELF_DRIVER);
//            }
            historyRouteVo.setEntId(taskDto.getEntId());
            EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(taskDto.getEntId());
            if (enterpriseInfoEntity == null){
                throw new ServiceException(EnterpriseErrorEnums.MESSAGE_ENT_NOT_EXIST);
            }
            historyRouteVo.setEntName(enterpriseInfoEntity.getName());
            historyRouteVo.setEntId(taskDto.getEntId());
        }
        return historyRouteVo;
    }

    private NetworkVo setNetworkNear(NetworkEntity thisEntity, Double lng, Double lat) {
        NetworkVo networkVo = new NetworkVo();
        networkVo.setAddress(thisEntity.getNetworkAddress());
        networkVo.setContactName(thisEntity.getNetworkContactUser());
        networkVo.setContactPhone(thisEntity.getNetworkContactPhone());
        networkVo.setEntId(thisEntity.getEntid());
        networkVo.setGuid(thisEntity.getGuid());
        networkVo.setLat(thisEntity.getLat());
        networkVo.setLng(thisEntity.getLng());
        networkVo.setName(thisEntity.getNetworkName());
        networkVo.setNetworkAreainfo(thisEntity.getNetworkAreainfo());
        networkVo.setNetworkNamePinyin(thisEntity.getNetworkNamePinyin());
        networkVo.setRegionCode(thisEntity.getRegionCode());

        if(lng != null && lat != null && thisEntity.getLng() != null && thisEntity.getLat() != null){
            networkVo.setNear(LatLngUtil.distance(lng, lat, thisEntity.getLng(), thisEntity.getLat()));
        }
        
        return networkVo;
    }
  
    //拼参数
    private EntNetWorkVo setNetWork(NetworkEntity thisEntity) {
        EntNetWorkVo entNetworkVo = new EntNetWorkVo();
        entNetworkVo.setAddress(thisEntity.getNetworkAddress());
        entNetworkVo.setContactName(thisEntity.getNetworkContactUser());
        entNetworkVo.setContactPhone(thisEntity.getNetworkContactPhone());
        entNetworkVo.setEntId(thisEntity.getEntid());
        entNetworkVo.setGuid(thisEntity.getGuid());
        entNetworkVo.setLat(thisEntity.getLat());
        entNetworkVo.setLng(thisEntity.getLng());
        entNetworkVo.setName(thisEntity.getNetworkName());
        entNetworkVo.setNetworkAreainfo(thisEntity.getNetworkAreainfo());
        entNetworkVo.setNetworkNamePinyin(thisEntity.getNetworkNamePinyin());
        entNetworkVo.setRegionCode(thisEntity.getRegionCode());
        return entNetworkVo;
    }

}
