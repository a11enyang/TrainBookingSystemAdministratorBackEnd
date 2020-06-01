package com.bupt.trainbookingsystem.dao;

import com.bupt.trainbookingsystem.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * 开发者：严智琪
 * 内容：座位管理
 */
@Repository
public interface SeatRepository extends JpaRepository<SeatEntity,Integer> {
    @Transactional
    void deleteSeatEntitiesByTripId(int id);
    @Transactional
    @Query(value = "select seat_info from seat where first_station = ?1 and next_station =?2 and trip_id=?3",nativeQuery=true)
    byte[] getSeatByStartEndTripId(String first,String next,int trip_id);
}
