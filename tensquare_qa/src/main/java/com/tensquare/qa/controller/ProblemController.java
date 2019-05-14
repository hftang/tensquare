package com.tensquare.qa.controller;

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private HttpServletRequest request;

    //添加问题

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Problem problem) {
        //从域中获取到token

        String token = (String) request.getAttribute("claims_user");
        if (StringUtils.isEmpty(token)) {
            return new Result(false, StatusCode.ERROR, "token失效，请重新登录。");
        }

        problemService.add(problem);

        return new Result(true, StatusCode.OK, "添加问题成功");

    }


    //最新回答
    @RequestMapping(value = "/newlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result newlist(@PathVariable("labelid") String labelid, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Problem> pageData = problemService.newlist(labelid, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Problem>(pageData.getTotalElements(), pageData.getContent()));

    }

    //最多
    @RequestMapping(value = "/hotlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result hotlist(@PathVariable("labelid") String labelid, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Problem> pageData = problemService.hotlist(labelid, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Problem>(pageData.getTotalElements(), pageData.getContent()));
    }

    @RequestMapping(value = "/waitlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result waitlist(@PathVariable("labelid") String labelid, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Problem> pageData = problemService.waitlist(labelid, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Problem>(pageData.getTotalElements(), pageData.getContent()));
    }


}
