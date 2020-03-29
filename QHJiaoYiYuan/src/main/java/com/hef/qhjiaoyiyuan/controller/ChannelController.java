package com.hef.qhjiaoyiyuan.controller;

import com.hef.qhjiaoyiyuan.bean.Article;
import com.hef.qhjiaoyiyuan.bean.exchange.ParamCondition;
import com.hef.qhjiaoyiyuan.bean.exchange.Result;
import com.hef.qhjiaoyiyuan.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Date 2020/3/29
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/channelController")
public class ChannelController {


    private ArticleService articleService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 根据条件查询文件
     * @param paramCondition
     * @return
     */
    @RequestMapping(value = "/findArticleListByCondition", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<Article>> findArticleListByCondition(@RequestBody ParamCondition paramCondition){
        return this.articleService.findArticleListByCondition(paramCondition);
    }
}
