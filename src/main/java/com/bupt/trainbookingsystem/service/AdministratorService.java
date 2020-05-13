package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.entity.AdministratorEntity;

public interface AdministratorService {
    AdministratorEntity findAdministratorEntityByNameAndPassword(String name, String password);
}
