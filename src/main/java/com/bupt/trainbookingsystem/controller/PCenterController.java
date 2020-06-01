package com.bupt.trainbookingsystem.controller;

import com.bupt.trainbookingsystem.entity.ContactEntity;
import com.bupt.trainbookingsystem.entity.OrdinaryUserEntity;
import com.bupt.trainbookingsystem.entity.UserOrderEntity;
import com.bupt.trainbookingsystem.entity.custom.Pay_userinfo;
import com.bupt.trainbookingsystem.entity.custom.Selectcontactor;
import com.bupt.trainbookingsystem.entity.custom.Userorder_search;
import com.bupt.trainbookingsystem.service.ContactService;
import com.bupt.trainbookingsystem.service.OrdinaryUserService;
import com.bupt.trainbookingsystem.service.UserOrderService;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

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


            Pageable pageable3=PageRequest.of(page4,3,sort);
            List<Userorder_search> ordernotpay=userOrderService.order_paystate(user.getId(),"0");
            Page<Userorder_search> notpayorders=listConvertToPage(ordernotpay,pageable3);

            Pageable pageable1=PageRequest.of(page1,3,sort);
            List<Userorder_search> ordernotgo=userOrderService.order_paystate(user.getId(),"1");
            Page<Userorder_search> notgoorders=listConvertToPage(ordernotgo,pageable1);

            Pageable pageable2=PageRequest.of(page2,3,sort);
            List<Userorder_search> ordercomplete=userOrderService.order_paystate(user.getId(),"2");
            Page<Userorder_search> completeorders=listConvertToPage(ordercomplete,pageable2);
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
            Userorder_search payorder=userOrderService.orderinfo(id).get(0);
            List<Pay_userinfo> payUserinfos=new ArrayList<>();
            String[] namelist=payorder.getNamelist().split(",");
            String[] seatlist=payorder.getSeat().split(",");
            String[] pricelist=payorder.getPricelist().split(",");
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
            model.addAttribute("start",payorder.getStartstation());
            model.addAttribute("end",payorder.getEndstation());
            model.addAttribute("tripnum",payorder.getTrainnum());
            model.addAttribute("time",payorder.getDepaturetime());
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
    public List<Selectcontactor> getchooseperson(@RequestBody List<String> checknames, HttpSession session){
        OrdinaryUserEntity user=(OrdinaryUserEntity)session.getAttribute("user");
        List<Selectcontactor> selectcontactors=new ArrayList<>();
       if(user!=null){
         for(int i=0;i<checknames.size();i++){
               if(checknames.get(i).equals(user.getRealname())){
                   Selectcontactor selectuser=new Selectcontactor();
                   selectuser.setName(user.getRealname());
                   selectuser.setPersonid(user.getPersonId());
                   selectuser.setPhonenum(user.getPhonenum());
                   selectcontactors.add(selectuser);
               }
               else {
                   ContactEntity contactor=contactorsmethods.findbyname(checknames.get(i));
                   Selectcontactor selectuser=new Selectcontactor();
                   selectuser.setName(contactor.getName());
                   selectuser.setPersonid(contactor.getPersonId());
                   selectuser.setPhonenum(contactor.getPhonenum());
                   selectcontactors.add(selectuser);
               }
          }
       }
       return selectcontactors;
    }

    @PostMapping("buyticket/createorder")
    public void createorder(@RequestBody List<Selectcontactor> selectcontactor,HttpSession session){
        OrdinaryUserEntity user=(OrdinaryUserEntity)session.getAttribute("user");
        String namelist="",seatlist="";
        BigDecimal price=new BigDecimal(100);
        if(user!=null) {
            UserOrderEntity userOrderEntity = new UserOrderEntity();
            Timestamp time=new Timestamp(new Date().getTime());
            for (int i = 0; i < selectcontactor.size(); i++) {
                if (i == 0) {
                    namelist = selectcontactor.get(i).getName();
                    seatlist="1-11";
                } else {
                    namelist = namelist + "," + selectcontactor.get(i).getName();
                    seatlist += ","+"1-11";
                }
            }
            userOrderEntity.setUserOrderCondition("0");
            userOrderEntity.setTripId(1);
            userOrderEntity.setPrice(price);
            userOrderEntity.setOrdineryUserId(user.getId());
            userOrderEntity.setNameList(namelist);
            userOrderEntity.setSeatList(seatlist);
            userOrderEntity.setTripTime(time);
            userOrderService.save(userOrderEntity);
        }
    }


}
