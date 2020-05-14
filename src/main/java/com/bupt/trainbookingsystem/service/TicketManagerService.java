/**
 开发人员：徐玉韬
 内容：票务管理员的Service层
 **/
package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.entity.TicketManagerEntity;

import javax.transaction.Transactional;
import java.util.List;


public interface TicketManagerService {
    //找到所有票务端用户
    List<TicketManagerEntity> findALL();
    //储存票务端用户
   TicketManagerEntity save(TicketManagerEntity t);
    //通过id 找票务端用户
    TicketManagerEntity findTicketManagerEntityById(int id);
    //通过名字找票务端用户
    TicketManagerEntity findTicketManagerEntityByNameContains(String name);
    //通过工号找票务端用户
    TicketManagerEntity findTicketManagerEntityByStaffIdNotContains(String staff_id);
    //通过ID删除票务端用户
    @Transactional
    void deleteTicketManagerEntityById(int id);
    //通过ID修改票务端用户信息
    void updateTicketManagerById(String name,String password,String staff_id, int id);
}
