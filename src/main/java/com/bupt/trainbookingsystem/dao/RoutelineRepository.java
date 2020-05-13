package com.bupt.trainbookingsystem.dao;

import com.bupt.trainbookingsystem.entity.RoutelineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 开发者：严智琪
 * 内容：火车路线
 */
@Repository
public interface RoutelineRepository extends JpaRepository<RoutelineEntity,Integer> {

}
