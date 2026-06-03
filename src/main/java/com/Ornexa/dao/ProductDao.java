package com.Ornexa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Ornexa.model.Product;
import com.Ornexa.utils.DBconfig;

public class ProductDao {

    // GET ALL PRODUCTS
    public List<Product> getAllProducts() throws Exception {

        Connection conn = DBconfig.getConnection();

        String sql = "SELECT product_id, product_name, product_price, stock_quantity, " +
                "material, gender, style, product_description, img_url, category_id " +
                "FROM product ORDER BY product_id DESC";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Product> productList = new ArrayList<>();

        while (rs.next()) {

            Product p = new Product();

            p.setId(rs.getInt("product_id"));
            p.setName(rs.getString("product_name"));
            p.setPrice(rs.getDouble("product_price"));
            p.setStockQuantity(rs.getInt("stock_quantity"));
            p.setMaterial(rs.getString("material"));
            p.setGender(rs.getString("gender"));
            p.setStyle(rs.getString("style"));
            p.setDescription(rs.getString("product_description"));
            p.setImgUrl(rs.getString("img_url"));
            p.setCategoryId(rs.getInt("category_id"));

            productList.add(p);
            p.setStyle(rs.getString("style"));
        }

        rs.close();
        ps.close();
        conn.close();

        return productList;
    }

    
    // HOME PAGE PRODUCTS
    public List<Product> getHomeProducts() throws Exception {

        Connection conn = DBconfig.getConnection();

        String sql = "SELECT product_id, product_name, product_price, "
                + "product_description, img_url "
                + "FROM product ORDER BY product_id DESC LIMIT 5";

        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        List<Product> homeProducts = new ArrayList<>();

        while (rs.next()) {

            Product p = new Product();

            p.setId(rs.getInt("product_id"));
            p.setName(rs.getString("product_name"));
            p.setPrice(rs.getDouble("product_price"));
            p.setDescription(rs.getString("product_description"));
            p.setImgUrl(rs.getString("img_url"));

            homeProducts.add(p);
        }

        rs.close();
        ps.close();
        conn.close();

        return homeProducts;
    }

    
    // FILTER PRODUCTS
    public List<Product> getFilteredProducts(String material, String style, String gender) throws Exception {

        Connection conn = DBconfig.getConnection();

        String sql = "SELECT product_id, product_name, product_price, stock_quantity, "
                + "material, gender, style, product_description, img_url, category_id "
                + "FROM product WHERE 1=1 ";

        if (material != null && !material.isEmpty()) {
            sql += " AND material = ?";
        }

        if (gender != null && !gender.isEmpty()) {
            sql += " AND gender = ?";
        }

        if (style != null && !style.isEmpty()) {
            sql += " AND style = ?";
        }

        sql += " ORDER BY product_id DESC";

        PreparedStatement ps = conn.prepareStatement(sql);

        int i = 1;

        if (material != null && !material.isEmpty()) {
            ps.setString(i++, material);
        }

        if (gender != null && !gender.isEmpty()) {
            ps.setString(i++, gender);
        }

        if (style != null && !style.isEmpty()) {
            ps.setString(i++, style);
        }

        ResultSet rs = ps.executeQuery();

        List<Product> products = new ArrayList<>();

        while (rs.next()) {

            Product prod = new Product();

            prod.setId(rs.getInt("product_id"));
            prod.setName(rs.getString("product_name"));
            prod.setPrice(rs.getDouble("product_price"));
            prod.setStockQuantity(rs.getInt("stock_quantity"));
            prod.setMaterial(rs.getString("material"));
            prod.setGender(rs.getString("gender"));
            prod.setStyle(rs.getString("style"));
            prod.setDescription(rs.getString("product_description"));
            prod.setImgUrl(rs.getString("img_url"));
            prod.setCategoryId(rs.getInt("category_id"));

            products.add(prod);
        }

        rs.close();
        ps.close();
        conn.close();

        return products;
    }

    
    // GET PRODUCT BY ID
    public Product getProductById(int id) throws Exception {

        Connection conn = DBconfig.getConnection();

        String sql = "SELECT * FROM product WHERE product_id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        Product p = null;

        if (rs.next()) {

            p = new Product();

            p.setId(rs.getInt("product_id"));
            p.setName(rs.getString("product_name"));
            p.setPrice(rs.getDouble("product_price"));
            p.setStockQuantity(rs.getInt("stock_quantity"));
            p.setMaterial(rs.getString("material"));
            p.setGender(rs.getString("gender"));
            p.setStyle(rs.getString("style"));
            p.setDescription(rs.getString("product_description"));
            p.setImgUrl(rs.getString("img_url"));
            p.setCategoryId(rs.getInt("category_id"));
        }

        rs.close();
        ps.close();
        conn.close();

        return p;
    }
    
 // HOME PAGE COLLECTION PRODUCTS
    public List<Product> getCollectionProducts() throws Exception {

        Connection conn = DBconfig.getConnection();

        String sql = "SELECT product_id, product_name, product_price, " +
                "product_description, img_url " +
                "FROM (SELECT * FROM product " +
                "ORDER BY product_id DESC LIMIT 5) AS latest_products " +
                "ORDER BY product_id ASC";
        		
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        List<Product> collectionProducts = new ArrayList<>();

        while (rs.next()) {

            Product p = new Product();

            p.setId(rs.getInt("product_id"));
            p.setName(rs.getString("product_name"));
            p.setPrice(rs.getDouble("product_price"));
            p.setDescription(rs.getString("product_description"));
            p.setImgUrl(rs.getString("img_url"));

            collectionProducts.add(p);
        }

        rs.close();
        ps.close();
        conn.close();

        return collectionProducts;
    }
    
    // INSERT PRODUCT
    public boolean insertProduct(Product p) throws Exception {

	    Connection conn = DBconfig.getConnection();

	    String sql = "INSERT INTO product (pname, price, image) VALUES (?, ?, ?)";

	    PreparedStatement ps = conn.prepareStatement(sql);

	    ps.setString(1, p.getName());
	    ps.setDouble(2, p.getPrice());
	    ps.setString(3, p.getImgUrl());

	    int rows = ps.executeUpdate();

	    return rows > 0;
	}
    
}