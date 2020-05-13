package com.bupt.trainbookingsystem.dao;

import com.bupt.trainbookingsystem.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 开发者：严智琪
 * 内容：座位管理
 */
@Repository
public interface SeatRepository extends JpaRepository<SeatEntity,Integer> {
}
