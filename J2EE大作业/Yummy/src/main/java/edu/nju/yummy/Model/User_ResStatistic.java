package edu.nju.yummy.Model;

public class User_ResStatistic {

    private double value;
    private String name;

    public User_ResStatistic() {
    }

    public User_ResStatistic(double value,String restaurantName) {
        this.name = restaurantName;
        this.value = value;
    }

    public String getRestaurantName() {
        return name;
    }

    public void setRestaurantName(String restaurantName) {
        this.name = restaurantName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double price) {
        this.value = price;
    }
}