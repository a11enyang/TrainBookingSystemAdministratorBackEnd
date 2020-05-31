/**
 开发人员：徐玉韬
 内容：用户订单的Service层
 **/
package com.bupt.trainbookingsystem.service;
import com.bupt.trainbookingsystem.entity.UserOrderEntity;
import com.bupt.trainbookingsystem.entity.custom.Userorder_search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface UserOrderService {
    void save(UserOrderEntity u);
    List<UserOrderEntity> findAll();
    //通过ID找订单
    UserOrderEntity findUserOrderEntityById(int id);
    //通过车次编号找订单
    List<UserOrderEntity> findUserOrderEntitiesByTripNumber(String trip_number);
    //通过车次ID找订单

    //通过乘车人ID找订单price

    //通过ID删除订单
    void deleteUserOrderEntityById(int id);
    //修改订单信息

    UserOrderEntity updateUserOrderEntityById1(String condition, int id);

    void updateUserOrderEntityById(String condition, int id);


    List<Userorder_search> order_paystate(int id, String state);



=======
    //根据状态查找订单
    List<Userorder_search> order_paystate(int id, String state);

    //根据id查找订单及列车信息
    List<Userorder_search> orderinfo(int id);
>>>>>>> Stashed changes
}
