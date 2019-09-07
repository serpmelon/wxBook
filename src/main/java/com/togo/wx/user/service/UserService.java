package com.togo.wx.user.service;

import com.togo.wx.common.entity.ResponseResult;
import com.togo.wx.common.entity.Result;
import com.togo.wx.common.util.SessionUtil;
import com.togo.wx.common.util.WXUtil;
import com.togo.wx.user.dao.UserMapper;
import com.togo.wx.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * @date Created in 2019年08月18日 10:59
 * @since 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WXUtil wxUtil;

    public UserEntity getUser(int id) {

        UserEntity user = userMapper.getUserEntity(id);

        return user;
    }

    public void insertUser(UserEntity userEntity) {

//        userMapper.insertUserEntity(userEntity);
    }

    public Result login(String code, HttpServletRequest request) {

        String openId = SessionUtil.getOpenId(request);
        if (StringUtils.isBlank(openId)) {

            ResponseResult responseResult = wxUtil.login(code);

            if (responseResult.getCode() != HttpServletResponse.SC_OK){

                return Result.getErrorResultWithMessage("login error, status: " + responseResult.getCode());
            }

            SessionUtil.
        }

        return new Result();
    }
}