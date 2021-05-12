package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.FileEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileDao {
    int delete(String guid);

    int insert(FileEntity entity);

    FileEntity select(String guid);

    int update(FileEntity entity);

    List<FileEntity> getFileList(@Param("objectId") String objectId, @Param("fileType")Integer fileType, @Param("objectType")Integer objectType);

}