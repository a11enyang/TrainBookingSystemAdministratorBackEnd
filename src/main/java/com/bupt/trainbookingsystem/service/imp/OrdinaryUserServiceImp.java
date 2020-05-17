package com.bupt.trainbookingsystem.service.imp;

import com.bupt.trainbookingsystem.dao.OrdinaryUserRepository;
import com.bupt.trainbookingsystem.entity.OrdinaryUserEntity;
import com.bupt.trainbookingsystem.service.OrdinaryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdinaryUserServiceImp implements OrdinaryUserService {
    @Autowired
    OrdinaryUserRepository ordinaryUserRepository;

    @Override
    public OrdinaryUserEntity checkuser(String name, String password) {
        OrdinaryUserEntity ordinaryuser=ordinaryUserRepository.TrygetUser(name,password);
        return ordinaryuser;
    }

    @Override
    public OrdinaryUserEntity getuser(String username) {
        OrdinaryUserEntity ordinaryuser=ordinaryUserRepository.finduserbyname(username);
        return ordinaryuser;
    }

    @Override
    public List<OrdinaryUserEntity> findAll() {
        return ordinaryUserRepository.findAll();
    }

    @Override
    public void deleteOrdinaryUserEntityById(int id) {
        ordinaryUserRepository.deleteOrdinaryUserEntityById(id);
    }

    @Override
    public OrdinaryUserEntity findOrdinaryUserEntityById(int id) {
        return ordinaryUserRepository.findOrdinaryUserEntityById(id);
    }

    @Override
    public List<OrdinaryUserEntity> findOrdinaryUserEntitiesByNameContaining(String s) {
        return ordinaryUserRepository.findOrdinaryUserEntitiesByNameContaining(s);
    }

    @Override
    public OrdinaryUserEntity updateUserById(String name, String password, String person_id, Byte is_student, Byte credit, int id) {
        ordinaryUserRepository.updateUserById(name, password, person_id, is_student, credit, id);
        return ordinaryUserRepository.findOrdinaryUserEntityById(id);
    }

}
