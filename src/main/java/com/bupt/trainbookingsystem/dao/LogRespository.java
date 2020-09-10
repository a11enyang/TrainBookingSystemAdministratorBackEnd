package com.bupt.trainbookingsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRespository extends JpaRepository<LoginfoEntity,Integer> {

}
