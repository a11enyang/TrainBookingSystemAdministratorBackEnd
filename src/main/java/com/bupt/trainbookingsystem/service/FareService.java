package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.entity.FareEntity;
import com.bupt.trainbookingsystem.entity.TripEntity;

import java.math.BigDecimal;
import java.util.*;
public interface FareService {
    FareEntity save(FareEntity f);
    List<FareEntity> findAll();
    List<FareEntity>  findFareEntitiesByTripId(int id);
    List<FareEntity> updateFareEntityById(BigDecimal price , int id);
    TripEntity deleteFareEntityById(int id);
    BigDecimal getFareByStationsAndTripId(String start,String end,String type,int tripId);
}
