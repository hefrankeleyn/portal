package com.hef.qhjiaoyiyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Date 2020/3/5
 * @Author lifei
 */
@Controller
@RequestMapping(value = {"/","/home"})
public class HomeController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(boolean error, Model model){
        model.addAttribute("loginError", error);
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(){
        return "index";
    }
}
