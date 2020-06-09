package com.bupt.trainbookingsystem.controller.systemCenter.ticketUser;


import com.bupt.trainbookingsystem.dao.TicketManagerRepository;
import com.bupt.trainbookingsystem.entity.TicketManagerEntity;
import com.bupt.trainbookingsystem.service.systemCenter.AdministratorService;
import com.bupt.trainbookingsystem.vo.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/administratorapi/ticketuser")
@RestController
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class TicketUserRest {
    @Autowired
    public AdministratorService administratorService;


    /**
     * 1. 删除
     */
    @PostMapping("/delete/{id}")
    public Meta deleteTicketUser(@PathVariable int  id){
        Meta meta = new Meta();
        try {
            administratorService.deleteTicketUser(id);
        }catch (Exception e){
            meta.setStatus(false);
            meta.setMsg("删除失败");
            return meta;
        }
        meta.setStatus(true);
        meta.setMsg("删除成功");
        return meta;
    }

    /**
     * 2. 保存
     */
    @PostMapping("/save")
    public  Meta saveTicketUser(@RequestBody TicketManagerEntity ticketManagerEntity){
        Meta meta = new Meta();
        try {
            administratorService.saveTicketUser(ticketManagerEntity);
        }catch (Exception e){
            meta.setMsg("失败");
            meta.setStatus(false);
            return meta;
        }
        meta.setStatus(true);
        meta.setMsg("成功");
        return meta;
    }



}
