package com.hef.qhjiaoyiyuan.service.impl;

import com.hef.qhjiaoyiyuan.base.PageResult;
import com.hef.qhjiaoyiyuan.base.impl.ArticleQuery;
import com.hef.qhjiaoyiyuan.bean.Article;
import com.hef.qhjiaoyiyuan.bean.exchange.ArticleCondition;
import com.hef.qhjiaoyiyuan.dao.ArticleDao;
import com.hef.qhjiaoyiyuan.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Date 2020/3/11
 * @Author lifei
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleDao articleDao;

    @Autowired(required = false)
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    @Transactional
    public void saveArticle(Article article) {
        if (article==null) return;
        if (article.getIssueTime()==null) article.setIssueTime(Instant.now());
        articleDao.saveArticle(article);
    }

    @Override
    public void updateArticle(Article article) {
        if (article==null || article.getAid()==null) return;
        Article oldArticle = articleDao.findArticleByAid(article.getAid());
        BeanUtils.copyProperties(article, oldArticle,"aId");
        articleDao.updateArticle(oldArticle);
    }

    @Override
    public Article findArticleByAId(Integer aId) {
        if (aId==null) return null;
        return articleDao.findArticleByAid(aId);
    }

    @Override
    public List<Article> findArticlesByConditions(ArticleCondition articleCondition) {
        if (articleCondition==null) return new ArrayList<>();
        return articleDao.findArticlesByConditions(articleCondition);
    }

    @Override
    public List<Article> findAllArticle() {
        return articleDao.findAllArticle();
    }

    @Override
    public PageResult<Article> findPageArticleList(ArticleQuery articleQuery) {
        // 查询总的条数
        int totalItemNum = articleDao.findTotalArticleNumByQuery(articleQuery);
        PageResult<Article> pageResult = new PageResult<>(totalItemNum,articleQuery.getCurrentPage(),articleQuery.getPageSize());
        List<Article> resultList = articleDao.findPageArticleListByQuery(articleQuery, pageResult.getCurrentPageFirstItemNum(), pageResult.getPageSize());
        pageResult.setPageObjList(resultList);
        return pageResult;
    }
}
