package com.bupt.trainbookingsystem.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "routeline", schema = "booking", catalog = "")
public class RoutelineEntity {
    private int id;
    private String routeLine;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "route_line")
    public String getRouteLine() {
        return routeLine;
    }

    public void setRouteLine(String routeLine) {
        this.routeLine = routeLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutelineEntity that = (RoutelineEntity) o;
        return id == that.id &&
                Objects.equals(routeLine, that.routeLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, routeLine);
    }
}
