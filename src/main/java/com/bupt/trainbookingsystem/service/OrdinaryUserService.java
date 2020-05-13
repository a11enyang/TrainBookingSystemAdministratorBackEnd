package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.entity.OrdinaryUserEntity;

public interface OrdinaryUserService {

    //普通用户检验账号密码是否正确
    OrdinaryUserEntity checkuser(String name, String password);

    //获取用户登录状态
    OrdinaryUserEntity getuser(String username);
}
