/**

 * 开发者：徐玉韬
 * 内容：增加路线的方法
 */
package com.bupt.trainbookingsystem.service;

import com.bupt.trainbookingsystem.entity.RoutelineEntity;

public interface RoutelineService {
    RoutelineEntity findRoutelineEntityByTripId(int id);
    void save(RoutelineEntity r);
    void updateRoutelineEntityById(String route_line , int id);
}
