package edu.nju.yummy.Dao;

import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-23 15:42
 * @Modified By: hyh
 */
public interface OnlineBankingDao {
    double getBalance(String name);

    boolean modifyBalance(String name,double money);//可正可负

    String getPassword(String email);
}
