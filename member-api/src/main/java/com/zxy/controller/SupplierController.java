package com.zxy.controller;


import com.zxy.base.Result;
import com.zxy.entity.Supplier;
import com.zxy.req.SupplierREQ;
import com.zxy.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 供应商信息表 前端控制器
 * </p>
 *
 * @author zxy-www.zxy.com
 * @since 2021-04-01
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;

    /**
     * 分页查询供应商列表
     * localhost:6666/supplier/list/search/1/20
     */
        @PostMapping("/list/search/{page}/{size}")
        public Result search(@PathVariable("page")  int page,
                              @PathVariable("size") int size,
                              @RequestBody(required = false) SupplierREQ req){
        return supplierService.search(page,size,req);
        }

    /**
     * 添加供应商
     * localhost:6666/supplier
     */
    @PostMapping
        public  Result add(@RequestBody Supplier supplier){
            boolean b = supplierService.save(supplier);
            if(b){
                return Result.ok();
            }
            return Result.error("新增失败！");
        }

    /**
     * 通过供应商id删除数据
     * 先判断是否被商品引用再来删除
     */
    @DeleteMapping("/{id}")
        public  Result delete(@PathVariable("id") int id){
            return  supplierService.deleteById(id);


        }

    /**
     * 查询供应商详情
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable("id") int id){
        Supplier supplier = supplierService.getById(id);
        return Result.ok(supplier);
    }
    /**
     * 修改供应商详情
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable("id")int id,
                          @RequestBody Supplier supplier){
        return supplierService.update(id, supplier);
    }

}
