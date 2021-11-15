package com.zxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zxy.base.Page;
import com.zxy.base.Result;
import com.zxy.entity.Goods;
import com.zxy.entity.Supplier;
import com.zxy.mapper.SupplierMapper;
import com.zxy.req.SupplierREQ;
import com.zxy.service.IGoodsService;
import com.zxy.service.ISupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 供应商信息表 服务实现类
 * </p>
 *
 * @author zxy-www.zxy.com
 * @since 2021-04-01
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {

    @Override
    public Result search(long page, long size, SupplierREQ req) {
        QueryWrapper<Supplier> query = new QueryWrapper<>();
        if (req != null) {
            if (StringUtils.isNotBlank(req.getName())) {
                query.like("name", req.getName());
            }
            if (StringUtils.isNotBlank(req.getLinkman())) {
                query.like("linkman", req.getLinkman());
            }
            if (StringUtils.isNotBlank(req.getMobile())) {
                query.like("mobile", req.getMobile());
            }
        }
        //分页查询供应商
        IPage<Supplier> data = baseMapper.selectPage(new Page<Supplier>(page, size), query);
        return Result.ok(data);
        }

    @Autowired
    private IGoodsService goodsService;

    @Override
    public Result deleteById(int id) {
        //1.通过供应商id查询 商品里是否引用了供应商
        List<Goods> goodsList = goodsService.selectBySupplierId(id);
        //2.如果集合有数据就是被引用，则不然删除供应商
        if(CollectionUtils.isNotEmpty(goodsList)){
            return Result.error("该供应商被商品引用，不允许删除!");
        }
        //3.如果没有被引用，可以直接删除供应商
        int i = baseMapper.deleteById(id);
        if(i < 1){
            return Result.error("删除供应商失败!");
        }
        return Result.ok();
    }

    @Override
    public Result update(int id, Supplier supplier) {
        if(supplier.getId()== null){
            supplier.setId(id);
        }
        int i = baseMapper.updateById(supplier);
        if(i < 1){
            return Result.error("修改供应商信息失败!");
        }
        return Result.ok();
    }


}

