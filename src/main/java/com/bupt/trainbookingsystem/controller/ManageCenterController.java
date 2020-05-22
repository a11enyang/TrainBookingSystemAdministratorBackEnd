package com.bupt.trainbookingsystem.controller;

import com.bupt.trainbookingsystem.entity.AdministratorEntity;
import com.bupt.trainbookingsystem.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

/**
 * 开发者：杨韦岽
 * 内容：系统管理员中心
 * 开发者：徐玉韬
 * 内容：重写系统管理员controller
 */
@Controller
public class ManageCenterController {
    public AdministratorEntity Administrator = new AdministratorEntity();
    @Autowired
    private AdministratorService administratorService;
    //索引到管理员登陆
    @RequestMapping("/managerLogin")
    public String managerLogin(){
        return "managerlogin";
    }
    //检查管理员登录
    @RequestMapping("manager/login")
    public String checkManager(@RequestParam("name") String name,
                               @RequestParam("pw") String pw, Map<String,Object> map){
        Administrator = administratorService.findAdministratorEntityByNameAndPassword(name,pw);
        if(Administrator!=null ) {
            System.out.println("用户"+name+"登录");
            return "managercenter";
        }
        else{
            System.out.println("no user");
            map.put("msg1","用户名密码错误");
            return "managerlogin";
        }
    }
    @RequestMapping("/backLogin")
    public String backLogin(){
        return "managerlogin";
    }


}
