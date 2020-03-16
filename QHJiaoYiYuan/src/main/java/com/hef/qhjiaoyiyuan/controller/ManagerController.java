package com.hef.qhjiaoyiyuan.controller;

import com.hef.qhjiaoyiyuan.base.PageResult;
import com.hef.qhjiaoyiyuan.base.impl.ArticleQuery;
import com.hef.qhjiaoyiyuan.bean.Article;
import com.hef.qhjiaoyiyuan.bean.Channel;
import com.hef.qhjiaoyiyuan.bean.exchange.OptionArticleParam;
import com.hef.qhjiaoyiyuan.bean.exchange.ResponseResult;
import com.hef.qhjiaoyiyuan.service.ArticleService;
import com.hef.qhjiaoyiyuan.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 更新文章内容
     * @param aid
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateArticleShow/{aid}", method = RequestMethod.GET)
    public String updateArticleShow(@PathVariable("aid") int aid, Model model){
        Article article = articleService.findArticleByAId(aid);
        List<Channel> channelList = channelService.findAllChannel();
        model.addAttribute("channelList",channelList);
        model.addAttribute("article", article);
        return "manage/updateArticle";
    }

    /**
     * 更新作品
     * @param article
     * @return
     */
    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
    public String updateArticle(Article article){
        articleService.updateArticle(article);
        return "redirect:/managerController/updateSuccessShow";
    }

    @RequestMapping(value = "/updateSuccessShow", method = RequestMethod.GET)
    public String updateSuccessShow(){
        return "manage/updateSuccess";
    }

    /**
     * 更新或删除作品
     * @param optionArticleParam
     * @return
     */
    @RequestMapping(value = "/updateOrDeleteArticleByStatus")
    @ResponseBody
    public ResponseResult<String> updateOrDeleteArticleByStatus(@RequestBody OptionArticleParam optionArticleParam){
        return articleService.updateOrDeleteArticleByStatus(optionArticleParam);
    }
}
