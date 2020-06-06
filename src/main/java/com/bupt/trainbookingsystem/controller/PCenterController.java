package com.bupt.trainbookingsystem.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bupt.trainbookingsystem.entity.*;
import com.bupt.trainbookingsystem.entity.custom.Pay_userinfo;
import com.bupt.trainbookingsystem.entity.custom.Selectcontactor;
import com.bupt.trainbookingsystem.entity.custom.Userorder_search;
import com.bupt.trainbookingsystem.service.*;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import java.security.PublicKey;
import java.sql.Timestamp;
import java.util.*;


/**
 * 个人中心
 * 编辑：严智琪
 */
@Controller
public class PCenterController {
    @Autowired
    ContactService contactorsmethods;

    @Autowired
    UserOrderService userOrderService;

    @Autowired
    OrdinaryUserService userService;

    @Autowired
    StationsService stationsService;

    @RequestMapping("/pcenter")
    public String showpagestu(HttpSession session, Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "page1", defaultValue = "0")int page1,
                              @RequestParam(value = "page3", defaultValue = "0")int page2,
                              @RequestParam(value = "page4",defaultValue = "0")int page4){
        OrdinaryUserEntity user=(OrdinaryUserEntity) session.getAttribute("user");
        if(user!=null){
            Sort sort = Sort.by(Sort.Direction.DESC,"id");
            Pageable pageable= PageRequest.of(page,3,sort);
            Page<ContactEntity> contactuser=contactorsmethods.findallcontator(user.getId(),pageable);
            List<Userorder_search> not_payorder=new ArrayList<>();
            List<Userorder_search> not_goorder=new ArrayList<>();
            List<Userorder_search> complete_order=new ArrayList<>();

            Pageable pageable3=PageRequest.of(page4,3,sort);
            List<UserOrderEntity> ordernotpay=userOrderService.orderstate_get(user.getId(),"0");
            for(int i=0;i<ordernotpay.size();i++){
                UserOrderEntity pay0=ordernotpay.get(i);
                String[] route=pay0.getRoutLine().split("-");
                String start=route[0];
                String end=route[route.length-1];
                Timestamp starttime=stationsService.getStationTimeByTripIdAndStation(start,pay0.getTripId());
                Timestamp endtime=stationsService.getStationTimeByTripIdAndStation(end,pay0.getTripId());
                Userorder_search pay_0=new Userorder_search(pay0.getId(),pay0.getTripNumber(),pay0.getNameList(),pay0.getSeatList()
                ,pay0.getPrice(),start,end,starttime,endtime);
                not_payorder.add(pay_0);
            }
            Page<Userorder_search> notpayorders=listConvertToPage(not_payorder,pageable3);

            Pageable pageable1=PageRequest.of(page1,3,sort);
            List<UserOrderEntity> ordernotgo=userOrderService.orderstate_get(user.getId(),"1");
            for(int i=0;i<ordernotgo.size();i++){
                UserOrderEntity pay1=ordernotgo.get(i);
                String[] route=pay1.getRoutLine().split("-");
                String start=route[0];
                String end=route[route.length-1];
                Timestamp starttime=stationsService.getStationTimeByTripIdAndStation(start,pay1.getTripId());
                Timestamp endtime=stationsService.getStationTimeByTripIdAndStation(end,pay1.getTripId());
                Userorder_search pay_1=new Userorder_search(pay1.getId(),pay1.getTripNumber(),pay1.getNameList(),pay1.getSeatList()
                        ,pay1.getPrice(),start,end,starttime,endtime);
                not_goorder.add(pay_1);
            }
            Page<Userorder_search> notgoorders=listConvertToPage(not_goorder,pageable1);



            Pageable pageable2=PageRequest.of(page2,3,sort);
            List<UserOrderEntity> ordercomplete=userOrderService.orderstate_get(user.getId(),"2");
            for(int i=0;i<ordercomplete.size();i++){
                UserOrderEntity pay2=ordercomplete.get(i);
                String[] route=pay2.getRoutLine().split("-");
                String start=route[0];
                String end=route[route.length-1];
                Timestamp starttime=stationsService.getStationTimeByTripIdAndStation(start,pay2.getTripId());
                Timestamp endtime=stationsService.getStationTimeByTripIdAndStation(end,pay2.getTripId());
                Userorder_search pay_2=new Userorder_search(pay2.getId(),pay2.getTripNumber(),pay2.getNameList(),pay2.getSeatList()
                        ,pay2.getPrice(),start,end,starttime,endtime);
                complete_order.add(pay_2);
            }
            Page<Userorder_search> completeorders=listConvertToPage(complete_order,pageable2);

            model.addAttribute("page",contactuser);
            model.addAttribute("notpaypage",notpayorders);
            model.addAttribute("notgopage",notgoorders);
            model.addAttribute("completepage",completeorders);
            model.addAttribute("names", user.getName());
            model.addAttribute("realname",user.getRealname());
            model.addAttribute("types", user.getType());
            model.addAttribute("personIds", user.getPersonId());
            model.addAttribute("phonenums", user.getPhonenum());
        }


        return "personalcenter";
    }


    @GetMapping("/pcenter/{id}/delete")
    public String deletecontactor(@PathVariable int id, RedirectAttributes attributes){
        contactorsmethods.delecontactbyid(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/pcenter";
    }

    @GetMapping("/pcenter/{id}/returnticket")
    public String returnticket(@PathVariable int id){
        userOrderService.updateUserOrderEntityById("3",id);
        return "redirect:/pcenter";
    }

    @PostMapping("/pcenter/editinfo")
    @ResponseBody
    public String editinfo(@RequestParam("editname")String name,@RequestParam("editphonenum") String phonenum,HttpSession session){
           userService.edituinfo(phonenum,name);
           OrdinaryUserEntity user=userService.getuser(name);
           session.removeAttribute("user");
           session.setAttribute("user",user);
           return "success";
    }

    @PostMapping("/pcenter/altercontactor")
    @ResponseBody
    public String altercontator(@RequestParam("num") String num,@RequestParam("phonenum") String phonenum,RedirectAttributes attributes){
        int id=Integer.parseInt(num);
        contactorsmethods.altercontactor(phonenum,id);
        attributes.addFlashAttribute("message1","修改成功");
        return "success";
    }

    @PostMapping("/pcenter/addcontactor")
    @ResponseBody
    public String addcontactor(@RequestParam("name") String name,@RequestParam("personid") String personid,
                               @RequestParam("phonenum") String phonenum){
        ContactEntity contactEntity=new ContactEntity();
        contactEntity.setName(name);
        contactEntity.setPersonId(personid);
        contactEntity.setPhonenum(phonenum);
        contactEntity.setOrdineryUserId(1);
        contactorsmethods.addcontatcor(contactEntity);
        return "success";
    }



    public <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : ( start + pageable.getPageSize());
        return new PageImpl<T>(list.subList(start, end), pageable, list.size());
    }


    /**
     * 支付页面
     * 编辑：严智琪
     */
    @RequestMapping("/pay/{id}")
    public String paypage(@PathVariable int id, Model model,HttpSession session){
        OrdinaryUserEntity user=(OrdinaryUserEntity) session.getAttribute("user");
        if(user!=null){
            UserOrderEntity payorder=userOrderService.findUserOrderEntityById(id);
            List<Pay_userinfo> payUserinfos=new ArrayList<>();
            String[] namelist=payorder.getNameList().split(",");
            String[] seatlist=payorder.getSeatList().split(",");
            String[] pricelist=payorder.getPricelist().split(",");
            String[] routelist=payorder.getRoutLine().split("-");
            String start=routelist[0];
            String end=routelist[routelist.length-1];
            Timestamp starttime=stationsService.getStationTimeByTripIdAndStation(start,payorder.getTripId());
            Timestamp endtime=stationsService.getStationTimeByTripIdAndStation(end,payorder.getTripId());
            for(int i=0;i<namelist.length;i++){
                Pay_userinfo payUserinfo=new Pay_userinfo();
                String[] carriage=seatlist[i].split("-");
                payUserinfo.setName(namelist[i]);
                //payUserinfo.setSeat(seatlist[i]);
                payUserinfo.setPricelist(pricelist[i]);
                payUserinfo.setCarriage(carriage[0]);
                payUserinfo.setSeat(carriage[1]);
                if(namelist[i].equals(user.getRealname())){
                    payUserinfo.setPersonid(user.getPersonId());
                }
                else {
                   ContactEntity contact=contactorsmethods.findbyname(namelist[i]);
                   payUserinfo.setPersonid(contact.getPersonId());
                }
                payUserinfos.add(payUserinfo);
            }
            model.addAttribute("names", user.getName());
            model.addAttribute("start",start);
            model.addAttribute("end",end);
            model.addAttribute("tripnum",payorder.getTripNumber());
            model.addAttribute("starttime",starttime);
            model.addAttribute("endtime",endtime);
            model.addAttribute("price",payorder.getPrice());
            model.addAttribute("person",payUserinfos);
            model.addAttribute("orderid",id);
        }
        return "pay";

    }

    @GetMapping("/pcenter/{id}/paytheorder")
    public String paytheorder(@PathVariable int id){
        userOrderService.updateUserOrderEntityById("1",id);
       // String url="redirect:/pcenter/"+id;
        return "redirect:/pcenter";
    }

    /**
     * 买票页面
     * @return
     */
    @RequestMapping("/buyTicket")
    public String getBuyTicket(HttpSession session,Model model)
    {
        OrdinaryUserEntity user=(OrdinaryUserEntity) session.getAttribute("user");
        if(user!=null){
            model.addAttribute("names",user.getName());
        }
        return "buyticket";
    }

    @GetMapping("buyticket/getcontators")
    @ResponseBody
    public Map<String,Object> getallcontator(HttpSession session){
        OrdinaryUserEntity user=(OrdinaryUserEntity)session.getAttribute("user");
        Map<String,Object> map=new HashMap<>();
        if(user!=null){
            List<ContactEntity> contators=contactorsmethods.findcontactors(user.getId());
            map.put("myuser",user);
            map.put("mycontactors",contators);
            return map;
        }
        return map;
    }

    @PostMapping("buyticket/getchooseperson")
    @ResponseBody
    public List<Selectcontactor> getchooseperson(@RequestParam MultiValueMap params, HttpSession session){
        OrdinaryUserEntity user=(OrdinaryUserEntity)session.getAttribute("user");
        List<Selectcontactor> selectcontactors=new ArrayList<>();

      if(user!=null){
          List<String> checknames=new ArrayList<>();
          String start=(String)params.getFirst("start");
          String end=(String)params.getFirst("end");
          String trainnum=(String)params.getFirst("trainnum");
          List<String> checkname=(LinkedList)params.get("checknames");
          String[] list=checkname.get(0).split(",");
          for(int k=0;k<list.length;k++){
              checknames.add(list[k]);
          }
          TripEntity tripEntity=tripService.findTripEntityByTrainNumber(trainnum);
          BigDecimal fare=fareService.getFareByStationsAndTripId(start,end,"1",tripEntity.getId());
         for(int i=0;i<checknames.size();i++){
               if(checknames.get(i).equals(user.getRealname())){
                   Selectcontactor selectuser=new Selectcontactor();
                   selectuser.setName(user.getRealname());
                   selectuser.setPersonid(user.getPersonId());
                   selectuser.setPhonenum(user.getPhonenum());
                   selectuser.setPrice(fare);
                   selectuser.setTripid(tripEntity.getId());
                   selectcontactors.add(selectuser);
               }
               else {
                   ContactEntity contactor=contactorsmethods.findbyname(checknames.get(i));
                   Selectcontactor selectuser=new Selectcontactor();
                   selectuser.setName(contactor.getName());
                   selectuser.setPersonid(contactor.getPersonId());
                   selectuser.setPhonenum(contactor.getPhonenum());
                   selectuser.setPrice(fare);
                   selectuser.setTripid(tripEntity.getId());
                   selectcontactors.add(selectuser);
               }
          }
       }
       return selectcontactors;
    }

    @PostMapping("buyticket/selecttickettype")
    @ResponseBody
    public List<Selectcontactor> selecttickettype(@RequestBody Map<String,Object> data){
        String tickettype=(String) data.get("index1");
        int indexofselect=(int)data.get("index2");
        String start=(String)data.get("start");
        String end=(String)data.get("end");
        String str=(String)data.get("selectcontactors");
        List<Selectcontactor> selectcontactor= JSONObject.parseArray(str,Selectcontactor.class);
        BigDecimal price=fareService.getFareByStationsAndTripId(start,end,tickettype,selectcontactor.get(0).getTripid());
        selectcontactor.get(indexofselect).setPrice(price);
        return selectcontactor;
    }

    //生成订单
    @Autowired
    TripService tripService;

    @Autowired
    RoutelineService routelineService;

    @Autowired
    FareService fareService;

    @PostMapping("buyticket/createorder")
    @ResponseBody
    public void createorder(@RequestBody Map<String,Object> data, HttpSession session){
        String trainnum=(String)data.get("trainnum");
        String start=(String)data.get("start");
        String end=(String)data.get("end");
        String str=(String)data.get("selectcontactors");
        List<Selectcontactor> selectcontactor= JSONObject.parseArray(str,Selectcontactor.class);
       // JSONArray jsonArray=new JSONArray();


        OrdinaryUserEntity user=(OrdinaryUserEntity)session.getAttribute("user");
        TripEntity tripEntity=tripService.findTripEntityByTrainNumber(trainnum);
        String namelist="",seatlist="",myroute="",pricelist="";
        BigDecimal price=new BigDecimal(0);
        RoutelineEntity routelineEntity=routelineService.findRoutelineEntityByTripId(tripEntity.getId());
        if(user!=null) {
            UserOrderEntity userOrderEntity = new UserOrderEntity();
            String rou=routelineEntity.getRouteLine();
            String[] routeline=rou.split("-");
            int startindex=getindex(routeline,start);
            int endindex=getindex(routeline,end);
            for(int j=startindex;j<=endindex;j++){
                if(j==startindex){
                    myroute=myroute.concat(routeline[j]);
                }
                else{
                    myroute=myroute.concat("-").concat(routeline[j]);
                }
            }
            Timestamp time=new Timestamp(new Date().getTime());
            for (int i = 0; i < selectcontactor.size(); i++) {
                if (i == 0) {
                    namelist = selectcontactor.get(i).getName();
                    pricelist=""+selectcontactor.get(i).getPrice();
                    price=selectcontactor.get(i).getPrice();
                    seatlist="1-11";
                } else {
                    namelist = namelist + "," + selectcontactor.get(i).getName();
                    pricelist=""+pricelist+","+selectcontactor.get(i).getPrice();
                    price =price.add(selectcontactor.get(i).getPrice());
                    seatlist += ","+"1-11";
                }
            }
            userOrderEntity.setUserOrderCondition("0");
            userOrderEntity.setTripId(tripEntity.getId());
            userOrderEntity.setPrice(price);
            userOrderEntity.setOrdineryUserId(user.getId());
            userOrderEntity.setNameList(namelist);
            userOrderEntity.setSeatList(seatlist);
            userOrderEntity.setTripTime(time);
            userOrderEntity.setRoutLine(myroute);
            userOrderEntity.setTripNumber(trainnum);
            userOrderEntity.setPricelist(pricelist);
            userOrderService.save(userOrderEntity);
        }
    }


    private int getindex(String[] arr,String str){
        for(int i=0;i<arr.length;i++){
            if(arr[i].equals(str))
                return i;
        }
        return -1;
    }




    @GetMapping("/api/gettrip")
    @ResponseBody
    public String gettrp(){
        return "success";
    }

}
