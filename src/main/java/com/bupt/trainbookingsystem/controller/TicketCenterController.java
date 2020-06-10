package com.bupt.trainbookingsystem.controller;

import com.bupt.trainbookingsystem.entity.TicketManagerEntity;
import com.bupt.trainbookingsystem.service.TicketManagerService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;



/**
 * 开发者： 杨韦岽
 * 内容： 售票端
 * 开发者：徐玉韬
 * 内容：售票端管理员的登录和退出登录
 */
@Controller
public class TicketCenterController {
    public TicketManagerEntity ticketManagerEntity = new TicketManagerEntity();
    @Autowired
    public TicketManagerService ticketManagerService;
    @RequestMapping("ticketLogin")
    public String ticketLogin(){
        return "ticketlogin";
    }
    //检查管理员登录
    @RequestMapping("ticketManager/login")
    public String checkManager(@RequestParam("name") String name,
                               @RequestParam("pw") String pw,
                               Map<String,Object> map,
                               Model model,
                               HttpSession session) throws Exception {
        ticketManagerEntity = ticketManagerService.findTicketManagerEntityByNameAndPassword(name, pw);
        if(ticketManagerEntity!=null ) {
            System.out.println("用户"+name+"登录");
            //doPost("127.0.0.1:8080", String.valueOf(ticketManagerEntity.getId()));
            model.addAttribute("ticketManager",ticketManagerEntity);
            session.setAttribute("ticketManager",ticketManagerEntity);
            return "ticketCenter";

        }
        else{
            System.out.println("no user");
            map.put("msg1","用户名密码错误");
            model.addAttribute("msg1","用户名密码错误");
            return "redirect:/ticketLogin";
        }
    }
    @RequestMapping("/ticketLoginBack")
    public String backLogin(){
        return "redirect:/ticketLogin";
    }

}
