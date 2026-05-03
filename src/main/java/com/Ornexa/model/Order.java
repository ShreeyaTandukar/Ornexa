package com.Ornexa.model;

import java.util.List;

public class Order {
	private int id;
	private String orderDate;
	private String userName;
	private String destination;
	private double amount;
	private String status;
	private int userId;
	private String email; 
	private List<OrderItem> items;
	public Order() {}
	public Order (int id, String userName, String destination, double amount,String status, int userId,String orderDate,String email) {
		this.id=id;
		this.userName=userName;
		this.destination=destination;
		this.amount=amount;
		this.status=status;
		this.orderDate=orderDate;
		this.userId = userId;
		this.email=email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName=userName;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination( String destination) {
		this.destination=destination;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount=amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status=status;
	}
	public String getOrderDate() {
	        return orderDate;
	 }

	 public void setOrderDate(String orderDate) {
	        this.orderDate = orderDate;
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
	  public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }
}
