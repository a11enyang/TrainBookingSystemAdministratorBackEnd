/**
 * 开发者：徐玉韬
 * 内容：管理端的restcontroller
 */
package com.bupt.trainbookingsystem.controller.restController;
import com.bupt.trainbookingsystem.entity.AdvertisementEntity;
import com.bupt.trainbookingsystem.entity.TicketManagerEntity;
import com.bupt.trainbookingsystem.service.AdvertisementService;
import com.bupt.trainbookingsystem.service.TicketManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/managercenter")
public class ManagerCenterRestController {
    @Autowired
    private TicketManagerService ticketManagerService;
    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping("/ticketmanager")
    public List<TicketManagerEntity> getAllTicketManager(){
        return  ticketManagerService.findALL();
    }
    @GetMapping("/ad")
    public List<AdvertisementEntity> getAllAdvertisement(){
        return advertisementService.findAll();
    }

}
