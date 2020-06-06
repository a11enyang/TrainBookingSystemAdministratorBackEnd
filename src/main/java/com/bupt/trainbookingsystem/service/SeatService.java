package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.entity.SeatEntity;

public interface SeatService {
    void save(SeatEntity s);
    void deleteSeatEntitiesByTripId(int id);
    String getSeatByStartEndTripId(String first,String next,int trip_id);
    void updateSeatInfoByTripId(String s, String  startFirst,String endNext,int tripId);
}
