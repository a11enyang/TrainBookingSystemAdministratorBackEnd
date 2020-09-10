package com.bupt.trainbookingsystem.controller.systemCenter.ad;


import com.bupt.trainbookingsystem.entity.AdvertisementEntity;
import com.bupt.trainbookingsystem.service.imp.AdministratorService;
import com.bupt.trainbookingsystem.vo.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/administratorapi/ad")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class AdRest {
    @Autowired
    public AdministratorService administratorService;

    @PostMapping("/save")
    public Meta saveAd(@RequestBody AdvertisementEntity advertisementEntity){
        Meta meta = new Meta();
        try {
            administratorService.saveAd(advertisementEntity);
        }catch (Exception e){
            meta.setStatus(false);
            meta.setMsg("失败");
            return meta;
        }
        meta.setMsg("成功");
        meta.setStatus(true);
        return meta;
    }


    @PostMapping("/delete/{id}")
    public Meta deleteAd(@PathVariable int id){
        Meta meta = new Meta();
        try {
            administratorService.deleteAd(id);
        }catch (Exception e){
            meta.setStatus(false);
            meta.setMsg("失败");
            return meta;
        }
        meta.setStatus(true);
        meta.setMsg("成功");
        return meta;
    }


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
