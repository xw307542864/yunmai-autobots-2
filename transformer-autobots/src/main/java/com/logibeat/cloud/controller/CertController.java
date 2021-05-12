package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.common.vo.CertVo;
import com.logibeat.cloud.core.dto.CertDto;
import com.logibeat.cloud.core.dto.SyncCertDto;
import com.logibeat.cloud.services.CertService;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "autobots.CertController")
@RequestMapping(value = "/driver/cert")
@Scope("prototype")
public class CertController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(CertController.class);

	@Autowired
	private CertService certService;

	/**
	 * 添加证件
	 *
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	@NotLogin
	public JSONPrompt add(@RequestBody CertDto certDto) {
		certService.add(certDto);
		return new JSONPrompt<>();
	}

	@RequestMapping(value = "/sysPersonCert")
	@ResponseBody
	@NotLogin
	public JSONPrompt sysPersonCert() {
		certService.insertAllPersonCard();
		return new JSONPrompt<>();
	}

	@RequestMapping(value = "/sysDriverCert")
	@ResponseBody
	@NotLogin
	public JSONPrompt sysDriverCert() {
		certService.insertAllDriverCard();
		return new JSONPrompt<>();
	}

	/**
	 * 添加证件
	 *
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	@NotLogin
	public JSONPrompt update(@RequestBody CertDto certDto) {
		certService.update(certDto);
		return new JSONPrompt<>();
	}

	/**
	 * 证件列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public JSONPrompt<List<CertVo>> list(String personId) {
		return new JSONPrompt<>(certService.list(personId));
	}

	/**
	 * 证件详情
	 *
	 * @return
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public JSONPrompt<CertVo> detail(String guid) {
		return new JSONPrompt<>(certService.detail(guid));
	}

	/**
	 * 删除证件
	 *
	 * @return
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	public JSONPrompt del(String guid) {
		certService.del(guid);
		return new JSONPrompt<>();
	}
	
	@RequestMapping(value = "/sync")
	@ResponseBody
	@NotLogin
	public JSONPrompt sync(@RequestBody SyncCertDto syncCertDto) {
		certService.sync(syncCertDto);
		return new JSONPrompt<>();
	}
}
