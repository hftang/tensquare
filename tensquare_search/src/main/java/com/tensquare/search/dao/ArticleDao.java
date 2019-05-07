package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author hftang
 * @date 2019-05-07 14:25
 * @desc
 */
public interface ArticleDao extends ElasticsearchRepository<Article, String> {

    public Page<Article> findByTitleAndContentLike(String title, String content, Pageable pageable);

}
