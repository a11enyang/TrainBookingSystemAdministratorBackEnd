package com.bupt.trainbookingsystem.service.imp;

import com.bupt.trainbookingsystem.dao.ContactRespository;
import com.bupt.trainbookingsystem.entity.ContactEntity;
import com.bupt.trainbookingsystem.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImp implements ContactService {

    @Autowired
    ContactRespository contactRespository;

    @Override
    public Page<ContactEntity> findallcontator(int id, Pageable pageable) {
        Page<ContactEntity> contactEntityPage=contactRespository.findallcontactpage(id,pageable);
        return contactEntityPage;
    }

    @Override
    public List<ContactEntity> findcontactors(int id) {
        List<ContactEntity> contactEntities=contactRespository.findContactEntitiesByUserId(id);
        return contactEntities;
    }


}
