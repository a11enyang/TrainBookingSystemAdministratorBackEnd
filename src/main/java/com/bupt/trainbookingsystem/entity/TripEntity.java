package com.bupt.trainbookingsystem.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "trip", schema = "booking", catalog = "")
public class TripEntity {
    private int id;
    private String trainNumber;
    private String startStation;
    private String endStation;
    private Timestamp departureTime;
    private String remainseatInfo;
    private Byte tripStatus;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "train_number")
    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    @Basic
    @Column(name = "start_station")
    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    @Basic
    @Column(name = "end_station")
    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    @Basic
    @Column(name = "departure_time")
    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    @Basic
    @Column(name = "remainseat_info")
    public String getRemainseatInfo() {
        return remainseatInfo;
    }

    public void setRemainseatInfo(String remainseatInfo) {
        this.remainseatInfo = remainseatInfo;
    }

    @Basic
    @Column(name = "trip_status")
    public Byte getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(Byte tripStatus) {
        this.tripStatus = tripStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripEntity that = (TripEntity) o;
        return id == that.id &&
                Objects.equals(trainNumber, that.trainNumber) &&
                Objects.equals(startStation, that.startStation) &&
                Objects.equals(endStation, that.endStation) &&
                Objects.equals(departureTime, that.departureTime) &&
                Objects.equals(remainseatInfo, that.remainseatInfo) &&
                Objects.equals(tripStatus, that.tripStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trainNumber, startStation, endStation, departureTime, remainseatInfo, tripStatus);
    }
}
