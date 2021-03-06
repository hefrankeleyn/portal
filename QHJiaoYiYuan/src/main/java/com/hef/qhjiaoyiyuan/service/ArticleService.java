package com.hef.qhjiaoyiyuan.service;

import com.hef.qhjiaoyiyuan.base.PageResult;
import com.hef.qhjiaoyiyuan.base.impl.ArticleQuery;
import com.hef.qhjiaoyiyuan.bean.Article;
import com.hef.qhjiaoyiyuan.bean.exchange.ArticleCondition;
import com.hef.qhjiaoyiyuan.bean.exchange.OptionArticleParam;
import com.hef.qhjiaoyiyuan.bean.exchange.ParamCondition;
import com.hef.qhjiaoyiyuan.bean.exchange.Result;

import java.util.List;

/**
 * 文章管理
 */
public interface ArticleService {

    /**
     * 保存文章信息
     * @param article
     */
    void saveArticle(Article article);

    /**
     * 更新文章信息
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 根据id 查询文章信息
     * @param aId
     * @return
     */
    Article findArticleByAId(Integer aId);

    /**
     * 根据条件查询文章信息
     * 文章的名字、作者、栏目的名字、文章的状态
     * @return
     */
    List<Article> findArticlesByConditions(ArticleCondition articleCondition);

    List<Article> findAllArticle();

    /**
     * 分页查询 文章内容
     * @param articleQuery
     * @return
     */
    PageResult<Article> findPageArticleList(ArticleQuery articleQuery);

    /**
     * 修改或删除作品
     * @param optionArticleParam
     * @return
     */
    Result<String> updateOrDeleteArticleByStatus(OptionArticleParam optionArticleParam);

    /** 根据条件查询文件 */
    Result<List<Article>> findArticleListByCondition(ParamCondition paramCondition);
    List<Article> findArticleListByParamCondition(ParamCondition paramCondition);
}
