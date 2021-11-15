package com.zxy.service.impl;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zxy.base.Page;
import com.zxy.base.Result;
import com.zxy.entity.Staff;
import com.zxy.mapper.StaffMapper;
import com.zxy.req.PasswordREQ;
import com.zxy.req.StaffREQ;
import com.zxy.service.IStaffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxy.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 员工信息表 服务实现类
 * </p>
 *
 * @author zxy-www.zxy.com
 * @since 2021-04-01
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {

    @Override
    public Result search(long page, long size, StaffREQ req) {
        //封装查询条件
        QueryWrapper<Staff> query = new QueryWrapper();
        if(req!= null){
            if(StringUtils.isNotBlank(req.getUsername()))
            {
                query.like("username",req.getUsername());
            }
            if (StringUtils.isNotBlank(req.getName())){
                query.like("name",req.getName());
            }
        }
        //分页查询列表数据
        IPage<Staff>data =
                baseMapper.selectPage(new Page<Staff>(page,size),query);
        return Result.ok(data);
    }

    @Override
    public Result add(Staff staff) {
        if(staff ==null || StringUtils.isBlank(staff.getUsername())){
            return Result.error("用户名不能为空！");
        }
       // 1. 检验提交的账号是否已存在
        Staff s = getByUsername(staff.getUsername());
        if(s != null){
            return Result.error("用户名已存在!");
        }
       // 2.密码加密
        String password = new BCryptPasswordEncoder().encode(staff.getPassword());
        staff.setPassword(password);
        //3.提交数据
        boolean b = this.save(staff);
        if (b){
            return Result.ok();
        }
        return Result.error("新增员工信息失败!");
    }



    public  Staff getByUsername(String username){
        QueryWrapper<Staff> query = new QueryWrapper<>();
        query.eq("username",username);
        return  baseMapper.selectOne(query);
    }

    @Override
    public Result update(int id, Staff staff) {
        if(staff.getId() == null){
            staff.setId(id);
        }

        //更新操作
        int i = baseMapper.updateById(staff);
        if(i < 1){
            return Result.error("修改员工信息失败!");
        }
        return Result.ok();
    }

    @Override
    public Result checkPassword(PasswordREQ req) {
       if(req == null || StringUtils.isBlank(req.getPassword()))
       {
           return Result.error("原密码不能为空!");
       }
       //通过用户id查询正确密码
       Staff staff = baseMapper.selectById(req.getUserId());
      //判断输入的密码是否正确                 输入的密码         数据库的密码
       boolean b =new BCryptPasswordEncoder().matches(req.getPassword(),staff.getPassword());
       if (b){
            return Result.ok();
       }
       return Result.error("原密码错误!");
    }

    @Override
    public Result updatePassword(PasswordREQ req) {
        if (req == null || StringUtils.isBlank(req.getPassword())) {
            return Result.error("新密码不能为空!");
        }

        //新密码加密
        String password = new BCryptPasswordEncoder().encode(req.getPassword());
        //更新操作
        Staff staff = baseMapper.selectById(req.getUserId());
        staff.setPassword(password);
        baseMapper.updateById(staff);
        return Result.ok();
    }

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Result login(String username, String password) {
        Result error = Result.error("用户名或者密码错误");
        if(StringUtils.isBlank(username) ||StringUtils.isBlank(password) ){
            return error;
        }

        //1.通过用户名查询数据
        Staff staff = getByUsername(username);
        //用户不存在
        if(staff == null){
            return error;
        }
        //2.用户存在，判断输入的密码和数据库密码是否一致
        boolean b = new BCryptPasswordEncoder().matches(password, staff.getPassword());
        if(!b){
            return error;
        }
        //3.密码正确 生成token
           String jwt =
           jwtUtil.createJWT(staff.getId()+"",staff.getUsername(),true);
        //4. 传给客户端
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);

        return Result.ok(map);
    }

    @Override
    public Result getUserInfo(String token){
        //1.解析令牌
        Claims claims = jwtUtil.parseJWT(token);
        //2.解析不到，或者用户名为空时，提供获取失败
        if(claims == null || StringUtils.isBlank(claims.getSubject())){
            return Result.error("获取用户令牌失败");
        }
        //3.获取用户名
        String username = claims.getSubject();
        //4.通过用户名查询对应用户信息
        Staff staff = getByUsername(username);
        return Result.ok(staff);
    }

}
