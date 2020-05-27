/**
 * 开发者：徐玉韬
 * 内容：售票端的数据请求处理
 */
package com.bupt.trainbookingsystem.controller.restController;
import com.bupt.trainbookingsystem.controller.TicketCenterController;
import com.bupt.trainbookingsystem.entity.TicketManagerEntity;
import com.bupt.trainbookingsystem.entity.TrainEntity;
import com.bupt.trainbookingsystem.entity.UserOrderEntity;
import com.bupt.trainbookingsystem.service.TicketManagerService;
import com.bupt.trainbookingsystem.service.TrainService;
import com.bupt.trainbookingsystem.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticketCenter")
public class TicketCenterRestController {
    public final TicketManagerService ticketManagerService;
    public final UserOrderService userOrderService;
    public final TrainService trainService;
    public TicketCenterRestController(TicketManagerService ticketManagerService, UserOrderService userOrderService, TrainService trainService) {
        this.ticketManagerService = ticketManagerService;
        this.userOrderService = userOrderService;
        this.trainService = trainService;
    }

    //更新票务管理员
    @PostMapping("/ticketManagerUpdate")
    public TicketManagerEntity ticketManagerUpdate(@RequestParam(value = "name",required = false)String name, @RequestParam(value = "password",required = false)
            String password, @RequestParam(value = "id",required = false) String id){
        return ticketManagerService.updateTicketManagerById2(name, password, Integer.parseInt(id));
    }

    //获取订单
    @GetMapping("/trains")
    public List<TrainEntity> trains(){
        return  trainService.findAll();
    }
    //获取订单
    @GetMapping("/userOrders")
    public List<UserOrderEntity> userOrders(){
        return  userOrderService.findAll();
    }
    //查找对应的订单

    @PostMapping("/findOrderByOrderId")
    public UserOrderEntity findOrderByOrderId(@RequestParam(value = "id",required = false) String id){
        UserOrderEntity u = userOrderService.findUserOrderEntityById(Integer.parseInt(id));
        return  u;
    }
    //修改订单
    @PostMapping("/editOrder")
    public UserOrderEntity editOrder(@RequestParam(value = "condition",required = false)String condition,@RequestParam("id") int id){
        System.out.println(condition);
        return userOrderService.updateUserOrderEntityById1(condition, id);
    }
    //删除订单
    @DeleteMapping("/deleteOrder/{id}")
    public void deleteOrder(@PathVariable int id) {
        userOrderService.deleteUserOrderEntityById(id);
    }
    //增加列车
    @PostMapping("/addTrain")
    public TrainEntity addTrain(@RequestParam(value = "trainType",required = false)String trainType,
                                @RequestParam(value = "seatInfo",required = false)String seatInfo){
       TrainEntity trainEntity = new TrainEntity();
       trainEntity.setSeatInfo(seatInfo);
       trainEntity.setTrainType(trainType);
       return trainService.save(trainEntity);
    }
}
