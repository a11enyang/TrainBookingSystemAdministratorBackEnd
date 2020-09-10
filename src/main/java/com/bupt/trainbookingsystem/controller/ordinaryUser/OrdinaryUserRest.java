package com.bupt.trainbookingsystem.controller.ordinaryUser;

import com.bupt.trainbookingsystem.entity.OrdinaryUserEntity;
import com.bupt.trainbookingsystem.service.imp.AdministratorService;
import com.bupt.trainbookingsystem.vo.Meta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = "普通用户管理的接口")
@RestController
@RequestMapping("/administratorapi/ordinaryuser")
@CrossOrigin(origins = "http://localhost:8081")
public class OrdinaryUserRest {

    @Autowired
    public AdministratorService administratorService;

    @ApiOperation("删除一个普通用户")
    //删除一个普通用户
    @PostMapping("/delete/{id}")
    public Object deleteOrdinaryUser(@PathVariable int id){
        administratorService.deleteOrdinaryUserById(id);
        return null;
    }

    @ApiOperation("更新一个普通用户")
    //编辑一个普通用户
    @PostMapping("/updateOne")
    public Object saveOrdinaryUser(@RequestBody OrdinaryUserEntity ordinaryUserEntity){
        administratorService.saveOrdinaryUser(ordinaryUserEntity);
        return null;
    }


    @ApiOperation("用户列表分页")
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

    @ApiOperation("搜索一个普通用户")
    //搜索一个普通用户
    @PostMapping("/search")
    public List<OrdinaryUserEntity> findAllSpecification(@RequestBody OrdinaryUserEntity ordinaryUserEntity){
        return administratorService.findPeoplesWithSpecification(ordinaryUserEntity);
    }
}
