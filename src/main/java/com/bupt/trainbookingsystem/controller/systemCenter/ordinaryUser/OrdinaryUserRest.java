package com.bupt.trainbookingsystem.controller.systemCenter.ordinaryUser;

import com.bupt.trainbookingsystem.entity.OrdinaryUserEntity;
import com.bupt.trainbookingsystem.service.systemCenter.AdministratorService;
import com.bupt.trainbookingsystem.vo.Meta;
import com.bupt.trainbookingsystem.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/administratorapi/ordinaryuser")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class OrdinaryUserRest {

    @Autowired
    public AdministratorService administratorService;

    //删除一个普通用户
    @PostMapping("/delete/{id}")
    public Meta deleteOrdinaryUser(@PathVariable int id){
        Meta meta = new Meta();
        try {
            administratorService.deleteOrdinaryUserById(id);
        }catch (Exception e){
            meta.setStatus(false);
            meta.setMsg("删除失败!");
            return meta;
        }
        meta.setMsg("删除成功");
        meta.setStatus(true);
        return meta;
    }

    //编辑一个普通用户
    @PostMapping("/updateOne")
    public Meta saveOrdinaryUser(@RequestBody OrdinaryUserEntity ordinaryUserEntity){
        Meta meta = new Meta();
        try {
            administratorService.saveOrdinaryUser(ordinaryUserEntity);
        }catch (Exception e){
            meta.setStatus(false);
            meta.setMsg("编辑失败");
            return meta;
        }
        meta.setMsg("编程成功");
        meta.setStatus(true);
        return meta;
    }


    //分页查询
    @PostMapping("/page/{page}")
    public Map<String, Object> findOrdinaryUsersWithPage(@PathVariable int page){
        int pageSize = 10;
        page = page == 0 ? 1 : page;
        Page<OrdinaryUserEntity> pageInfo = administratorService.findOrdinaryUsersInPage(page, pageSize);
        Map<String, Object> mapper = new HashMap<>();
        mapper.put("totalElements", pageInfo.getTotalElements());
        mapper.put("onePageContent", pageInfo.getContent());
        return mapper;
    }

    //搜索一个普通用户
    @PostMapping("/search")
    public List<OrdinaryUserEntity> findAllSpecification(@RequestBody OrdinaryUserEntity ordinaryUserEntity){
        return administratorService.findPeoplesWithSpecification(ordinaryUserEntity);
    }
}
