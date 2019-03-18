package edu.nju.yummy.Model;

import javax.persistence.*;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-27 01:08
 * @Modified By: hyh
 */
@Entity
@Table(name = "Verification", schema = "Yummy")
public class Verification {
    private int id;
    private String email;
    private String password;
    private int state;
    private String code;

    public Verification(int id, String email, String password, int state, String code) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.state = state;
        this.code = code;
    }

    public Verification() {
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Verification that = (Verification) o;

        if (id != that.id) return false;
        if (state != that.state) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + state;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
