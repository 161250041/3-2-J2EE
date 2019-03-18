package nju.edu.order.Model;

import javax.persistence.*;

@Entity
@Table(name = "Commodities", schema = "J2EEOrderPj")
public class CommoditiesEntity {
    private int commodityID;

    private String commodityName;

    private double commodityPrice;

    private String url;

    @Id
    @Column(name = "commodityID")
    public int getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    @Basic
    @Column(name = "commodityName")
    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    @Basic
    @Column(name = "commodityPrice")
    public double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommoditiesEntity that = (CommoditiesEntity) o;

        if (commodityID != that.commodityID) return false;
        if (Double.compare(that.commodityPrice, commodityPrice) != 0) return false;
        if (commodityName != null ? !commodityName.equals(that.commodityName) : that.commodityName != null)
            return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = commodityID;
        result = 31 * result + (commodityName != null ? commodityName.hashCode() : 0);
        temp = Double.doubleToLongBits(commodityPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
