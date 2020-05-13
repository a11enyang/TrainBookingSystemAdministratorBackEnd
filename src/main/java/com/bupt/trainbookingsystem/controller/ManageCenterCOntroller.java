package com.bupt.trainbookingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 开发者：杨韦岽
 * 内容：系统管理员中心
 */
@Controller
public class ManageCenterCOntroller {
    @RequestMapping("managerCenter")
    public String getManageCenter(){
        return "managercenter";
    }
}
