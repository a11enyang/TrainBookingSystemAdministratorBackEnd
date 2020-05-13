package com.bupt.trainbookingsystem.dao;

import com.bupt.trainbookingsystem.entity.FareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 开发者：杨韦岽
 * 内容：车票的费用
 */
@Repository
public interface FareRespository extends JpaRepository<FareEntity, Integer> {

}
