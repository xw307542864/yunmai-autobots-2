package com.logibeat.cloud.persistent.dao;


import com.logibeat.cloud.persistent.entity.EntStarSoftEntity;
import org.apache.ibatis.annotations.Param;

public interface EntStarSoftDao {

    EntStarSoftEntity getByStar(@Param("entId") String entId);


    EntStarSoftEntity getBySoft(@Param("starsoftId") String starsoftId);



}
