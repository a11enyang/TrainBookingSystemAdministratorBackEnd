package com.bupt.trainbookingsystem.controller.systemCenter.ticketUser;


import com.bupt.trainbookingsystem.entity.TicketManagerEntity;
import com.bupt.trainbookingsystem.service.imp.AdministratorService;
import com.bupt.trainbookingsystem.vo.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 3. 分页显示
     */
    @PostMapping("/page/{page}")
    public Map<String, Object> findTicketUsersPage(@PathVariable int page){
        int pageSize = 10;
        page = page ==0 ? 1 : page;
        Page<TicketManagerEntity> pageInfo = administratorService.findTicketUsersInPage(page, pageSize);
        Map<String, Object> mapper = new HashMap<>();
        mapper.put("totalElements", pageInfo.getTotalElements());
        mapper.put("onePageContent", pageInfo.getContent());
        return mapper;
    }

    /**
     * 4. 多条件查询
     */
    @PostMapping("/search")
    public List<TicketManagerEntity> search(@RequestBody TicketManagerEntity ticketManagerEntity){
        System.out.println(ticketManagerEntity.getName());
        return administratorService.findTicketUsersWithSpecification(ticketManagerEntity);
    }

}
