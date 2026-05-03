<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Invoice</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/Invoice.css">
</head>

<body>

<div class="invoice-container">

    <div class="invoice-header">

        <div>
            <h1>Invoice</h1>
            <p>Order ID: <b>#ORN-${order.orderId}</b></p>
            <p>Date: ${order.orderDate}</p>
        </div>

        <div class="company-info">
            <img src="<%= request.getContextPath() %>/pictures/OrnexaAdminLogo.png" class="logo">
            <p class="company-text">Inventory System</p>
        </div>

    </div>

    <div class="section">
        <h3>Bill To:</h3>
        <p><b>${order.userName}</b></p>
        <p>${order.email}</p>
    </div>

    <table class="invoice-table">
        <thead>
            <tr>
                <th>Item</th>
                <th>Qty</th>
                <th>Price</th>
                <th>Total</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td>${item.productName}</td>
                    <td>${item.quantity}</td>
                    <td>Rs.${item.price}</td>
                    <td>Rs.${item.price * item.quantity}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="summary">

        <div>
            <p>Subtotal:</p>
            <p>Tax (0%):</p>
            <h3>Total:</h3>
        </div>

        <div class="amount">
            <p>Rs.${order.totalAmount}</p>
            <p>Rs.0</p>
            <h3>Rs.${order.totalAmount}</h3>
        </div>

    </div>

    <div class="status">
        <span class="delivered">${order.orderStatus}</span>
    </div>

    <div class="actions">
        <button onclick="window.print()">Print Invoice</button>
    </div>

</div>

</body>
</html>