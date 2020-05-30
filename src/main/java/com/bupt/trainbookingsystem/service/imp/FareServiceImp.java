package com.bupt.trainbookingsystem.service.imp;

import com.bupt.trainbookingsystem.dao.FareRespository;
import com.bupt.trainbookingsystem.entity.FareEntity;
import com.bupt.trainbookingsystem.service.FareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FareServiceImp implements FareService {
    public final FareRespository fareRespository;

    public FareServiceImp(FareRespository fareRespository) {
        this.fareRespository = fareRespository;
    }

    @Override
    public void save(FareEntity f) {
    fareRespository.save(f);
    }

    @Override
    public List<FareEntity> findAll() {
        return  fareRespository.findAll();
    }

    @Override
    public List<FareEntity> findFareEntitiesByTripId(int id) {
        return fareRespository.findFareEntitiesByTripId(id);
    }

    @Override
    public void updateFareEntityById(BigDecimal price, int id) {
        fareRespository.updateFareEntityById(price,id);
    }

    @Override
    public void deleteFareEntityById(int id) {
        fareRespository.deleteFareEntityById(id);
    }
}
