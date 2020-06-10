package com.bupt.trainbookingsystem.dao;


import com.bupt.trainbookingsystem.entity.AdministratorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * 开发者：杨韦岽
 * 内容：管理员账户
 * 开发者：徐玉韬
 * 内容：登陆检测
 */
@Repository
public interface AdministratorRespository extends JpaRepository<AdministratorEntity, Integer>, JpaSpecificationExecutor<AdministratorEntity> {
    //根据账户名称和密码查找相关系统管理员账户
    Optional<AdministratorEntity>
    findAdministratorEntityByNameAndPassword(String name,String password);


    //根据token查找相关管理员账户
    Optional<AdministratorEntity> findAdministratorEntityByToken(String token);
}
