package edu.nju.yummy.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-23 00:10
 * @Modified By: hyh
 */
@Entity
@Table(name = "User", schema = "Yummy")
public class User {
    private int userId;
    private String email;
    private String password;
    private String userName;
    private String phoneNumber;
    private Double consumption;
    private List<Address> addressList;

    public User() {
    }

    public User(int userId, String email, String password, String userName, String phoneNumber, Double consumption, List<Address> addressList) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.consumption = consumption;
        this.addressList = addressList;
    }

    @Id
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "consumption")
    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Address.class)
    @JoinColumn(name = "userId")
    public List<Address> getAddressSet() {
        return addressList;
    }

    public void setAddressSet(List<Address> addressSet) {
        this.addressList = addressSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        if (consumption != null ? !consumption.equals(user.consumption) : user.consumption != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (consumption != null ? consumption.hashCode() : 0);
        return result;
    }
}
