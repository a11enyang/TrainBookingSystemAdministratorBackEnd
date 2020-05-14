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
    public List<AdvertisementEntity> findAll() {
        return advertisementRespository.findAll();
    }
}
