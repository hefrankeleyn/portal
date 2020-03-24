package com.hef.qhjiaoyiyuan.controller;

import com.hef.qhjiaoyiyuan.base.PageResult;
import com.hef.qhjiaoyiyuan.base.impl.ArticleQuery;
import com.hef.qhjiaoyiyuan.bean.Article;
import com.hef.qhjiaoyiyuan.bean.exchange.ResponseResult;
import com.hef.qhjiaoyiyuan.bean.exchange.ResultStatus;
import com.hef.qhjiaoyiyuan.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



/**
 * @Date 2020/3/14
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/articleController")
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 添加文章
     * @param article
     * @return
     */
    @RequestMapping(value = "/addArticleSubmit", method = RequestMethod.POST)
    public String addArticleSubmit(Article article){
        articleService.saveArticle(article);
        return "redirect:/articleController/showSuccessView/"+article.getStatus();
    }

    /**
     * 展示添加成功的页面
     * @param status
     * @return
     */
    @RequestMapping(value = "showSuccessView/{status}", method = RequestMethod.GET)
    public String showSuccessView(@PathVariable("status") int status,Model model){
        model.addAttribute("status", status);
        return "manage/addSuccess";
    }

    /**
     * 查询一页的数据
     * @return
     */
    @RequestMapping(value = "/findPageArticleResult", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<PageResult<Article>> findPageArticleResult(@RequestBody ArticleQuery articleQuery){
        PageResult<Article> pageArticleList = articleService.findPageArticleList(articleQuery);
        return new ResponseResult<>(ResultStatus.SUCCESS, pageArticleList);
    }

    @RequestMapping(value = "/showArticleContent/{aid}")
    public String showArticleContent(@PathVariable("aid") int aid, Model model){
        Article article = articleService.findArticleByAId(aid);
        model.addAttribute("article",article);
        return "articleContent";
    }
}
