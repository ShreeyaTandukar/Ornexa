package com.Ornexa.service;

import java.util.List;

import com.Ornexa.dao.OrderDao;
import com.Ornexa.model.Order;
import com.Ornexa.model.OrderItem;

public class OrderManagementService {

    OrderDao dao = new OrderDao();
    stockService stockService = new stockService();

    public List<Order> getAllOrders() {
        return dao.getAllOrdersA();
    }

    public List<Order> getFilteredOrders(String status, String fromDate, String toDate) {
        return dao.getFilteredOrders(status, fromDate, toDate);
    }

    public boolean updateOrderStatus(int orderId, String status) {

        boolean updated = dao.updateOrderStatus(orderId, status);

        if (updated && "DELIVERED".equalsIgnoreCase(status.trim())) {

            List<OrderItem> items = dao.getItemsByOrderId(orderId);

            for (OrderItem item : items) {

                stockService.decreaseStock(
                    item.getProductId(),
                    item.getQuantity()
                );
            }
        }

        return updated;
    }

    public double getTotalRevenue() {
        return dao.getTotalRevenue();
    }

    public double getGrowthPercent() {

        double thisMonth = dao.getMonthlyRevenue(0);
        double lastMonth = dao.getMonthlyRevenue(1);

        if (lastMonth == 0) return 0;

        return ((thisMonth - lastMonth) / lastMonth) * 100;
    }
    
    public Order getOrderWithItems(int orderId) {

        Order order = dao.getSingleOrder(orderId);

        if (order == null) return null;

        List<OrderItem> items = dao.getItemsByOrderId(orderId);

        order.setItems(items);

        return order;
    }
}