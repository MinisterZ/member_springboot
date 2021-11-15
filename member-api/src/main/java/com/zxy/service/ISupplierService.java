package com.zxy.service;

import com.zxy.base.Result;
import com.zxy.entity.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxy.req.PasswordREQ;
import com.zxy.req.SupplierREQ;

/**
 * <p>
 * 供应商信息表 服务类
 * </p>
 *
 * @author zxy-www.zxy.com
 * @since 2021-04-01
 */
public interface ISupplierService extends IService<Supplier> {
    /**
     * 分页条件查询
     * @param page 当前页码
     * @param size  每页查询条数
     * @param req   请求条件参数
     * @return
     */
    Result search(long page, long size, SupplierREQ req);


    Result deleteById(int id);

    Result update(int id , Supplier supplier);



}
