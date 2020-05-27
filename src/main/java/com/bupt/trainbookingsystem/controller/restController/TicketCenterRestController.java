/**
 * 开发者：徐玉韬
 * 内容：售票端的数据请求处理
 */
package com.bupt.trainbookingsystem.controller.restController;
import com.bupt.trainbookingsystem.controller.TicketCenterController;
import com.bupt.trainbookingsystem.entity.TicketManagerEntity;
import com.bupt.trainbookingsystem.entity.TrainEntity;
import com.bupt.trainbookingsystem.entity.TripEntity;
import com.bupt.trainbookingsystem.entity.UserOrderEntity;
import com.bupt.trainbookingsystem.service.TicketManagerService;
import com.bupt.trainbookingsystem.service.TrainService;
import com.bupt.trainbookingsystem.service.TripService;
import com.bupt.trainbookingsystem.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/ticketCenter")
public class TicketCenterRestController {
    public final TicketManagerService ticketManagerService;
    public final UserOrderService userOrderService;
    public final TrainService trainService;
    public final TripService tripService;
    public TicketCenterRestController(TicketManagerService ticketManagerService, UserOrderService userOrderService, TrainService trainService, TripService tripService) {
        this.ticketManagerService = ticketManagerService;
        this.userOrderService = userOrderService;
        this.trainService = trainService;
        this.tripService = tripService;
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
    //修改列车信息
    @PostMapping("/editTrain")
    public TrainEntity editTrain(@RequestParam(value = "trainType",required = false)String trainType,
                                 @RequestParam(value = "seatInfo",required = false)String seatInfo,
                                 @RequestParam("id") int id) {
                        trainService.updateTrainEntityById(trainType, seatInfo, id);
                        return trainService.findTrainEntityById(id);
    }
    //删除列车
    @DeleteMapping("/deleteTrain/{id}")
    public void deleteTrain(@PathVariable int id) {
        trainService.deleteTrainEntityById(id);
    }

    //增加车次
    @PostMapping("/addTrip")
    public TripEntity addTrip(@RequestParam(value = "de_time",required = false)String de_time,
                              @RequestParam(value = "start_station",required = false)String start_station,
                              @RequestParam(value = "end_station",required = false)String end_station,
                              @RequestParam(value = "train_number",required = false)String train_number,
                              @RequestParam(value = "train_status",required = false)String train_status,
                              @RequestParam(value = "train_id",required = false)int train_id){
        TripEntity tripEntity = new TripEntity();
        tripEntity.setTrainId(train_id);
        tripEntity.setTrainByTrainId(trainService.findTrainEntityById(train_id));
        tripEntity.setStartStation(start_station);
        tripEntity.setEndStation(end_station);
        tripEntity.setTrainNumber(train_number);
        tripEntity.setTripStatus(Byte.valueOf(train_status));
        tripEntity.setDepartureTime(Timestamp.valueOf(de_time));
        tripEntity.setRemainseatInfo(trainService.findTrainEntityById(train_id).getSeatInfo());
        tripService.save(tripEntity);
        return tripEntity;
    }

    //查看车次
    @GetMapping("/trips")
    public List<TripEntity> seeTrips(){
        return  tripService.findAll();
    }


    @PostMapping("/editTrip")
    public TrainEntity editTrip(@RequestParam(value = "edit_number",required = false)String edit_number,
                                 @RequestParam(value = "edit_time",required = false)String edit_time,
                                @RequestParam(value = "edit_status",required = false)String edit_status,
                                @RequestParam("id") int id) {
        tripService.updateTripEntityById(edit_number,Timestamp.valueOf(edit_time),Byte.valueOf(edit_status),id);
        System.out.println("修改");
        return trainService.findTrainEntityById(id);
    }
    @PostMapping("/searchTripsByNumber")
            List<TripEntity> searchTripsByNumber(@RequestParam(value = "number",required = false)String number){
        return  tripService.findTripEntitiesByTrainNumberContaining(number);
    }
    @PostMapping("/searchTripsByStatus")
    List<TripEntity> searchTripsByStatus(@RequestParam(value = "status",required = false)String status){
        return tripService.findTripEntitiesByTripStatus(Byte.valueOf(status));
    }


}


