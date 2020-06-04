package com.bupt.trainbookingsystem.controller.administrator;

import com.bupt.trainbookingsystem.entity.AdministratorEntity;
import com.bupt.trainbookingsystem.service.AdministratorService;
import com.bupt.trainbookingsystem.vo.Accept;
import com.bupt.trainbookingsystem.vo.Meta;
import com.bupt.trainbookingsystem.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/administrator")
public class AdministratorLogin {
    @Autowired
    public AdministratorService administratorService;

    @PostMapping("/login")
    public Result login(@RequestBody Accept accept){
        String token = administratorService.login(accept.getUsername(), accept.getPassword());
        Result result = new Result();
        Meta meta = new Meta();
        if (token.isEmpty()){
            meta.setStatus(100);
            meta.setMsg("用户名或者密码错误");
            result.setMeta(meta);
            return result;
        }
        meta.setMsg("登录成功");
        meta.setStatus(200);
        result.setToken(token);
        result.setName(accept.getUsername());
        result.setMeta(meta);
        return result;
    }


    @PostMapping("/administratorpersonal/{token}")
    public AdministratorEntity findAdtor(@PathVariable String token){
        return administratorService.findByToken(token);
    }
}
