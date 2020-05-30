package com.bupt.trainbookingsystem.service.imp;

import com.bupt.trainbookingsystem.dao.StationsRepository;
import com.bupt.trainbookingsystem.entity.StationsEntity;
import com.bupt.trainbookingsystem.service.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class StationsServiceImp implements StationsService {
    public final StationsRepository stationsRepository;

    public StationsServiceImp(StationsRepository stationsRepository) {
        this.stationsRepository = stationsRepository;
    }

    @Override
    public void save(StationsEntity s) {
        stationsRepository.save(s);
    }

    @Override
    public List<StationsEntity> findStationsEntitiesByTripId(int id) {
        return stationsRepository.findStationsEntitiesByTripId(id);
    }

    @Override
    public void updateRoutelineEntityById(Timestamp arrive_time, int id) {
        stationsRepository.updateRoutelineEntityById(arrive_time, id);
    }

    @Override
    public void deleteStationsEntityById(int id) {
        stationsRepository.deleteStationsEntityById(id);
    }
}
