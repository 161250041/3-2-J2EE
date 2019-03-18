package edu.nju.onlineStock.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "userID")
    private String userID;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private double balance;

    public User(int id, String userID, String password, double balance) {
        this.id = id;
        this.userID = userID;
        this.password = password;
        this.balance = balance;
    }

    public User() {
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
}
