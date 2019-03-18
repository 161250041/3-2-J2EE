package nju.edu.order.Model;

import javax.persistence.*;

@Entity
@Table(name = "User", schema = "J2EEOrderPj")
public class UserEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "userID")
    private String userID;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "balance")
    private double balance;

    public UserEntity(String userID, String password, double balance) {
        this.userID = userID;
        this.password = password;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.balance, balance) != 0) return false;
        if (userID != null ? !userID.equals(that.userID) : that.userID != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
