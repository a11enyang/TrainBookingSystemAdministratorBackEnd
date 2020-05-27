/**
 开发人员：徐玉韬
 内容：用户订单的Service层实现
 **/
package com.bupt.trainbookingsystem.service.imp;
import com.bupt.trainbookingsystem.dao.UserOrderRepository;
import com.bupt.trainbookingsystem.entity.UserOrderEntity;
import com.bupt.trainbookingsystem.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserOrderServiceImp implements UserOrderService {
    private final UserOrderRepository uor;

    public UserOrderServiceImp(UserOrderRepository uor) {
        this.uor = uor;
    }

    @Override
    public List<UserOrderEntity> findAll() {
        return uor.findAll();
    }

    @Override
    public void save(UserOrderEntity u) {
        uor.save(u);
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
    public UserOrderEntity updateUserOrderEntityById(String condition, int id) {
        uor.updateUserOrderEntityById(condition, id);
        return uor.findUserOrderEntityById(id);
    }
}
