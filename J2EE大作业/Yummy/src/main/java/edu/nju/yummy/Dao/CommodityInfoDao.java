package edu.nju.yummy.Dao;

import edu.nju.yummy.Model.CommodityInfo;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @Author: hyh
 * @Description:
 * @Date: 2019-02-23 17:57
 * @Modified By: hyh
 */
public interface CommodityInfoDao {
    boolean addCommodity(String code, String commodityName, double commodityPrice, int commodityNum, Timestamp beginTime, Timestamp endTime, String type);

    double getPrice(int commodityId);

    ArrayList<CommodityInfo> getCommodityList(int id);

    ArrayList<CommodityInfo> getCommodityList(String code);

    ArrayList<CommodityInfo> getSetList(String code);

    ArrayList<String> getCommodityTypeList(int id);

}
