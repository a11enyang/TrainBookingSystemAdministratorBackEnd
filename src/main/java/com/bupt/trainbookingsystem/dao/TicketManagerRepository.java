/**
开发人员：徐玉韬
内容：票务管理员的DAO层
**/
package com.bupt.trainbookingsystem.dao;
import com.bupt.trainbookingsystem.entity.TicketManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TicketManagerRepository extends JpaRepository<TicketManagerEntity,Integer> {

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
    @Transactional
    @Modifying
    @Query(value="update ticket_manager set name = ?1,password=?2,staff_id=?3  where id =?4",nativeQuery=true)
    void updateTicketManagerById(String name,String password,String staff_id, int id);
}
