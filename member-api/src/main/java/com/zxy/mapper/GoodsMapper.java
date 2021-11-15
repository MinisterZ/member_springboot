package com.zxy.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxy.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxy.req.GoodsREQ;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品信息表 Mapper 接口
 * </p>
 *
 * @author zxy-www.zxy.com
 * @since 2021-04-01
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 分页查询商品列表
     * 1. 第一个参数传递分页对象 Page（此对象封装当前页码，还有每次查询多少条）
     * 2. 查询条件， @Param 取别名
     */
    IPage<Goods> searchPage(IPage<Goods>page, @Param("req") GoodsREQ req);
}
