package com.Ornexa.service;

import java.util.List;

import com.Ornexa.dao.ProductDao;
import com.Ornexa.model.Product;

public class ProductService {
	ProductDao productDao = new ProductDao();

    //  GET ALL PRODUCTS
    public List<Product> getAllProducts() throws Exception {
        return productDao.getAllProducts();
    }
    public List<Product> getFilteredProducts(String Material, String style, String gender) throws Exception {
    	return productDao.getFilteredProducts(Material, style, gender);
    }
    public Product getProductById(int id) throws Exception {
        return productDao.getProductById(id);
    }
    private ProductDao dao = new ProductDao();

    public boolean addProduct(Product p) {
        try {
            return dao.insertProduct(p);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Product> searchProductsByName(String query) throws Exception {
    
        if (query == null || query.trim().isEmpty()) {
            return getAllProducts();
        }
        
        return productDao.searchProducts(query.trim());
    }

}
