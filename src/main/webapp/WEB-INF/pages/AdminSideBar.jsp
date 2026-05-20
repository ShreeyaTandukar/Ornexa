<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page isELIgnored="false" %>
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
       class="nav-item ${fn:contains(pageContext.request.requestURI, 'AdminDashboard') ? 'active' : ''}">
        <i class="fas fa-th-large"></i> DASHBOARD
    </a>

    <a href="<%= request.getContextPath() %>/Product"
       class="nav-item ${fn:contains(pageContext.request.requestURI, 'Product') ? 'active' : ''}">
        <i class="fas fa-box"></i> PRODUCTS
    </a>
    
    <a href="<%= request.getContextPath() %>/OrderManagement"
       class="nav-item ${fn:contains(pageContext.request.requestURI, 'OrderManagement') ? 'active' : ''}">
        <i class="fas fa-shopping-cart"></i> ORDERS
    </a>
    
    <a href="<%= request.getContextPath() %>/StockManagement"
       class="nav-item ${fn:contains(pageContext.request.requestURI, 'StockManagement') ? 'active' : ''}">
        <i class="fas fa-layer-group"></i> STOCK
    </a>
    
    <a href="<%= request.getContextPath() %>/UserManagementServlet"
       class="nav-item ${fn:contains(pageContext.request.requestURI, 'UserManagement') ? 'active' : ''}">
        <i class="fas fa-users"></i> USERS
    </a>
    
    <a href="<%= request.getContextPath() %>/Report"
       class="nav-item ${fn:contains(pageContext.request.requestURI, 'Reports') ? 'active' : ''}">
        <i class="fas fa-chart-line"></i> REPORTS
    </a>

</nav>

    <button type="button" class="add-product-btn" onclick="openAddModal()">
   	 <i class="fas fa-plus"></i> ADD PRODUCT
	</button>

    <div class="sidebar-footer">
    <a href="${pageContext.request.contextPath}/logout" class="footer-item">
        <i class="fas fa-sign-out-alt"></i> LOG OUT
    </a>
</div>

</aside>

<div id="addModal" class="modal ${not empty openAddModal ? 'show' : ''}">
    <div class="modal-content">

        <span class="close" onclick="closeAddModal()">&times;</span>
        <h2>Add Product</h2>

        <!-- Error message -->
        <c:if test="${not empty errorMsg}">
<p style="color:red;">${errorMsg}</p>
</c:if>
        <form action="${pageContext.request.contextPath}/Product" method="post">

            <input type="hidden" name="action" value="add">

            <input type="text"
                   name="name"
                   placeholder="Name"
                   value="${param.name}">

            <input type="number"
                   name="price"
                   placeholder="Price"
                   value="${param.price}">

            <input type="number"
                   name="stockQuantity"
                   placeholder="Stock"
                   value="${param.stockQuantity}">


            <!-- Material -->
            <select name="material">

                <option value="">Select Material</option>

                <option value="Gold"
                    ${param.material == 'Gold' ? 'selected' : ''}>
                    Gold
                </option>

                <option value="Silver"
                    ${param.material == 'Silver' ? 'selected' : ''}>
                    Silver
                </option>

                <option value="Diamond"
                    ${param.material == 'Diamond' ? 'selected' : ''}>
                    Diamond
                </option>

            </select>


            <!-- Gender -->
            <select name="gender">

                <option value="">Select Gender</option>

                <option value="Men"
                    ${param.gender == 'Men' ? 'selected' : ''}>
                    Men
                </option>

                <option value="Women"
                    ${param.gender == 'Women' ? 'selected' : ''}>
                    Women
                </option>


            </select>


            <!-- Style -->
            <select name="style">

                <option value="">Select Style</option>

                <option value="Traditional"
                    ${param.style == 'Traditional' ? 'selected' : ''}>
                    Traditional
                </option>

                <option value="Modern"
                    ${param.style == 'Modern' ? 'selected' : ''}>
                    Modern
                </option>


            </select>

			<label for="image">
			    Upload Image
			</label>
            <input type="file" id="image" name="image" accept="image/*" >

			
				
           <input type="text"
                   name="description"
                   placeholder="Description"
                   value="${param.description}">


            <select name="categoryId">

                <option value="">Select Category</option>

                <option value="1"
                    ${param.categoryId == '1' ? 'selected' : ''}>
                    Necklace
                </option>

                <option value="2"
                    ${param.categoryId == '2' ? 'selected' : ''}>
                    Earring
                </option>

                <option value="3"
                    ${param.categoryId == '3' ? 'selected' : ''}>
                    Ring
                </option>

                <option value="4"
                    ${param.categoryId == '4' ? 'selected' : ''}>
                    Bracelet
                </option>

            </select>


            <button type="submit">
                SAVE
            </button>

        </form>

    </div>

</div>


<script>
function openAddModal() {
    document.getElementById("addModal").classList.add("show");
    document.body.classList.add("modal-open");
}

function closeAddModal() {
    document.getElementById("addModal").classList.remove("show");
    document.body.classList.remove("modal-open");
}
</script>

<c:if test="${not empty openAddModal}">
<script>
document.addEventListener("DOMContentLoaded", function () {
    openAddModal();
});
</script>
</c:if>
