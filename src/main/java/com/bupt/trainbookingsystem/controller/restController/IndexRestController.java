package com.bupt.trainbookingsystem.controller.restController;

import com.bupt.trainbookingsystem.entity.RoutelineEntity;
import com.bupt.trainbookingsystem.entity.TripEntity;
import com.bupt.trainbookingsystem.service.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;
@RestController
@RequestMapping("/api/index")
public class IndexRestController {
    public final StationsService stationsService;
    public final RoutelineService routelineService;
    public final TicketManagerService ticketManagerService;
    public final UserOrderService userOrderService;
    public final TrainService trainService;
    public final TripService tripService;
    public final FareService fareService;
    public final SeatService seatService;
    public IndexRestController(StationsService stationsService, RoutelineService routelineService,
                               TicketManagerService ticketManagerService, UserOrderService userOrderService,
                               TrainService trainService, TripService tripService, FareService fareService,
                               SeatService seatService) {
        this.stationsService = stationsService;
        this.routelineService = routelineService;
        this.ticketManagerService = ticketManagerService;
        this.userOrderService = userOrderService;
        this.trainService = trainService;
        this.tripService = tripService;
        this.fareService = fareService;
        this.seatService = seatService;
    }
    @PostMapping("/getTrips")
    public List<TripEntity> getTrips(@RequestParam(value = "start",required = false)String start,
                                     @RequestParam(value = "end",required = false)String end,
                                     @RequestParam(value = "time",required = false)String time){
            System.out.println(time);
            List<TripEntity> routeTrips = new ArrayList<>();
            //路线匹配
            List<RoutelineEntity> routeLists = routelineService.findRouteEntitiesByStations(start,end);
            for(RoutelineEntity routelineEntity:routeLists){
                int tripId = routelineEntity.getTripId();
                //时间匹配
                Timestamp timestamp = stationsService.getStationTimeByTripIdAndStation(start,tripId);
                String timestampString = String.valueOf(timestamp);
                System.out.println(timestampString);
                System.out.println((timestampString.substring(0,10)));
                if((timestampString.substring(0,10)).equals(time)){
                    routeTrips.add(tripService.findTripEntityById(tripId));
                    System.out.println((timestampString.substring(0,10)));
                    System.out.println(tripService.findTripEntityById(tripId));
                }
            }
            for(TripEntity tripEntity: routeTrips){
                //当前车次
                int tripId = tripEntity.getId();
                System.out.println("查到的车次");
                System.out.println(tripId);
                //时间表找出发时间
                System.out.println("出发时间");
                System.out.println(stationsService.getStationTimeByTripIdAndStation(start,tripId));
                //时间表找到达时间
                System.out.println("到达时间");
                System.out.println(stationsService.getStationTimeByTripIdAndStation(end,tripId));
                //费用表找到费用
                System.out.println("费用");
                System.out.println(fareService.getFareByStationsAndTripId(start,end,"1",tripId));
                System.out.println(fareService.getFareByStationsAndTripId(start,end,"2",tripId));
                //获取总路线
                //获取用户经过路线
                System.out.println("总路线");
                String rout = routelineService.findRoutelineEntityByTripId(tripId).getRouteLine();
                System.out.println(rout);
                String []TripRoute = rout.split("-");
                String myRout = "";
                for(int i = 0;i<TripRoute.length;++i){
                    if(start.equals(TripRoute[i])){
                        myRout = myRout.concat(start).concat("-");
                        i = i+1;
                        while (!end.equals(TripRoute[i])){
                            myRout = myRout.concat(TripRoute[i]).concat("-");
                            i = i+1;
                        }
                        myRout = myRout.concat(end);
                    }
                }
                System.out.println("用户路线");
                System.out.println(myRout);
                //根据用户经过路线找座位
                String[] MyRoute = myRout.split("-");
                //初始化座位序列
                String numberOfSeat = tripService.findTripEntityById(tripId).getRemainseatInfo();
                String[] NumberOfSeat = numberOfSeat.split("-");
                int seatNumber = Integer.parseInt(NumberOfSeat[0])+Integer.parseInt(NumberOfSeat[1]);
                String seatNum = "";
                for(int m=0;m<seatNumber;++m){
                    seatNum = seatNum.concat("1");
                }
                //初始化结果数组
                byte[] startByte = seatNum.getBytes();
                String last = "";
                for(int j =0 ;j<MyRoute.length-1;++j){
                    String startFirst = MyRoute[j];
                    String endNext = MyRoute[j+1];
                    //查找每个二维组的座位并并起来
                    byte[] seatByte = seatService.getSeatByStartEndTripId(MyRoute[j],MyRoute[j+1],tripId);
                    for(int n=0;n<seatByte.length;++n){
                        startByte[n] = (byte) ((Integer.valueOf(startByte[n])-48) & (Integer.valueOf(seatByte[n])-48));
                        last = last.concat(String.valueOf(startByte[n]));
                    }
                }
                //显示余座
                System.out.println("座位信息");
                System.out.println(last);
                int buyNum = 1;
                int p = 0;
                while(buyNum!=0){
                    if (last.charAt(p)=='0'){
                        //当前余座
                        System.out.println("当前余座");
                        System.out.println(seatNumber-p);
                        System.out.println("当前座位");
                        System.out.println(p);
                        buyNum = buyNum -1;
                    }
                    p = p + 1;
                    if(p==last.length()-1){
                        System.out.println("no seat now");
                        break;
                    }
                }
            }
            return routeTrips;
    }
}
