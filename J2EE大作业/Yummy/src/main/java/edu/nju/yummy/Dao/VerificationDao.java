package edu.nju.yummy.Dao;

import edu.nju.yummy.Model.Verification;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-23 16:19
 * @Modified By: hyh
 */
public interface VerificationDao {

    boolean emailExist(String email); //state一定是0

    boolean addVerification(String email,String password,String code,int state);

    boolean modifyVerification(String email, String password, String code,int state);//曾经注册过，但未验证

    Verification getVerification(String email);

    boolean setState(String email, int state);
}
