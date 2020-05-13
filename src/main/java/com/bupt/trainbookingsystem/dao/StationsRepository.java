package com.bupt.trainbookingsystem.dao;

import com.bupt.trainbookingsystem.entity.StationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 开发者：严智琪
 * 内容：站点信息
 */
@Repository
public interface StationsRepository extends JpaRepository<StationsEntity,Integer> {
}
