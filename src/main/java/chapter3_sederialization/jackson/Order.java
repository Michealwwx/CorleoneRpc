package chapter3_sederialization.jackson;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Order
 * @Description 必须实现serializable接口
 * @Author wuwenxiang
 * @Date 2019-04-11 21:32
 * @Version 1.0
 **/
public class Order implements Serializable {
    private String userName;
    private Date orderDate;
    private double money;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Order(String userName, Date orderDate, double money) {
        this.userName = userName;
        this.orderDate = orderDate;
        this.money = money;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userName='" + userName + '\'' +
                ", orderDate=" + orderDate +
                ", money=" + money +
                '}';
    }
}
