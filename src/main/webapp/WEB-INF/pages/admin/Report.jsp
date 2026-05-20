<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ornexa Admin | Reports</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/report.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>

<body>
<div class="admin-layout">

<jsp:include page="AdminSideBar.jsp" />

    <main class="main-content">

        <header class="content-header">
            <div class="header-info">
                <h1>Admin Reports</h1>
                <p>Business Performance Overview</p>
            </div>
        </header>

        <!-- STATS -->
        <section class="stats-container">

            <div class="stat-card">
                <span class="stat-label">Total Revenue</span>
                <div class="stat-value">Rs. ${totalSales}</div>
                <span class="trend trend-up"><i class="fas fa-caret-up"></i> ${revenueGrowth}%</span>
            </div>

            <div class="stat-card">
                <span class="stat-label">Total Orders</span>
                <div class="stat-value">${totalOrders}</div>
                <span class="trend trend-up"><i class="fas fa-caret-up"></i> ${orderGrowth}%</span>
            </div>

            <div class="stat-card">
                <span class="stat-label">Active Users</span>
                <div class="stat-value">${activeUsers}</div>
                <span class="trend trend-up"><i class="fas fa-caret-up"></i> ${userGrowth}%</span>
            </div>

            <div class="stat-card border-danger">
                <span class="stat-label">Pending Orders</span>
                <div class="stat-value">${pendingOrders}</div>
                <span class="trend trend-down"><i class="fas fa-caret-down"></i> ${pendingDrop}%</span>
            </div>

        </section>

        <!-- VISUALS -->
        <section class="visuals-row">

            <div class="visual-box chart-wide">
                <div class="visual-header">
                    <div class="header-text">
                        <h3>Revenue Growth</h3>
                        <p class="header-desc">Performance metrics for the current fiscal quarter</p>
                    </div>

                    <div class="tab-controls">
                        <button class="tab-link active">WEEKLY</button>
                        <button class="tab-link">MONTHLY</button>
                        <button class="tab-link">YEARLY</button>
                    </div>
                </div>

                <div class="chart-area">
                    <img src="${pageContext.request.contextPath}/picture/revenue.jpeg"
                         class="chart-img"
                         alt="Revenue Graph">
                </div>
            </div>

            <div class="visual-box">

                <div class="visual-header">
                    <h3>Orders Analytics</h3>
                </div>

                <div class="chart-area">
                    <img src="${pageContext.request.contextPath}/picture/monthly.jpeg"
                         class="chart-img-graph"
                         alt="Orders Analytics">
                </div>

            </div>

            <div class="visual-box">

                <div class="visual-header">
                    <h3>Recent Activity</h3>
                    <a href="OrderManagement" class="link-gold">View All</a>
                </div>

                <div class="feed-container">

                    <c:forEach var="order" items="${orders}" begin="0" end="2">

                        <div class="feed-item">

                            <div class="feed-icon 
                                ${order.orderStatus == 'Paid' ? 'bg-success' : 
                                  order.orderStatus == 'Pending' ? 'bg-warning' : 'bg-info'}">

                                <i class="fas 
                                    ${order.orderStatus == 'Paid' ? 'fa-check' : 
                                      order.orderStatus == 'Pending' ? 'fa-clock' : 'fa-truck'}">
                                </i>

                            </div>

                            <div class="feed-info">
                                <p>
                                    Order placed <strong>#${order.orderId}</strong>
                                </p>

                                <span>
                                    Rs. ${order.totalAmount} • ${order.destination}
                                </span>
                            </div>

                        </div>

                    </c:forEach>

                </div>

            </div>

        </section>

        <!-- LEDGER TABLE -->
        <section class="ledger-card">

            <div class="ledger-header">
                <div class="header-text">
                    <h3>Global Ledger Summary</h3>
                    <p class="header-desc">Detailed transaction history and order tracking</p>
                </div>

                <button class="btn-pdf" onclick="window.print()">
                    <i class="fas fa-file-pdf"></i> Export PDF
                </button>
            </div>

            <div class="table-scroll">

                <table class="data-table">

                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Date</th>
                            <th>Customer</th>
                            <th>Product(s) Purchased</th>
                            <th>Status</th>
                            <th>Total Amount</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <td>${order.orderId}</td>
                                <td>${order.orderDate}</td>
                                <td>${order.userName}</td>
                                <td>
                                    <c:forEach var="item" items="${order.items}">
                                        ${item.productName}<br>
                                    </c:forEach>
                                </td>
                                <td>${order.orderStatus}</td>
                                <td>Rs. ${order.totalAmount}</td>
                            </tr>
                        </c:forEach>
                    </tbody>

                    <tfoot>
                        <tr class="total-row">
                            <td colspan="5">Cumulative Revenue</td>
                            <td>Rs. ${totalSales}</td>
                        </tr>
                    </tfoot>

                </table>

            </div>
        </section>

    </main>

</div>

</body>
</html>