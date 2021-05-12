package com.logibeat.cloud.services.impl;

import com.logibeat.cloud.common.enumtype.FileObjectType;
import com.logibeat.cloud.common.valide.TValide;
import com.logibeat.cloud.core.dto.UploadFileInfoDto;
import com.logibeat.cloud.errorenum.FileErrorEnums;
import com.logibeat.cloud.persistent.dao.FileDao;
import com.logibeat.cloud.persistent.entity.FileEntity;
import com.logibeat.cloud.services.FileService;
import com.logibeat.cloud.util.tools.ImageUtil;
import com.logibeat.cloud.util.tools.RandomTool;
import com.logibeat.cloud.util.tools.TypeCastUtil;
import com.logibeat.cloud.util.tools.aliyun.AliyunFileEntity;
import com.logibeat.cloud.util.tools.aliyun.AliyunFileType;
import com.logibeat.cloud.util.tools.exception.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	protected FileDao fileDao;


	/**
	 * 文件保存
	 */
	/**
	 * @throws TException
	 */
	@Override
	public FileEntity addFile(UploadFileInfoDto uploadFileInfoDto, String path, String savePath, String rootPath,
							  String objectId, Integer objectType, Integer fileType, String personId) throws TException {

		AliyunFileType keyType = null;

		String fileBase64 = uploadFileInfoDto.getFileBase64();
		// 上传图片到服务器
		String fileName = uploadFileInfoDto.getFileName() + ".jpg";

		// 上传阿里云目录
		if (objectType == FileObjectType.PERSONLOGO.getCode() || objectType == FileObjectType.CARLOGO.getCode()) {
			keyType = AliyunFileType.ImageAvatar;
		} else {
			keyType = AliyunFileType.ImageBiz;
		}

		AliyunFileEntity aliyunFileEntity = ImageUtil.generateImage(fileBase64, rootPath, path, fileName, keyType);
		TValide.notNull(aliyunFileEntity, FileErrorEnums.FileError.UPLOAD_FAIL);

		// 保存文件记录到数据库
		FileEntity fileEntity = new FileEntity();
		fileEntity.setGuid(RandomTool.getGUId());
		fileEntity.setFileType(fileType);
		fileEntity.setObjectId(objectId);
		fileEntity.setObjectType(objectType); // 认证
		fileEntity.setPersonId(personId);
		fileEntity.setName(uploadFileInfoDto.getFileName());
		fileEntity.setSuffix(uploadFileInfoDto.getFileSuffix());
		fileEntity.setSize(uploadFileInfoDto.getFileSize());
		fileEntity.setOrd(uploadFileInfoDto.getOrd());
		fileEntity.setAddtime(new Timestamp(new Date().getTime()));
		fileEntity.setEdittime(new Timestamp(new Date().getTime()));
		fileEntity.setPath(aliyunFileEntity.getPath());
		fileEntity.setFullpath(aliyunFileEntity.getHttpName() + aliyunFileEntity.getPath());
		fileDao.insert(fileEntity);

		return fileEntity;
	}

	/**
	 * 获取文件
	 */
	@Override
	public List<FileEntity> getFileList(String objectId, Integer fileType, Integer objectType) {
		return fileDao.getFileList(objectId, fileType, objectType);
	}

	/**
	 * 
	 * 方法: isExists 描述: 检查文件是否已经存在
	 * 
	 * @param objectId
	 * @param fileType
	 * @param objectType
	 * @return
	 * @see FileService#isExists(String, Integer,
	 *      Integer)
	 */
	@Override
	public Boolean isExists(String objectId, Integer fileType, Integer objectType) {
		List<FileEntity> fileList = getFileList(objectId, fileType, objectType);
		if (TypeCastUtil.isNullOrEmpty(fileList)) {
			return false;
		}
		return true;
	}

	/**
	 * 获取图片全路径 @Title: getPicFullPath @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param @param fileList @param @return 设定文件 @return
	 * String 返回类型 @throws
	 */
	public String getPicFullPath(List<FileEntity> fileList, String type) {
		String pics = "";
		if (fileList.size() == 1) {
			pics = fileList.get(0).getFullpath();
		} else if (fileList.size() > 1) {
			for (int i = 0; i < fileList.size() - 1; i++) {
				pics += fileList.get(i).getFullpath() + type;
			}
			pics += fileList.get(fileList.size() - 1).getFullpath();
		}
		return pics;
	}

}
