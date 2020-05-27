/**
 开发人员：徐玉韬
 内容：用户订单的Service层实现
 **/
package com.bupt.trainbookingsystem.service.imp;
import com.bupt.trainbookingsystem.dao.UserOrderRepository;

import com.bupt.trainbookingsystem.entity.UserOrderEntity;
import com.bupt.trainbookingsystem.entity.custom.EntityUtils;
import com.bupt.trainbookingsystem.entity.custom.Userorder_search;
import com.bupt.trainbookingsystem.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserOrderServiceImp implements UserOrderService {
    @Autowired
    UserOrderRepository userOrderRepository;


    private final UserOrderRepository uor;

    public UserOrderServiceImp(UserOrderRepository uor) {
        this.uor = uor;
    }

    @Override
    public void save(UserOrderEntity u) {
        uor.save(u);
    }

    @Override
    public List<UserOrderEntity> findAll() {
        return uor.findAll();
    }

    @Override
    public UserOrderEntity findUserOrderEntityById(int id) {
        return uor.findUserOrderEntityById(id);
    }

    @Override
    public List<UserOrderEntity> findUserOrderEntitiesByTripNumber(String trip_number) {
        return uor.findUserOrderEntitiesByTripNumber(trip_number);
    }

    @Override
    public void deleteUserOrderEntityById(int id) {
        uor.deleteUserOrderEntityById(id);
    }

    @Override
    public UserOrderEntity updateUserOrderEntityById1(String condition, int id) {
        uor.updateUserOrderEntityById(condition, id);
        return uor.findUserOrderEntityById(id);
    }
    @Override
    public void updateUserOrderEntityById(String condition,int id) {
        userOrderRepository.updateUserOrderEntityById(condition, id);

    }

    @Override
    public List<Userorder_search> orderpaystate(int id, String state) {
       List<Object[]> users= userOrderRepository.notpayorder(id,state);
       List<Userorder_search> orderlist=EntityUtils.castEntity(users,Userorder_search.class,new Userorder_search());
       return orderlist;
    }




}
