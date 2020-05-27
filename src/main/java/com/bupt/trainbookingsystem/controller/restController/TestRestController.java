package com.bupt.trainbookingsystem.controller.restController;

import com.bupt.trainbookingsystem.entity.OrdinaryUserEntity;
import com.bupt.trainbookingsystem.service.OrdinaryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestRestController {
    @Autowired
    private OrdinaryUserService ordinaryUserService;

    @PostMapping("/test/register")
    public OrdinaryUserEntity register(@RequestParam String name,
                           @RequestParam String password,
                           @RequestParam String cardType,
                           @RequestParam String personId,
                           @RequestParam String email,
                           @RequestParam String phonenum,
                           @RequestParam String personType){
        OrdinaryUserEntity userEntity = new OrdinaryUserEntity();
        userEntity.setName(name);
        userEntity.setPassword(password);
        userEntity.setType(cardType);
        userEntity.setPersonId(personId);
        userEntity.setEmail(email);
        userEntity.setPhonenum(phonenum);
        if(personType=="成人")
            userEntity.setIsstudent((byte) 0);
        else
            userEntity.setIsstudent((byte) 1);
        return userEntity;
    }
}
