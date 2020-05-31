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
                int tripId = tripEntity.getId();
                //剩余座位
                System.out.println(tripEntity.getRemainseatInfo());
                //时间表找出发时间
                System.out.println();
                //时间表找到达时间
                System.out.println();
                //费用表找到费用
                System.out.println();
                System.out.println();
                //显示余座
                System.out.println();



            }
            return routeTrips;
    }
}
