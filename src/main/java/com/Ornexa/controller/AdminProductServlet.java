package com.Ornexa.controller;

import java.io.IOException;
import java.util.List;

import com.Ornexa.model.AdminProduct;
import com.Ornexa.service.AdminProductService;
import com.Ornexa.utils.ValidationUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Product")
public class AdminProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    AdminProductService service = new AdminProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            String material = req.getParameter("material");
            String gender = req.getParameter("gender");
            String style = req.getParameter("style");

            List<AdminProduct> list;

            if ((material != null && !material.isEmpty()) ||
                (gender != null && !gender.isEmpty()) ||
                (style != null && !style.isEmpty())) {

                list = service.getFilteredProducts(material, style, gender);
            } else {
                list = service.getAllProducts();
            }

            req.setAttribute("products", list);
            req.getRequestDispatcher("/WEB-INF/pages/adminProduct.jsp")
               .forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if ("add".equals(action) || "update".equals(action)) {

            String validating = ValidationUtils.validateProduct(
                    req.getParameter("name"),
                    req.getParameter("price"),
                    req.getParameter("stockQuantity"),
                    req.getParameter("categoryId")
            );

            if (validating != null) {

                req.setAttribute("errorMsg", validating);

                if ("add".equals(action)) {
                    req.setAttribute("openAddModal", true);
                } else {
                    req.setAttribute("openEditModal", true);
                }

                doGet(req, res);
                return;
            }
        }
        try {

        	if ("add".equals(action)) {

        	    AdminProduct p = new AdminProduct();

        	    p.setName(req.getParameter("name"));

        	    p.setPrice(Double.parseDouble(req.getParameter("price")));
        	    p.setStockQuantity(Integer.parseInt(req.getParameter("stockQuantity")));

        	    p.setMaterial(req.getParameter("material"));
        	    p.setGender(req.getParameter("gender"));
        	    p.setStyle(req.getParameter("style"));
        	    p.setDescription(req.getParameter("description"));

        	    String img = req.getParameter("imgUrl");
        	    p.setImgUrl((img == null || img.isEmpty()) ? "default.png" : img);

        	    String cat = req.getParameter("categoryId");
        	    if (cat == null || cat.isEmpty()) {
        	        p.setCategoryId(1); 
        	    } else {
        	        p.setCategoryId(Integer.parseInt(cat));
        	    }

        	    service.addProduct(p);
        	}

            else if ("update".equals(action)) {

                AdminProduct p = new AdminProduct();

                p.setId(Integer.parseInt(req.getParameter("id")));
                p.setName(req.getParameter("name"));
                p.setPrice(Double.parseDouble(req.getParameter("price")));
                p.setStockQuantity(Integer.parseInt(req.getParameter("stockQuantity")));
                p.setMaterial(req.getParameter("material"));
                p.setGender(req.getParameter("gender"));
                p.setStyle(req.getParameter("style"));
                p.setDescription(req.getParameter("description"));
                p.setImgUrl(req.getParameter("imgUrl"));
                p.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));

                service.updateProduct(p);
            }

            else if ("delete".equals(action)) {

                int id = Integer.parseInt(req.getParameter("id"));
                service.deleteProduct(id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        res.sendRedirect("Product");
    }
}