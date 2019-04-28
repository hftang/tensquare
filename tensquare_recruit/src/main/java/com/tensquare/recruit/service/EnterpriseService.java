package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hftang
 * @date 2019-04-28 14:33
 * @desc
 */
@Service
public class EnterpriseService {
    @Autowired
    EnterpriseDao enterpriseDao;

    public List<Enterprise> hotlist(String ishot) {

        return enterpriseDao.findByIshot(ishot);
    }
}
