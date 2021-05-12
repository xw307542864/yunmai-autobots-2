package com.logibeat.cloud.msg.sender;

import com.logibeat.cloud.common.cache.util.JsonMapper;
import com.logibeat.cloud.core.constant.SenderConstantUtil;
import com.logibeat.cloud.persistent.dao.SysSettingDao;
import com.logibeat.cloud.persistent.entity.TaskOrdersGoodsEntity;
import com.logibeat.cloud.util.tools.HttpClientUtil;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class BizorderSender {


    @Autowired
    private SysSettingDao sysSettingDao;

    public JSONPrompt getCargo(String relationOrderId,Integer relationOrderType){
        List<TaskOrdersGoodsEntity> goodsList = new ArrayList<>();
        String rootUrl = sysSettingDao.getValue(SenderConstantUtil.BIZORDER_URL);
        String postUrl = rootUrl+SenderConstantUtil.QUERY_CARGO;
        Map<String, String> params = new TreeMap<>();
        params.put("relationOrderId", relationOrderId);
        params.put("relationOrderType", relationOrderType.toString());
        String result = HttpClientUtil.post(postUrl, params);
        JSONPrompt jsonPrompt = JsonMapper.buildNonDefaultMapper().fromJson(result, JSONPrompt.class);
        return jsonPrompt;
    }

}
