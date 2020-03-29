package com.hef.qhjiaoyiyuan.controller;

import com.hef.qhjiaoyiyuan.base.PageResult;
import com.hef.qhjiaoyiyuan.base.impl.ArticleQuery;
import com.hef.qhjiaoyiyuan.bean.Article;
import com.hef.qhjiaoyiyuan.bean.Channel;
import com.hef.qhjiaoyiyuan.bean.QHUser;
import com.hef.qhjiaoyiyuan.bean.exchange.OptionArticleParam;
import com.hef.qhjiaoyiyuan.bean.exchange.Result;
import com.hef.qhjiaoyiyuan.bean.exchange.ResultStatus;
import com.hef.qhjiaoyiyuan.service.ArticleService;
import com.hef.qhjiaoyiyuan.service.ChannelService;
import com.hef.qhjiaoyiyuan.service.QHUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    private QHUserService qhUserService;


    @Autowired
    public void setQhUserService(QHUserService qhUserService) {
        this.qhUserService = qhUserService;
    }

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

    /**
     * 跳转到栏目管理的页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/manageChannelView", method = RequestMethod.GET)
    public String manageChannelView(Model model){
        List<Channel> channelList = channelService.findAllChannel();
        model.addAttribute("channelList", channelList);
        return "manage/channelIndex";
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
    public Result<String> updateOrDeleteArticleByStatus(@RequestBody OptionArticleParam optionArticleParam){
        return articleService.updateOrDeleteArticleByStatus(optionArticleParam);
    }

    /**
     * 查询栏目信息
     * @param cid
     * @return
     */
    @RequestMapping(value = "/findChannelByCid/{cid}", method = RequestMethod.GET)
    @ResponseBody
    public Result<Channel> findChannelByCid(@PathVariable("cid") int cid){
        Channel channel = channelService.findChannelByCid(cid);
        return new Result<>(ResultStatus.SUCCESS.getStatus(),ResultStatus.SUCCESS.getInfo(), channel);
    }

    /**
     * 添加栏目
     * @return
     */
    @RequestMapping(value = "/addChannelView", method = RequestMethod.GET)
    public String addChannelView(){
        return "manage/addChannel";
    }

    /**
     * 添加栏目
     * @param channel
     * @param authentication
     * @return
     */
    @RequestMapping(value = "/addChannel", method = RequestMethod.POST)
    public String addChannel(Authentication authentication,Channel channel){
        String userName = authentication.getName();
        QHUser qhUser = qhUserService.findUserByLoginUsername(userName);
        channel.setQhUser(qhUser);
        channelService.saveOrUpdateChannel(channel);
        return "redirect:/managerController/manageChannelView";
    }

    /**
     * 更新栏目
     * @param cid
     * @return
     */
    @RequestMapping(value = "/updateChannelView/{cid}", method = RequestMethod.GET)
    public String updateChannelView(@PathVariable("cid") int cid, Model model){
        Channel channel = channelService.findChannelByCid(cid);
        model.addAttribute("channel", channel);
        return "manage/updateChannel";
    }

    /**
     * 删除栏目
     * @param cid
     * @return
     */
    @RequestMapping(value = "/deleteChannel/{cid}", method = RequestMethod.GET)
    public String deleteChannel(@PathVariable("cid") int cid){
        channelService.deleteChannel(cid);
        return "redirect:/managerController/manageChannelView";
    }

    /**
     * 查询Channel
     * @param channel
     * @return
     */
    @RequestMapping(value = "/findChannelByChannelName", method = RequestMethod.POST)
    @ResponseBody
    public Result<Channel> findChannelByChannelName(@RequestBody Channel channel){
        return channelService.findChannelByChannelName(channel);
    }
}
