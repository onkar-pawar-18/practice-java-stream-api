package com.streamapi.entity;

import java.util.List;

public class Product {

    private int productId;
    private String name;
    private String category;
    private double price;
    private List<Order> orders;

    public Product() {}

    public Product(int productId, String name, String category, double price, List<Order> orders) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.orders = orders;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
