/*
 * 开发者：徐玉韬
 * 开发内容：广告类的service实现
 * */
package com.bupt.trainbookingsystem.service.imp;

import com.bupt.trainbookingsystem.dao.AdvertisementRespository;
import com.bupt.trainbookingsystem.entity.AdvertisementEntity;
import com.bupt.trainbookingsystem.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementServiceImp implements AdvertisementService {
    @Autowired
    private AdvertisementRespository advertisementRespository;

    @Override
    public AdvertisementEntity save(AdvertisementEntity a) {
        return advertisementRespository.save(a);
    }

    @Override
    public List<AdvertisementEntity> findAll() {
        return advertisementRespository.findAll();
    }

    @Override
    public     List<AdvertisementEntity> findAdvertisementEntitiesByLinkContaining(String s) {
        return advertisementRespository.findAdvertisementEntitiesByLinkContaining(s);
    }

    @Override
    public AdvertisementEntity findAdvertisementEntityById(int id) {
        return advertisementRespository.findAdvertisementEntityById(id);
    }

    @Override
    public void deleteAdvertisementEntityById(int id) {
        advertisementRespository.deleteAdvertisementEntityById(id);
    }

    @Override
    public AdvertisementEntity updateAdById(String link, String photo, int id) {
        advertisementRespository.updateAdById(link, photo, id);
        return advertisementRespository.findAdvertisementEntityById(id);
    }
}
