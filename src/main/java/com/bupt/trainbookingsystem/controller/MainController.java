package com.bupt.trainbookingsystem.controller;

import com.bupt.trainbookingsystem.entity.OrdinaryUserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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
    @GetMapping("/search")
    public String search(HttpSession session, Model model){

        OrdinaryUserEntity user=(OrdinaryUserEntity) session.getAttribute("user");
        if(user!=null)
            model.addAttribute("names",user.getName());
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
    public String getPersonalCenter(HttpSession session,Model model) {
        OrdinaryUserEntity user=(OrdinaryUserEntity) session.getAttribute("user");
        if(user!=null) {
            model.addAttribute("names", user.getName());
            model.addAttribute("types", user.getType());
            model.addAttribute("personIds", user.getPersonId());
            model.addAttribute("phonenums", user.getPhonenum());
        }
        return "personalcenter";
    }

    /**
     * 买票页面
     * @return
     */
    @RequestMapping("/search_new")
    public String getSearch_new(){
        return "search_new";
    }

}
