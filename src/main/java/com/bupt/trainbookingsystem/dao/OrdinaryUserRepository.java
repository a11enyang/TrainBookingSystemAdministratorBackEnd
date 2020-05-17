package com.bupt.trainbookingsystem.dao;

import com.bupt.trainbookingsystem.entity.OrdinaryUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

/**
 * 开发者：严智琪
 * 内容：普通用户管理
 * 开发者：徐玉韬
 * 内容：增加了用户的删除修改查找等功能
 */

@Repository
public interface OrdinaryUserRepository extends JpaRepository<OrdinaryUserEntity,Integer> {

    @Query("select t from OrdinaryUserEntity t where t.name=?1 and t.password=?2")
    public OrdinaryUserEntity TrygetUser(String name,String password);

    @Query("select t from OrdinaryUserEntity t where t.name=?1")
    public OrdinaryUserEntity finduserbyname(String name);
    //根据ID查找用户
    OrdinaryUserEntity findOrdinaryUserEntityById(int id);
    //根据ID删除用户
    @Transactional
    void deleteOrdinaryUserEntityById(int id);
    //根据姓名查找用户
    List<OrdinaryUserEntity> findOrdinaryUserEntitiesByNameContaining(String s);
    //根据ID编辑用户
    @Transactional
    @Modifying
    @Query(value="update ordinary_user set name = ?1,password=?2,person_id=?3,isstudent=?4,credit=?5  where id =?6",
            nativeQuery=true)
    void updateUserById(String name,String password,String person_id,Byte is_student,Byte credit ,int id);
}
