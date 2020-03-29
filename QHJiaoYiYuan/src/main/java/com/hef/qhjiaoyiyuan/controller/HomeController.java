package com.hef.qhjiaoyiyuan.controller;

import com.hef.qhjiaoyiyuan.bean.Article;
import com.hef.qhjiaoyiyuan.bean.ArticleStatusEnum;
import com.hef.qhjiaoyiyuan.bean.Channel;
import com.hef.qhjiaoyiyuan.bean.exchange.ParamCondition;
import com.hef.qhjiaoyiyuan.service.ArticleService;
import com.hef.qhjiaoyiyuan.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/3/5
 * @Author lifei
 */
@Controller
@RequestMapping(value = {"/","/home"})
public class HomeController {

    private ChannelService channelService;

    private ArticleService articleService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(boolean error, Model model){
        model.addAttribute("loginError", error);
        return "login";
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model){
        List<Channel> channelList = channelService.findAllChannel();
        List<Integer> status = new ArrayList<>();
        status.add(ArticleStatusEnum.ISSUE.getStatus());
        List<Article> articleList = articleService.findArticleListByParamCondition(new ParamCondition.Builder()
                .status(status).builder());
        model.addAttribute("channelList", channelList);
        model.addAttribute("articleList", articleList);
        return "index";
    }
}
