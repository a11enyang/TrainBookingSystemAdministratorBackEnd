package com.bupt.trainbookingsystem.dao;

import com.bupt.trainbookingsystem.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 开发者：杨韦岽
 * 内容：常用联系人
 */
@Repository
public interface ContactRespository extends JpaRepository<ContactEntity, Integer>{

}

