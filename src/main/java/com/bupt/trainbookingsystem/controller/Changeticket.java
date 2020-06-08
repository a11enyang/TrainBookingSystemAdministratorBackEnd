package com.bupt.trainbookingsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.bupt.trainbookingsystem.entity.*;
import com.bupt.trainbookingsystem.entity.custom.Selectcontactor;
import com.bupt.trainbookingsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.NamingEnumeration;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.bupt.trainbookingsystem.controller.PCenterController.isDateBefore;

@Controller
public class Changeticket {
    @Autowired
    UserOrderService userOrderService;

    @Autowired
    StationsService stationsService;

    @Autowired
    TripService tripService;

    @Autowired
    ContactService contactService;

    @Autowired
    FareService fareService;

    @PostMapping ("/changeticket/orderlist")
    @ResponseBody
    public Map<String,Object> changeorderlist(@RequestParam("orderid") String orderid,
                               @RequestParam("tripid") int tripid,HttpSession session){
        OrdinaryUserEntity user=(OrdinaryUserEntity) session.getAttribute("user");
        //int tripid=Integer.parseInt(tripid1);
        Map<String,Object> map=new HashMap<>();
        UserOrderEntity userOrderEntity=userOrderService.findUserOrderEntityById(Integer.parseInt(orderid));
/*        TripEntity tripEntity=tripService.findTripEntityByTrainNumber(trainnum);
        int tripid=tripEntity.getId();*/
        String[] route=userOrderEntity.getRoutLine().split("-");
        Timestamp statrtime=stationsService.getStationTimeByTripIdAndStation(route[0],tripid);
        Timestamp endtime=stationsService.getStationTimeByTripIdAndStation(route[route.length-1],tripid);
        String[] namelist=userOrderEntity.getNameList().split(",");
        BigDecimal fare=fareService.getFareByStationsAndTripId(route[0],route[route.length-1],"1",tripid);
        BigDecimal price=new BigDecimal(0);
        List<Selectcontactor> selectcontactors=new ArrayList<>();
        for(int i=0;i<namelist.length;i++){
            Selectcontactor selectcontactor=new Selectcontactor();
            if(namelist[i].equals(user.getRealname())){
                selectcontactor.setPersonid(user.getPersonId());
            }
            else {
                ContactEntity contact=contactService.findbyname(namelist[i]);
                selectcontactor.setPersonid(contact.getPersonId());
            }
            price =price.add(fare);
            selectcontactor.setTripid(tripid);
            selectcontactor.setType("1");
            selectcontactor.setName(namelist[i]);
            selectcontactor.setPrice(fare);
            selectcontactors.add(selectcontactor);
        }
        map.put("starttime",statrtime);
        map.put("endtime",endtime);
        map.put("startstation",route[0]);
        map.put("endstation",route[route.length-1]);
        map.put("peoplelist",selectcontactors);
        map.put("price",price);
        return map;
    }

    @PostMapping("/changeticket/selecttickettype")
    @ResponseBody
    public Map<String,Object> selecttickettype(@RequestBody Map<String,Object> data){
        Map<String,Object> map=new HashMap<>();
        BigDecimal totalprice=new BigDecimal(0);
        String tickettype=(String) data.get("index1");
        int indexofselect=(int)data.get("index2");
        String start=(String)data.get("start");
        String end=(String)data.get("end");
        String str=(String)data.get("selectcontactors");
        List<Selectcontactor> selectcontactor= JSONObject.parseArray(str,Selectcontactor.class);
        BigDecimal price=fareService.getFareByStationsAndTripId(start,end,tickettype,selectcontactor.get(0).getTripid());
        selectcontactor.get(indexofselect).setPrice(price);
        selectcontactor.get(indexofselect).setType(tickettype);
        for(int i=0;i<selectcontactor.size();i++){
            totalprice = totalprice.add(selectcontactor.get(i).getPrice());
        }
        map.put("total",totalprice);
        map.put("person",selectcontactor);
        return map;
    }



}
