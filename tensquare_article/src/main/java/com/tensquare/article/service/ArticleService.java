package com.tensquare.article.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import util.IdWorker;

import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;

/**
 * 服务层
 *
 * @author Administrator
 */
@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;

    public void updateState(String id) {
        articleDao.updateState(id);
    }

    public void addThumpup(String id) {
        articleDao.addThumpup(id);
    }

    /**
     * 根据ID查询实体
     * @param id
     * @return
     */
    public Article findById(String id) {
        //首先从缓存中查询，如果缓存中没有再从数据库中查询并放入缓存
        Article article=(Article)redisTemplate.opsForValue().get("article_"+id  );

        if(article==null){
            article=articleDao.findById(id).get();
            redisTemplate.opsForValue().set("article_"+id,article,10, TimeUnit.HOURS );//放入缓存(1天过期)
            System.out.println("从数据库查询记录并放入缓存");
        }else{
            System.out.println("从缓存中查询数据");
        }
        return article;
    }

    /**
     * 修改
     * @param article
     */
    public void update(Article article) {
        articleDao.save(article);
        redisTemplate.delete("article_"+article.getId());//从缓存中移除值
    }
    /**
     * 删除
     * @param id
     */
    public void deleteById(String id) {
        articleDao.deleteById(id);
        redisTemplate.delete("article_"+id);//从缓存中移除值
    }


}
