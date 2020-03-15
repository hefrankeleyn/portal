package com.hef.qhjiaoyiyuan.controller;

import com.hef.qhjiaoyiyuan.base.PageResult;
import com.hef.qhjiaoyiyuan.base.impl.ArticleQuery;
import com.hef.qhjiaoyiyuan.bean.Article;
import com.hef.qhjiaoyiyuan.bean.Channel;
import com.hef.qhjiaoyiyuan.service.ArticleService;
import com.hef.qhjiaoyiyuan.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Date 2020/3/11
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/managerController")
public class ManagerController {

    private ChannelService channelService;

    private ArticleService articleService;

    @Autowired
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/manageIndex", method = RequestMethod.GET)
    public String manageIndex(Model model, ArticleQuery articleQuery){
        // 查询所有的栏目
        List<Channel> channelList = channelService.findAllChannel();
        // 查询所有的文章
        PageResult<Article> pageResult = articleService.findPageArticleList(articleQuery);
        model.addAttribute("channelList", channelList);
        model.addAttribute("pageResult", pageResult);
        return "manage/manageIndex";
    }

    @RequestMapping(value = "/addArticlePage", method = RequestMethod.GET)
    public String addArticlePage(Model model){
        // 查询所有的栏目
        List<Channel> channelList = channelService.findAllChannel();
        model.addAttribute("channelList",channelList);
        return "manage/addArticle";
    }
}
