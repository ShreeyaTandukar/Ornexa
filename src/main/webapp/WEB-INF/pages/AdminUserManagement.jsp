<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page isELIgnored="false" %>


<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Admin UserM</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/AdminUserM.css">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>

<body>


<div class="admin-layout">

   <%@ include file="AdminSideBar.jsp" %>

    <!-- MAIN CONTENT -->
    
    <main class="main-content">
        <header class="content-header">
        
            <h1>
                User Directory
                <span>Administration Control Panel</span>
            </h1>
        </header>

        <div class="stats-grid">
        

            <div class="stat-card">
                <p class="stat-label">TOTAL COMMUNITY</p>
                <h2>${totalCommunity}</h2>
                <span class="badge-green">+14% vs last month</span>
            </div>

            <div class="stat-card">
                <p class="stat-label">PENDING APPROVALS</p>
                <h2>${pendingApprovals}</h2>
            </div>

            <div class="stat-card quick-action">
                <p>QUICK ACTION</p>
                <h3>Generate User Access Report</h3>
               <button class="btn-download" 
            onclick="window.location.href='${pageContext.request.contextPath}/ExportUserServlet'">
        DOWNLOAD CSV
    </button>
            </div>

        </div>

        <section class="directory-container">

            <div class="directory-header">

                <div class="dir-title">
                    <h3>Registered User Directory</h3>
                    <p>Manage permissions, roles, and account security levels.</p>
                </div>

                <div class="dir-actions">
					    <div class="filter-wrapper">
					        <!-- Filter Icon -->
					        <i class="fas fa-filter filter-icon"></i>
					        
					        <form action="${pageContext.request.contextPath}/UserManagementServlet" method="get">
					            <select name="status" class="filter-select" onchange="this.form.submit()">
					                <option value="all" ${currentFilter == 'all' ? 'selected' : ''}>ALL USERS</option>
					                <option value="approved" ${currentFilter == 'approved' ? 'selected' : ''}>APPROVED</option>
					                <option value="pending" ${currentFilter == 'pending' ? 'selected' : ''}>PENDING</option>
					            </select>
					        </form>
					    </div>
					</div>

            </div>

            <table class="user-table">

                <thead>
                    <tr>
                        <th>USER INFORMATION</th>
                        <th>ROLE</th>
                        <th>STATUS</th>
                        <th>LAST ACTIVITY</th>
                        <th>ACTIONS</th>
                    </tr>
                </thead>

                <tbody>

                   <c:forEach var="user" items="${listUser}">
    <tr>
        <td>
            <div class="user-info">
                <span class="avatar-text">${fn:toUpperCase(fn:substring(user.userName, 0, 2))}</span>
                <div>
                    <strong>${user.userName}</strong><br>
                    <small>${user.email}</small>
                </div>
            </div>
        </td>
        <td>${user.role}</td>
        <td>
            <span class="status-badge ${user.status}">${user.status}</span>
        </td>
        <td>
			<form action="${pageContext.request.contextPath}/updateUserStatus" method="post">                
			<input type="hidden" name="username" value="${user.userName}">
                <select name="status" onchange="this.form.submit()">
                    <option value="pending" ${user.status == 'pending' ? 'selected' : ''}>PENDING</option>
                    <option value="approved" ${user.status == 'approved' ? 'selected' : ''}>APPROVED</option>
                </select>
            </form>
        </td>
        <td></td>
    </tr>
</c:forEach>

                </tbody>

            </table>

        </section>

    </main>

</div>

<%@ include file="footer.jsp" %>

</body>

</html>
