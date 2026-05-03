<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user Profile</title>
<link rel="stylesheet" href="../css/user.css">
</head>
<body>
	<%@ include file="Header.jsp" %>
	<img class ="profile_image" src="${pageContext.request.contextPath}/Profile_image?userName=${user.username}" alt="Profile Image" onerror="this.style.display='none'">
	
	<main class="profile-container">
    <h1 class="page-title">Profile</h1>

    <div class="info-card">
        <div class="edit-btn-container">
            <button class="edit-profile-btn">Edit Profile</button>
        </div>
        
        <div class="profile-content">
            <div class="profile-avatar">
                <div class="avatar-circle">
                    <span class="user-icon">&#128100;</span>
                </div>
            </div>

            <div class="detail-row">
    			<span class="label">Name:</span>
    			<span class="value">${user.firstName} ${user.lastName}</span>
			</div>

			<div class="detail-row">
    			<span class="label">Phone Number:</span>
    			<span class="value">${user.phoneNum}</span>
			</div>

			<div class="detail-row">
    			<span class="label">Email:</span>
    			<span class="value">${user.email}</span>
			</div>

                <div class="detail-row">
                    <span class="label">Password:</span>
                    <span class="value">**********</span>
                </div>
            </div>
        </div>
    </div>

    <div class="profile-nav">
        <a href="Cart.jsp" class="nav-btn">My Cart</a> <br>
        <a href="Wishlist.jsp" class="nav-btn">Wishlist</a> <br>
        <a href="OrderHistory.jsp" class="nav-btn">Order History</a> <br>
    </div>
</main>
<%@ include file="footer.jsp" %>
</body>
</html>