package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.dao.AdministratorRespository;
import com.bupt.trainbookingsystem.entity.AdministratorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface AdministratorService {

    public String login(String name, String password);

    public void saveAdministrator(AdministratorEntity administratorEntity);

    public AdministratorEntity findAdministratorByToken(String token);

    public AdministratorEntity findAdtorByNameAndPwd(String name, String pwd);

    public Page<AdministratorEntity> findAdministratorPage(int page, int pageSize);

    public Optional<User> findByToken(String token);
}
