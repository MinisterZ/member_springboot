package com.zxy.controller;

import com.zxy.base.Result;
import com.zxy.entity.Member;
import com.zxy.req.MemberREQ;
import com.zxy.service.IMemberService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;


/**
 * @version JDK  1.8.151
 * @Author: Mirrors
 * @Description: Chengdu City
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IMemberService memberService;


    //    localhost:6666/member/list/search/1/20
    @PostMapping("/list/search/{page}/{size}")
    public Result search(@PathVariable("page") long page,
                         @PathVariable("size") long size,
                         @RequestBody MemberREQ req ) {
        logger.info("查询会员列表：page={}, size={}", page, size);
        return memberService.search(page, size, req);
    }

    //  localhost:6666/member
    @PostMapping
    public Result add(@RequestBody Member member){
        boolean b = memberService.save(member);
        if(b){
            return Result.ok();
        }
        return Result.error("新增会员信息失败！");
    }

    //localhost:6666/member/33
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id")Integer id){
       boolean b= memberService.removeById(id);
        if(b){
            return Result.ok();
        }
        return Result.error("删除失败！");

    }

    //localhost:6666/member/34
    @GetMapping("/{id}")
    public Result get(@PathVariable int id){
        Member member = memberService.getById(id);
        return Result.ok(member);
    }

    //localhost:6666/member/34
    @PutMapping("/{id}")
    public Result update(@PathVariable int id,
                         @RequestBody Member member){

        return memberService.update(id,member);
    }


}
