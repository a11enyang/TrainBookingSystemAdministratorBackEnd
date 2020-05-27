package com.bupt.trainbookingsystem.controller;

import com.bupt.trainbookingsystem.entity.ContactEntity;
import com.bupt.trainbookingsystem.entity.OrdinaryUserEntity;
import com.bupt.trainbookingsystem.entity.UserOrderEntity;
import com.bupt.trainbookingsystem.entity.custom.Userorder_search;
import com.bupt.trainbookingsystem.service.ContactService;
import com.bupt.trainbookingsystem.service.UserOrderService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 开发者：杨韦岽
 * 内容：用户端的项目导航
 * index 首页
 * search 查询车票
 *
 */
@Controller
public class MainController {

    /**
     * 首页url
     * @return
     */
    @RequestMapping("/index")
    public String getIndex(){
        return "index";
    }

    /**
     * 查询(新)页面
     * @return
     */
    @GetMapping("/search_new")
    public String search_new(HttpSession session, Model model){

        OrdinaryUserEntity user=(OrdinaryUserEntity) session.getAttribute("user");
        if(user!=null)
            model.addAttribute("names",user.getName());
        return "search_new";
    }

    /**
     * 查询页面
     * @return
     */
    @GetMapping("/search")
    public String search(HttpSession session, Model model){

        OrdinaryUserEntity user=(OrdinaryUserEntity) session.getAttribute("user");
        if(user!=null)
            model.addAttribute("names",user.getName());
        return "search";
    }

    /**
     * 买票页面
     * @return
     */
    @RequestMapping("/buyTicket")
    public String getBuyTicket(){
        return "buyticket";
    }

    /**
     * 支付页面
     * @return
     */
    @RequestMapping("/pay")
    public String getPay(HttpSession session,Model model){
        OrdinaryUserEntity user=(OrdinaryUserEntity) session.getAttribute("user");
        if(user!=null){
            contactorsmethods.findcontactors(user.getId());
             model.addAttribute("names",user.getName());
        }
        return "pay";
    }

    /**
     * 个人中心
     * @return
     */
    @Autowired
    ContactService contactorsmethods;

    @Autowired
    UserOrderService userOrderService;

   @RequestMapping("/pcenter")
    public String showpagestu(HttpSession session,Model model,@RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "page1", defaultValue = "0")int page1,
                              @RequestParam(value = "page3", defaultValue = "0")int page2,
                              @RequestParam(value = "page4",defaultValue = "0")int page4){
       OrdinaryUserEntity user=(OrdinaryUserEntity) session.getAttribute("user");
       if(user!=null){
           Sort sort = Sort.by(Sort.Direction.DESC,"id");
           Pageable pageable=PageRequest.of(page,3,sort);
           Page<ContactEntity>  contactuser=contactorsmethods.findallcontator(user.getId(),pageable);


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

}
