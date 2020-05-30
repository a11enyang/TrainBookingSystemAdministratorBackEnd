package com.bupt.trainbookingsystem.dao;

import com.bupt.trainbookingsystem.entity.FareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
/**
 * 开发者：杨韦岽
 * 内容：车票的费用
 * 开发者：徐玉韬
 * 内容：填写方法
 */
@Repository
public interface FareRespository extends JpaRepository<FareEntity, Integer> {
List<FareEntity>  findFareEntitiesByTripId(int id);
    @Transactional
    @Modifying
    @Query(value="update fare set price =?1 where id =?2",nativeQuery=true)
    void updateFareEntityById(BigDecimal price , int id);
    @Transactional
    void deleteFareEntityById(int id);
}
