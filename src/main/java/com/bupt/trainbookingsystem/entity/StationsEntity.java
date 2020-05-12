package com.bupt.trainbookingsystem.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "stations", schema = "booking", catalog = "")
public class StationsEntity {
    private int id;
    private String stationName;
    private Timestamp arriveTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "station_name")
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Basic
    @Column(name = "arrive_time")
    public Timestamp getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Timestamp arriveTime) {
        this.arriveTime = arriveTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationsEntity that = (StationsEntity) o;
        return id == that.id &&
                Objects.equals(stationName, that.stationName) &&
                Objects.equals(arriveTime, that.arriveTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stationName, arriveTime);
    }
}
