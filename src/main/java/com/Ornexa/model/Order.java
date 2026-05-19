package com.Ornexa.model;

import java.util.List;

public class Order {

    private int orderId;
    private String orderDate;
    private String destination;
    private double totalAmount;
    private String orderStatus;
    private int userId;

    private String userName;
    private String email; 

 
    private List<OrderItem> items; //order item model

    public Order() {} //default constructor
    
    //constructor
	public Order (int id, String userName, String destination, double amount,String status, int userId,String orderDate,String email) {
		this.orderId=id;
		this.userName=userName;
		this.destination=destination;
		this.totalAmount=amount;
		this.orderStatus=status;
		this.orderDate=orderDate;
		this.userId = userId;
		this.email=email;
	}

	//getter and setter
	
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
