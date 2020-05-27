/**
 * 开发者：徐玉韬
 * 内容：售票端的数据请求处理
 */
package com.bupt.trainbookingsystem.controller.restController;
import com.bupt.trainbookingsystem.controller.TicketCenterController;
import com.bupt.trainbookingsystem.entity.TicketManagerEntity;
import com.bupt.trainbookingsystem.entity.UserOrderEntity;
import com.bupt.trainbookingsystem.service.TicketManagerService;
import com.bupt.trainbookingsystem.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticketCenter")
public class TicketCenterRestController {
    public final TicketManagerService ticketManagerService;
    public final UserOrderService userOrderService;

    public TicketCenterRestController(TicketManagerService ticketManagerService, UserOrderService userOrderService) {
        this.ticketManagerService = ticketManagerService;
        this.userOrderService = userOrderService;
    }


    @PostMapping("/ticketManagerUpdate")
    public TicketManagerEntity ticketManagerUpdate(@RequestParam(value = "name",required = false)String name, @RequestParam(value = "password",required = false)
            String password, @RequestParam(value = "id",required = false) String id){
        return ticketManagerService.updateTicketManagerById2(name, password, Integer.parseInt(id));
    }
/*    @GetMapping("/userOrders")
    public List<UserOrderEntity> userOrders(){
        return  userOrderService.findAll();
    }*/

    @PostMapping("/findOrderByOrderId")
    public UserOrderEntity findOrderByOrderId(@RequestParam(value = "id",required = false) String id){
        System.out.println(id);
        UserOrderEntity u = userOrderService.findUserOrderEntityById(Integer.parseInt(id));
        if(u==null){
            System.out.println("找不到");
        }
        else{
            System.out.println(u.getId());
        }
        return  u;
    }
}
