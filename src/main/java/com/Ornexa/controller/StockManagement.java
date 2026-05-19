package com.Ornexa.controller;

import java.io.IOException;
import java.text.DecimalFormat;

import com.Ornexa.service.stockService;
import com.Ornexa.utils.ValidationUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StockManagement")
public class StockManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

    stockService service = new stockService(); //Service Object

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

    	DecimalFormat df = new DecimalFormat("#.##");

        String type = request.getParameter("type");

        try {
        	// filter 

            if ("low".equals(type)) {
                request.setAttribute("stockList", service.getLow());
            }
            else if ("out".equals(type)) {
                request.setAttribute("stockList", service.getOut());
            }
            else {
                request.setAttribute("stockList", service.getAllStock());
            }

            request.setAttribute("totalItems", service.totalItems());
            request.setAttribute("stockValue", df.format(service.stockValue()));
            request.setAttribute("lowStock", service.lowStock());
            request.setAttribute("outStock", service.outStock());


        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher rd =
        request.getRequestDispatcher("/WEB-INF/pages/StockManagement.jsp");

        rd.forward(request, response);
    }

    
  
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String productId = req.getParameter("id");
        String qty = req.getParameter("qty");

        // error message when add stock button is clicked without entering quantity
        String validating = ValidationUtils.validateStock(qty);

        if (validating != null) {

            req.setAttribute("validating", validating);

            // reload same page with data + message
            doGet(req, res);

            return;
        }

        int quantity = Integer.parseInt(qty);

        service.addStock(Integer.parseInt(productId), quantity);

        res.sendRedirect("StockManagement");
    }
}