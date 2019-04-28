package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hftang
 * @date 2019-04-28 15:52
 * @desc
 */
@Service
public class RecruitService {

    @Autowired
    private RecruitDao recruitDao;

    //推荐职位
    public List<Recruit> recommand() {

        return recruitDao.findTop6ByStateOrderByCreatetimeDesc("2");

    }

    //最新职位

    public List<Recruit> newList() {
        return recruitDao.findTop6ByStateNotOrderByCreatetimeDesc("0");
    }

}
