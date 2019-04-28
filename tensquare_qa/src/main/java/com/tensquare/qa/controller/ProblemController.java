package com.tensquare.qa.controller;
import com.tensquare.qa.client.LabelClient;
import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;
<<<<<<< HEAD
	
	
=======

	@Autowired
	private LabelClient labelClient;

	@Autowired
	private HttpServletRequest request;


>>>>>>> e20378517f0f9c4d51350be57406a2dbbb1cf89f
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findAll());
	}
<<<<<<< HEAD
	
=======

>>>>>>> e20378517f0f9c4d51350be57406a2dbbb1cf89f
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
	}
<<<<<<< HEAD

=======
>>>>>>> e20378517f0f9c4d51350be57406a2dbbb1cf89f
	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findSearch(searchMap));
    }

<<<<<<< HEAD
    @Autowired
    private HttpServletRequest request;
=======
>>>>>>> e20378517f0f9c4d51350be57406a2dbbb1cf89f

	/**
	 * 增加
	 * @param problem
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Problem problem  ){
		Claims claims=(Claims)request.getAttribute("user_claims");
		if(claims==null){
			return new Result(false,StatusCode.ACCESSERROR,"权限不足");
		}
		//设置用户ID
		problem.setUserid( claims.getId());
		problemService.add(problem);
		return new Result(true,StatusCode.OK,"增加成功");
	}
<<<<<<< HEAD
	
=======

>>>>>>> e20378517f0f9c4d51350be57406a2dbbb1cf89f
	/**
	 * 修改
	 * @param problem
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Problem problem, @PathVariable String id ){
		problem.setId(id);
<<<<<<< HEAD
		problemService.update(problem);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
=======
		problemService.update(problem);
		return new Result(true,StatusCode.OK,"修改成功");
	}

>>>>>>> e20378517f0f9c4d51350be57406a2dbbb1cf89f
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		problemService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}

	/**
	 *  最新回答列表
	 * @param label
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value="/newlist/{label}/{page}/{size}",method= RequestMethod.GET)
	public Result findNewListByLabelId(@PathVariable  String label,@PathVariable  int page,@PathVariable  int size){
		Page<Problem> pageList = problemService.findNewListByLabelId(label, page, size);
		PageResult pageResult =new PageResult( pageList.getTotalElements(),pageList.getContent() );
		return new Result(true,StatusCode.OK,"查询成功",pageResult);
	}

	/**
	 *  热门回答列表
	 * @param label
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value="/hotlist/{label}/{page}/{size}",method= RequestMethod.GET)
	public Result findHotListByLabelId(@PathVariable  String label,@PathVariable  int page,@PathVariable  int size){
		Page<Problem> pageList = problemService.findHotListByLabelId(label, page, size);
		PageResult pageResult =new PageResult( pageList.getTotalElements(),pageList.getContent() );
		return new Result(true,StatusCode.OK,"查询成功",pageResult);
	}

	/**
	 *  等待回答列表
	 * @param label
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value="/waitlist/{label}/{page}/{size}",method= RequestMethod.GET)
	public Result findWaitListByLabelId(@PathVariable  String label,@PathVariable  int page,@PathVariable  int size){
		Page<Problem> pageList = problemService.findWaitListByLabelId(label, page, size);
		PageResult pageResult =new PageResult( pageList.getTotalElements(),pageList.getContent() );
		return new Result(true,StatusCode.OK,"查询成功",pageResult);
	}


<<<<<<< HEAD
	@Autowired
	private LabelClient labelClient;

=======
>>>>>>> e20378517f0f9c4d51350be57406a2dbbb1cf89f
	/**
	 * 根据标签ID查询标签对象
	 * @param labelid
	 * @return
	 */
	@RequestMapping(value="/label/{labelid}",method = RequestMethod.GET)
	public Result findLabelById(@PathVariable String labelid){
		return labelClient.findById(labelid);
	}

}
