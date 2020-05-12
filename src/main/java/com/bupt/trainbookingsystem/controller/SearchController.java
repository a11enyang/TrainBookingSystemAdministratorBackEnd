package com.bupt.trainbookingsystem.controller;

import com.bupt.trainbookingsystem.entity.OrdinaryUserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SearchController {

    @GetMapping("/search")
    public String search(HttpSession session, Model model){

        OrdinaryUserEntity user=(OrdinaryUserEntity) session.getAttribute("user");
        if(user!=null)
            model.addAttribute("names",user.getName());
        return "search";
    }



}
