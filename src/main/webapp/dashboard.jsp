<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Jewelry Store</title>
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
        <header>
            <h1>Dashboard Overview</h1>
            <p>Quick insights into your jewelry store</p>
        </header>

        <div class="stats">
            <div class="card"><h3>Total Products</h3><p>120</p></div>
            <div class="card"><h3>Pending Orders</h3><p>15</p></div>
            <div class="card"><h3>Shipped Orders</h3><p>40</p></div>
            <div class="card"><h3>Declined Orders</h3><p>5</p></div>
        </div>
    </div>
</body>
</html>
