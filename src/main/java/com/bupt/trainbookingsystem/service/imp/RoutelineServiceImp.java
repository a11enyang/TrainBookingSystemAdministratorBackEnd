package com.bupt.trainbookingsystem.service.imp;
/**
 * 开发者：徐玉韬
 * 内容：增加路线的方法
 */
import com.bupt.trainbookingsystem.dao.RoutelineRepository;
import com.bupt.trainbookingsystem.entity.RoutelineEntity;
import com.bupt.trainbookingsystem.service.RoutelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutelineServiceImp implements RoutelineService {

    private final RoutelineRepository routelineRepository;

    public RoutelineServiceImp(RoutelineRepository routelineRepository) {
        this.routelineRepository = routelineRepository;
    }

    @Override
    public RoutelineEntity findRoutelineEntityByTripId(int id) {
        return routelineRepository.findRoutelineEntityByTripId(id);
    }

    @Override
    public void save(RoutelineEntity r) {
        routelineRepository.save(r);
    }

    @Override
    public void updateRoutelineEntityById(String route_line, int id) {
        routelineRepository.updateRoutelineEntityById(route_line, id);
    }

    @Override
    public List<RoutelineEntity> findRouteEntitiesByStations(String start, String end) {
        return routelineRepository.findRouteEntitiesByStations(start, end);
    }
}
