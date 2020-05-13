package com.bupt.trainbookingsystem.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "seat", schema = "booking", catalog = "")
public class SeatEntity {
    private int id;
    private String firstStation;
    private String nextStation;
    private byte[] seatInfo;
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
    @Column(name = "first_station")
    public String getFirstStation() {
        return firstStation;
    }

    public void setFirstStation(String firstStation) {
        this.firstStation = firstStation;
    }

    @Basic
    @Column(name = "next_station")
    public String getNextStation() {
        return nextStation;
    }

    public void setNextStation(String nextStation) {
        this.nextStation = nextStation;
    }

    @Basic
    @Column(name = "seat_info")
    public byte[] getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(byte[] seatInfo) {
        this.seatInfo = seatInfo;
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
        SeatEntity that = (SeatEntity) o;
        return id == that.id &&
                Objects.equals(firstStation, that.firstStation) &&
                Objects.equals(nextStation, that.nextStation) &&
                Arrays.equals(seatInfo, that.seatInfo) &&
                Objects.equals(tripId, that.tripId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, firstStation, nextStation, tripId);
        result = 31 * result + Arrays.hashCode(seatInfo);
        return result;
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
