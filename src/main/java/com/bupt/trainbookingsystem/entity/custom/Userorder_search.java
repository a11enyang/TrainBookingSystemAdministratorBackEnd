package com.bupt.trainbookingsystem.entity.custom;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Userorder_search {
    private  int id;
    private  String trainnum;
    private  String namelist;
    private  String seat;
    private  BigDecimal price;
    private  String startstation;
    private  String endstation;
    private  Timestamp depaturetime;

    public Userorder_search(){
        super();
    }

    public Userorder_search(int id, String trainnum, String namelist, String seat, BigDecimal price, String startstation, String endstation, Timestamp depaturetime) {
        this.id = id;
        this.trainnum = trainnum;
        this.namelist = namelist;
        this.seat = seat;
        this.price = price;
        this.startstation = startstation;
        this.endstation = endstation;
        this.depaturetime = depaturetime;
    }

    @Override
    public String toString() {
        return "Userorder_search{" +
                "id=" + id +
                ", trainnum='" + trainnum + '\'' +
                ", namelist='" + namelist + '\'' +
                ", seat='" + seat + '\'' +
                ", price=" + price +
                ", startstation='" + startstation + '\'' +
                ", endstation='" + endstation + '\'' +
                ", depaturetime=" + depaturetime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrainnum() {
        return trainnum;
    }

    public void setTrainnum(String trainnum) {
        this.trainnum = trainnum;
    }

    public String getNamelist() { return namelist; }

    public void setNamelist(String namelist) { this.namelist = namelist; }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStartstation() {
        return startstation;
    }

    public void setStartstation(String startstation) {
        this.startstation = startstation;
    }

    public String getEndstation() {
        return endstation;
    }

    public void setEndstation(String endstation) {
        this.endstation = endstation;
    }

    public Timestamp getDepaturetime() {
        return depaturetime;
    }

    public void setDepaturetime(Timestamp depaturetime) {
        this.depaturetime = depaturetime;
    }


}
