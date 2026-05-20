package com.Ornexa.service;

import java.util.List;
import com.Ornexa.dao.stockDao;
import com.Ornexa.model.stock;

public class stockService {

    stockDao dao = new stockDao(); //DAO Object

    // getting stock details
    public List<stock> getAllStock() {
        return dao.getAllStock();
    }
    
    //finding product who is out of stock

    public int outStock() {
        return dao.outStock();
    }
    
    //finding product whose stock is low
    public int lowStock() {
        return dao.lowStock();
    }

    //getting product list whose stock is low
    public List<stock> getLow() {
        return dao.getLowStockList();
    }

    //getting product list which is out of stock
    public List<stock> getOut() {
        return dao.getOutStockList();
    }

    //adding quantity to stock
    public boolean addStock(int id, int qty) {
        return dao.addStock(id, qty);
    }

    //Decreasing stock when item gets delivered
    public boolean decreaseStock(int id, int qty) {
        return dao.decreaseStock(id, qty);
    }

    //total number of products
    public int totalItems() {
        return dao.totalItems();
    }

    //Calculating stock value
    public double stockValue() {
        return dao.stockValue();
    }
}