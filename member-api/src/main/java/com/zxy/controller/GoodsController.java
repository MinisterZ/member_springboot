package com.zxy.controller;


import com.zxy.base.Result;
import com.zxy.entity.Goods;
import com.zxy.req.GoodsREQ;
import com.zxy.service.IGoodsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author zxy-www.zxy.com
 * @since 2021-04-01
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    /**
     *商品请求模糊分页查询
     * localhost:6666/goods/list/search/1/20
     */
    @PostMapping("/list/search/{page}/{size}")
    public Result search(@PathVariable("page")long page,
                         @PathVariable("size")long size,
                         @RequestBody(required = false) GoodsREQ req){

        return goodsService.search(page, size, req);
    }

    /**
     *新增商品
     * localhost:6666/goods
     */
    @PostMapping
    public Result add(@RequestBody Goods goods){
        boolean b = goodsService.save(goods);
        if(b){
            return Result.ok();
        }
        return Result.error("新增失败");
    }

    /**
     *删除商品
     * localhost:6666/goods
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id")int id){
        boolean b = goodsService.removeById(id);
        if(b){
            return Result.ok();
        }
        return Result.error("删除失败");
    }

    /**
     * 查询商品详情
     * localhost:6666/goods/1
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable("id") int id){
        return goodsService.findById(id);
    }

    /**
     * 更新商品信息
     * localhost:6666/goods/1
     */
    @PostMapping("/{id}")
    public Result update(@PathVariable("id") int id,
                          @RequestBody Goods goods){
        return goodsService.update(id, goods);
    }

}
