/**
 开发人员：徐玉韬
 内容：票务管理员的Service层实现
 **/
package com.bupt.trainbookingsystem.service;
import com.bupt.trainbookingsystem.dao.TicketManagerRepository;
import com.bupt.trainbookingsystem.entity.TicketManagerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketManagerServiceImp implements TicketManagerService {
    private final TicketManagerRepository tmr;

    public TicketManagerServiceImp(TicketManagerRepository tmr) {
        this.tmr = tmr;
    }

    @Override
    public List<TicketManagerEntity> findALL() {
        return tmr.findAll();
    }

    @Override
    public void save(TicketManagerEntity t) {
        tmr.save(t);
    }

    @Override
    public TicketManagerEntity findTicketManagerEntityById(int id) {
      return  tmr.findTicketManagerEntityById(id);
    }

    @Override
    public TicketManagerEntity findTicketManagerEntityByNameContains(String name) {
        return tmr.findTicketManagerEntityByNameContains(name);
    }

    @Override
    public TicketManagerEntity findTicketManagerEntityByStaffIdNotContains(String staff_id) {
        return tmr.findTicketManagerEntityByStaffIdNotContains(staff_id);
    }

    @Override
    public void deleteTicketManagerEntityById(int id) {
        tmr.deleteTicketManagerEntityById(id);
    }

    @Override
    public void updateTicketManagerById(String name, String password, String staff_id, int id) {
        tmr.updateTicketManagerById(name, password, staff_id, id);
    }
}
