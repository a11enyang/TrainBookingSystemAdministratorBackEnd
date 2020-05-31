package com.bupt.trainbookingsystem.dao;

import com.bupt.trainbookingsystem.entity.RoutelineEntity;
import com.bupt.trainbookingsystem.entity.UserOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * 开发者：严智琪
 * 内容：火车路线
 * 开发者：徐玉韬
 * 内容：增加路线的方法
 */
@Repository
public interface RoutelineRepository extends JpaRepository<RoutelineEntity,Integer> {
    //根据车次ID找到车次
     RoutelineEntity findRoutelineEntityByTripId(int id);
    @Transactional
    @Modifying
    @Query(value="update routeline set  route_line =?1 where id =?2",nativeQuery=true)
    void updateRoutelineEntityById(String route_line , int id);
    @Transactional
    @Modifying
    @Query(value = "select * from routeline where route_line like concat('%', ?1, '%', ?2, '%') ",nativeQuery=true)
    List<RoutelineEntity> findRouteEntitiesByStations(String start,String end);
}
