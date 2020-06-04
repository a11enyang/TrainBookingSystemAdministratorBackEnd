package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.dao.AdministratorRespository;
import com.bupt.trainbookingsystem.entity.AdministratorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface AdministratorService {

    public String login(String name, String password);

    public void saveAdministrator(AdministratorEntity administratorEntity);

    public AdministratorEntity findByToken(String token);

    public AdministratorEntity findAdtorByNameAndPwd(String name, String pwd);

    public Page<AdministratorEntity> findAdministratorPage(int page, int pageSize);
}
