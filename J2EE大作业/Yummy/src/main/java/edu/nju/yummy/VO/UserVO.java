package edu.nju.yummy.VO;

import javax.persistence.Entity;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-03-08 00:53
 * @Modified By: hyh
 */
@Entity
public class UserVO {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
