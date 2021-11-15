package com.zxy.controller;


import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.zxy.base.Result;
import com.zxy.entity.Staff;
import com.zxy.req.StaffREQ;
import com.zxy.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 员工信息表 前端控制器
 * </p>
 *
 * @author zxy-www.zxy.com
 * @since 2021-04-01
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private IStaffService staffService;

    /**
     * 分页条件查询
     * localhost:6666/staff/list/search/1/20
     */
    @PostMapping("/list/search/{page}/{size}")
    public Result search(@PathVariable("page")long page,
                         @PathVariable("size")long size,
                         @RequestBody(required = false) StaffREQ req){
    return staffService.search(page,size,req);
    }

    /**
     * 新增员工
     * localhost:6666/staff
     */
    @PostMapping
    public Result add(@RequestBody Staff staff){
        return staffService.add(staff);
    }

    /**
     * 删除员工
     * localhost:6666/staff
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id")int id){
        boolean b = staffService.removeById(id);
        if(b){
            return Result.ok();
        }
        return Result.error("删除员工信息失败!");
    }
    /**
     * 查询员工
     * localhost:6666/staff
     */
    @GetMapping("/{id}")
    public  Result get(@PathVariable("id")int id){
       Staff staff = staffService.getById(id);
       return Result.ok(staff);
    }

    /**
     * 修改员工信息
     * localhost:6666/staff
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable int id,
                          @RequestBody Staff staff){
       return  staffService.update(id,staff);
    }
}
