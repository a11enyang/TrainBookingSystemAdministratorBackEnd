/**
 开发人员：徐玉韬
 内容：列车的Service层实现
 **/
package com.bupt.trainbookingsystem.service.imp;
import com.bupt.trainbookingsystem.dao.TrainRepository;
import com.bupt.trainbookingsystem.entity.TrainEntity;
import com.bupt.trainbookingsystem.service.TrainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainServiceImp implements TrainService {
    private final TrainRepository tr;

    public TrainServiceImp(TrainRepository tr) {
        this.tr = tr;
    }

    @Override
    public List<TrainEntity> findAll() {
        return tr.findAll();
    }

    @Override
    public void save(TrainEntity t) {
        tr.save(t);
    }

    @Override
    public TrainEntity findTrainEntityById(int id) {
        return tr.findTrainEntityById(id);
    }

    @Override
    public List<TrainEntity> findTrainEntitiesByTrainTypeContaining(String type) {
        return tr.findTrainEntitiesByTrainTypeContaining(type);
    }

    @Override
    public void deleteTrainEntityById(int id) {
        tr.deleteTrainEntityById(id);
    }

    @Override
    public void updateTrainEntityById(String train_type, String seat_info, int id) {
        tr.updateTrainEntityById(train_type, seat_info, id);
    }
}
