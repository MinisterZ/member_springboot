package com.zxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxy.base.Page;
import com.zxy.base.Result;
import com.zxy.entity.Goods;
import com.zxy.entity.Supplier;
import com.zxy.mapper.GoodsMapper;
import com.zxy.req.GoodsREQ;
import com.zxy.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxy.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author zxy-www.zxy.com
 * @since 2021-04-01
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    //供应商删除时需判断是否有商品引用
    @Override
    public List<Goods> selectBySupplierId(int supplierId) {

        QueryWrapper query = new QueryWrapper();

        query.eq("supplier_id",supplierId);

        return baseMapper.selectList(query);
    }

    @Override
    public Result search(long page, long size, GoodsREQ req) {
        if(req == null){
            req = new GoodsREQ();
        }
        //在GoodSMapper已经实现了 seacrchPage分页查询
        IPage<Goods> data =
                     baseMapper.searchPage(new Page<Goods>(page,size),req);
        return Result.ok(data);
    }

    @Autowired
    private ISupplierService supplierService;

    @Override
    public Result findById(int id) {
        //1.查询商品详情
        Goods goods = baseMapper.selectById(id);
        //2.通过供应商id查询供应商名称
        if (goods !=null  && goods.getSupplierId() !=null){
            Supplier supplier = supplierService.getById(goods.getSupplierId());
            if (supplier != null){
                goods.setSupplierName(supplier.getName());
            }
        }
        return Result.ok(goods);
    }

    @Override
    public Result update(int id, Goods goods) {
        if(goods.getId() == null){
            goods.setId(id);
        }
        int i = baseMapper.updateById(goods);

        if(i<1){
            return Result.error("修改商品信息失败");
        }
        return Result.ok();
    }
}
