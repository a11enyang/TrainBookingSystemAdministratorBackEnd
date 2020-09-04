package com.bupt.trainbookingsystem.service.imp;

import com.bupt.trainbookingsystem.dao.LogRespository;
import com.bupt.trainbookingsystem.entity.LoginfoEntity;
import com.bupt.trainbookingsystem.service.LoginfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginfoServiceImp implements LoginfoService {
    @Autowired
    LogRespository logRespository;


    @Override
    public void insertLogininfo(LoginfoEntity loginfoEntity) {
        logRespository.save(loginfoEntity);
    }
}
