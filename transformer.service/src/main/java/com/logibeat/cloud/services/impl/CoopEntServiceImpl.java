package com.logibeat.cloud.services.impl;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.logibeat.cloud.common.cache.util.StringUtils;
import com.logibeat.cloud.common.enumtype.*;
import com.logibeat.cloud.common.valide.TValide;
import com.logibeat.cloud.common.vo.*;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.core.dto.BaseRequestDTO;
import com.logibeat.cloud.core.dto.CooperAndDriverDTO;
import com.logibeat.cloud.core.dto.EntBusinessInfoDTO;
import com.logibeat.cloud.core.dto.EntFriendDto;
import com.logibeat.cloud.core.properties.CommonProperties;
import com.logibeat.cloud.errorenum.CoopEntEnums;
import com.logibeat.cloud.errorenum.EnterpriseErrorEnums;
import com.logibeat.cloud.helper.GrimlockServiceHelper;
import com.logibeat.cloud.helper.NewFriendServiceHelper;
import com.logibeat.cloud.msg.sender.JetfireMsgSender;
import com.logibeat.cloud.msg.sender.StarSoftSender;
import com.logibeat.cloud.msg.template.DriverCoopEntPushTemplate;
import com.logibeat.cloud.persistent.dao.*;
import com.logibeat.cloud.persistent.entity.*;
import com.logibeat.cloud.redis.CarListTac;
import com.logibeat.cloud.redis.CoopcarListTac;
import com.logibeat.cloud.services.CoopEntService;
import com.logibeat.cloud.util.tools.PinyinUtil;
import com.logibeat.cloud.util.tools.RandomTool;
import com.logibeat.cloud.util.tools.TypeCastUtil;
import com.logibeat.cloud.util.tools.exception.ServiceException;
import com.logibeat.cloud.util.tools.pageMdl.Page;
import com.logibeat.cloud.util.tools.pageMdl.PageResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sean
 * @version 1.0
 * @ClassName: CoopEntServiceImpl
 * @Description: ?????????????????????
 * @date 2015???12???16??? ??????9:58:52
 */
@Service
public class CoopEntServiceImpl implements CoopEntService {

    private static final Logger logger = LoggerFactory.getLogger(CoopEntServiceImpl.class);

    @Autowired
    private EnterpriseInfoDao enterpriseInfoDao;

    @Autowired
    private FriendsDao friendsDao;

    @Autowired
    private EnterpriseCooperatePerDao enterpriseCooperatePerDao;

    @Autowired
    private EnterpriseOrganizationDictDao enterpriseOrganizationDictDao;

    @Autowired
    private NewFriendLogDao newFriendLogDao;

    @Autowired
    private DictDao dictDao;

    @Autowired
    private CarsDao carsDao;

    @Autowired
    private GrimlockServiceHelper grimlockServiceHelper;

    @Autowired
    private EnterpriseCooperateCarDao enterpriseCooperateCarDao;

    @Autowired
    private ImUserDao imUserDao;

    @Autowired
    protected ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private CoopcarListTac coopcarListTac;

    @Autowired
    private SyspersonDao syspersonDao;

    @Autowired
    private TaskOrdersCarDao taskOrdersCarDao;

    @Autowired
    private CarListTac carListTac;

    @Autowired
    private NewFriendServiceHelper newFriendServiceHelper;

    @Autowired
    private JetfireMsgSender jetfireMsgSender;

    @Autowired
    private EnterpriseCensorDao enterpriseCensorDao;
    
    @Autowired
    private EnterpriseSettingDao enterpriseSettingDao;


    @Autowired
    private StarSoftSender starSoftSender;





    /**
     * ?????????????????????????????????
     */
    @Override
    public PageResultDTO<EntCoopInfoDriverVo> searchEnt(String keyWord, String baseUserId, Page page) {

        List<EntCoopInfoDriverVo> resultList = new ArrayList<>();
        PageHelper.startPage(page.getPageIndex(),page.getPageSize());
        List<EnterpriseInfoEntity> entList = enterpriseInfoDao.searchEnt(keyWord);

        for (EnterpriseInfoEntity enterpriseInfoEntity : entList) {
            EntCoopInfoDriverVo entCoopInfoDriverVo = new EntCoopInfoDriverVo();
            entCoopInfoDriverVo.setId(enterpriseInfoEntity.getId());
            entCoopInfoDriverVo.setName(enterpriseInfoEntity.getName());
            entCoopInfoDriverVo.setPinYin(enterpriseInfoEntity.getPinYin());
            entCoopInfoDriverVo.setAddress(enterpriseInfoEntity.getAddress());
            entCoopInfoDriverVo.setEntAuditStatus(enterpriseInfoEntity.getAuditStatus());
            entCoopInfoDriverVo.setEntTypeDictGUID(enterpriseInfoEntity.getDictGUID());
            entCoopInfoDriverVo.setProfile(enterpriseInfoEntity.getProfile());
            entCoopInfoDriverVo.setEntStatus(enterpriseInfoEntity.getEntStatus());
            entCoopInfoDriverVo.setOauthType(OauthType.Ent.getValue());
            entCoopInfoDriverVo.setLegalPerson(enterpriseInfoEntity.getLegalPerson());
            entCoopInfoDriverVo.setBuildDate(enterpriseInfoEntity.getBuildDate());
            entCoopInfoDriverVo.setRegStatus(enterpriseInfoEntity.getRegStatus());
            entCoopInfoDriverVo.setRegNumber(enterpriseInfoEntity.getRegNumber());
            entCoopInfoDriverVo.setLegalPerson(enterpriseInfoEntity.getLegalPerson());
            entCoopInfoDriverVo.setEntName(enterpriseInfoEntity.getName());
            // ????????????
            EnterpriseCooperatePerEntity entPer = enterpriseCooperatePerDao.getCoopByEntIdAndPersonId(
                    entCoopInfoDriverVo.getId(), baseUserId);
            Integer inviteState = null;
            if (null != entPer) {
                inviteState = entPer.getInviteState();
                entCoopInfoDriverVo.setInviteState(inviteState);
                if (inviteState == InviteState.Pass.getValue()) {
                    entCoopInfoDriverVo.setInviteStateName("?????????");
                } else if (inviteState == InviteState.Stop.getValue()) {
                    entCoopInfoDriverVo.setInviteStateName("?????????");
                } else if (inviteState == InviteState.Wait.getValue()) {
                    entCoopInfoDriverVo.setInviteStateName("?????????");
                }
            } else {
                entCoopInfoDriverVo.setInviteState(InviteState.Unknown.getValue());
                entCoopInfoDriverVo.setInviteStateName("?????????");
            }

            // ?????????????????????
            NewFriendLogEntity newFriendLogEntity = newFriendLogDao.getNewFriendForDriver(baseUserId,
                    entCoopInfoDriverVo.getId());
            if (null != newFriendLogEntity) {
                if (inviteState == InviteState.Wait.getValue()) {
                    entCoopInfoDriverVo.setInviteState(InviteState.ACKNOWLEDGEMENT.getValue());//????????????   -- ????????????????????????9 -- ?????????
                    entCoopInfoDriverVo.setInviteStateName("?????????");
                } else if (inviteState == null) {
                    entCoopInfoDriverVo.setInviteStateName("?????????");
                }

                entCoopInfoDriverVo.setIsInviteByEnt(ConstantUtil.TRUE);
                entCoopInfoDriverVo.setNewFriendGUID(newFriendLogEntity.getNewFriendGUID());
            } else {
                entCoopInfoDriverVo.setIsInviteByEnt(ConstantUtil.FALSE);
            }
            // ??????LOGO
            entCoopInfoDriverVo.setLogo(enterpriseInfoEntity.getLogo());
            entCoopInfoDriverVo
                    .setEntTypeDictName(dictDao.getDictNameByGuid(entCoopInfoDriverVo.getEntTypeDictGUID()));
            resultList.add(entCoopInfoDriverVo);

            PageInfo pageInfo = new PageInfo(entList);
            page.setTotalCount(pageInfo.getTotal());
        }
        // ??????????????????1.5
//        if (null != entList && entList.size() < 10) {
//            if (StringUtils.isNotBlank(keyWord)) {
//                keyWord = keyWord.replaceAll(" +", "");
//            }
//            List<EntBusinessInfoDTO> list = QichachaUtil.search(keyWord.trim());
//            if (list != null && list.size() > 0) {
//                for (EntBusinessInfoDTO dto : list) {
//                    EntCoopInfoDriverVo vo = new EntCoopInfoDriverVo();
//                    vo.setEntName(dto.getEntName());
//                    vo.setEntAuditStatus(EntStatus.UnEnter.getValue());
//                    vo.setInviteState(InviteState.Unknown.getValue());
//                    vo.setInviteStateName("?????????");
//                    vo.setBuildDate(dto.getBuildDate());
//                    vo.setRegStatus(dto.getRegStatus());
//                    vo.setRegNumber(dto.getRegNumber());
//                    vo.setLegalPerson(dto.getLegalPerson());
//                    vo.setEntStatus(EntStatus.UnEnter.getValue());
//
//                    // ??????
//                    long count = entList.parallelStream().filter(p -> StringUtils.isNotBlank(dto.getEntName()) && dto.getEntName().equals(p.getName())).count();
//                    //Boolean flag = entEnterpriseExpandDao.getEntByNameIsAudit(dto.getEntName(), null);
//                    if (count <= 0) {
//                        resultList.add(vo);
//                    }
//                }
//                page.setTotalCount((long) resultList.size());
//            }
//
//        }
        return new PageResultDTO<>(resultList, page);
    }

    /**
     * ?????????????????? :???
     */
    @Override
    public PageResultDTO<EntCoopShortInfoVo> getFriendsNew(EntFriendDto entFriendDto) {
        List<EntCoopShortInfoVo> resultList = new ArrayList<>();
        String personId = entFriendDto.getBaseUserId();
        Integer pageIndex = entFriendDto.getPageIndex();
        Integer pageSize = entFriendDto.getPageSize();

        PageHelper.startPage(pageIndex,pageSize);
        String coopTypes = CoopType.FriendCar.getValue().toString();
        List<EnterpriseCooperateCarEntity> entCarList = enterpriseCooperateCarDao.getDriverCarList(null,personId,coopTypes);
//        List<EnterpriseCooperatePerEntity> cooperatePerEntities = enterpriseCooperatePerDao.getEnterpriseCooperatePer(
//                entFriendDto.getKeyword(), entFriendDto.isIsAll(), entFriendDto.getStarMark(),
//                entFriendDto.getEntTypeDictGUID(), entFriendDto.getFriend(), personId, CoopType.FriendDriver.getValue());
        List<String> newList = new ArrayList<>();
        for (EnterpriseCooperateCarEntity cooperateCarEntity : entCarList) {
            //??????????????????????????????
            EnterpriseCensorEntity censorEntity = enterpriseCensorDao.getCensorEntityByEntId(cooperateCarEntity.getEntid());
            if(censorEntity != null && (censorEntity.getAccountState() == 3 || censorEntity.getAccountState() == 2)){
                continue;
            }
        	//??????
        	if(!newList.isEmpty() && newList.size()>0 && newList.contains(cooperateCarEntity.getEntid())){
        		continue;
        	}
        	newList.add(cooperateCarEntity.getEntid());
            EntCoopShortInfoVo entCoopShortInfoVo = new EntCoopShortInfoVo();
            EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(cooperateCarEntity.getEntid());
            if (null != enterpriseInfoEntity) {
                entCoopShortInfoVo.setID(enterpriseInfoEntity.getId());
                entCoopShortInfoVo.setName(enterpriseInfoEntity.getName());
                entCoopShortInfoVo.setPinYin(enterpriseInfoEntity.getPinYin());
                entCoopShortInfoVo.setCode(enterpriseInfoEntity.getCode());
                entCoopShortInfoVo.setAddress(enterpriseInfoEntity.getAddress());
                entCoopShortInfoVo.setEntAuditStatus(enterpriseInfoEntity.getAuditStatus());
                entCoopShortInfoVo.setEntTypeDictGUID(enterpriseInfoEntity.getParentDictGUID());
                entCoopShortInfoVo.setProfile(enterpriseInfoEntity.getProfile());
                entCoopShortInfoVo.setOauthType(OauthType.Ent.getValue());
                entCoopShortInfoVo.setLogo(enterpriseInfoEntity.getLogo());
//                entCoopShortInfoVo.setInviteState(cooperateCarEntity.getInviteState());
                entCoopShortInfoVo.setInviteState(InviteState.Pass.getValue());
                
                // ????????????
                entCoopShortInfoVo.setEntTypeDictName(dictDao.getDictNameByGuid(enterpriseInfoEntity.getParentDictGUID()));
                resultList.add(entCoopShortInfoVo);
            }
        }

        PageInfo pageInfo = new PageInfo(newList);
        Page page = new Page();
        page.setPageIndex(pageIndex);
        page.setTotalCount(pageInfo.getTotal());
        page.setPageSize(pageSize);

        return new PageResultDTO<>(resultList, page);
    }

    @Override
    public List<EntCoopShortInfoVo> searchEntFriends(EntFriendDto entFriendDto) {
        String keyWord = entFriendDto.getKeyword();
        String personId = entFriendDto.getBaseUserId();

        List<EntCoopShortInfoVo> resultList = new ArrayList<>();
        PageHelper.startPage(entFriendDto.getPageIndex(), entFriendDto.getPageSize());
        List<EnterpriseInfoEntity> list = enterpriseInfoDao.getEnterpriseInfo(personId, keyWord);
        for (EnterpriseInfoEntity entity : list) {
            EntCoopShortInfoVo entCoopShortInfoVo = new EntCoopShortInfoVo();
            entCoopShortInfoVo.setID(entity.getId());
            entCoopShortInfoVo.setName(entity.getName());
            entCoopShortInfoVo.setPinYin(entity.getPinYin());
            // ?????????????????????????????????????????????????????????code
            if (!entity.getName().contains(keyWord)) {
                entCoopShortInfoVo.setCode(entity.getCode());
            }
            entCoopShortInfoVo.setAddress(entity.getAddress());
            entCoopShortInfoVo.setEntAuditStatus(entity.getAuditStatus());
            entCoopShortInfoVo.setEntTypeDictGUID(entity.getDictGUID());
            entCoopShortInfoVo.setProfile(entity.getProfile());
            entCoopShortInfoVo.setLogo(entity.getLogo());
            resultList.add(entCoopShortInfoVo);
        }
        return resultList;
    }

    /**
     * ??????????????????
     */
    @Override
    public void setStarMark(String entId, String personId, boolean isStarMark) {
        try {
            EnterpriseCooperatePerEntity enterpriseCooperatePerEntity = enterpriseCooperatePerDao
                    .getCoopByEntIdAndPersonId(entId, personId);
            if (null != enterpriseCooperatePerEntity) {
                enterpriseCooperatePerEntity.setIsStarMark(TypeCastUtil.booleanToByte(isStarMark));
                enterpriseCooperatePerDao.update(enterpriseCooperatePerEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new ServiceException(CoopEntEnums.CoopEntErrors.MESSAGE_HANDLE_FAIL);
        }
    }

    public List<DriverEntVo> getDriverEntList(String personId) {
        List<EnterpriseCooperatePerEntity> entPerList = enterpriseCooperatePerDao.getSelfEntByPersonId(personId);
        List<DriverEntVo> resultList = new ArrayList<>();
        for (EnterpriseCooperatePerEntity entPer : entPerList) {
            if (entPer.getInviteState() == InviteState.Pass.getValue()) {
                DriverEntVo driverEntVo = new DriverEntVo();
                // ????????????
                EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(entPer.getEntID());

                driverEntVo = this.getOrgPer(entPer.getEntID(), personId, driverEntVo);
                driverEntVo.setEntId(enterpriseInfoEntity.getId());
                driverEntVo.setEntName(enterpriseInfoEntity.getName());
                driverEntVo.setNameRemark(entPer.getNameRemark());
                driverEntVo.setEntLogo(enterpriseInfoEntity.getLogo());
                driverEntVo.setAuditStatus(enterpriseInfoEntity.getAuditStatus());

                if (CoopType.SelfDriver.getValue().equals(entPer.getCoopType())) {
                    driverEntVo.setIsShowCarCoachType(true);
                    driverEntVo.setIsShowPlateNumber(true);
                } else {
                    driverEntVo.setIsShowCarCoachType(false);
                    driverEntVo.setIsShowPlateNumber(false);
                }

                // ????????????
                EnterpriseCooperateCarEntity entCar = enterpriseCooperateCarDao
                        .getSelfCarByEntIdAndPersonId(enterpriseInfoEntity.getId(), personId);
                if (null != entCar) {
                    CarsEntity carsEntity = carsDao.select(entCar.getCarid());
                    driverEntVo.setCarId(carsEntity.getCarID());
                    driverEntVo.setCarLength(dictDao.getDictNameByGuid(carsEntity.getCarLengthDictGUID()));
                    driverEntVo.setCarCoachType(dictDao.getDictNameByGuid(carsEntity.getCoachTypeDictGUID()));
                    driverEntVo.setPlateNumber(carsEntity.getPlateNumber());
                    driverEntVo.setRatedLoad(carsEntity.getRatedLoad());
                    driverEntVo.setRatedVolume(carsEntity.getRatedVolume());
                    driverEntVo.setCarLogo(carsEntity.getLogo());
                    if (StringUtils.isNotBlank(entCar.getFirstDriverPersonID())) {
                        if (entCar.getFirstDriverPersonID().equals(personId)) {
                            driverEntVo.setIsFirstDriver(ConstantUtil.TRUE);
                        } else {
                            driverEntVo.setIsFirstDriver(ConstantUtil.FALSE);
                        }
                    } else {
                        driverEntVo.setIsFirstDriver(ConstantUtil.FALSE);
                    }
                }
                resultList.add(driverEntVo);
            }

        }
        return resultList;
    }

    /**
     * ????????????????????????
     *
     * @throws Exception
     */
    @Override
    public String addEntCoop(String entId, Integer inviteType, BaseRequestDTO baseRequestDTO, EntBusinessInfoDTO entBusinessInfoDTO) {
        String baseUserId = baseRequestDTO.getBaseUserId();
        SystemPersonEntity systemPersonEntity = syspersonDao.select(baseUserId);
        TValide.notNull(systemPersonEntity, CoopEntEnums.CoopEntErrors.NOT_USER_FAIL);
        String newFriendLogId = null;
        String entName = entBusinessInfoDTO.getEntName();
        boolean isPush = true;
        //???????????????????????????????????????????????????????????????????????????
        if (null != entBusinessInfoDTO && StringUtils.isNotBlank(entBusinessInfoDTO.getRegNumber())) {
            EnterpriseInfoEntity entity = enterpriseInfoDao.getEntByRegNumber(entBusinessInfoDTO.getRegNumber());
            if (null == entity) {
                entId = RandomTool.getGUId();
                entity = new EnterpriseInfoEntity();
                entity.setId(entId);
                entity.setName(entBusinessInfoDTO.getEntName());
                entity.setPinYin(PinyinUtil.getPinYin(entBusinessInfoDTO.getEntName()));// ??????
                entity.setLegalPerson(entBusinessInfoDTO.getLegalPerson());
                entity.setRegNumber(entBusinessInfoDTO.getRegNumber());
                entity.setRegStatus(entBusinessInfoDTO.getRegStatus());
                entity.setBuildDate(entBusinessInfoDTO.getBuildDate());
                entity.setEntStatus(EntStatus.UnEnter.getValue());
                entity.setIsDelete(ConstantUtil.BYTE_FALSE);
                entity.setIssham(ConstantUtil.BYTE_CODE_ZERO);
                enterpriseInfoDao.insert(entity);
            } else {
                entId = entity.getId();
                entName = entity.getName();
            }
            isPush = false;
        }
        // ?????????????????????????????????????????????????????????????????????????????????????????????
        CarsEntity carsEntity = carsDao.getMySelfCar(baseUserId);
        if (null == carsEntity) {
            throw new ServiceException(CoopEntEnums.CoopEntErrors.MAINTENANCE_VEHICLE_FAIL);
        }
        EnterpriseInfoEntity entInfo = enterpriseInfoDao.select(entId);
        
        //????????????????????????1?????????0??????????????? ?????????1
        Integer isCheckCoopDriver = 1;
        if (null != entInfo) {
        	EnterpriseSettingEntity entSetting = enterpriseSettingDao.queryByEntId(entId);
        	if(entSetting!=null) {
        		isCheckCoopDriver = StringUtils.toInteger(entSetting.getIsCheckCoopDriver());
        	}
            if (entInfo.getEntStatus() != EntStatus.Enter.getValue()) {
                isPush = false;
            }
        }
        // ????????????????????????
        EnterpriseCooperatePerEntity entPer = enterpriseCooperatePerDao.getCoopByEntIdAndPersonId(entId, baseUserId);

        // ????????????????????????????????????????????????
        addPartnership(entId, baseUserId, carsEntity, systemPersonEntity, entPer, entBusinessInfoDTO, isCheckCoopDriver);

        NewFriendLogEntity oldNewFriendLogEntity = newFriendLogDao.getNewFriendForCarToEnt(entId, baseUserId);
        if (null == oldNewFriendLogEntity) {
            NewFriendLogEntity newFriendLogEntity = newFriendServiceHelper.addNewFriendLog(entId, baseUserId, inviteType,
                    null, isCheckCoopDriver);
            newFriendLogId = newFriendLogEntity.getNewFriendGUID();

            //????????????
            NewFriendContentVo newFriendContentVo = new NewFriendContentVo();
            newFriendContentVo.setNameContent(systemPersonEntity.getNiChen());
            newFriendContentVo.setCarContent(carsEntity.getPlateNumber());
            newFriendContentVo.setLogoContent(systemPersonEntity.gethDpic());
            newFriendContentVo.setMobileContent(systemPersonEntity.getLoginName());
            newFriendLogEntity.setContentPrex(new Gson().toJson(newFriendContentVo));

            newFriendLogDao.update(newFriendLogEntity);
        } else {
            newFriendLogId = oldNewFriendLogEntity.getNewFriendGUID();
        }

        // ??????????????????
        newFriendServiceHelper.addNewFriendLogDetail(newFriendLogId, "", baseUserId);

        //????????????
        NewFriendLogEntity newFriendLog = newFriendLogDao
                .getNewFriendByStatusAndType(baseUserId, entId, InviteType.AddEnt.getValue(), null);
        if (null != newFriendLog) {
            newFriendLog.setInviteState(InviteState.Pass.getValue());
            newFriendLogDao.update(newFriendLog);
        }

        //??????????????????????????????
        NewFriendLogEntity newFriendLog1 = newFriendLogDao
                .getNewFriendByStatusAndType(entId, baseUserId, InviteType.AddFriendDriver.getValue(), null);
        if (null != newFriendLog1) {
            newFriendLogDao.delete(newFriendLog1.getNewFriendGUID());
        }

        // ??????IsCheckCoopDriver=1 ?????????????????????????????????????????????????????????????????????????????????
        if ((isCheckCoopDriver == CommonProperties.INT_ONE && isPush)) {
            //?????????????????????
            List<EnterpriseCooperatePerEntity> enterpriseCooperatePerList = enterpriseCooperatePerDao.getEntAdministrators(entId);
            if(!enterpriseCooperatePerList.isEmpty() && enterpriseCooperatePerList.size() > 0){
                List<DriverCoopEntPushTemplate> templateList = new ArrayList<>();
                for (EnterpriseCooperatePerEntity enterpriseCooperatePerEntity : enterpriseCooperatePerList){
                    String personId = enterpriseCooperatePerEntity.getCoopObjectID();
                    SystemPersonEntity systemPerson = syspersonDao.select(personId);
                    if(systemPerson != null){
                        String niChen = systemPerson.getNiChen();
                        DriverCoopEntPushTemplate template =
                                new DriverCoopEntPushTemplate(personId,niChen,entId,entName,carsEntity.getPlateNumber());
                        templateList.add(template);
                    }
                }
                jetfireMsgSender.sendPush(templateList);
            }
        }

        // ??????IsCheckCoopDriver=0 ?????? ??????carTag
//        if (ConstantUtil.INTEGER_CODE_ZERO.equals(isCheckCoopDriver)) {
//            grimlockServiceHelper.addCarTag(baseUserId, GpsDeviceType.Mobile.getValue().toString(), entId);
//        }

        return String.valueOf(isCheckCoopDriver);

    }

    /**
     * ????????????????????????????????????????????????
     *
     * @param entId
     * @param baseUserId
     * @param carsEntity
     * @param systemPersonEntity
     */
    private void addPartnership(String entId, String baseUserId, CarsEntity carsEntity,
                                SystemPersonEntity systemPersonEntity, EnterpriseCooperatePerEntity entPer,
                                EntBusinessInfoDTO entBusinessInfoDTO, Integer isCheckCoopDriver) {

        if (null != entPer) {
            if (entPer.getCoopType() == CoopType.FriendDriver.getValue()
                    && entPer.getInviteState() == InviteState.Pass.getValue()) {
                throw new ServiceException(CoopEntEnums.CoopEntErrors.ENT_EXTERNAL_DRIVER_FAIL);
            } else {
                if (isCheckCoopDriver == CommonProperties.INT_ONE) {
                    entPer.setInviteState(InviteState.Wait.getValue());
                } else {
                    entPer.setInviteState(InviteState.Pass.getValue());
                }
                entPer.setCoopType(CoopType.FriendDriver.getValue());
            }

            entPer.setCarid(carsEntity.getCarID());
            entPer.setPlateNumber(carsEntity.getPlateNumber());
            enterpriseCooperatePerDao.update(entPer);

        } else {
            entPer = new EnterpriseCooperatePerEntity();
            // ?????????????????????
            String niChen = systemPersonEntity.getNiChen();
            entPer.setGuid(RandomTool.getGUId());
            entPer.setEntID(entId);
            entPer.setCoopObjectID(baseUserId);
            entPer.setCoopType(CoopType.FriendDriver.getValue());
            // ???????????? ????????????????????????
            if (isCheckCoopDriver == CommonProperties.INT_ONE) {
                entPer.setInviteState(InviteState.Wait.getValue());
            } else {
                entPer.setInviteState(InviteState.Pass.getValue());
            }
            entPer.setInviteType(InviteType.CarToEnt.getValue());
            entPer.setIsDelete(ConstantUtil.BYTE_FALSE);
            entPer.setNameRemark(niChen);
            entPer.setPinYin(PinyinUtil.getPinYin(niChen));
            entPer.setAddTime(DateUtil.getSqlTime());
            entPer.setPhoneNumber(systemPersonEntity.getMobilePhoneNumber());
            entPer.setOrgGuid("0");
            entPer.setAllOrgGuid("0");
            entPer.setCarid(carsEntity.getCarID());
            entPer.setPlateNumber(carsEntity.getPlateNumber());
            enterpriseCooperatePerDao.insert(entPer);

        }

        // ????????????????????????
        EnterpriseCooperateCarEntity entCar = enterpriseCooperateCarDao.getCoopCarByEntIdAndPersonId(entId, baseUserId);
        if (null == entCar) {
            entCar = new EnterpriseCooperateCarEntity();
            entCar.setGuid(RandomTool.getGUId());
            entCar.setCarid(carsEntity.getCarID());
            entCar.setEntid(entId);
            entCar.setOwnerpersonid(baseUserId);
            entCar.setCoopType(CoopType.FriendCar.getValue());
            entCar.setInviteType(InviteType.CarToEnt.getValue());
            if (isCheckCoopDriver == CommonProperties.INT_ONE) {
                entCar.setInviteState(InviteState.Wait.getValue());
            } else {
                entCar.setInviteState(InviteState.Pass.getValue());
            }

            entCar.setIsDelete(ConstantUtil.BYTE_FALSE);
            entCar.setEdittime(DateUtil.getSqlTime());
            entCar.setFirstDriverPersonID(baseUserId);
            enterpriseCooperateCarDao.insert(entCar);

        } else {
            entCar.setCarid(carsEntity.getCarID());
            entCar.setInviteState(InviteState.Wait.getValue());
            enterpriseCooperateCarDao.update(entCar);
        }

        if (isCheckCoopDriver != CommonProperties.INT_ONE) {
            // 1.50??????????????????
            coopcarListTac.setCarListReisCachByEntity(ConstantUtil.COOP_CAR_LIST + entId, entCar);
            if (StringUtils.isNotBlank(carsEntity.getCarID())) {
                carListTac.setCarListReisCachByEntity(ConstantUtil.CAR_LIST + entId, carsEntity);
            }
        }
    }

    /**
     * ??????????????????
     *
     * @param entId
     * @return
     */
    @Override
    @Deprecated
    public EntCoopInfoVo getCoopEntDetail(String entId, String baseUserId) {
        // TODO EntCoopInfoVo ??????
        EntCoopInfoVo entCoopInfoVo = new EntCoopInfoVo();
        // ????????????
        EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(entId);
        if (null != enterpriseInfoEntity) {
            entCoopInfoVo.setID(enterpriseInfoEntity.getId());
            entCoopInfoVo.setName(enterpriseInfoEntity.getName());
            entCoopInfoVo.setAddress(enterpriseInfoEntity.getAddress());
            entCoopInfoVo.setCode(enterpriseInfoEntity.getCode());
            entCoopInfoVo.setEntAuditStatus(enterpriseInfoEntity.getAuditStatus());
            entCoopInfoVo.setEntTypeDictGUID(enterpriseInfoEntity.getDictGUID());
            entCoopInfoVo.setEntTypeDictName(dictDao.getDictNameByGuid(enterpriseInfoEntity.getDictGUID()));
            entCoopInfoVo.setLogo(enterpriseInfoEntity.getLogo());
            entCoopInfoVo.setProfile(enterpriseInfoEntity.getProfile());
            entCoopInfoVo.setStar(enterpriseInfoEntity.getStar());
            //??????
            entCoopInfoVo.setPopularity(enterpriseInfoEntity.getPopularity());
            ContactInfoVo contactInfoVo = new ContactInfoVo();


            // ???????????????
            SystemPersonEntity adminPersonEntity = syspersonDao.select(enterpriseInfoEntity.getOwerPersonID());
            if (null != adminPersonEntity) {
                //??????im??????
                ImUserEntity imUserEntity = imUserDao.getByPersonId(adminPersonEntity.getPersonID());
                //ContactInfoVo contactInfoVo = new ContactInfoVo();
                contactInfoVo.setID(adminPersonEntity.getPersonID());
                contactInfoVo.setName(adminPersonEntity.getNiChen());
                contactInfoVo.setLogo(adminPersonEntity.gethDpic());
                contactInfoVo.setPhone(adminPersonEntity.getMobilePhoneNumber());
                contactInfoVo.setLogitalkId(adminPersonEntity.getLogitalkId());
                if (imUserEntity != null) {
                    contactInfoVo.setDisplayName(imUserEntity.getName());
                } else {
                    contactInfoVo.setDisplayName("?????????");
                }
                // ???????????????
                EnterpriseCooperatePerEntity ownCoop = enterpriseCooperatePerDao.getEntOwnerPerson(entId,
                        null);
                if (null != ownCoop) {
                    contactInfoVo.setImGuid(ownCoop.getImGUID());
                }
                //?????????????????????????????????
                EnterpriseCooperatePerEntity enterpriseCooperatePerEntity
                        = enterpriseCooperatePerDao.getEntContact(entId);
                if (null != enterpriseCooperatePerEntity) {
                    contactInfoVo.setPhone(enterpriseCooperatePerEntity.getPhoneNumber());
                    contactInfoVo.setName(enterpriseCooperatePerEntity.getNameRemark());
                } else {
                    EnterpriseCooperatePerEntity entCoopPer = enterpriseCooperatePerDao.getEntOwnerPerson(entId, null);
                    if (null != entCoopPer) {
                        contactInfoVo.setPhone(entCoopPer.getPhoneNumber());
                        contactInfoVo.setName(entCoopPer.getNameRemark());
                    }
                }

                entCoopInfoVo.setContact(contactInfoVo);
            }
            // ???????????????
            List<SystemPersonEntity> childPersonList = syspersonDao.getChildPersonList(entId);
            childPersonList.add(adminPersonEntity);// ????????????add??????

            //???????????????????????????
            Long entPerCount = enterpriseCooperatePerDao.getCoopEntIsVisiblePersonCount(entId, null);
            entCoopInfoVo.setContactNum(entPerCount.intValue());
            // ????????????
            Long CoopCarNum = enterpriseCooperateCarDao.getEntCarNum(entId, CoopType.FriendCar.getValue()); // ?????????
            Long SelfCarNum = enterpriseCooperateCarDao.getEntCarNum(entId, CoopType.SelfCar.getValue()); // ?????????
            entCoopInfoVo.setCoopCarNum(Integer.parseInt(CoopCarNum + ""));
            entCoopInfoVo.setSelfCarNum(Integer.parseInt(SelfCarNum + ""));
            // ?????????????????????
            EnterpriseCooperatePerEntity entPer = enterpriseCooperatePerDao.getCoopByEntIdAndPersonId(entId, baseUserId);
            if (null != entPer && entPer.getInviteState() == InviteState.Pass.getValue()) {
                entCoopInfoVo.setIsShareGps(TypeCastUtil.byteToBoolean(entPer.getIsShareGps()));
                entCoopInfoVo.setIsStarMark(TypeCastUtil.byteToBoolean(entPer.getIsStarMark()));
                entCoopInfoVo.setIsFriend(ConstantUtil.TRUE);

            } else {
                entCoopInfoVo.setIsFriend(ConstantUtil.FALSE);
            }
            // ????????????????????????
            long currentCarrierOrdersNum = taskOrdersCarDao.getUnfinishTaskCount(baseUserId, entId);
            long totaltCarrierOrdersNum = taskOrdersCarDao.getAllTaskCount(baseUserId, entId);
            entCoopInfoVo.setCurrentCarrierOrdersNum(Integer.parseInt(currentCarrierOrdersNum + ""));
            entCoopInfoVo.setTotaltCarrierOrdersNum(Integer.parseInt(totaltCarrierOrdersNum + ""));
        }
        return entCoopInfoVo;
    }

    /**
     * ????????????????????????
     */
    @Override
    public void cancleEntCoop(String entId, String baseUserId) {
        List<EnterpriseCooperateCarEntity> entCarList = enterpriseCooperateCarDao.getCarByPersonIdAndEntId(baseUserId,entId);
        // ??????????????????
        for(EnterpriseCooperateCarEntity entCar:entCarList){
        	enterpriseCooperateCarDao.delete(entCar.getGuid());
        	NewFriendLogEntity newFriendLogEntity = newFriendLogDao.getNewFriendCar(baseUserId, entId, entCar.getCarid());
        	if(newFriendLogEntity != null){
        		newFriendLogDao.delete(newFriendLogEntity.getNewFriendGUID());
        	}
        }

        // ?????????????????????????????????????????????
//        friendsDao.cancelEntFriend(entId, baseUserId);

        //??????carTag
//        grimlockServiceHelper.deleteCarTag(baseUserId, GpsDeviceType.Mobile.getValue().toString(), entId);
    }

    /**
     * ?????????????????????????????????????????????????????????????????????????????????
     */
    @Override
    public void quitEnt(String baseUserId, String entId) {
        EnterpriseCooperatePerEntity entPer = enterpriseCooperatePerDao.getCoopByEntIdAndPersonId(entId, baseUserId);
        TValide.notNull(entPer, CoopEntEnums.CoopEntErrors.NOT_OWN_DRIVER_FAIL);
        if (entPer.getInviteState() != InviteState.Pass.getValue()
                || entPer.getCoopType() != CoopType.SelfDriver.getValue()) {
            throw new ServiceException(CoopEntEnums.CoopEntErrors.NOT_OWN_DRIVER_FAIL);
        }
        // ????????????????????????
        entPer.setInviteState(InviteState.Stop.getValue());
        enterpriseCooperatePerDao.update(entPer);

        // ??????????????????
        EnterpriseCooperateCarEntity entCar = enterpriseCooperateCarDao.getSelfCarByEntIdAndPersonId(entId, baseUserId);
        if (null != entCar) {
            if (StringUtils.isNotBlank(entCar.getFirstDriverPersonID())
                    && entCar.getFirstDriverPersonID().equals(baseUserId)) {
                entCar.setFirstDriverPersonID(null);
            }
            if (StringUtils.isNotBlank(entCar.getSecondDriverPersonID())
                    && entCar.getSecondDriverPersonID().equals(baseUserId)) {
                entCar.setSecondDriverPersonID(null);
            }
            enterpriseCooperateCarDao.update(entCar);
            //1.50??????????????????
            coopcarListTac.setCarListReisCachByEntity(ConstantUtil.COOP_CAR_LIST + entCar.getEntid(), entCar);
        }
        // ???????????????????????????????????????
        friendsDao.cancelEntFriend(entId, baseUserId);

//        //??????carTag
//        grimlockServiceHelper.deleteCarTag(baseUserId, GpsDeviceType.Mobile.getValue().toString(), entId);
    }

    /**
     * ????????????
     */
    @Override
    public void shareGps(boolean share, String entId, String baseUserId) {
        try {
            EnterpriseCooperatePerEntity enterpriseCooperatePerEntity = enterpriseCooperatePerDao
                    .getCoopByEntIdAndPersonId(entId, baseUserId);
            if (null != enterpriseCooperatePerEntity) {
                enterpriseCooperatePerEntity.setIsShareGps(TypeCastUtil.booleanToByte(share));
                enterpriseCooperatePerDao.update(enterpriseCooperatePerEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            throw new ServiceException(CoopEntEnums.CoopEntErrors.MESSAGE_HANDLE_FAIL);
        }
    }

    @Override
    public List<DriverEntVo> getCoopEntList(String baseUserId) {
        List<DriverEntVo> driverEntVos = new ArrayList<>();
        EntFriendDto entFriendDto = new EntFriendDto();
        entFriendDto.setBaseUserId(baseUserId);
        String coopTypes = CoopType.FriendCar.getValue().toString();
        List<EnterpriseCooperateCarEntity> entCarList = enterpriseCooperateCarDao.getDriverCarList(null,baseUserId,coopTypes);
//        List<EnterpriseCooperatePerEntity> enterpriseCooperatePerEntityList = enterpriseCooperatePerDao.getCoopEntByPersonId(baseUserId);
        entCarList.forEach(n -> {
            String entId = n.getEntid();
            EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(entId);
            if(enterpriseInfoEntity != null){
                DriverEntVo driverEntVo = new DriverEntVo();
                driverEntVo.setEntId(entId);
                driverEntVo.setEntName(enterpriseInfoEntity.getName());
                driverEntVo.setNameRemark(n.getFirstDriverName());
                driverEntVo.setEntLogo(enterpriseInfoEntity.getLogo());
                driverEntVo.setAuditStatus(enterpriseInfoEntity.getAuditStatus());
                driverEntVos.add(driverEntVo);
            }
        });
        return driverEntVos;
    }

    @Override
    @Deprecated
    public List<EnterpriseVo> getMyEnterpriseList(BaseRequestDTO baseRequestDTO) {
        List<EnterpriseVo> entList = new ArrayList<>();
        String baseUserId = baseRequestDTO.getBaseUserId();
        Long allPerNum = 0L;
        List<EnterpriseCooperatePerEntity> perList
                = enterpriseCooperatePerDao.getSelfEntByPersonId(baseUserId);
        for (EnterpriseCooperatePerEntity per : perList) {
            //??????????????????????????????
            EnterpriseCensorEntity censorEntity = enterpriseCensorDao.getCensorEntityByEntId(per.getEntID());
            if(censorEntity != null && (censorEntity.getAccountState() == 3||censorEntity.getAccountState() == 2)){
                continue;
            }
            EnterpriseVo entOrgVo = new EnterpriseVo();
            String entId = per.getEntID();
            EnterpriseInfoEntity entprise = enterpriseInfoDao.select(entId);
            entOrgVo.setId(entprise.getId());
            entOrgVo.setName(entprise.getName());
            entOrgVo.setAuditStatus(entprise.getAuditStatus());
            entOrgVo.setEntLogo(entprise.getLogo());
            allPerNum = enterpriseCooperatePerDao.getEntPerNumByOrgGuid(entId, ConstantUtil.String_CODE_ZERO, false);
            allPerNum += enterpriseCooperatePerDao.getFriendDriverNum(entId, per.getOrgGuid());
            entOrgVo.setPerNum(allPerNum);
            entList.add(entOrgVo);
        }
        return entList;
    }

    /**
     * ??????????????? ,?????? ID
     */
    @Override
    public String getYunMai(String yunMaiCode) {
        //TValide.notNull(yunMaiCode, EnterpriseErrorEnums.DTO_IS_NOT_NULL);
        EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.getYmNumber(yunMaiCode);
        //TValide.notNull(enterpriseInfoEntity, EnterpriseErrorEnums.MESSAGE_ENT_NOT_EXIST);
        if (enterpriseInfoEntity != null) {
            if (StringUtils.isNoneBlank(enterpriseInfoEntity.getId())) {
                return enterpriseInfoEntity.getId();
            }
        }
        return null;
    }

    /**
     * ???????????????????????????????????????????????????/???????????????
     *
     * @param baseUserId
     * @param entId
     * @return0
     */
    public Integer getDriverCoopType(String baseUserId, String entId) {
        Integer coopType = CoopType.Unknown.getValue();
        EnterpriseCooperatePerEntity entPer = enterpriseCooperatePerDao.getCoopByEntIdAndPersonId(entId, baseUserId);
        if (null != entPer) {
            coopType = entPer.getCoopType();
        }
        return coopType;
    }

    /**
     * ??????????????????
     */
    private DriverEntVo getOrgPer(String entId, String personId, DriverEntVo driverEntVo) {
        DriverEntVo infoVo = new DriverEntVo();
        EnterpriseCooperatePerEntity per = null;
        String orgName = "";
        String orgGuid = "";
        if (entId != null) {
            per = enterpriseCooperatePerDao.getCoopByEntIdAndPersonId(entId, personId);
            if (per != null) {
                orgGuid = per.getOrgGuid();
                //String[] orgGuids = orgGuid.split(",");

                String[] orgGuids = new String[0];
                if (StringUtils.isNotBlank(orgGuid)) {
                	orgGuids =  orgGuid.split(",");
                }
                List<String> orgList = Arrays.asList(orgGuids);
                if (!"0".equals(orgGuid)) {
                    for (int i = 0; i < orgList.size() - 1; i++) {
                        String org = orgList.get(i);
                        EnterpriseOrganizationDictEntity orgEntity = enterpriseOrganizationDictDao.select(org);
                        if (null != orgEntity) {
                            String name = orgEntity.getName();
                            orgName += name + ",";
                        }

                    }
                    if (null != orgList && !orgList.isEmpty()) {
                        EnterpriseOrganizationDictEntity orgEntity = enterpriseOrganizationDictDao
                                .select(orgList.get(orgList.size() - 1));
                        String name = orgEntity.getName();
                        orgName += name;
                    }
                }
            }
        }
        infoVo.setOrgGuid(orgGuid);
        infoVo.setOrgName(orgName);
        return infoVo;
    }


    /**
     * ???????????????????????????
     */
    @Override
    public List<FriendShortInfoVo> getContactList(String entId, String personId) {
        List<FriendShortInfoVo> resultList = new ArrayList<FriendShortInfoVo>();
        EnterpriseCooperatePerEntity entDriver = enterpriseCooperatePerDao.getCoopByEntAndPersonByDelete(entId,
                personId);
        if (null == entDriver) {
            throw new ServiceException(CoopEntEnums.CoopEntErrors.NOT_EXITS_COOP_ENT);
        }
        String orgId = entDriver.getOrgGuid();
        List<EnterpriseCooperatePerEntity> entPerList;
        if ("0".equals(orgId)) {
            orgId = null;
        }
        entPerList = enterpriseCooperatePerDao.getCoopEntIsVisiblePerson(entId, orgId);
        // ????????????
        EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(entId);
        for (EnterpriseCooperatePerEntity entPer : entPerList) {
            FriendShortInfoVo friendShortInfoVo = new FriendShortInfoVo();

            friendShortInfoVo.setEntId(entId);
            friendShortInfoVo.setEntName(enterpriseInfoEntity.getName());
            friendShortInfoVo.setJobName(entPer.getPosition());
            friendShortInfoVo.setImGUID(entPer.getImGUID());
            friendShortInfoVo.setNameRemark(entPer.getNameRemark());
            friendShortInfoVo.setPinYin(entPer.getPinYin());
            friendShortInfoVo.setPersonID(entPer.getCoopObjectID());

            SystemPersonEntity systemPersonEntity = syspersonDao.select(entPer.getCoopObjectID());
            if(systemPersonEntity != null){
                friendShortInfoVo.setPersonID(systemPersonEntity.getPersonID());
                friendShortInfoVo.setLogo(systemPersonEntity.gethDpic());
                friendShortInfoVo.setMobile(systemPersonEntity.getMobilePhoneNumber());
            }
            resultList.add(friendShortInfoVo);
        }
        return resultList;
    }

    // ?????????????????????
    @Override
    public FriendShortInfoVo getContactDetail(String personId) {
        FriendShortInfoVo friendShortInfoVo = new FriendShortInfoVo();
        // ????????????
        SystemPersonEntity systemPersonEntity = syspersonDao.select(personId);
        TValide.notNull(systemPersonEntity, EnterpriseErrorEnums.MESSAGE_USER_NOT_EXIST);
        // ????????????
        EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(systemPersonEntity.getEntID());
        TValide.notNull(enterpriseInfoEntity, EnterpriseErrorEnums.MESSAGE_ENT_NOT_EXIST);
        friendShortInfoVo.setPersonID(personId);
        friendShortInfoVo.setEntId(systemPersonEntity.getEntID());
        friendShortInfoVo.setEntName(enterpriseInfoEntity.getName());
        friendShortInfoVo.setNameRemark(systemPersonEntity.getNiChen());
        friendShortInfoVo.setLogo(systemPersonEntity.gethDpic());
        friendShortInfoVo.setJobName(systemPersonEntity.getChildAdminType().toString());
        friendShortInfoVo.setMobile(systemPersonEntity.getMobilePhoneNumber());
        return friendShortInfoVo;
    }

    @Override
    public List<EntCooperAndDriverVo> getOrgCooperList(CooperAndDriverDTO cooperAndDriverDTO, BaseRequestDTO baseRequestDTO) {
        String entId = cooperAndDriverDTO.getEntId();
        String appKey = baseRequestDTO.getAppKey();
        String orgGuid = cooperAndDriverDTO.getOrgGuid();
        String baseUserId = baseRequestDTO.getBaseUserId();
        List<EntCooperAndDriverVo> coopVoList = new ArrayList<>();

        EnterpriseCooperatePerEntity cooper
                = enterpriseCooperatePerDao.getCoopByEntIdAndPersonId(entId, baseUserId);
        String myOrg = cooper.getOrgGuid();
        Boolean all = false;
        if (orgGuid.equals(myOrg)) {
            all = true;
        }
        
        PageHelper.startPage(cooperAndDriverDTO.getPageIndex(),cooperAndDriverDTO.getPageSize());
        List<EnterpriseCooperatePerEntity> entCooperAndDriverList = enterpriseCooperatePerDao
                .getEntPerByOrgGuid(entId, orgGuid, all);
        for (EnterpriseCooperatePerEntity entity : entCooperAndDriverList) {
            SystemPersonEntity sysperson = syspersonDao.select(entity.getCoopObjectID());
            if (sysperson == null || sysperson.getIsDelete().equals(ConstantUtil.BYTE_TRUE)) {
                continue;
            }
            EntCooperAndDriverVo vo = new EntCooperAndDriverVo();
            vo.setCoopType(entity.getCoopType());
            vo.setGuid(entity.getGuid());
            vo.setPersonId(entity.getCoopObjectID());
            vo.setPersonLogo(sysperson.gethDpic());
            vo.setNameRemark(entity.getNameRemark());
            vo.setPinYin(entity.getPinYin());
            vo.setPhoneNumber(entity.getPhoneNumber());
            vo.setPosition(entity.getPosition());
            vo.setImGUID(entity.getImGUID());
            vo.setIsManager(PersonType.SysAdmin.getValue().equals(entity.getPersonType())
                    || PersonType.SuperAdmin.getValue().equals(entity.getPersonType()));
            vo.setIsActivate(InviteState.Activation.getValue().equals(entity.getInviteState()));
            vo.setLogitalkId(sysperson.getLogitalkId());
            vo.setIsVisible(TypeCastUtil.byteToBoolean(entity.getIsVisible()));
            if (CoopType.SelfDriver.getValue().equals(entity.getCoopType()) || CoopType.FriendDriver.getValue().equals(entity.getCoopType())) {
                EnterpriseCooperateCarEntity entCar
                        = enterpriseCooperateCarDao.getCarsByEntIdAndPersonId(entity.getEntID(), entity.getCoopObjectID());
                if (entCar != null) {
                    if (entCar.getFirstDriverPersonID() != null) {
                        vo.setIsFirstDriver(true);
                    } else {
                        vo.setIsFirstDriver(false);
                    }
                    CarsEntity car = carsDao.select(entCar.getCarid());
                    if (car != null) {
                        vo.setCarLogo(car.getLogo());
                        vo.setCarId(car.getCarID());
                        vo.setPlateNumber(car.getPlateNumber());
                        vo.setRatedLoad(car.getRatedLoad());
                        vo.setRatedVolume(car.getRatedVolume());
                        String carLength = dictDao.getDictNameByGuid(car.getCarLengthDictGUID());
                        if (null != carLength) {
                            vo.setCarLength(carLength);
                        }
                        String carCoachType = dictDao.getDictNameByGuid(car.getCoachTypeDictGUID());
                        if (carCoachType != null) {
                            vo.setCarCoachType(carCoachType);
                        }
                    }
                }
            }
            coopVoList.add(vo);
        }
        return coopVoList;
    }


    /**
     * ??????????????????????????????????????? ??????????????????  ???????????????????????????
     * @param  confirmGuid
     */
    @Override
    public void joinEnt(String confirmGuid,Integer status){
        //????????????????????????????????????
        if(null == status){
          status = ConstantUtil.INTEGER_CODE_ONE;
        }
        Integer finalStatus = status;

        //???????????????
        for(StartSoftApiKeyType startSoftApiKeyType : StartSoftApiKeyType.values()){
            taskExecutor.execute(()-> starSoftSender.driverJoinEnt(confirmGuid,finalStatus,startSoftApiKeyType));

        }


    }


}
