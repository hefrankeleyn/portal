package com.hef.qhjiaoyiyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Date 2020/3/11
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/managerController")
public class ManagerController {

    @RequestMapping(value = "/manageIndex", method = RequestMethod.GET)
    public String manageIndex(){
        return "manage/manageIndex";
    }
}
