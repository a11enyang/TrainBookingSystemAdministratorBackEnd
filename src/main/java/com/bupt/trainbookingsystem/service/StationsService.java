package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.entity.StationsEntity;

import java.sql.Timestamp;
import java.util.List;

public interface StationsService {
    void save(StationsEntity s);
    List<StationsEntity> findStationsEntitiesByTripId(int id);
    void updateRoutelineEntityById(Timestamp arrive_time , int id);
    void deleteStationsEntityById(int id);
    Timestamp getStationTimeByTripIdAndStation(String start,int tripId);
}
