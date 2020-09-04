package com.bupt.trainbookingsystem.service.imp;
/**
 * 开发者：徐玉韬
 * 内容：增加路线的方法
 */
import com.bupt.trainbookingsystem.dao.RoutelineRepository;
import com.bupt.trainbookingsystem.entity.RoutelineEntity;
import com.bupt.trainbookingsystem.service.RoutelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutelineServiceImp implements RoutelineService {

    private final RoutelineRepository routelineRepository;

    public RoutelineServiceImp(RoutelineRepository routelineRepository) {
        this.routelineRepository = routelineRepository;
    }

    @Override
    @Cacheable(value = "Route",key = "#id")
    public RoutelineEntity findRoutelineEntityByTripId(int id) {
        return routelineRepository.findRoutelineEntityByTripId(id);
    }
    @Override
    public String getRouteLineByTripId(int id) {
        return getRouteLineByTripId(id);
    }
    @Override
    @CachePut(value="Route",key="#result.getTripId()")
    public RoutelineEntity updateRoutelineEntityById(String route_line, int id) {
        routelineRepository.updateRoutelineEntityById(route_line, id);
        return  routelineRepository.findRoutelineEntityById(id);
    }
    @Override
    @CachePut(value="Route",key="#result.getTripId()")
    public  RoutelineEntity save(RoutelineEntity r) {
        routelineRepository.save(r);
        return  r;
    }



    @Override
    public List<RoutelineEntity> findRouteEntitiesByStations(String start, String end) {
        return routelineRepository.findRouteEntitiesByStations(start, end);
    }


}
