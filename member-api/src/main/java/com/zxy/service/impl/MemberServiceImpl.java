package com.zxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxy.base.Page;
import com.zxy.base.Result;
import com.zxy.entity.Member;
import com.zxy.mapper.MemberMapper;
import com.zxy.req.MemberREQ;
import com.zxy.service.IMemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;



/**
 * @version JDK  1.8.151
 * @Author: Mirrors
 * @Description: Chengdu City
 */


//继承mabatis提供的ServiceImpl
@Service
public class MemberServiceImpl

        extends ServiceImpl<MemberMapper,Member>
        implements IMemberService {

    @Override
    public Result search(long page, long size, MemberREQ req) {
        QueryWrapper query = new QueryWrapper();
        if(req != null) {
            if(StringUtils.isNotBlank(req.getName())) {
                query.like("name", req.getName());
            }
            if(StringUtils.isNotBlank(req.getCardNum())) {
                query.like("card_num", req.getCardNum());
            }
            if(req.getBirthday() != null) {
                query.eq("birthday", req.getBirthday());
            }
            if(StringUtils.isNotBlank(req.getPayType())) {
                query.eq("pay_type", req.getPayType());
            }
        }

        // 封装分页对象
        IPage<Member> p = new Page<>(page ,size);
        IPage<Member> data = baseMapper.selectPage(p, query);

        return Result.ok(data);
    }

    @Override
    public Result update(int id, Member member) {
        if(member.getId()==null){
            member.setId(id);
        }
        int i = baseMapper.updateById(member);
        if (i<1){
            return Result.error("修改会员信息失败！");
        }
        return Result.ok();
    }


}
