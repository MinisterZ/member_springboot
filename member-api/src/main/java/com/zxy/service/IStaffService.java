package com.zxy.service;

import com.zxy.base.Result;
import com.zxy.entity.Staff;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxy.req.PasswordREQ;
import com.zxy.req.StaffREQ;

import java.awt.image.RescaleOp;

/**
 * <p>
 * 员工信息表 服务类
 * </p>
 *
 * @author zxy-www.zxy.com
 * @since 2021-04-01
 */
public interface IStaffService extends IService<Staff> {

    Result search(long page, long size, StaffREQ req);

    Result add(Staff staff);

    Result update(int id, Staff staff);

    //校验原密码是否正确
    Result checkPassword(PasswordREQ req);
    //更新密码
    Result updatePassword(PasswordREQ req);

    Result login(String username, String password);

    Result getUserInfo(String token);

}
