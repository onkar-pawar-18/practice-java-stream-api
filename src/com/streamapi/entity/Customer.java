package com.streamapi.entity;

import java.util.List;

public class Customer {
	
	private int customerId;
    private String customerName;
    private int tier;

    private List<Order> orders;

    public Customer() {}

    public Customer(int customerId, String customerName, int tier, List<Order> orders) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.tier = tier;
        this.orders = orders;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", tier=" + tier +
                ", orders=" + orders +
                '}';
    }
}
