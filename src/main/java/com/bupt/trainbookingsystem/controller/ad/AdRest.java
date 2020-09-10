package com.bupt.trainbookingsystem.controller.ad;


import com.bupt.trainbookingsystem.entity.AdvertisementEntity;
import com.bupt.trainbookingsystem.service.imp.AdministratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Api(tags = "广告相关接口", description = "提供操作广告相关的 Rest API")
@RestController
@RequestMapping("/administratorapi/ad")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class AdRest {
    @Autowired
    public AdministratorService administratorService;

    @ApiOperation("保存广告信息的api")
    @PostMapping("/save")
    public Object saveAd(@RequestBody AdvertisementEntity advertisementEntity){
        administratorService.saveAd(advertisementEntity);
        return null;
    }


    @ApiOperation("删除广告的api")
    @PostMapping("/delete/{id}")
    public Object deleteAd(@PathVariable int id){
        administratorService.deleteAd(id);
        return null;
    }



    @ApiOperation("广告分页的api")
    @PostMapping("/page/{page}")
    public Map<String, Object> findAdsPage(@PathVariable int page){
        int pageSize = 10;
        page = page == 0 ? 1 : page;
        Page<AdvertisementEntity> pageInfo = administratorService.findAdInPage(page, pageSize);
        Map<String, Object>  mapper= new HashMap<>();
        mapper.put("totalElements", pageInfo.getTotalElements());
        mapper.put("onePageContent", pageInfo.getContent());
        return mapper;
    }
}
