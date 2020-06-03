package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.entity.FareEntity;

import java.math.BigDecimal;
import java.util.*;
public interface FareService {
    void save(FareEntity f);
    List<FareEntity> findAll();
    List<FareEntity>  findFareEntitiesByTripId(int id);
    void updateFareEntityById(BigDecimal price , int id);
    void deleteFareEntityById(int id);
    BigDecimal getFareByStationsAndTripId(String start,String end,String type,int tripId);
}
