/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

import sample.food.FoodDTO;

/**
 *
 * @author huynh
 */
public class OrderDTO{
    private String foodId;
    private String userId;
    private int quantity;
    private double price;
    private double money;

    public OrderDTO(String foodId, String userId, int quantity, double price, double money) {
        this.foodId = foodId;
        this.userId = userId;
        this.quantity = quantity;
        this.price = price;
        this.money = money;
    }

    public OrderDTO() {
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    
}
