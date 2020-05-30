package com.bupt.trainbookingsystem.service.imp;

import com.bupt.trainbookingsystem.dao.SeatRepository;
import com.bupt.trainbookingsystem.entity.SeatEntity;
import com.bupt.trainbookingsystem.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImp implements SeatService {
    @Autowired
    public SeatRepository seatRepository;
    @Override
    public void save(SeatEntity s) {
        seatRepository.save(s);
    }

    @Override
    public void deleteSeatEntitiesByTripId(int id) {
        seatRepository.deleteSeatEntitiesByTripId(id);
    }
}
