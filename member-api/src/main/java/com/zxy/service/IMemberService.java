package com.zxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxy.base.Result;
import com.zxy.entity.Member;
import com.zxy.req.MemberREQ;

/**
 * @version JDK  1.8.151
 * @Author: Mirrors
 * @Description: Chengdu City
 */

public interface IMemberService extends IService<Member> {

    /**
     * 分页条件查询
     * @param page 当前页码
     * @param size  每页查询条数
     * @param req   请求条件参数
     * @return
     */
    Result search(long page,long size,MemberREQ req);

    /**
     *
     * 更新数据
     * @param id
     * @param member
     * @return
     */
    Result update(int id,Member member);
}
