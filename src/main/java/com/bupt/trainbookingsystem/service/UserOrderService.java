/**
 开发人员：徐玉韬
 内容：用户订单的Service层
 **/
package com.bupt.trainbookingsystem.service;
import com.bupt.trainbookingsystem.entity.UserOrderEntity;
import java.util.List;

public interface UserOrderService {
    void save(UserOrderEntity u);

    //通过ID找订单
    UserOrderEntity findUserOrderEntityById(int id);
    //通过车次编号找订单
    List<UserOrderEntity> findUserOrderEntitiesByTripNumber(String trip_number);
    //通过车次ID找订单

    //通过乘车人ID找订单price

    //通过ID删除订单
    void deleteUserOrderEntityById(int id);
    //修改订单信息
    void updateUserOrderEntityById(String condition, int id);
}
