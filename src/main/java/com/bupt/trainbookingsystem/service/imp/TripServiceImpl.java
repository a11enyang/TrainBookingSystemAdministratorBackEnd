/**
 开发人员：徐玉韬
 内容：车次的Service层实现
 **/
package com.bupt.trainbookingsystem.service.imp;

import com.bupt.trainbookingsystem.dao.TripRepository;
import com.bupt.trainbookingsystem.entity.TripEntity;
import com.bupt.trainbookingsystem.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {
    @Autowired
    private final TripRepository tpr;

    public TripServiceImpl(TripRepository tpr) {
        this.tpr = tpr;
    }

    @Override
    public void save(TripEntity t) {
        tpr.save(t);
    }

    @Override
    public List<TripEntity> findAll() {
        return tpr.findAll();
    }

    @Override
    public TripEntity findTripEntityById(int id) {
        return tpr.findTripEntityById(id);
    }

    @Override
    public TripEntity findTripEntityByTrainNumber(String num) {
        return tpr.findTripEntityByTrainNumber(num);
    }

    @Override
    public TripEntity findTripEntityByStartStation(String start) {
        return tpr.findTripEntityByStartStation(start);
    }

    @Override
    public TripEntity findTripEntityByEndStation(String end) {
        return tpr.findTripEntityByEndStation(end);
    }

    @Override
    public List<TripEntity> findTripEntitiesByDepartureTimeIsStartingWith(Timestamp departureTime) {
        return tpr.findTripEntitiesByDepartureTimeIsStartingWith(departureTime);
    }

    @Override
    public List<TripEntity> findTripEntitiesByTripStatus(Byte status) {
        return tpr.findTripEntitiesByTripStatus(status);
    }

    @Override
    public List<TripEntity> findTripEntitiesByTrainNumberContaining(String number) {
        return tpr.findTripEntitiesByTrainNumberContaining(number);
    }

    @Override
    public void deleteTripEntityById(int id) {
        tpr.deleteTripEntityById(id);
    }

    @Override
    public void updateTripEntityById(String train_number, Timestamp departureTime, Byte status, int id) {
        tpr.updateTripEntityById(train_number, departureTime, status, id);
    }

    @Override
    public void updateRemainSeatByTripId(String afterRemain, int tripId) {
        tpr.updateRemainSeatByTripId(afterRemain, tripId);
    }
}
