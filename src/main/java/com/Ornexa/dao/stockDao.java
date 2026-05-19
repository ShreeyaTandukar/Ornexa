package com.Ornexa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Ornexa.model.stock;
import com.Ornexa.utils.DBconfig;

public class stockDao {

    public List<stock> getAllStock() {

        String sql =
        "SELECT p.*, c.Category_Name FROM product p " +
        "JOIN category c ON p.Category_Id=c.Category_Id";

        return getStockByQuery(sql);
    }

    public int lowStock() {

        String sql = "SELECT COUNT(*) FROM product WHERE Stock_Quantity BETWEEN 1 AND 50";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    public int outStock() {

        String sql = "SELECT COUNT(*) FROM product WHERE Stock_Quantity = 0";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    
    public List<stock> getLowStockList() {

        String sql =
        "SELECT p.*, c.Category_Name FROM product p " +
        "JOIN category c ON p.Category_Id=c.Category_Id " +
        "WHERE Stock_Quantity BETWEEN 1 AND 50";

        return getStockByQuery(sql);
    }

    public List<stock> getOutStockList() {

        String sql =
        "SELECT p.*, c.Category_Name FROM product p " +
        "JOIN category c ON p.Category_Id=c.Category_Id " +
        "WHERE Stock_Quantity = 0";

        return getStockByQuery(sql);
    }

    public boolean addStock(int id, int qty) {

        String sql =
        "UPDATE product SET Stock_Quantity = Stock_Quantity + ? " +
        "WHERE Product_Id=?";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, qty);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

 
    public boolean decreaseStock(int id, int qty) {

        String sql =
        "UPDATE product SET Stock_Quantity = Stock_Quantity - ? " +
        "WHERE Product_Id=? AND Stock_Quantity >= ?";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, qty);
            ps.setInt(2, id);
            ps.setInt(3, qty);

            int rows = ps.executeUpdate();

            System.out.println("ROWS UPDATED = " + rows);

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    public int totalItems() {

        String sql = "SELECT COUNT(*) FROM product";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

 
    public double stockValue() {

        String sql =
        "SELECT SUM(Product_Price * Stock_Quantity) FROM product";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getDouble(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    
    private List<stock> getStockByQuery(String sql) {

        List<stock> list = new ArrayList<>();

        try (Connection con = DBconfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                stock s = new stock();

                int qty = rs.getInt("Stock_Quantity");

                s.setProductId(rs.getInt("Product_Id"));
                s.setProductName(rs.getString("Product_Name"));
                s.setProductPrice(rs.getDouble("Product_Price"));
                s.setStockQuantity(qty);
                s.setImgUrl(rs.getString("img_url"));
                s.setCategoryName(rs.getString("Category_Name"));

                if (qty == 0) s.setStockStatus("OUT OF STOCK");
                else if (qty <= 50) s.setStockStatus("LOW STOCK");
                else s.setStockStatus("AVAILABLE");

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}