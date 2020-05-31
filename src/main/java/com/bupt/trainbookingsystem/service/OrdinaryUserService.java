package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.entity.OrdinaryUserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface OrdinaryUserService {

    //普通用户检验账号密码是否正确
    OrdinaryUserEntity checkuser(String name, String password);

    //获取用户登录状态
    OrdinaryUserEntity getuser(String username);
    //找到所有用户
    List<OrdinaryUserEntity> findAll();
    //删除用户
    void deleteOrdinaryUserEntityById(int id);
    //根据ID查找用户
    OrdinaryUserEntity findOrdinaryUserEntityById(int id);
    //根据姓名查找用户
    List<OrdinaryUserEntity> findOrdinaryUserEntitiesByNameContaining(String s);
    //根据ID编辑用户
    OrdinaryUserEntity updateUserById(String name,String password,String person_id,Byte is_student,Byte credit ,int id);
<<<<<<< Updated upstream
    //创建用户(id自增)
    void createOrdinaryUserEntity(OrdinaryUserEntity userEntity);

=======
    //根据姓名更改用户
    void edituinfo(String phonenum,String name);
>>>>>>> Stashed changes
}
