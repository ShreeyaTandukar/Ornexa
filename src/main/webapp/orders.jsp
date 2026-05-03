<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Orders - Jewelry Store</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/adminDashboard.css" />
</head>
<body>
	<div class="sidebar">
    <h2>ORNEXA Admin</h2>
    <ul>
        <!-- Home (Admin Dashboard main landing page) -->
        <li><a href="<%=request.getContextPath()%>/adminDashboard.jsp">Home</a></li>

        <!-- Dashboard (stats overview page) -->
        <li><a href="<%=request.getContextPath()%>/dashboard.jsp">Dashboard</a></li>

        <!-- Add Product -->
        <li><a href="<%=request.getContextPath()%>/addProduct.jsp">Add Product</a></li>

        <!-- Orders -->
        <li><a href="<%=request.getContextPath()%>/orders.jsp">Orders</a></li>

        <!-- Customers -->
        <li><a href="<%=request.getContextPath()%>/customers.jsp">Customers</a></li>

        <!-- Logout -->
        <li><a href="<%=request.getContextPath()%>/logout.jsp">Logout</a></li>
    </ul>
</div>
	

    <div class="main-content">
        <h1>Orders Management</h1>
        <div class="order-tabs">
            <button class="active">Pending</button>
            <button>Shipped</button>
            <button>Declined</button>
        </div>
        <table>
            <thead>
                <tr>
                    <th>ID</th><th>Product</th><th>Customer</th><th>Date</th><th>Total</th><th>Status</th><th>Qty</th>
                </tr>
            </thead>
            <tbody>
                <!-- Dynamic rows from DB -->
            </tbody>
        </table>
    </div>
</body>
</html>
