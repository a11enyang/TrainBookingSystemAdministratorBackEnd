/**
 * 开发者：徐玉韬
 * 内容：管理端的restcontroller
 */
package com.bupt.trainbookingsystem.controller.restController;
import com.bupt.trainbookingsystem.dao.UserOrderRepository;
import com.bupt.trainbookingsystem.entity.AdvertisementEntity;
import com.bupt.trainbookingsystem.entity.OrdinaryUserEntity;
import com.bupt.trainbookingsystem.entity.TicketManagerEntity;
import com.bupt.trainbookingsystem.service.AdvertisementService;
import com.bupt.trainbookingsystem.service.OrdinaryUserService;
import com.bupt.trainbookingsystem.service.TicketManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/managercenter")
public class ManagerCenterRestController {
    @Autowired
    private TicketManagerService ticketManagerService;
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private OrdinaryUserService ordinaryUserService;
    //获取所有售票管理员
    @GetMapping("/ticketmanager")
    public List<TicketManagerEntity> getAllTicketManager(){
        return  ticketManagerService.findALL();
    }
    //获取某管理员的信息
    @GetMapping("/findticketmanager/{id}")
    public TicketManagerEntity getaTicketManager(@PathVariable int id){
        return  ticketManagerService.findTicketManagerEntityById(id);
    }
    //增加售票管理员
    @PostMapping("/addticketmanager")
    public TicketManagerEntity addTicketManager(@RequestBody TicketManagerEntity t){
        return ticketManagerService.save(t);
    }
    //修改售票管理员
    @PostMapping("/editticketmanager")
    public TicketManagerEntity editticketmanager(@RequestParam("name")String name,@RequestParam("password") String password,
                                                 @RequestParam ("staffId")String staffId,@RequestParam("id") int id){
        System.out.println(name+"name");
        return ticketManagerService.updateTicketManagerById(name, password, staffId, id);
    }
    //查找售票管理员
    @PostMapping("/findticketmanager")
    public List<TicketManagerEntity> findticketmanager(@RequestParam(value="name",required = false) String name){
        return ticketManagerService.findTicketManagerEntitiesByNameContainingOrStaffIdContaining(name);
    }
    //删除售票管理员
    @DeleteMapping("/deleteticketmanager/{id}")
    public void deleteById(@PathVariable int id) {
        ticketManagerService.deleteTicketManagerEntityById(id);
    }

    //查看所有用户
    @GetMapping("/user")
    public List<OrdinaryUserEntity> getAllUsers(){
        return ordinaryUserService.findAll();
    }
    //删除用户
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUserById(@PathVariable int id) {
        ordinaryUserService.deleteOrdinaryUserEntityById(id);
    }
    //查找用户
    @PostMapping("/findUser")
    public List<OrdinaryUserEntity> findUser(@RequestParam(value="name",required = false) String name){
        return ordinaryUserService.findOrdinaryUserEntitiesByNameContaining(name);
    }
    //编辑用户
    @PostMapping("/editUser")
    public OrdinaryUserEntity editUser(@RequestParam("name")String name, @RequestParam("password") String password,
                                        @RequestParam ("personId")String staffId, @RequestParam("is_student") Byte is_student,
                                        @RequestParam("credit") Byte credit, @RequestParam("id") int id){

        return ordinaryUserService.updateUserById(name, password, staffId, is_student, credit, id);
    }





    //显示所有广告信息
    @GetMapping("/ad")
    public List<AdvertisementEntity> getAllAdvertisement(){
        return advertisementService.findAll();
    }

}
