package com.zxy.service;

import com.zxy.base.Result;
import com.zxy.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxy.req.GoodsREQ;

import java.util.List;

/**
 * <p>
 * 商品信息表 服务类
 * </p>
 *
 * @author zxy-www.zxy.com
 * @since 2021-04-01
 */
public interface IGoodsService extends IService<Goods> {

    /**
     * 通过供应商id查询商品信息
     * @param supplierId
     * @return
     */
    List<Goods> selectBySupplierId(int supplierId);

    Result search(long page , long size , GoodsREQ req);

    Result findById(int id);

    Result update(int id, Goods goods);
}
