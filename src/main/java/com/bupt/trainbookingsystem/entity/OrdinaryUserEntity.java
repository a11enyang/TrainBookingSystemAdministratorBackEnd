package com.bupt.trainbookingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ordinary_user", schema = "booking", catalog = "")
public class OrdinaryUserEntity {
    private int id;
    private String name;
    private String password;
    private String personId;
    private String email;
    private String phonenum;
    private String type;
    private BigDecimal balance;
    private Byte isstudent;
    private Byte credit;
    private Collection<ContactEntity> contactsById;
    private Collection<UserOrderEntity> userOrdersById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "person_id", nullable = true, length = 100)
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phonenum", nullable = true, length = 100)
    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 100)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "balance", nullable = true, precision = 2)
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "isstudent", nullable = true)
    public Byte getIsstudent() {
        return isstudent;
    }

    public void setIsstudent(Byte isstudent) {
        this.isstudent = isstudent;
    }

    @Basic
    @Column(name = "credit", nullable = true)
    public Byte getCredit() {
        return credit;
    }

    public void setCredit(Byte credit) {
        this.credit = credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdinaryUserEntity that = (OrdinaryUserEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(password, that.password) &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phonenum, that.phonenum) &&
                Objects.equals(type, that.type) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(isstudent, that.isstudent) &&
                Objects.equals(credit, that.credit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, personId, email, phonenum, type, balance, isstudent, credit);
    }
    @JsonIgnore
    @OneToMany(mappedBy = "ordinaryUserByOrdineryUserId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    public Collection<ContactEntity> getContactsById() {
        return contactsById;
    }

    public void setContactsById(Collection<ContactEntity> contactsById) {
        this.contactsById = contactsById;
    }
    @JsonIgnore
    @OneToMany(mappedBy = "ordinaryUserByOrdineryUserId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    public Collection<UserOrderEntity> getUserOrdersById() {
        return userOrdersById;
    }

    public void setUserOrdersById(Collection<UserOrderEntity> userOrdersById) {
        this.userOrdersById = userOrdersById;
    }
}
