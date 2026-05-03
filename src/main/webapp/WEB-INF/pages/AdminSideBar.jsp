<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet"
    href="<%= request.getContextPath() %>/css/adminSideBar.css">

<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<!-- SIDEBAR -->
<aside class="sidebar">

    <div class="sidebar-header">

        <img src="<%= request.getContextPath() %>/picture/OrnexaAdmin.png"
             alt="ORNEXA"
             class="sidebar-logo">

        <div class="admin-brand">
            <span class="brand-name">Admin Portal</span>
            <span class="brand-sub">INVENTORY CONTROL</span>
        </div>

    </div>

    <nav class="nav-menu">

        <a href="<%= request.getContextPath() %>/AdminDashboard"
           class="nav-item ${pageContext.request.requestURI.contains('AdminDashboard') ? 'active' : ''}">
            <i class="fas fa-th-large"></i> DASHBOARD
        </a>

        <a href="<%= request.getContextPath() %>/Product"
           class="nav-item ${pageContext.request.requestURI.contains('Product') ? 'active' : ''}">
            <i class="fas fa-box"></i> PRODUCTS
        </a>

        <a href="<%= request.getContextPath() %>/OrderManagement"
           class="nav-item ${pageContext.request.requestURI.contains('OrderManagement') ? 'active' : ''}">
            <i class="fas fa-shopping-cart"></i> ORDERS
        </a>

        <a href="<%= request.getContextPath() %>/StockManagement"
           class="nav-item ${pageContext.request.requestURI.contains('StockManagement') ? 'active' : ''}">
            <i class="fas fa-layer-group"></i> STOCK
        </a>

        <a href="<%= request.getContextPath() %>/UserManagementServlet"
           class="nav-item ${pageContext.request.requestURI.contains('UserManagementServlet') ? 'active' : ''}">
            <i class="fas fa-users"></i> USERS
        </a>

        <a href="<%= request.getContextPath() %>/adminReports.jsp"
           class="nav-item ${pageContext.request.requestURI.contains('adminReports') ? 'active' : ''}">
            <i class="fas fa-chart-line"></i> REPORTS
        </a>

    </nav>

    <!-- ADD PRODUCT BUTTON -->
    <button class="add-product-btn" onclick="openAddModal()">
        <i class="fas fa-plus"></i> ADD PRODUCT
    </button>

    <div class="sidebar-footer">
        <div class="footer-item">
            <i class="fas fa-sign-out-alt"></i> LOG OUT
        </div>
    </div>

</aside>

<!-- ================= ADD PRODUCT MODAL ================= -->

<div id="addModal" class="modal">

    <div class="modal-content">

        <span onclick="closeAddModal()" style="cursor:pointer; float:right;">&times;</span>

        <h2>Add Product</h2>

        <!-- error message -->
        <p style="color:red;">
            <%= request.getAttribute("errorMsg") != null
                ? request.getAttribute("errorMsg")
                : "" %>
        </p>

        <form action="<%= request.getContextPath() %>/Product" method="post">

            <input type="hidden" name="action" value="add">

            <input type="text" name="name" placeholder="Name" required>
            <input type="number" name="price" placeholder="Price" required>
            <input type="number" name="stockQuantity" placeholder="Stock" required>

            <input type="text" name="material" placeholder="Material">
            <input type="text" name="gender" placeholder="Gender">
            <input type="text" name="style" placeholder="Style">

            <input type="text" name="imgUrl" placeholder="Image URL">
            <input type="text" name="description" placeholder="Description">
            <input type="number" name="categoryId" placeholder="Category ID">

            <button type="submit">SAVE</button>

        </form>

    </div>

</div>

<!-- ================= SCRIPT ================= -->

<script>

function openAddModal() {
    document.getElementById("addModal").style.display = "block";
}

function closeAddModal() {
    document.getElementById("addModal").style.display = "none";
}

window.onload = function () {

    <% if(request.getAttribute("openAddModal") != null) { %>
        openAddModal();
    <% } %>

};

</script>
