package edu.nju.yummy.VO;

import java.sql.Timestamp;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-03-13 20:04
 * @Modified By: hyh
 */
public class UserTimeVO {
    private Timestamp beginTime;
    private Timestamp endTime;
    private String email;

    public UserTimeVO(Timestamp beginTime, Timestamp endTime, String email) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.email = email;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
