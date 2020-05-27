/**
 开发人员：徐玉韬
 内容：用户订单的DAO层
 **/
package com.bupt.trainbookingsystem.dao;


import com.bupt.trainbookingsystem.entity.UserOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


import com.bupt.trainbookingsystem.entity.UserOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrderEntity,Integer> {
    //通过ID找订单

    UserOrderEntity findUserOrderEntityById(int id);
    //通过车次编号找订单
    List<UserOrderEntity> findUserOrderEntitiesByTripNumber(String trip_number);
    //通过车次ID找订单

    //通过乘车人ID找订单price

    //通过ID删除订单
    void deleteUserOrderEntityById(int id);
    //修改订单信息
    @Transactional
    @Modifying
    @Query(value="update user_order set  condition =?1 where id =?2",nativeQuery=true)
    void updateUserOrderEntityById(String condition, int id);

}
