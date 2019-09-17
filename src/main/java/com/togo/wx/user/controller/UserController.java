package com.togo.wx.user.controller;

import com.togo.wx.common.entity.Result;
import com.togo.wx.user.entity.UserEntity;
import com.togo.wx.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
 * @date Created in 2019年08月18日 09:49
 * @since 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "information")
    public Result getUser(@RequestParam int uid){

        UserEntity userEntity = userService.getUser(uid);
        Result result = new Result();
        result.setCode(1);
        result.setSuccess(true);
        result.setData(userEntity);

        return result;
    }

    @PostMapping(value = "information")
    public Result insertUser(@RequestBody UserEntity userEntity) {

        if (userEntity != null){

            userService.insertUser(userEntity);
        }

        return new Result();
    }

    @PostMapping(value = "login")
    public Result login(@RequestParam String code) {

        return userService.login(code);
    }

    @PostMapping(value = "start")
    public Result startExam() {

        return null;
    }
}