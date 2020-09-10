package com.bupt.trainbookingsystem.controller.ticketUser;


import com.bupt.trainbookingsystem.entity.TicketManagerEntity;
import com.bupt.trainbookingsystem.service.imp.AdministratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "票务端用户管理的接口")
@RequestMapping("/administratorapi/ticketuser")
@RestController
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class TicketUserRest {
    @Autowired
    public AdministratorService administratorService;


    /**
     * 1. 删除
     */
    @ApiOperation("删除一个票务端使用者")
    @PostMapping("/delete/{id}")
    public Object deleteTicketUser(@PathVariable int  id){
        administratorService.deleteTicketUser(id);
        return null;
    }

    /**
     * 2. 保存
     */
    @ApiOperation("保存一个票务端使用者")
    @PostMapping("/save")
    public  Object saveTicketUser(@RequestBody TicketManagerEntity ticketManagerEntity){
        System.out.println(ticketManagerEntity.getStaffId());
        administratorService.saveTicketUser(ticketManagerEntity);
        return null;
    }

    /**
     * 3. 分页显示
     */
    @ApiOperation("分页显示票务端使用者")
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
    @ApiOperation("查询票务端使用者")
    @PostMapping("/search")
    public List<TicketManagerEntity> search(@RequestBody TicketManagerEntity ticketManagerEntity){
        return administratorService.findTicketUsersWithSpecification(ticketManagerEntity);
    }

}
