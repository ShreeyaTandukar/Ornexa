<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Order Ledger</title>
    
    <link rel="stylesheet" 
    href="<%= request.getContextPath() %>/css/AdminOrder.css">
</head>
<body>
<div class="admin-layout">
   
<%@ include file="AdminSideBar.jsp" %>

        <main class="main-content">
            <header class="header">
                <div class="header-info">
                    <h1>Order Ledger</h1>
                    <p class="sub-text">MONITORING 10 ACTIVE TRANSACTIONS</p>
                </div>
                <div class="header-stats">

				    <div class="stat-item">
				        <span class="label">TOTAL REVENUE</span>
				        <span class="value">Rs.${revenue}</span>
				    </div>
				
				    <div class="stat-item">
				        <span class="label">MONTHLY GROWTH</span>
				        <span class="value growth">~ +${growth}%</span>
				    </div>
				
				</div>
            </header>

            
               <form action="OrderManagement" method="get" class="action-bar">
				
				    <div class="btn-group">
				
				        <select name="status" class="tool-btn">
				            <option value="">Filter By Status</option>
				            <option value="CONFIRMED">Confirmed</option>
				            <option value="PROCESSING">Processing</option>
				            <option value="IN TRANSIT">In Transit</option>
				            <option value="DELIVERED">Delivered</option>
				            <option value="CANCELLED">Cancelled</option>
				        </select>
				        
					<div class="date-container">
						    <div>
						        <span>From:</span>
						        <input type="date" name="fromDate" class="tool-btn">
						    </div>
						
						    <div>
						        <span>To:</span>
						        <input type="date" name="toDate" class="tool-btn">
						    </div>
						</div>
						<button type="submit" class="tool-btn">
           				 Apply
        				</button>
				
				    </div>
				
				</form>
                
            

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
                    <c:forEach var="o" items="${orders}">

						<tr>
						
						    <td class="order-id">
						        #ORN-${o.orderId}
						    </td>
						
						    <td>
						        <div class="customer-info">
						
						            <span class="avatar" style="background:#f1ebeb">
						                ${fn:toUpperCase(fn:substring(o.userName,0,2))}
						            </span>
						
						            <div class="c-text">
						                <strong>${o.userName}</strong>
						                <p>${fn:toUpperCase(o.email)}</p>
						            </div>
						
						        </div>
						    </td>
						
						    <td>${o.orderDate}</td>
						
						    <td class="amount">
						        Rs.${o.totalAmount}
						    </td>
						
						   <td>
							    <form action="OrderManagement" method="post">
							        <input type="hidden" name="orderId" value="${o.orderId}">
							
							        <select name="orderStatus"
							                class="status-dropdown
							                ${o.orderStatus == 'CONFIRMED' ? 'confirmed' :
							                  o.orderStatus == 'IN TRANSIT' ? 'transit' :
							                  o.orderStatus == 'DELIVERED' ? 'delivered' :
							                  o.orderStatus == 'CANCELLED' ? 'cancelled' :
							                  'processing'}"
							                onchange="this.form.submit()">
							
							            <option value="CONFIRMED"
							                ${o.orderStatus == 'CONFIRMED' ? 'selected' : ''}>
							                Confirmed
							            </option>
							            
							             <option value="PROCESSING"
							                ${o.orderStatus == 'PROCESSING' ? 'selected' : ''}>
							                Processing
							            </option>
							
							
							            <option value="IN TRANSIT"
							                ${o.orderStatus == 'IN TRANSIT' ? 'selected' : ''}>
							                In Transit
							            </option>
							
							            <option value="DELIVERED"
							                ${o.orderStatus == 'DELIVERED' ? 'selected' : ''}>
							                Delivered
							            </option>
							
							           
							            <option value="CANCELLED"
							                ${o.orderStatus == 'CANCELLED' ? 'selected' : ''}>
							                Cancelled
							            </option>
							
							        </select>
							
							        <input type="hidden" name="action" value="updateStatus">
							    </form>
							</td>
						
						    <td>
						        <a href="<%=request.getContextPath()%>/Invoice?orderId=${o.orderId}" class="action-link">
						            VIEW INVOICE
						        </a>
						    </td>
						
						</tr>

					</c:forEach>
                </tbody>
            </table>

            <div class="table-footer">
                <p class="sync-text">LAST SYNCHRONIZED 2 MINUTES AGO</p>
                
            </div>
        </main>
    </div>
   <%@ include file="footer.jsp" %>
</body>
</html>