<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page isELIgnored="false" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Admin Stock</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/stock.css">
</head>

<body>

<div class="layout-wrapper">

   
<jsp:include page="AdminSideBar.jsp" />
    <main class="main-content">

        <header class="content-header">

            <div class="title-section">
                <p class="overline">GLOBAL INVENTORY</p>
                <h1>The Curated Ledger</h1>
            </div>

            <div class="header-stats">

                <div class="stat-item">
                    <span class="label">TOTAL PRODUCT</span>
                    <span class="value">${totalItems}</span>
                </div>

                <div class="stat-item">
                    <span class="label">STOCK VALUE</span>
                    <span class="value">Rs. ${stockValue}</span>
                </div>

                <div class="stat-item">
                    <span class="label">CRITICAL LOW</span>
                    <span class="value critical">${lowStock}</span>
                </div>
                
                 <div class="stat-item">
                    <span class="label">OUT OF STOCK</span>
                    <span class="value"> ${outStock}</span>
                </div>

            </div>

        </header>



        <div class="dashboard-grid">

            <section class="ledger-card">

                <div class="ledger-toolbar">

                    <div class="filters">

					    <a href="StockManagement" 
					       class="filter-btn ${empty param.type ? 'active' : ''}">
					       ALL ITEMS
					    </a>
					
					    <a href="StockManagement?type=low" 
					       class="filter-btn ${param.type == 'low' ? 'active' : ''}">
					       LOW STOCK
					    </a>
					
					    <a href="StockManagement?type=out" 
					       class="filter-btn ${param.type == 'out' ? 'active' : ''}">
					       OUT OF STOCK
					    </a>
					
					</div>

                </div>



                <div class="table-head">
                    <span>PRODUCT DETAILS</span>
                    <span>CATEGORY</span>
                    <span>STOCK LEVEL</span>
                    <span>ACTIONS</span>
                </div>

				 <p style="color:red;">
					    <%= request.getAttribute("validating") != null
					        ? request.getAttribute("validating")
					        : "" %>
			</p>

                <c:forEach var="s" items="${stockList}">

                <div class="product-row">

                    <div class="cell-main">
						<img src="${pageContext.request.contextPath}/picture/${s.imgUrl}" class="item-img">
                        <div>
                            <p class="item-name">${s.productName}</p>
                            <p class="item-price">Rs. ${s.productPrice}</p>
                        </div>
                    </div>
					<div class="cell-cat">
					    <span class="badge">${s.categoryName}</span>
					</div>
			                  

                    <div class="cell-stock">

                        <p class="stock-num">${s.stockQuantity}</p>

                        <c:if test="${s.stockStatus=='LOW STOCK'}">
                            <span class="critical-tag">LOW STOCK</span>
                        </c:if>

                        <c:if test="${s.stockStatus=='OUT OF STOCK'}">
                            <span class="critical-tag">OUT OF STOCK</span>
                        </c:if>

                    </div>
                   

                   <div class="cell-actions">
                   
					

					    <form action="StockManagement" method="post" class="stock-form">
					
					        <input type="hidden" name="id" value="${s.productId}">
					
					        <input type="number"
					               name="qty"
					               placeholder="Qty"
					               class="stock-input">
					
					        <button type="submit" class="btn-update">
					            ADD STOCK
					        </button>
					
					    </form>
					
					</div>

                </div>

                </c:forEach>



                <footer class="pagination-area">
                    <span class="info-text">
                        SHOWING ALL ITEMS
                    </span>
                </footer>

            </section>



            <aside class="side-widgets">

               



                <div class="widget warning-widget">

                    <div class="warn-head">
                        <span class="warn-label">ACTION REQUIRED</span>
                    </div>

                    <h3>Inventory Warning</h3>

                    <p>${lowStock} items have fallen below the critical threshold.</p>

                </div>

            </aside>

        </div>

    </main>

</div>

<jsp:include page="footer.jsp" />

</body>
</html>