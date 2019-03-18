package edu.nju.yummy.Service;

import edu.nju.yummy.Model.Restaurant;

import java.util.ArrayList;

public interface ManagerService {
    //审批各餐厅修改信息的申请
    ArrayList<Restaurant> getUnapprovedApplication();

    boolean approveApplication(int id);
    boolean rejectApplication(int id);

    ArrayList<Double> getProfit();

    int getUserNum();
    int getResNum();
    //结算 每一单？ 按照餐厅？
//    ArrayList<Order> getOrderDetailList();


    //查看统计信息（非详情）
    //餐厅统计
    //会员统计
    //Yummy！财务情况
    //采用图表显示（可选）

}
