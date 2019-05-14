package com.tensquare.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tensquare.user.dao.UserDao;
import io.jsonwebtoken.Claims;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import util.JwtUtil;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 发送短息
     */
    @RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.POST)
    public Result sendSms(@PathVariable String mobile) {
        userService.sendSms(mobile);

        return new Result(true, StatusCode.OK, "发送短信成功");
    }

    /**
     * 注册
     */
    @RequestMapping(value = "/regist/{code}", method = RequestMethod.POST)
    public Result regist(@PathVariable String code, @RequestBody User user) {
        String mobile = user.getMobile();
        //先从缓存中取
        String redis_checkcode = (String) redisTemplate.opsForValue().get("checkcode_" + mobile);
        if (redis_checkcode.isEmpty()) {
            return new Result(true, StatusCode.ERROR, "请先获取手机验证码");
        }

        //比较传过来的验证码 跟 缓存中的验证码是否一致
        if (!redis_checkcode.equals(code)) {
            return new Result(true, StatusCode.ERROR, "手机验证码有误");
        }

        userService.add(user);

        return new Result(true, StatusCode.OK, "账号注册成功");
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", userService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", userService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<User> pageList = userService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<User>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", userService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param user
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody User user) {
        userService.add(user);
        return new Result(true, StatusCode.OK, "增加成功");
    }


    /**
     * 修改
     *
     * @param user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody User user, @PathVariable String id) {
        user.setId(id);
        userService.update(user);
        return new Result(true, StatusCode.OK, "修改成功");
    }


    /**
     * 用户登陆
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user) {

        User user1 = userService.login(user.getMobile(), user.getPassword());
        if (user1 == null) {
            return new Result(false, StatusCode.LOGINERROR, "登录失败");
        }

        //登录成功 生成 token
        String token = jwtUtil.createJWT(user1.getId(), user1.getMobile(), "user");

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("roles", "user");


        return new Result(true, StatusCode.OK, "登录成功", map);

    }

    //删除用户

    /***
     * 必须是admin 角色 才能删除
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Result delete(@PathVariable String id) {
        userService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }


    @RequestMapping(value = "/incfans/{userid}/{x}", method = RequestMethod.POST)
    public void incFanscount(@PathVariable String userid, @PathVariable int x) {
        userService.incFanscount(userid, x);
    }

    @RequestMapping(value = "/incfollow/{userid}/{x}", method = RequestMethod.POST)
    public void incFollowcount(@PathVariable String userid, @PathVariable int x) {
        userService.incFollowcount(userid, x);
    }

}
