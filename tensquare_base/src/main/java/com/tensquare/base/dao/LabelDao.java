package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author hftang
 * @date 2019-04-26 13:22
 * @desc 返回Label
 *
 * JpaSpecificationExecutor 使用分页查询的时候使用这个类；
 *  第一个参数是 返回类型  第二参数是传入类型
 *
 *  JpaRepository是一般的查询
 *  JpaSpecificationExecutor 可以分页
 */
public interface LabelDao extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {


}
