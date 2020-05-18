/*
* 开发者：徐玉韬
* 开发内容：广告类的service
* */
package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.entity.AdvertisementEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AdvertisementService {
    AdvertisementEntity save(AdvertisementEntity a);
    List<AdvertisementEntity> findAll();
    //依靠链接检索
    List<AdvertisementEntity> findAdvertisementEntitiesByLinkContaining(String s);
    //依靠ID检索
    AdvertisementEntity findAdvertisementEntityById(int id);
    //依靠ID删除
    void deleteAdvertisementEntityById(int id);
    //更新广告表
    AdvertisementEntity updateAdById(String link,String photo, int id);
}
