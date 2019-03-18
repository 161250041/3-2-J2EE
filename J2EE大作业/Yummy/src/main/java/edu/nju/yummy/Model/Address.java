package edu.nju.yummy.Model;

import javax.persistence.*;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-23 00:10
 * @Modified By: hyh
 */
@Entity
@Table(name = "Address", schema = "Yummy")
public class Address {
    private int addressId;
    private String address;
    private User user;
    private Integer position;

    public Address() {
    }

    public Address(int addressId, String address, User user, Integer position) {
        this.addressId = addressId;
        this.address = address;
        this.user = user;
        this.position = position;
    }

    @ManyToOne
    @JoinColumn(name="userId",referencedColumnName="userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Id
    @Column(name = "addressId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Basic
    @Column(name = "position")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address1 = (Address) o;

        if (addressId != address1.addressId) return false;
        if (address != null ? !address.equals(address1.address) : address1.address != null) return false;
        if (position != null ? !position.equals(address1.position) : address1.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressId;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
