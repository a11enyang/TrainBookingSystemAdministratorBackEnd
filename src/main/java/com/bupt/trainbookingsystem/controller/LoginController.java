package com.bupt.trainbookingsystem.controller;

import com.bupt.trainbookingsystem.entity.OrdinaryUserEntity;
import com.bupt.trainbookingsystem.service.OrdinaryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private OrdinaryUserService userLogin;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String password,
                           @RequestParam String cardType,
                           @RequestParam String personId,
                           @RequestParam String email,
                           @RequestParam String phonenum,
                           @RequestParam String personType){
        OrdinaryUserEntity userEntity = new OrdinaryUserEntity();
        userEntity.setName(name);
        userEntity.setPassword(password);
        userEntity.setType(cardType);
        userEntity.setPersonId(personId);
        userEntity.setEmail(email);
        userEntity.setPhonenum(phonenum);
        if(personType.equals("成人"))
            userEntity.setIsstudent((byte) 0);
        else
            userEntity.setIsstudent((byte) 1);
        userLogin.createOrdinaryUserEntity(userEntity);
        return "redirect:login";
    }


    @PostMapping("/admin/login")
    public String logintry(@RequestParam("username")String username, @RequestParam("password")String password,
                           HttpSession session, RedirectAttributes attributes){

        OrdinaryUserEntity ordinaryuser=userLogin.checkuser(username,password);
        if(ordinaryuser!=null){
            ordinaryuser.setPassword(null);
            session.setAttribute("user",ordinaryuser);
            return "redirect:/search";
        }
        else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/login";
        }
    }

    @ResponseBody
    @PostMapping("/admin/logout")
    public void logout(HttpSession session){
        session.removeAttribute("user");

    }

}
