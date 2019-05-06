package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 吐槽控制层
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {

        return new Result(true, StatusCode.OK, "查询全部完成", spitService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{spitId}")
    public Result findById(@PathVariable("spitId") String spitId) {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(spitId));

    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit) {
        spitService.save(spit);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    //更新
    @RequestMapping(method = RequestMethod.POST, value = "/{spitId}")
    public Result update(@RequestBody Spit spit, @PathVariable("spitId") String spitId) {

        spit.set_id(spitId);
        spitService.update(spit);

        return new Result(true, StatusCode.OK, "更新成功");
    }

    //删除
    @RequestMapping(method = RequestMethod.DELETE, value = "/{spitId}")
    public Result delete(@PathVariable("spitId") String spitId) {
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    //根据父id查询
    @RequestMapping(method = RequestMethod.GET, value = "/comment/{parentid}/{page}/{size}")
    public Result findByParentid(@PathVariable String parentid, @PathVariable int page, @PathVariable int size) {
        Page<Spit> pageData = spitService.findByParentid(parentid, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Spit>(pageData.getTotalElements(), pageData.getContent()));
    }

    //点赞
    @RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitId) {
        String userId = "111";
        //使用redis来判断是否已点赞
        if (redisTemplate.opsForValue().get("thumbup_" + userId) != null) {
            return new Result(false, StatusCode.REPERROR, "你已点过赞了");
        }
        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_" + userId, 1);
        return new Result(true, StatusCode.OK, "点赞成功");
    }


}
