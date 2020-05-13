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
    private Integer tripId;
    private TripEntity tripByTripId;

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

    @Basic
    @Column(name = "trip_id")
    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationsEntity that = (StationsEntity) o;
        return id == that.id &&
                Objects.equals(stationName, that.stationName) &&
                Objects.equals(arriveTime, that.arriveTime) &&
                Objects.equals(tripId, that.tripId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stationName, arriveTime, tripId);
    }

    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id",insertable = false,updatable = false)
    public TripEntity getTripByTripId() {
        return tripByTripId;
    }

    public void setTripByTripId(TripEntity tripByTripId) {
        this.tripByTripId = tripByTripId;
    }
}
