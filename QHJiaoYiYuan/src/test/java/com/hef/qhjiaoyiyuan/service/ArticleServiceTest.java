package com.hef.qhjiaoyiyuan.service;

import com.hef.qhjiaoyiyuan.bean.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Date 2020/3/11
 * @Author lifei
 */
@SpringBootTest
public class ArticleServiceTest {

    private ArticleService articleService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }


    @Test
    public void saveArticleTest(){
        Article article = new Article();
        articleService.saveArticle(article);
    }

}
