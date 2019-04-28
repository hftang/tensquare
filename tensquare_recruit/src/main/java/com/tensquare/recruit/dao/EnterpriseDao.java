package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author hftang
 * @date 2019-04-28 14:20
 * @desc
 */
public interface EnterpriseDao extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise> {

    List<Enterprise> findByIshot(String ishot);//where ishot =?

}
