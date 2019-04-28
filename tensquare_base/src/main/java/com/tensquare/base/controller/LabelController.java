package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hftang
 * @date 2019-04-26 11:17
 * @desc restcontroller ResponseBody 以后不用写了 直接返回是json
 */
@RestController
@CrossOrigin
@RequestMapping("label")
public class LabelController {

    @Autowired
    private LabelService labelService;


    //查找全部
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        int i = 10 / 0;
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    //查找某个
    @RequestMapping(method = RequestMethod.GET, value = "/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId) {

        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(labelId));
    }

    //保存
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label) {

        labelService.save(label);

        return new Result(true, StatusCode.OK, "添加成功");
    }

    //修改
    @RequestMapping(method = RequestMethod.PUT, value = "/{labelId}")
    public Result update(@RequestBody Label label, @PathVariable("labelId") String labelId) {

        label.setId(labelId);
        labelService.update(label);

        return new Result(true, StatusCode.OK, "修改成功");

    }

    //删除
    @RequestMapping(method = RequestMethod.DELETE, value = "/{labelId}")
    public Result deleteById(@PathVariable("labelId") String labelId) {
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    //查询
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label) {

        //@RequestBody Label label 既可以转成对象 也可以转成 Map
        List<Label> labels = labelService.findSearch(label);
        return new Result(true, StatusCode.OK, "查询成功", labels);

    }

    //分页查询
    @PostMapping(value = "/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Label> pageData = labelService.pageQuery(label, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(pageData.getTotalElements(), pageData.getContent()));
    }


}
