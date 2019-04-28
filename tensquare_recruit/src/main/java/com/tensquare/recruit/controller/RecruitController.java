package com.tensquare.recruit.controller;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.service.RecruitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hftang
 * @date 2019-04-28 15:57
 * @desc
 */
@RestController
@RequestMapping("/recruit")
public class RecruitController {
    @Autowired
    RecruitService recruitService;


    //推荐职位
    @GetMapping("/search/recommend")
    public Result recommend() {

        return new Result(true, StatusCode.OK, "查询成功", recruitService.recommand());
    }

    //最新职位
    @GetMapping("/search/newlist")
    public Result newList() {
        return new Result(true, StatusCode.OK, "查询成功", recruitService.newList());
    }
}
