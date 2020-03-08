package com.hef.qhjiaoyiyuan.controller;

import com.hef.qhjiaoyiyuan.bean.QHUser;
import com.hef.qhjiaoyiyuan.service.QHUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 提供 RESTFUL 接口
 * @Date 2020/3/1
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/qHUserController")
public class QHUserController {


    private QHUserService qhUserService;

    @Autowired
    public void setQhUserService(QHUserService qhUserService) {
        this.qhUserService = qhUserService;
    }

    @RequestMapping(value = "/findAllQHUsers", method = RequestMethod.GET)
    public String findAllQHUsers(Model model){
        List<QHUser> allQHUser = qhUserService.findAllQHUser();
        model.addAttribute("allQHUser", allQHUser);
        return "allQHUsers";
    }
}
