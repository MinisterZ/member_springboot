package com.zxy.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.sun.org.apache.regexp.internal.RE;
import com.zxy.base.Result;
import com.zxy.entity.Staff;
import com.zxy.req.PasswordREQ;
import com.zxy.service.IStaffService;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version JDK  1.8.151
 * @Author: Mirrors
 * @Description: Chengdu City
 */

@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    IStaffService staffService;

    /**
     *    校验原密码是否正确
     *    localhost:6666/user/pwd
     */
    @PostMapping("/pwd")
    public Result checkPwd(@RequestBody PasswordREQ req){
       return  staffService.checkPassword(req);
    }
    /**
     *    提交修改密码
     *    localhost:6666/user/pwd
     */
    @PutMapping("/pwd")
    public Result updatePwd(@RequestBody PasswordREQ req){
        return  staffService.updatePassword(req);
    }


    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody  Staff staff){
          return  staffService.login(staff.getUsername(),staff.getPassword());
    }

    /**
     * 通过token 获取用户信息
     * localhost:6666/user/info?token=
     */
    @GetMapping("/info")
    public Result getUserInfo(@RequestParam String token){
        return staffService.getUserInfo(token);
    }

    @PostMapping("/logout")
    public  Result logout(){
        return Result.ok();
    }

}
