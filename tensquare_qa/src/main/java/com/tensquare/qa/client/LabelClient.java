package com.tensquare.qa.client;

<<<<<<< HEAD
import com.tensquare.qa.client.impl.LabelClientImpl;
=======
>>>>>>> e20378517f0f9c4d51350be57406a2dbbb1cf89f
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 37269 on 2018/7/11.
 */
<<<<<<< HEAD
@FeignClient(value="tensquare-base",fallback = LabelClientImpl.class)
=======
@FeignClient("tensquare-base")
>>>>>>> e20378517f0f9c4d51350be57406a2dbbb1cf89f
public interface LabelClient {


    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @RequestMapping(value="/label/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id);


}
