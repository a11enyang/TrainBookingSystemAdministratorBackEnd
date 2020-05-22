package com.bupt.trainbookingsystem.dao;

import com.bupt.trainbookingsystem.entity.ContactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

import static org.hibernate.jpa.QueryHints.HINT_COMMENT;

/**
 * 开发者：杨韦岽
 * 内容：常用联系人
 */
@Repository
public interface ContactRespository extends JpaRepository<ContactEntity, Integer>{
       //根据用户id找常用联系人
       @Query("select t from ContactEntity t where t.ordineryUserId=:id")
       List<ContactEntity> findContactEntitiesByUserId(@Param("id") int id);

       @QueryHints(value = {@QueryHint(name=HINT_COMMENT,value = "a query for pageable")},forCounting = false)
       @Query("select t from ContactEntity t where t.ordineryUserId=:id")
       Page<ContactEntity> findallcontactpage(@Param("id") int id, Pageable pageable);
}

