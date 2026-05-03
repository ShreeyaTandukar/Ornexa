package com.Ornexa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Ornexa.model.AdminProduct;
import com.Ornexa.utils.DBconfig;

public class AdminProductDao {

    public List<AdminProduct> getAllProducts() throws Exception {

        String sql = "SELECT Product_Id, Product_Name, Product_Price, Stock_Quantity, " +
                     "Material, gender, style, product_description, img_url, Category_Id " +
                     "FROM product ORDER BY Product_Id DESC";

        List<AdminProduct> list = new ArrayList<>();

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AdminProduct p = new AdminProduct();

                p.setId(rs.getInt("Product_Id"));
                p.setName(rs.getString("Product_Name"));
                p.setPrice(rs.getDouble("Product_Price"));
                p.setStockQuantity(rs.getInt("Stock_Quantity"));
                p.setMaterial(rs.getString("Material"));
                p.setGender(rs.getString("gender"));
                p.setStyle(rs.getString("style"));
                p.setDescription(rs.getString("product_description"));
                p.setImgUrl(rs.getString("img_url"));
                p.setCategoryId(rs.getInt("Category_Id"));

                list.add(p);
            }
        }

        return list;
    }

    public boolean insertProduct(AdminProduct p) throws Exception {

        String sql = "INSERT INTO product " +
                "(Product_Name, Product_Price, Stock_Quantity, Material, product_description, img_url, Category_Id, "
                + "gender, style) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getStockQuantity());
            ps.setString(4, p.getMaterial());
            ps.setString(5, p.getDescription());
            ps.setString(6, p.getImgUrl());
            ps.setInt(7, p.getCategoryId());
            ps.setString(8, p.getGender());
            ps.setString(9, p.getStyle());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateProduct(AdminProduct p) throws Exception {

        String sql = "UPDATE product SET " +
                "Product_Name=?, Product_Price=?, Stock_Quantity=?, " +
                "Material=?, product_description=?, img_url=?, " +
                "Category_Id=?, gender=?, style=? " +
                "WHERE Product_Id=?";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getStockQuantity());
            ps.setString(4, p.getMaterial());
            ps.setString(5, p.getDescription());
            ps.setString(6, p.getImgUrl());
            ps.setInt(7, p.getCategoryId());
            ps.setString(8, p.getGender());
            ps.setString(9, p.getStyle());
            ps.setInt(10, p.getId());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteProduct(int id) throws Exception {

        String sql = "DELETE FROM product WHERE Product_Id=?";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public AdminProduct getProductById(int id) throws Exception {

        String sql = "SELECT * FROM product WHERE Product_Id=?";
        AdminProduct p = null;

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new AdminProduct();

                p.setId(rs.getInt("Product_Id"));
                p.setName(rs.getString("Product_Name"));
                p.setPrice(rs.getDouble("Product_Price"));
                p.setStockQuantity(rs.getInt("Stock_Quantity"));
                p.setMaterial(rs.getString("Material"));
                p.setGender(rs.getString("gender"));
                p.setStyle(rs.getString("style"));
                p.setDescription(rs.getString("product_description"));
                p.setImgUrl(rs.getString("img_url"));
                p.setCategoryId(rs.getInt("Category_Id"));
            }

            rs.close();
        }

        return p;
    }

    public List<AdminProduct> getFilteredProducts(String material, String style, String gender) throws Exception {

        String sql = "SELECT Product_Id, Product_Name, Product_Price, Stock_Quantity, " +
                     "Material, gender, style, product_description, img_url, Category_Id " +
                     "FROM product WHERE 1=1";

        List<AdminProduct> list = new ArrayList<>();

        if (material != null && !material.isEmpty()) {
            sql += " AND Material=?";
        }
        if (gender != null && !gender.isEmpty()) {
            sql += " AND gender=?";
        }
        if (style != null && !style.isEmpty()) {
            sql += " AND style=?";
        }

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

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

            while (rs.next()) {
                AdminProduct p = new AdminProduct();

                p.setId(rs.getInt("Product_Id"));
                p.setName(rs.getString("Product_Name"));
                p.setPrice(rs.getDouble("Product_Price"));
                p.setStockQuantity(rs.getInt("Stock_Quantity"));
                p.setMaterial(rs.getString("Material"));
                p.setGender(rs.getString("gender"));
                p.setStyle(rs.getString("style"));
                p.setDescription(rs.getString("product_description"));
                p.setImgUrl(rs.getString("img_url"));
                p.setCategoryId(rs.getInt("Category_Id"));

                list.add(p);
            }

            rs.close();
        }

        return list;
    }
}