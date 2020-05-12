package com.bupt.trainbookingsystem.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user_order", schema = "booking", catalog = "")
public class UserOrderEntity {
    private int id;
    private String tripNumber;
    private String routLine;
    private Timestamp tripTime;
    private String nameList;
    private String seatList;
    private BigDecimal price;
    private String condition;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "trip_number")
    public String getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(String tripNumber) {
        this.tripNumber = tripNumber;
    }

    @Basic
    @Column(name = "rout_line")
    public String getRoutLine() {
        return routLine;
    }

    public void setRoutLine(String routLine) {
        this.routLine = routLine;
    }

    @Basic
    @Column(name = "trip_time")
    public Timestamp getTripTime() {
        return tripTime;
    }

    public void setTripTime(Timestamp tripTime) {
        this.tripTime = tripTime;
    }

    @Basic
    @Column(name = "name_list")
    public String getNameList() {
        return nameList;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }

    @Basic
    @Column(name = "seat_list")
    public String getSeatList() {
        return seatList;
    }

    public void setSeatList(String seatList) {
        this.seatList = seatList;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "condition")
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOrderEntity that = (UserOrderEntity) o;
        return id == that.id &&
                Objects.equals(tripNumber, that.tripNumber) &&
                Objects.equals(routLine, that.routLine) &&
                Objects.equals(tripTime, that.tripTime) &&
                Objects.equals(nameList, that.nameList) &&
                Objects.equals(seatList, that.seatList) &&
                Objects.equals(price, that.price) &&
                Objects.equals(condition, that.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tripNumber, routLine, tripTime, nameList, seatList, price, condition);
    }
}
