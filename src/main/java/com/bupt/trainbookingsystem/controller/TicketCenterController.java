package com.bupt.trainbookingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 开发者： 杨韦岽
 * 内容： 售票端
 */
@Controller
public class TicketCenterController {
    @RequestMapping("ticketCenter")
    public String getTicketCenter(){
        return "ticketCenter";
    }
}
