package com.hef.qhjiaoyiyuan.service.impl;

import com.hef.qhjiaoyiyuan.base.PageResult;
import com.hef.qhjiaoyiyuan.base.impl.ArticleQuery;
import com.hef.qhjiaoyiyuan.bean.Article;
import com.hef.qhjiaoyiyuan.bean.ArticleStatusEnum;
import com.hef.qhjiaoyiyuan.bean.exchange.*;
import com.hef.qhjiaoyiyuan.dao.ArticleDao;
import com.hef.qhjiaoyiyuan.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.ArrayList;
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
        if (article == null) return;
        if (article.getIssueTime() == null) article.setIssueTime(Instant.now());
        articleDao.saveArticle(article);
    }

    @Override
    public void updateArticle(Article article) {
        if (article == null || article.getAid() == null) return;
        Article oldArticle = articleDao.findArticleByAid(article.getAid());
        BeanUtils.copyProperties(article, oldArticle, "aId");
        oldArticle.setIssueTime(Instant.now());
        articleDao.updateArticle(oldArticle);
    }

    @Override
    public Article findArticleByAId(Integer aId) {
        if (aId == null) return null;
        return articleDao.findArticleByAid(aId);
    }

    @Override
    public List<Article> findArticlesByConditions(ArticleCondition articleCondition) {
        if (articleCondition == null) return new ArrayList<>();
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
        PageResult<Article> pageResult = new PageResult<>(totalItemNum, articleQuery.getCurrentPage(), articleQuery.getPageSize());
        List<Article> resultList = articleDao.findPageArticleListByQuery(articleQuery, pageResult.getCurrentPageFirstItemNum(), pageResult.getPageSize());
        pageResult.setPageObjList(resultList);
        return pageResult;
    }

    @Override
    @Transactional
    public Result<String> updateOrDeleteArticleByStatus(OptionArticleParam optionArticleParam) {
        Result<String> result = new Result<>();
        try {
            if (optionArticleParam == null || optionArticleParam.getAid() == null || optionArticleParam.getStatus() == null) {
                throw new IllegalArgumentException("aid and status cannot empty");
            }
            if (optionArticleParam.getStatus() == ArticleStatusEnum.DELETE.getStatus()) {
                articleDao.deleteArticleByAid(optionArticleParam.getAid());
            } else if (optionArticleParam.getStatus() == ArticleStatusEnum.CONSOLE.getStatus()
                    || optionArticleParam.getStatus() == ArticleStatusEnum.ISSUE.getStatus()) {
                Article oldArticle = articleDao.findArticleByAid(optionArticleParam.getAid());
                oldArticle.setStatus(optionArticleParam.getStatus());
                oldArticle.setIssueTime(Instant.now());
                articleDao.updateArticle(oldArticle);
            }
            result.setStatus(ResultStatus.SUCCESS.getStatus());
            result.setInfo(ResultStatus.SUCCESS.getInfo());
            return result;
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL.getStatus());
            result.setInfo(e.toString());
            return result;
        }
    }

    @Override
    public Result<List<Article>> findArticleListByCondition(ParamCondition paramCondition) {
        List<Article> resultList = new ArrayList<>();
        try {
            List<Article> articleList = findArticleListByParamCondition(paramCondition);
            if (!CollectionUtils.isEmpty(articleList)){
                resultList.addAll(articleList);
            }
            return new Result<>(ResultStatus.SUCCESS.getStatus(), ResultStatus.SUCCESS.getInfo(), resultList);
        } catch (Exception e) {
            return new Result<>(ResultStatus.FAIL.getStatus(), e.toString(),null);
        }
    }

    @Override
    public List<Article> findArticleListByParamCondition(ParamCondition paramCondition) {
        return articleDao.findArticleListByParamCondition(paramCondition);
    }
}
