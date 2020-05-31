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
    private String userOrderCondition;
    private Integer ordineryUserId;
    private Integer tripId;
    private OrdinaryUserEntity ordinaryUserByOrdineryUserId;
    private TripEntity tripByTripId;
    private String pricelist;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)  // 自增
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
    @Column(name = "userOrderCondition")
    public String getUserOrderCondition() {
        return userOrderCondition;
    }

    public void setUserOrderCondition(String condition) {
        this.userOrderCondition = condition;
    }

    @Basic
    @Column(name="pricelist")
    public String getPricelist() { return pricelist; }

    public void setPricelist(String pricelist) { this.pricelist = pricelist; }

    @Basic
    @Column(name = "ordinery_user_id")
    public Integer getOrdineryUserId() {
        return ordineryUserId;
    }

    public void setOrdineryUserId(Integer ordineryUserId) {
        this.ordineryUserId = ordineryUserId;
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
        UserOrderEntity that = (UserOrderEntity) o;
        return id == that.id &&
                Objects.equals(tripNumber, that.tripNumber) &&
                Objects.equals(routLine, that.routLine) &&
                Objects.equals(tripTime, that.tripTime) &&
                Objects.equals(nameList, that.nameList) &&
                Objects.equals(seatList, that.seatList) &&
                Objects.equals(price, that.price) &&
                Objects.equals(userOrderCondition, that.userOrderCondition) &&
                Objects.equals(ordineryUserId, that.ordineryUserId) &&
                Objects.equals(tripId, that.tripId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tripNumber, routLine, tripTime, nameList, seatList, price, userOrderCondition, ordineryUserId, tripId);
    }

    @ManyToOne
    @JoinColumn(name = "ordinery_user_id", referencedColumnName = "id",insertable = false,updatable = false)
    public OrdinaryUserEntity getOrdinaryUserByOrdineryUserId() {
        return ordinaryUserByOrdineryUserId;
    }

    public void setOrdinaryUserByOrdineryUserId(OrdinaryUserEntity ordinaryUserByOrdineryUserId) {
        this.ordinaryUserByOrdineryUserId = ordinaryUserByOrdineryUserId;
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
