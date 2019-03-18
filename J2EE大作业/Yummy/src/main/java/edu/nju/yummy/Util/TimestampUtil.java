package edu.nju.yummy.Util;

import java.sql.Timestamp;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-03-11 16:48
 * @Modified By: hyh
 */
public class TimestampUtil {
    public static Timestamp getActualTimestamp(Timestamp timestamp) {
        Timestamp timestamp1 = new Timestamp(timestamp.getTime()-12*60*60*1000);
        return timestamp1;
    }
}
