package com.tensquare.recruit.controller;

import com.tensquare.recruit.service.EnterpriseService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hftang
 * @date 2019-04-28 14:35
 * @desc
 */
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    EnterpriseService enterpriseService;


    //查询热门企业
    @GetMapping(value = "/search/hotlist")
    public Result hostlist() {

        return new Result(true, StatusCode.OK, "查询成功", enterpriseService.hotlist("1"));
    }

}
