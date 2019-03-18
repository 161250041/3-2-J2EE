package edu.nju.yummy.VO;

import java.sql.Timestamp;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-03-13 20:04
 * @Modified By: hyh
 */
public class ResTimeVO {
    private Timestamp beginTime;
    private Timestamp endTime;
    private int id;

    public ResTimeVO(Timestamp beginTime, Timestamp endTime, int id) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
