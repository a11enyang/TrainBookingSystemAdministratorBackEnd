package com.bupt.trainbookingsystem.controller;

import com.bupt.trainbookingsystem.dao.ContactRespository;
import com.bupt.trainbookingsystem.entity.ContactEntity;
import com.bupt.trainbookingsystem.entity.OrdinaryUserEntity;
import com.bupt.trainbookingsystem.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 开发者：杨韦岽
 * 内容：用户端的项目导航
 * index 首页
 * search 查询车票
 *
 */
@Controller
public class MainController{

    /**
     * 首页url
     * @return
     */
    @RequestMapping("/index")
    public String getIndex(){
        return "index";
    }

    /**
     * 查询(新)页面
     * @return
     */
    @GetMapping("/search_new")
    public String search_new(HttpSession session, Model model){

        OrdinaryUserEntity user=(OrdinaryUserEntity) session.getAttribute("user");
        if(user!=null)
            model.addAttribute("names",user.getName());
        return "search_new";
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

    @GetMapping("/changeticket/pay")
    public String paychangeticket(Model model,HttpSession session){
        OrdinaryUserEntity user=(OrdinaryUserEntity) session.getAttribute("user");
        if(user!=null){
            model.addAttribute("names",user.getName());
        }
        return "gaiqianpay";
    }


    /**
     * 买票页面
     * @return
     */
    @RequestMapping("/search_new")
    public String getSearch_new() {
        return "search_new";
    }
}
