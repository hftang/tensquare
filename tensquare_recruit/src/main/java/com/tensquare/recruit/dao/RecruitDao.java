package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author hftang
 * @date 2019-04-28 15:37
 * @desc 职位
 */
public interface RecruitDao extends JpaRepository<Recruit,String>, JpaSpecificationExecutor<Recruit> {

    //推荐职位按时间排序 时间倒序 1是热门
    public List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state);
    //非0的都是推荐职位
    public List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state);



}
