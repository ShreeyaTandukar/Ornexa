package com.Ornexa.service;

import java.util.List;

import com.Ornexa.dao.AdminProductDao;
import com.Ornexa.model.AdminProduct;

public class AdminProductService {

    AdminProductDao dao = new AdminProductDao();

    public List<AdminProduct> getAllProducts() {
        try {
            return dao.getAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addProduct(AdminProduct p) {
        try {
            return dao.insertProduct(p);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(AdminProduct p) {
        try {
            return dao.updateProduct(p);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(int id) {
        try {
            return dao.deleteProduct(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public AdminProduct getProductById(int id) {
        try {
            return dao.getProductById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<AdminProduct> getFilteredProducts(String material, String style, String gender) {
        try {
            return dao.getFilteredProducts(material, style, gender);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}