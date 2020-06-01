package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.entity.SeatEntity;

public interface SeatService {
    void save(SeatEntity s);
    void deleteSeatEntitiesByTripId(int id);
    byte[] getSeatByStartEndTripId(String first,String next,int trip_id);
}
