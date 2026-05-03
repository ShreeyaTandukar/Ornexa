<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Stock</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/stock.css">
</head>

<body>
  <div class="layout-wrapper">
        
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
        
        <a href="adminOrderL.jsp" class="nav-item">
            <i class="fas fa-shopping-cart"></i> ORDERS
        </a>
        
        <a href="stock.jsp" class="nav-item active">
            <i class="fas fa-layer-group"></i> STOCK
        </a>
        
        <a href="adminUserM.jsp" class="nav-item">
            <i class="fas fa-users"></i> USERS
        </a>
        
        <a href="adminReports.jsp" class="nav-item">
            <i class="fas fa-chart-line"></i> REPORTS
        </a>
    </nav>
            
            <button class="add-product-btn"><i class="fas fa-plus"></i> ADD PRODUCT</button>
            
            <div class="sidebar-footer">
                <div class="footer-item"><i class="fas fa-question-circle"></i> HELP CENTER</div>
                <div class="footer-item"><i class="fas fa-sign-out-alt"></i> LOG OUT</div>
            </div>
        </aside>

        <main class="main-content">
            
            <header class="content-header">
                <div class="title-section">
                    <p class="overline">GLOBAL INVENTORY</p>
                    <h1>The Curated Ledger</h1>
                </div>
                
                <div class="header-stats">
                    <div class="stat-item">
                        <span class="label">TOTAL ITEMS</span>
                        <span class="value">100</span>
                    </div>
                    <div class="stat-item">
                        <span class="label">STOCK VALUE</span>
                        <span class="value">Rs. 8,00,099</span>
                    </div>
                    <div class="stat-item">
                        <span class="label">CRITICAL LOW</span>
                        <span class="value critical">08</span>
                    </div>
                </div>
            </header>

            <div class="dashboard-grid">
                <section class="ledger-card">
                    <div class="ledger-toolbar">
                        <div class="filters">
                            <button class="filter-btn active">ALL ITEMS</button>
                            <button class="filter-btn">LOW STOCK</button>
                            <button class="filter-btn">OUT OF STOCK</button>
                        </div>
                        <div class="sort-box">
                            SORT BY: <small> <b>STOCK LEVEL (HIGH-LOW)</b> <i class="fas fa-chevron-down"></i></small>
                        </div>
                    </div>

                    <div class="table-head">
                        <span>PRODUCT DETAILS</span>
                        <span>CATEGORY</span>
                        <span>STOCK LEVEL</span>
                        <span class="text-right">ACTIONS</span>
                    </div>

                    <div class="product-row">
                        <div class="cell-main">
                            <img src="../picture/Rectangle 315.png" alt="Makasi" class="item-img">
                            <div>
                                <p class="item-name">Makasi</p>
                                <p class="item-price">Rs. 2,500</p>
                            </div>
                        </div>
                        <div class="cell-cat">
                            <span class="badge">NEWARI</span>
                        </div>
                        <div class="cell-stock">
                            <p class="stock-num">04 <small>/ 10</small></p>
                            <span class="critical-tag">CRITICAL</span>
                        </div>
                        <div class="cell-actions">
                            <button class="btn-folder"><i class="fas fa-folder-plus"></i></button>
                            <button class="btn-update">UPDATE STOCK</button>
                        </div>
                    </div>

                    <div class="product-row">
                        <div class="cell-main">
                            <img src="../picture/braclet-men.png" alt="Bracelet" class="item-img">
                            <div>
                                <p class="item-name">Engraved Curb Link Bracelet</p>
                                <p class="item-price">Rs. 8,000</p>
                            </div>
                        </div>
                        <div class="cell-cat">
                            <span class="badge">BRACELET</span>
                        </div>
                        <div class="cell-stock">
                            <p class="stock-num">10 <small>/ 15</small></p>
                        </div>
                        <div class="cell-actions">
                            <button class="btn-folder"><i class="fas fa-folder-plus"></i></button>
                            <button class="btn-update">UPDATE STOCK</button>
                        </div>
                    </div>

                    <footer class="pagination-area">
                        <span class="info-text">SHOWING 1-10 OF 100 ITEMS</span>
                        <div class="pag-controls">
                            <button class="p-btn"><i class="fas fa-chevron-left"></i></button>
                            <button class="p-btn active">1</button>
                            <button class="p-btn">2</button>
                            <button class="p-btn">3</button>
                            <button class="p-btn"><i class="fas fa-chevron-right"></i></button>
                        </div>
                    </footer>
                </section>

                <aside class="side-widgets">
                    <div class="widget cat-widget">
                        <h3>CATEGORIES</h3>
                        <ul class="cat-list">
                            <li>Newari <span>10</span></li>
                            <li>Bracelet <span>15</span></li>
                            <li>Ring <span>12</span></li>
                        </ul>
                       <button class="mang-btn">MANAGE ALL <i class="fas fa-chevron-right"></i></button>
                    </div>

                    <div class="widget warning-widget">
                        <div class="warn-head">
                            <i class="fas fa-exclamation-triangle"></i>
                            <span class="warn-label">ACTION REQUIRED</span>
                        </div>
                        <h3>Inventory Warning</h3><br>
                        <p>12 items have fallen below the critical threshold.</p><br>
                        <button class="review-btn">REVIEW CRITICAL LIST</button>
                    </div>
                </aside>
            </div>
            
        </main>
        
    </div>
      <%@ include file="footer.jsp" %>

</body>
</html>