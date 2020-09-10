package com.bupt.trainbookingsystem.controller.administrator;

import com.bupt.trainbookingsystem.bean.ResponseData;
import com.bupt.trainbookingsystem.entity.AdministratorEntity;
import com.bupt.trainbookingsystem.service.AdministratorService;
import com.bupt.trainbookingsystem.vo.Accept;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "管理员登录的接口")
@RestController
@CrossOrigin
@RequestMapping("/administrator")
public class AdministratorLogin {
    @Autowired
    public AdministratorService administratorService;

    @ApiOperation("管理员登录")
    @PostMapping("/login")
    public Object login(@RequestBody Accept accept) {
        String token = administratorService.login(accept.getUsername(), accept.getPassword());
        System.out.println(token);
        if (token.isEmpty()){
            return ResponseData.fail("账号或密码不对", 101);
        }
        return token;
    }


    @ApiOperation("根据token找到相关管理员")
    @PostMapping("/administratorpersonal/{token}")
    public AdministratorEntity findAdtor(@PathVariable String token){
        AdministratorEntity administratorEntity = administratorService.findAdministratorByToken(token);
        return administratorEntity;
    }
}
