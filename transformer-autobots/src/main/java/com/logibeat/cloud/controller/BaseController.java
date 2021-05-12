package com.logibeat.cloud.controller;

import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.dto.BaseRequestDTO;
import com.logibeat.cloud.core.helper.CacheHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wilson on 2016/12/13.
 */

@Scope("prototype")
public class BaseController {

    @Autowired
    protected CacheHelper cacheHelper;

     /**
     * ThreadLocal确保高并发下每个请求的request，response都是独立的
     */
    public static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<HttpServletRequest>();

    public static ThreadLocal<HttpServletResponse> currentResponse = new ThreadLocal<HttpServletResponse>();

    public static ThreadLocal<HttpHeaders> currentHeaders  = new ThreadLocal<HttpHeaders>();

    public BaseRequestDTO baseRequestDTO;

    public String baseUserId = null;

    public String baseEntId = null;

    public String appKey = null;

    /**
     * 线程安全初始化reque，respose对象
     *
     * @param request
     * @param response
     * @param httpHeaders
     * @date 2016年11月24日
     * @author Wilson
     */
    @ModelAttribute
    public void initReqAndRep(HttpServletRequest request, HttpServletResponse response, @RequestHeader HttpHeaders httpHeaders) {
        currentRequest.set(request);
        currentResponse.set(response);
        currentHeaders.set(httpHeaders);
        initData(request, httpHeaders);
    }


    //初始化
    public void initData(HttpServletRequest request, HttpHeaders httpHeaders){
        String  clintType = httpHeaders.getFirst(ConstantUtil.CLIENTTYPE);
        appKey  = clintType+"-";
        String cacheId = httpHeaders.getFirst(ConstantUtil.BASEUSERID);
        baseUserId = cacheHelper.getUserParam(appKey, cacheId, ConstantUtil.USERID);
//        if(ConstantUtil.UNICRON.equals(appKey) || ConstantUtil.WEB.equals(appKey)){
//            baseEntId = cacheHelper.getUserParam(appKey, cacheId, ConstantUtil.ENTID);
//        }
        baseRequestDTO = new BaseRequestDTO();
        baseRequestDTO.setClientType(httpHeaders.getFirst(ConstantUtil.CLIENTTYPE));
        baseRequestDTO.setAppKey(appKey);
        baseRequestDTO.setBaseEntId(baseEntId);
        baseRequestDTO.setEquipment(httpHeaders.getFirst(ConstantUtil.EQUIPMENT));
        baseRequestDTO.setEquipmentType(httpHeaders.getFirst(ConstantUtil.CLIENTSYSTEM));
        baseRequestDTO.setBaseUserId(baseUserId);
        baseRequestDTO.setCacheId(cacheId);
        baseRequestDTO.setIp(request.getRemoteAddr());
    }
}
