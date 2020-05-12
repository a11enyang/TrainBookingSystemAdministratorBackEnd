package com.bupt.trainbookingsystem.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "train", schema = "booking", catalog = "")
public class TrainEntity {
    private int id;
    private String trainType;
    private String seatInfo;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "train_type")
    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    @Basic
    @Column(name = "seat_info")
    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainEntity that = (TrainEntity) o;
        return id == that.id &&
                Objects.equals(trainType, that.trainType) &&
                Objects.equals(seatInfo, that.seatInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trainType, seatInfo);
    }
}
