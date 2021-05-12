package com.logibeat.cloud.services.impl;

import com.logibeat.cloud.common.enumtype.CertificationStatus;
import com.logibeat.cloud.common.enumtype.CoopType;
import com.logibeat.cloud.common.enumtype.InviteCarStatus;
import com.logibeat.cloud.vo.CarShortInfoVo;
import com.logibeat.cloud.core.constant.SettingConstantUtil;
import com.logibeat.cloud.core.dto.AuditVehicleDto;
import com.logibeat.cloud.core.dto.CarDTO;
import com.logibeat.cloud.persistent.entity.SystemPersonEntity;
import com.logibeat.cloud.remote.CarSender;
import com.logibeat.cloud.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {


    @Autowired
    private CarSender carSender;


    /**
     * 添加/更新车辆
     * @param auditVehicleDto
     */
     public CarShortInfoVo addOrUpdateVehicle(AuditVehicleDto auditVehicleDto, SystemPersonEntity person){
         Integer coopType = auditVehicleDto.getCoopType();
         String entCarId = auditVehicleDto.getEntCarId();
         CarDTO carDTO = new CarDTO();
         carDTO.setPlateNumber(auditVehicleDto.getPlateNumber());
         carDTO.setPlateColor(auditVehicleDto.getPlateColorCode());
         carDTO.setCoopType(coopType);
         carDTO.setEntCarId(entCarId);
         carDTO.setCarId(auditVehicleDto.getCarId());
         carDTO.setCarLengthCode(auditVehicleDto.getCarLengthCode());
         carDTO.setCarLengthValue(auditVehicleDto.getCarLengthValue());
         carDTO.setCarTypeCode(auditVehicleDto.getCarTypeCode());
         carDTO.setCarTypeValue(auditVehicleDto.getCarTypeValue());
         carDTO.setPersonId(auditVehicleDto.getPersonId());
         carDTO.setFirstDriverPersonID(auditVehicleDto.getPersonId());
         carDTO.setFirstDriverName(person.getMyName());
         carDTO.setFirstDriverMobile(person.getLoginName());
         carDTO.setRatedVolume(auditVehicleDto.getRatedVolume());
         carDTO.setRatedLoad(auditVehicleDto.getRatedLoad());
         carDTO.setTransportTypeCode(auditVehicleDto.getTransportTypeCode());
         carDTO.setTransportTypeValue(auditVehicleDto.getTransportTypeValue());
         carDTO.setVehicleEngineCode(auditVehicleDto.getVehicleEngineCode());
//         carDTO.setInviteStatus(InviteCarStatus.waiting.getValue());
         if(coopType.equals(CoopType.SelfCar.getValue())){
        	 carDTO.setInviteStatus(InviteCarStatus.ent_waiting.getValue());
         }else {
        	 carDTO.setInviteStatus(InviteCarStatus.verified.getValue());
         }
         
         carDTO.setVehicleIdentificationCode(auditVehicleDto.getVehicleIdentificationCode());
         carDTO.setVehiclePic(auditVehicleDto.getVehiclePic());
         carDTO.setVehicleLicenseOwner(auditVehicleDto.getVehicleLicenseOwner());
         carDTO.setFirstDriverPersonID(auditVehicleDto.getPersonId());
         carDTO.setFirstDriverMobile(person.getLoginName());
         carDTO.setFirstDriverName(person.getMyName());
         StringBuffer vehicleLicenseBuffer = new StringBuffer();
         vehicleLicenseBuffer.append(auditVehicleDto.getOriginalVehicleLicense())
                 .append(",").append(auditVehicleDto.getCopyVehicleLicense1())
                 .append(",").append(auditVehicleDto.getCopyVehicleLicense2());
         carDTO.setVehicleLicense(vehicleLicenseBuffer.toString());
//         carDTO.setAuditStatus(CertificationStatus.waiting.getValue());
         carDTO.setAuditStatus(CertificationStatus.verified.getValue());
         carDTO.setSourceType(auditVehicleDto.getSourceType());
         CarShortInfoVo carShortInfoVo = carSender.addOrUpdateCar(carDTO,SettingConstantUtil.ADD_DRIVER_CAR);
         return  carShortInfoVo;
     }
}
