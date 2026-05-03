package com.Ornexa.service;

import java.util.List;

import com.Ornexa.dao.OrderDao;
import com.Ornexa.model.Order;

public class OrderService {
	OrderDao order = new OrderDao();
	public List<Order> getOrders(){
		try {
			return 	order.getAllOrdersA();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int getTotalOrders() {
		try {
			return order.getAllOrdersA().size();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public double getTotalSales() {

	    try {
	        return order.getTotalSales();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return 0;
	}


}
