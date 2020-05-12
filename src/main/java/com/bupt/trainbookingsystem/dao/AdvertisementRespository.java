package com.bupt.trainbookingsystem.dao;

import com.bupt.trainbookingsystem.entity.AdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 开发者：杨韦岽
 * 内容：广告管理
 */
@Repository
public interface AdvertisementRespository extends JpaRepository<AdvertisementEntity, Integer> {
}
