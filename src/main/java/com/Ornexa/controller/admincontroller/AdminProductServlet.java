package com.Ornexa.controller.admincontroller;

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

@WebServlet(asyncSupported = true, urlPatterns = { "/Product" })
public class AdminProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    AdminProductService service = new AdminProductService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {

            String filtermaterial = req.getParameter("filtermaterial");
            String filtergender = req.getParameter("filtergender");
            String filterstyle = req.getParameter("filterstyle");

            List<AdminProduct> list;

            if ((filtermaterial != null && !filtermaterial.isEmpty()) ||
                (filtergender != null && !filtergender.isEmpty()) ||
                (filterstyle != null && !filterstyle.isEmpty())) {

                list = service.getFilteredProducts(filtermaterial, filterstyle, filtergender);
            } else {
                list = service.getAllProducts();
            }

            req.setAttribute("products", list);
            req.getRequestDispatcher("/WEB-INF/pages/admin/AdminProduct.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        try {

            // VALIDATION (ADD + UPDATE)
            if ("add".equals(action) || "update".equals(action)) {

                String validating = ValidationUtils.validateProduct(
                        req.getParameter("name"),
                        req.getParameter("price"),
                        req.getParameter("stockQuantity"),
                        req.getParameter("categoryId"),
                        req.getParameter("material"),
                        req.getParameter("gender"),
                        req.getParameter("style")
                );

                if (validating != null) {

                    req.setAttribute("errorMsg", validating);

                   if ("add".equals(action)) {
                        req.setAttribute("openAddModal", true);
                    } else {
                        req.setAttribute("openEditModal", true);
                    }

                    req.setAttribute("id", req.getParameter("id"));
                    req.setAttribute("name", req.getParameter("name"));
                    req.setAttribute("price", req.getParameter("price"));
                    req.setAttribute("stockQuantity", req.getParameter("stockQuantity"));
                    req.setAttribute("material", req.getParameter("material"));
                    req.setAttribute("gender", req.getParameter("gender"));
                    req.setAttribute("style", req.getParameter("style"));
                    req.setAttribute("description", req.getParameter("description"));
                    req.setAttribute("categoryId", req.getParameter("categoryId"));

                    List<AdminProduct> list = service.getAllProducts();
                    req.setAttribute("products", list);
                    
                    
                    req.getRequestDispatcher("/WEB-INF/pages/admin/AdminProduct.jsp").forward(req, res);
                    return;
                }
            }

         
            if ("add".equals(action)) {

                AdminProduct p = new AdminProduct();

                p.setName(req.getParameter("name"));
                p.setPrice(Double.parseDouble(req.getParameter("price")));
                p.setStockQuantity(Integer.parseInt(req.getParameter("stockQuantity")));
                p.setMaterial(req.getParameter("material"));
                p.setGender(req.getParameter("gender"));
                p.setStyle(req.getParameter("style"));
                p.setDescription(req.getParameter("description"));

                String img = req.getParameter("image");
                p.setImgUrl((img == null || img.trim().isEmpty()) ? "default.png" : img);

                p.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));

                service.addProduct(p);

                res.sendRedirect("Product");
                return;
            }

            // UPDATE PRODUCT
            if ("update".equals(action)) {

                AdminProduct p = new AdminProduct();

                p.setId(Integer.parseInt(req.getParameter("id")));
                p.setName(req.getParameter("name"));
                p.setPrice(Double.parseDouble(req.getParameter("price")));
                p.setStockQuantity(Integer.parseInt(req.getParameter("stockQuantity")));
                p.setMaterial(req.getParameter("material"));
                p.setGender(req.getParameter("gender"));
                p.setStyle(req.getParameter("style"));
                p.setDescription(req.getParameter("description"));
                p.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));

                service.updateProduct(p);

                res.sendRedirect("Product");
                return;
            }

            
            if ("delete".equals(action)) {

                int id = Integer.parseInt(req.getParameter("id"));
                service.deleteProduct(id);

                res.sendRedirect("Product");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}