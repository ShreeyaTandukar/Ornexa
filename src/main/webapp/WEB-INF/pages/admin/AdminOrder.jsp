<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Order Ledger</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminOrder.css">
</head>

<body>

<div class="admin-layout">

    <aside class="sidebar">

        <div class="sidebar-header">
            <img src="../picture/OrnexaAdmin.png" alt="ORNEXA" class="sidebar-logo">
            <div class="admin-brand">
                <span class="brand-name">Admin Portal</span>
                <span class="brand-sub">INVENTORY CONTROL</span>
            </div>
        </div>

        <nav class="nav-menu">

            <a href="adminDashboard.jsp" class="nav-item">
                <i class="fas fa-th-large"></i> DASHBOARD
            </a>

            <a href="adminProduct.jsp" class="nav-item">
                <i class="fas fa-box"></i> PRODUCTS
            </a>

            <a href="adminOrderL.jsp" class="nav-item active">
                <i class="fas fa-shopping-cart"></i> ORDERS
            </a>

            <a href="stock.jsp" class="nav-item">
                <i class="fas fa-layer-group"></i> STOCK
            </a>

            <a href="adminUserM.jsp" class="nav-item">
                <i class="fas fa-users"></i> USERS
            </a>

            <a href="adminReports.jsp" class="nav-item">
                <i class="fas fa-chart-line"></i> REPORTS
            </a>

        </nav>

        <button class="add-product-btn">
            <i class="fas fa-plus"></i> ADD PRODUCT
        </button>

        <div class="sidebar-footer">
            <div class="footer-item"><i class="fas fa-question-circle"></i> HELP CENTER</div>
            <div class="footer-item"><i class="fas fa-sign-out-alt"></i> LOG OUT</div>
        </div>

    </aside>

    <main class="main-content">

        <header class="header">
            <div class="header-info">
                <h1>Order Ledger</h1>
                <p class="sub-text">MONITORING 10 ACTIVE TRANSACTIONS</p>
            </div>

            <div class="header-stats">
                <div class="stat-item">
                    <span class="label">TOTAL REVENUE</span>
                    <span class="value">Rs.1,68,999</span>
                </div>

                <div class="stat-item">
                    <span class="label">MONTHLY GROWTH</span>
                    <span class="value growth">~ +12.4%</span>
                </div>
            </div>
        </header>

        <div class="action-bar">
            <div class="btn-group">
                <button class="tool-btn"><i class="fas fa-filter"></i> Filter By</button>
                <button class="tool-btn"><i class="far fa-calendar"></i> Date Range</button>
            </div>

            <div class="export-as">
                <small>EXPORT AS: <strong>PDF</strong></small>
            </div>
        </div>

        <table class="order-table">

            <thead>
                <tr>
                    <th>ORDER ID</th>
                    <th>CUSTOMER</th>
                    <th>DATE</th>
                    <th>AMOUNT</th>
                    <th>STATUS</th>
                    <th>ACTIONS</th>
                </tr>
            </thead>

            <tbody>
                <tr>
                    <td class="order-id">#ORN-9283</td>
                    <td>
                        <div class="customer-info">
                            <span class="avatar" style="background:#f1ebeb">KU</span>
                            <div class="c-text">
                                <strong>Kanti Shrestha</strong>
                                <p>KANTI@GMAIL.COM</p>
                            </div>
                        </div>
                    </td>
                    <td>APR 3, 2026</td>
                    <td class="amount">Rs.4,000</td>
                    <td><span class="badge transit">IN TRANSIT</span></td>
                    <td><a href="#" class="action-link">VIEW INVOICE</a></td>
                </tr>

                <tr>
                    <td class="order-id">#ORN-9284</td>
                    <td>
                        <div class="customer-info">
                            <span class="avatar" style="background:#f1ebeb">AS</span>
                            <div class="c-text">
                                <strong>Aashish Shah</strong>
                                <p>AASHISH.SHAH@GMAIL.COM</p>
                            </div>
                        </div>
                    </td>
                    <td>JAN 6, 2026</td>
                    <td class="amount">Rs.15,000</td>
                    <td><span class="badge delivered">DELIVERED</span></td>
                    <td><a href="#" class="action-link">VIEW INVOICE</a></td>
                </tr>

                <tr>
                    <td class="order-id">#ORN-9285</td>
                    <td>
                        <div class="customer-info">
                            <span class="avatar" style="background:#f1ebeb">KK</span>
                            <div class="c-text">
                                <strong>Kritika Khadka</strong>
                                <p>KRITIKA@GMAIL.COM</p>
                            </div>
                        </div>
                    </td>
                    <td>MAR 22, 2026</td>
                    <td class="amount">Rs.13,000</td>
                    <td><span class="badge processing">PROCESSING</span></td>
                    <td><a href="#" class="action-link">VIEW INVOICE</a></td>
                </tr>

            </tbody>

        </table>

        <div class="table-footer">

            <p class="sync-text">LAST SYNCHRONIZED 2 MINUTES AGO</p>

            <div class="pagination">
                <button class="pag-btn"><i class="fas fa-chevron-left"></i></button>
                <button class="pag-btn active">1</button>
                <button class="pag-btn">2</button>
                <button class="pag-btn">3</button>
                <button class="pag-btn"><i class="fas fa-chevron-right"></i></button>
            </div>

        </div>

    </main>

</div>

<%@ include file="footer.jsp" %>

</body>
</html>