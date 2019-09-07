package com.togo.wx.common.util;

import com.alibaba.fastjson.JSONObject;
import com.togo.wx.common.entity.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author taiyn
 * @version 1.0
 * @date Created in 2019年08月26日 21:36
 * @since 1.0
 */
@Component
public class WXUtil {

    @Value("${app.wx.loginUrl}")
    private String wxLoginUrl;
    @Value("${app.wx.secret}")
    private String secret;
    @Value("${app.wx.appId}")
    private String appId;

    @Autowired
    private HttpUtil httpUtil;

    public ResponseResult login(String code) {

        Map<String, Object> params = new HashMap<>();
        params.put("js_code", code);
        params.put("appid", appId);
        params.put("secret", secret);
        params.put("grant_type", "authorization_code");

        ResponseResult responseResult = httpUtil.get(wxLoginUrl, params);

        return responseResult;
    }
}