package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.dao.OrdinaryUserRepository;
import com.bupt.trainbookingsystem.entity.OrdinaryUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
