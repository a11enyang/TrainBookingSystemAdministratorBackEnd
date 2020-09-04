/**

 * 开发者：徐玉韬
 * 内容：增加路线的方法
 */
package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.entity.RoutelineEntity;

import java.util.List;

public interface RoutelineService {
    RoutelineEntity findRoutelineEntityByTripId(int id);
    RoutelineEntity  save(RoutelineEntity r);
    RoutelineEntity updateRoutelineEntityById(String route_line , int id);
    List<RoutelineEntity> findRouteEntitiesByStations(String start, String end);
    String getRouteLineByTripId(int id);
}
