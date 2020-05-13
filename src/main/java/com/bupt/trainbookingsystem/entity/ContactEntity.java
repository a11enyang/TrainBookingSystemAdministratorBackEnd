package com.bupt.trainbookingsystem.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contact", schema = "booking", catalog = "")
public class ContactEntity {
    private int id;
    private String name;
    private String personId;
    private Integer ordineryUserId;
    private OrdinaryUserEntity ordinaryUserByOrdineryUserId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "person_id")
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "ordinery_user_id")
    public Integer getOrdineryUserId() {
        return ordineryUserId;
    }

    public void setOrdineryUserId(Integer ordineryUserId) {
        this.ordineryUserId = ordineryUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactEntity that = (ContactEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(ordineryUserId, that.ordineryUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, personId, ordineryUserId);
    }

    @ManyToOne
    @JoinColumn(name = "ordinery_user_id", referencedColumnName = "id",insertable = false,updatable = false)
    public OrdinaryUserEntity getOrdinaryUserByOrdineryUserId() {
        return ordinaryUserByOrdineryUserId;
    }

    public void setOrdinaryUserByOrdineryUserId(OrdinaryUserEntity ordinaryUserByOrdineryUserId) {
        this.ordinaryUserByOrdineryUserId = ordinaryUserByOrdineryUserId;
    }
}
