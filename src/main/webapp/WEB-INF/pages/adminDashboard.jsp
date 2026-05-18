<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ page isELIgnored="false" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dasboard</title>
</head>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/adminDashboard.css">
<body>



<div class="admin-layout">
    <jsp:include page="AdminSideBar.jsp"/>
    <div class="main">

        <section class="stats">
            <div class="stat-card">
                <h4>Total Sales</h4>
                <h2>Rs. ${totalSales}</h2>
                <p class="green">+12.5% from last month</p>
            </div>

            <div class="stat-card">
                <h4>Total Orders</h4>
                <h2>${totalOrders}</h2>
                <p class="green">+4.2% from last month</p>
            </div>

            <div class="stat-card">
                <h4>New Users</h4>
                <h2>${newUsers}</h2>
                <p>Stable growth</p>    
            </div>

            <div class="alert-card">
            <i class="fas fa-exclamation-triangle"></i><br>
                <p class="alert-title">Action Required</p>
                <h4>Stock Alerts</h4>
                <h2>14 Items</h2>
                <button>Review Inventory</button>
            </div>
        </section>

        <section class="middle">
            <div class="box revenue-box">
                <div class="box-header">
                    <div>
                        <h3>Revenue Growth</h3>
                        <h5>Performance metrics for the current fiscal quarter</h5>
                    </div>
                    <div class="tabs">
                        <span class="active-tab">Weekly</span>
                        <span>Monthly</span>
                        <span>Yearly</span>
                    </div>
                </div>
               <img src="<%= request.getContextPath() %>/picture/revenue.jpeg"
	             class="chart-img">
                
            </div>

            <div class="activity-box">
			    <h3>Recent Activity</h3>
			
			    <c:forEach var="order" items="${orders}">
			        <div class="activity">
			
			            <div>
			                <p>Order #${order.id}</p>
			                <small>Rs.${order.amount} • ${order.destination}</small>
			            </div>
			
			            <span class="status ${order.status}">
			                ${order.status}
			            </span>
			
			        </div>
			    </c:forEach>

    <p class="view-all">View All Orders</p>
</div>

        </section>

        <section class="table-box">
            <div class="table-header">
                <h3>Global Ledger</h3>
                <div class="table-actions">
                    <button class="filter-btn">Filter</button>
                    <button class="export-btn">Export PDF</button>
                </div>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>ORDER REFERENCES</th>
                        <th>CUSTOMER</th>
                        <th>DESTINATION</th>
                        <th>AMOUNT</th>
                        <th>STATUS</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
	                    <tr> 
	                        <td>${order.id}</td>
	                        <td>
	                            <div class="user">
									<span class="avatar">
									    ${order.userName.substring(0,1)}
									</span>
	               
	                            </div>
	                        </td>
	                        <td>${order.destination}</td>
	                        <td>${order.amount}</td>
	                        <td><span class="status delivered">${order.status}</span></td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </div>
</div>
    <jsp:include page="footer.jsp"/>
</body>
</html>