/**
 * 开发者：徐玉韬
 * 内容：售票端的数据请求处理
 */
package com.bupt.trainbookingsystem.controller.restController;
import com.bupt.trainbookingsystem.controller.TicketCenterController;
import com.bupt.trainbookingsystem.entity.TicketManagerEntity;
import com.bupt.trainbookingsystem.service.TicketManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticketCenter")
public class TicketCenterRestController {
    @Autowired
    public TicketManagerService ticketManagerService;



    @PostMapping("/ticketManagerUpdate")
    public TicketManagerEntity ticketManagerUpdate(@RequestParam(value = "name",required = false)String name, @RequestParam(value = "password",required = false)
            String password, @RequestParam(value = "id",required = false) String id){
        System.out.println(name);
        System.out.println(password);
        System.out.println(id);
        return ticketManagerService.updateTicketManagerById2(name, password, Integer.parseInt(id));
    }
}
