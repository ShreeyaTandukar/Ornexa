package com.Ornexa.service;

import java.util.List;
import com.Ornexa.dao.stockDao;
import com.Ornexa.model.stock;

public class stockService {

    stockDao dao = new stockDao();

    public List<stock> getAllStock() {
        return dao.getAllStock();
    }
    
    public int outStock() {
        return dao.outStock();
    }
    
    public int lowStock() {
        return dao.lowStock();
    }

    public List<stock> getLow() {
        return dao.getLowStockList();
    }

    public List<stock> getOut() {
        return dao.getOutStockList();
    }

    public boolean addStock(int id, int qty) {
        return dao.addStock(id, qty);
    }

    public boolean decreaseStock(int id, int qty) {
        return dao.decreaseStock(id, qty);
    }

    public int totalItems() {
        return dao.totalItems();
    }

    public double stockValue() {
        return dao.stockValue();
    }
}