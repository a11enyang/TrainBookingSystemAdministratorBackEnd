package com.bupt.trainbookingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 开发者：杨韦岽
 * 内容：系统的项目导航
 * index 首页
 *
 */
@Controller
public class MainController {

    @RequestMapping("/index")
    public String getIndex(){
        return "index";
    }
}
