package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.entity.ContactEntity;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ContactService {

    List<ContactEntity> findcontactors(int id);

    Page<ContactEntity> findallcontator(int id, Pageable pageable);


}
