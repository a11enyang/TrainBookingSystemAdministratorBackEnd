package com.bupt.trainbookingsystem.service.imp;

import com.bupt.trainbookingsystem.dao.AdministratorRespository;
import com.bupt.trainbookingsystem.entity.AdministratorEntity;
import com.bupt.trainbookingsystem.service.AdministratorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdministratorServiceImp implements AdministratorService {
    @Autowired
    private AdministratorRespository administratorRespository;


    //根据账号找到管理员对象
    @Override
    public AdministratorEntity findAdtorByNameAndPwd(String name, String pwd){
        return administratorRespository.findAdministratorEntityByNameAndPassword(name, pwd).get();
    }


    //登录并且设置token
    @Override
    public String login(String name, String password){
        Optional<AdministratorEntity> administratorEntity = administratorRespository.findAdministratorEntityByNameAndPassword(name, password);
        if (administratorEntity.isPresent()){
            String token = UUID.randomUUID().toString();
            AdministratorEntity administratorEntity1 = administratorEntity.get();
            administratorEntity1.setToken(token);
            administratorRespository.save(administratorEntity1);
            return token;
        }
        return StringUtils.EMPTY;
    }

    @Override
    public AdministratorEntity findByToken(String token) {
        return administratorRespository.findAdministratorEntityByToken(token).get();
    }

    //保存管理员
    @Override
    public void saveAdministrator(AdministratorEntity administratorEntity) {
        administratorRespository.save(administratorEntity);
    }

    //分页显示
    public Page<AdministratorEntity> findAdministratorPage(int page, int pageSize){
        page--;
        page = page < 0 ? 0 :page;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        return administratorRespository.findAll(pageable);
    }
}
