package com.bupt.trainbookingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 开发者：杨韦岽
 * 内容：用户端的项目导航
 * index 首页
 * search 查询车票
 *
 */
@Controller
public class MainController {

    /**
     * 首页url
     * @return
     */
    @RequestMapping("/index")
    public String getIndex(){
        return "index";
    }

    /**
     * 查询页面
     * @return
     */
    @RequestMapping("/search")
    public String getSearch(){
        return "search";
    }


    /**
     * 买票页面
     * @return
     */
    @RequestMapping("/buyTicket")
    public String getBuyTicket(){
        return "buyticket";
    }

    /**
     * 支付页面
     * @return
     */
    @RequestMapping("/pay")
    public String getPay(){
        return "pay";
    }

    /**
     * 个人中心
     * @return
     */
    @RequestMapping("/pcenter")
    public String getPersonalCenter() {
        return "personalcenter";
    }
}
