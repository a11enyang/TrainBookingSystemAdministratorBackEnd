package com.bupt.trainbookingsystem.dao;

import com.bupt.trainbookingsystem.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
