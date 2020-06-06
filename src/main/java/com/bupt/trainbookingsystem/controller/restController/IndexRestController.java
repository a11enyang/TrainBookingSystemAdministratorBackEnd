package com.bupt.trainbookingsystem.controller.restController;
import com.bupt.trainbookingsystem.entity.searchResult.SearchTrip;
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
    public static String getDistanceTime(long time1, long time2) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long diff;

        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (day != 0) return day + "天"+hour + "小时"+min + "分钟" + sec + "秒";
        if (hour != 0) return hour + "小时"+ min + "分钟" + sec + "秒";
        if (min != 0) return min + "分钟" + sec + "秒";
        if (sec != 0) return sec + "秒" ;
        return "0秒";
    }

    //返回用户搜索信息
    @PostMapping("/getTrips")
    public Map<String,Object> getTrips(@RequestParam(value = "start",required = false)String start,
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
            List<SearchTrip> searchTrips = new ArrayList<>();
            for(TripEntity tripEntity: routeTrips){
                //当前车次
                SearchTrip searchTrip = new SearchTrip();
                searchTrip.setStartStation(start);
                searchTrip.setEndStation(end);
                int tripId = tripEntity.getId();
                System.out.println("查到的车次");
                System.out.println(tripId);
                searchTrip.setTripId(tripId);
                searchTrip.setTripNumber(tripService.findTripEntityById(tripId).getTrainNumber());
                //时间表找出发时间
                System.out.println("出发时间");
                Timestamp startTime =stationsService.getStationTimeByTripIdAndStation(start,tripId);
                System.out.println(startTime);
                searchTrip.setStartTime(String.valueOf(startTime));
                //时间表找到达时间
                System.out.println("到达时间"   );
                Timestamp endTime = stationsService.getStationTimeByTripIdAndStation(end,tripId);
                System.out.println(endTime);
                searchTrip.setEndTime(String.valueOf(endTime));
                String distanceTime = getDistanceTime(startTime.getTime(),endTime.getTime());
                searchTrip.setSpendTime(distanceTime);
                //费用表找到费用
                System.out.println("费用");
                System.out.println(fareService.getFareByStationsAndTripId(start,end,"1",tripId));
                searchTrip.setFareFirst(String.valueOf(fareService.getFareByStationsAndTripId(start,end,"1",tripId)));
                System.out.println(fareService.getFareByStationsAndTripId(start,end,"2",tripId));
                searchTrip.setFareSecond(String.valueOf(fareService.getFareByStationsAndTripId(start,end,"2",tripId)));
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
                searchTrip.setRouteLine(myRout);
                //根据用户经过路线找座位
                String[] MyRoute = myRout.split("-");
                //初始化座位序列
                String numberOfSeat = tripService.findTripEntityById(tripId).getRemainseatInfo();
                String[] NumberOfSeat = numberOfSeat.split("-");
                int seatFirst = Integer.parseInt(NumberOfSeat[0]);
                int seatSecond = Integer.parseInt(NumberOfSeat[1]);
                searchTrip.setSeatFirstRemain(seatFirst);
                searchTrip.setSeatSecondRemain(seatSecond);
                searchTrips.add(searchTrip);
            }
            Map<String,Object> map=new HashMap<>();
            map.put("searchTrips",searchTrips);
            map.put("sum",searchTrips.size());
            return map;
    }
    public  String[][] getSeatsInfo(int tripId,String[][] userSelect,String myRoute){
        //获取座位数
        String result[][] = new String[userSelect.length][2];
        String numberOfSeat = trainService.findTrainEntityById(tripService.findTripEntityById(tripId).getTrainId()).getSeatInfo();
        String[] NumberOfSeat = numberOfSeat.split("-");
        //一等座座位数
        int seatFirst = Integer.parseInt(NumberOfSeat[0]);
        //二等座座位数
        int seatSecond = Integer.parseInt(NumberOfSeat[1]);
        //总座位数
        int seatNumber = seatFirst + seatSecond;
        //初始化座位
        String seatInitial = "";
        for(int m=0;m<seatNumber;++m){
            seatInitial =seatInitial.concat("1");
        }
        //获取区间之间的座位状况
        String[] MyRoute = myRoute.split("-");
        for(int j =0 ;j<MyRoute.length-1;++j){
            String  last = "";
            String startFirst = MyRoute[j];
            String endNext = MyRoute[j+1];
            //查找每个二维组的座位并并起来
            String seatInfo = seatService.getSeatByStartEndTripId(startFirst,endNext,tripId);
            for(int n=0;n<seatInfo.length();++n){
                int x = (Integer.valueOf(seatInitial.charAt(n)-48)&Integer.valueOf(seatInfo.charAt(n)-48));
                last.concat(String.valueOf(x));
            }
            seatInitial = last;
        }
        //获取当前座位
        int peopleNum = userSelect.length;
        int q = 0;
        while (peopleNum!=0){
            String name = userSelect[q][0];
            String type = userSelect[q][1];
            String seatInfoFirst = seatInitial.substring(0,seatFirst);
            String seatInfoSecond = seatInitial.substring(seatFirst,seatFirst+seatSecond);
            int p = 0;
            int check = 1;
            if (type == "1"){
                while (check!=0){
                if (seatInfoFirst.charAt(p)=='0'){
                    //当前余座
                    System.out.println("当前座位");
                    System.out.println(p);
                    check = 0;
                    result[q][0] = name;
                    int x  = (p+1)/40;
                    int y  = ((p+1)%40)/5;
                    int z  = ((p+1)%40)%5;
                    String s = "".concat(String.valueOf(x)).concat("车").concat(String.valueOf(y))
                            .concat("排").concat(String.valueOf(z)).concat("座");
                    result[q][1] = s;
                }
                p = p + 1;
                if(p==seatInfoFirst.length()){
                    System.out.println("no seat now");
                    result[q][0] = name;
                    result[q][1] = "无座";
                    break;
                }
                }
            }
            else {
                while (check!=0){
                    if (seatInfoSecond.charAt(p)=='0'){
                        //当前余座
                        System.out.println("当前座位");
                        System.out.println(p+seatFirst);
                        check = 0;
                        p =  p+seatFirst;
                        result[q][0] = name;
                        int x  = (p+1)/40;
                        int y  = ((p+1)%40)/5;
                        int z  = ((p+1)%40)%5;
                        String s = "".concat(String.valueOf(x)).concat("车").concat(String.valueOf(y))
                                .concat("排").concat(String.valueOf(z)).concat("座");
                        result[q][1] = s;
                    }
                    p = p + 1;
                    if(p==seatInfoSecond.length()){
                        System.out.println("no seat now");
                        result[q][0] = name;
                        result[q][1] = "无座";
                        break;
                    }
                }
            }
            //更新余座

            String nowTripSeat = tripService.findTripEntityById(tripId).getRemainseatInfo();
            String beforeRemain[] = nowTripSeat.split("-");
            if(result[q][1]  != "无座"){
            if(p>=seatFirst){
                int afterRemainSecond = Integer.valueOf(beforeRemain[1])-1;
                String afterRemain = beforeRemain[0].concat("-").concat(String.valueOf(afterRemainSecond));
                tripService.updateRemainSeatByTripId(afterRemain,tripId);
            }
            else {
                int afterRemainFirst = Integer.valueOf(beforeRemain[0])-1;
                String afterRemain = String.valueOf(afterRemainFirst).concat("-").concat(beforeRemain[1]);
                tripService.updateRemainSeatByTripId(afterRemain,tripId);
            }
                //更新座位表
                for(int w =0 ;w<MyRoute.length-1;++w){
                    String startFirst = MyRoute[w];
                    String endNext = MyRoute[w+1];
                    //查找每个二维组的座位并并起来
                    String seatInfo = seatService.getSeatByStartEndTripId(startFirst,endNext,tripId);
                    StringBuilder strBuilder = new StringBuilder(seatInfo);
                    strBuilder.setCharAt(p,'1');
                    seatService.updateSeatInfoByTripId(strBuilder.toString(),startFirst,endNext,tripId);
                }
            }
            q = q + 1;
            peopleNum = peopleNum -1;
            }

        return result;
    }

}
