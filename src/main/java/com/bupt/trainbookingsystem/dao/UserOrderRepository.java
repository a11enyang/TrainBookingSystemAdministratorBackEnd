package com.bupt.trainbookingsystem.dao;

import com.bupt.trainbookingsystem.entity.UserOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrderEntity,Integer> {
    
}
