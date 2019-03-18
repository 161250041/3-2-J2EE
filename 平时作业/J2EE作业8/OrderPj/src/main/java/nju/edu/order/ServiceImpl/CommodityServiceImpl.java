package nju.edu.order.ServiceImpl;

import nju.edu.order.Dao.CommodityDao;
import nju.edu.order.Model.CommoditiesEntity;
import nju.edu.order.Service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("CommodityService")
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityDao commodityDao;

    private static CommodityServiceImpl commodityService=new CommodityServiceImpl();

    private CommodityServiceImpl() {
    }

    public static CommodityServiceImpl getInstance(){
        return commodityService;
    }

    public ArrayList<CommoditiesEntity> getCommodityList() {
        ArrayList<CommoditiesEntity> commodities= commodityDao.getCommodityList();
        return commodities;
    }

    public CommoditiesEntity getCommodityByCommodityID(int commodityID) {
        return commodityDao.getCommodityByCommodityID(commodityID);
    }

    public double getPriceByCommodityID(int commodityID) {
        return commodityDao.getPriceByCommodityID(commodityID);
    }
}
