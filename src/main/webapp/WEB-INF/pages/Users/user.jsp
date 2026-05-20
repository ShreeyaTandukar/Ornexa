<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Profile</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%@ include file="Header.jsp" %>

<main class="dashboard-container">

	<div class="hero-banner-img"
		style="background-image: url('${pageContext.request.contextPath}/picture/userPB.jpeg');">
		<div class="hero-text-overlay">
			<h1>Welcome Back, ${user.userName}!</h1>
			<p>Your curated collection awaits discovery.</p>
		</div>
	</div>

	<div class="main-layout">
		<div class="left-column">

			<div class="stats-row">

				<!-- Wishlist Card — links to WishListServlet -->
				<a href="${pageContext.request.contextPath}/wishlist" class="stat-card-link">
					<div class="stat-card">
						<i class="far fa-heart"></i>
						<div class="stat-info">
							<span class="count">${wishlistCount}</span>
							<span class="label">WISHLIST ITEMS</span>
						</div>
					</div>
				</a>

				<!-- Cart Card — links to CartServlet -->
				<a href="${pageContext.request.contextPath}/CartServlet" class="stat-card-link">
					<div class="stat-card">
						<i class="fas fa-shopping-bag"></i>
						<div class="stat-info">
							<span class="count">${cartCount}</span>
							<span class="label">CART</span>
						</div>
					</div>
				</a>

			</div>

			<section class="wishlist-section">
				<div class="section-header">
					<h2>My Wishlist</h2>
					<a href="${pageContext.request.contextPath}/wishlist" class="view-all">View All</a>
				</div>
				<div class="product-grid">
					<c:if test="${empty wishlist}">
						<p>No Wishlist yet</p>
					</c:if>
					<c:forEach var="list" items="${wishlist}">
						<div class="product-item">
							<div class="img-wrapper">
								<img src="${pageContext.request.contextPath}/picture/${list.imgUrl}" alt="Product">								<button class="remove-btn">&times;</button>
							</div>
							<h3>${list.name}</h3>
							<p class="price">Rs ${list.price}</p>
						</div>
					</c:forEach>
				</div>
			</section>

			<section class="orders-section">
			    <h2>Recent Orders</h2>
			    <div class="table-container">
			        <table class="orders-table">
			            <thead>
			                <tr>
			                    <th>ORDER ID</th>
			                    <th>DATE</th>
			                    <th>PRICE</th>
			                    <th>STATUS</th>
			                    <th>ACTION</th>
			                </tr>
			            </thead>
			            <tbody>
			                <c:if test="${empty orders}">
			                    <tr>
			                        <td colspan="5">No orders yet</td>
			                    </tr>
			                </c:if>
			                <c:forEach var="order" items="${orders}">
			                    <tr>
			                       <td>${order.orderId}</td>
									<td>${order.orderDate}</td>
									<td class="bold">Rs.${order.totalAmount}</td>
			                        <td>
									    <span class="status-tag
									        ${order.orderStatus == 'DELIVERED' ? 'delivered' :
											 order.orderStatus == 'placed' ? 'placed' :
											 order.orderStatus == 'CONFIRMED' ? 'confirmed' :
											 order.orderStatus == 'IN TRANSIT' ? 'transit' : 
									          order.orderStatus == 'CANCELLED' ? 'cancelled' :
									          'processing'}">
									        ${order.orderStatus}
									    </span>
									</td>
												                       
			                    </tr>
			                </c:forEach>
			            </tbody>
			        </table>
			    </div>
			</section>

		</div>

		<aside class="right-column">
			<div class="profile-card">
				<img src="${pageContext.request.contextPath}/${user.image}" class="avatar">
				<h3 class="user-name">${user.userName}</h3>
				<p class="user-email">${user.email}</p>

				<button class="outline-btn" onclick="openModel()">Edit Profile</button>
				<button class="outline-btn">Account Settings</button>

				<div class="account-links">
					<a href="#"><i class="far fa-question-circle"></i> HELP CENTER</a>
					<!-- Log Out — invalidates session and redirects to login -->
					<a href="${pageContext.request.contextPath}/logoutServlet" class="logout-link">
						<i class="fas fa-sign-out-alt"></i> LOG OUT
					</a>
				</div>
			</div>
		</aside>
	</div>

	<div class="promo-banner">
		<img src="${pageContext.request.contextPath}/picture/productimage.png" alt="Special Offer">
	</div>

	<div id="edit" class="popup">
		<div class="popup_box">
			<h2>EDIT PROFILE</h2>
			<form action="${pageContext.request.contextPath}/UpdateUserServlet" method="post" enctype="multipart/form-data">
				<input type="hidden" name="userId"   value="${user.id}"/>
				<input type="hidden" name="oldImage" value="${user.image}"/>

				<label>Username</label>
				<input type="text" name="userName" value="${user.userName}"/>

				<label>Phone Number</label>
				<input type="text" name="phoneNumber" value="${user.phoneNumber}"/>

				<label>Profile Image</label>
				<input type="file" name="image"/>

				<div class="action">
					<button type="submit" class="save_button">Save</button>
					<button type="button" class="cancel_button" onclick="closeModel()">Cancel</button>
				</div>
			</form>
		</div>
	</div>

</main>

<%@ include file="footer.jsp" %>

<script>
	function openModel() {
		document.getElementById("edit").style.display = "flex";
	}

	function closeModel() {
		document.getElementById("edit").style.display = "none";
	}
</script>

</body>
</html>