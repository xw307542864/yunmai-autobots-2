package com.logibeat.cloud.services;


import com.logibeat.cloud.core.dto.UploadFileInfoDto;
import com.logibeat.cloud.persistent.entity.FileEntity;

import java.util.List;

public interface FileService {

    FileEntity addFile(UploadFileInfoDto uploadFileInfoDto, String path, String savePath, String rootPath, String objectId, Integer objectType, Integer fileType, String personId);

    List<FileEntity> getFileList(String objectId, Integer fileType, Integer objectType);

    /**
     *
    * @Title: isExists
    * @Description: 检查图片是否存在
    * @param objectId
    * @param fileType
    * @param objectType
    * @return
    * @return Boolean
     */
    Boolean isExists(String objectId, Integer fileType, Integer objectType);

    String getPicFullPath(List<FileEntity> fileList, String type);
    
}
