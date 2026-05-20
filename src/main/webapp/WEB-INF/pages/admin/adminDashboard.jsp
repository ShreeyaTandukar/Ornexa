<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminDashboard.css">

</head>

<body>

<div class="admin-layout">

    <jsp:include page="AdminSideBar.jsp" />

    <div class="main">

        <!-- STATS -->
        <section class="stats">

            <div class="stat-card">
                <h4>Total Sales</h4>
                <h2>Rs. ${totalSales}</h2>
                <p class="green">Revenue overview</p>
            </div>

            <div class="stat-card">
                <h4>Total Orders</h4>
                <h2>${totalOrders}</h2>
                <p class="green">All time orders</p>
            </div>

            <div class="stat-card">
                <h4>New Users</h4>
                <h2>${newUsers}</h2>
                <p>Latest registrations</p>
            </div>

            <div class="alert-card">
                <p class="alert-title">Action Required</p>
                <h4>Stock Alerts</h4>
                <h2>${lowStockCount} Items</h2>

                <a href="${pageContext.request.contextPath}/StockManagement?type=low">
                    Review Inventory
                </a>
            </div>

        </section>

        <!-- MIDDLE -->
        <section class="middle">

            <div class="box revenue-box">

                <div class="box-header">
                    <div>
                        <h3>Revenue Growth</h3>
                        <h5>Performance overview</h5>
                    </div>
                </div>

                <img src="${pageContext.request.contextPath}/picture/revenue.jpeg"
                     class="chart-img">

            </div>

            <!-- RECENT ACTIVITY -->
            <div class="activity-box">

                <h3>Recent Activity</h3>

                <c:forEach var="order" items="${orders}">
                    <div class="activity">

                        <div>
                            <p>Order #${order.orderId}</p>
                            <small>
                                Rs.${order.totalAmount} • ${order.destination}
                            </small>
                        </div>

                        <span class="status ${order.orderStatus}">
                            ${order.orderStatus}
                        </span>

                    </div>
                </c:forEach>

                <a href="${pageContext.request.contextPath}/OrderManagement" class="view-all">
                    View All Orders
                </a>

            </div>

        </section>

        <!-- TABLE -->
        <section class="table-box">

            <div class="table-header">
                <h3>Global Ledger</h3>

                <div class="table-actions">
                    <button class="export-btn">Export PDF</button>
                </div>
            </div>

            <table>

                <thead>
                    <tr>
                        <th>ORDER ID</th>
                        <th>CUSTOMER</th>
                        <th>DESTINATION</th>
                        <th>AMOUNT</th>
                        <th>STATUS</th>
                    </tr>
                </thead>

                <tbody>

                    <c:forEach var="order" items="${orders}">
                        <tr>

                            <td>${order.orderId}</td>

                            <td>
                                <div class="user">
                                    <span class="avatar">
                                        ${fn:substring(order.userName, 0, 1)}
                                    </span>
                                    ${order.userName}
                                </div>
                            </td>

                            <td>${order.destination}</td>

                            <td>Rs. ${order.totalAmount}</td>

                            <td>
                                <span class="status ${order.orderStatus}">
                                    ${order.orderStatus}
                                </span>
                            </td>

                        </tr>
                    </c:forEach>

                </tbody>

            </table>

        </section>

    </div>

</div>

<jsp:include page="footer.jsp" />

</body>
</html>