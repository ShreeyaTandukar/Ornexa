<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn"  uri="jakarta.tags.functions" %>
<%@ taglib prefix="c"   uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment - Ornexa</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/payment.css">
</head>
<body>
<%@ include file="Header.jsp" %>

<main class="payment-container">

	<%-- success message after order placed --%>
	<c:if test="${param.status == 'success'}">
		<div class="success-box">
			<i>&#10003;</i>
			<h2>Order Placed Successfully!</h2>
			<p>Thank you for shopping with Ornexa. Your order will be delivered within 2-3 days.</p>
			<a href="${pageContext.request.contextPath}/Home_pageServlet" class="continue-btn">
				Continue Shopping
			</a>
		</div>
	</c:if>

	<%-- payment page shown when no status param --%>
	<c:if test="${empty param.status}">

		<div class="back-row">
			<a href="javascript:history.back()" class="back-btn">&#8592;</a>
		</div>

		<%-- address section --%>
		<div class="address-section">
			<div class="address-left">
				<span class="location-icon">&#128205;</span>
				<div class="address-info">
					<span class="address-name">
						${sessionScope.user.userName}, ${sessionScope.user.phoneNumber}
					</span>
					<span class="address-text">
						<c:choose>
							<c:when test="${not empty param.savedAddress}">
								${param.savedAddress}
							</c:when>
							<c:otherwise>Add your delivery address</c:otherwise>
						</c:choose>
					</span>
				</div>
			</div>

			<form action="${pageContext.request.contextPath}/payment" method="get">
				<input type="hidden" name="cartId"      value="${param.cartId}"/>
				<input type="hidden" name="cartTotal"   value="${param.cartTotal}"/>
				<input type="hidden" name="itemNames"   value="${param.itemNames}"/>
				<input type="hidden" name="itemQtys"    value="${param.itemQtys}"/>
				<input type="hidden" name="itemPrices"  value="${param.itemPrices}"/>
				<input type="hidden" name="itemImgs"    value="${param.itemImgs}"/>
				<input type="hidden" name="productName" value="${param.productName}"/>
				<input type="hidden" name="price"       value="${param.price}"/>
				<input type="hidden" name="quantity"    value="${param.quantity}"/>
				<input type="hidden" name="source"      value="${param.source}"/>
				<input type="hidden" name="imgUrl"      value="${param.imgUrl}"/>
				<input type="hidden" name="showAddress" value="true"/>
				<button type="submit" class="edit-btn">EDIT</button>
			</form>
		</div>

		<%-- address input shown when showAddress=true --%>
		<c:if test="${param.showAddress == 'true'}">
			<form action="${pageContext.request.contextPath}/payment" method="get"
				  class="address-edit-box">
				<input type="hidden" name="cartId"      value="${param.cartId}"/>
				<input type="hidden" name="cartTotal"   value="${param.cartTotal}"/>
				<input type="hidden" name="itemNames"   value="${param.itemNames}"/>
				<input type="hidden" name="itemQtys"    value="${param.itemQtys}"/>
				<input type="hidden" name="itemPrices"  value="${param.itemPrices}"/>
				<input type="hidden" name="itemImgs"    value="${param.itemImgs}"/>
				<input type="hidden" name="productName" value="${param.productName}"/>
				<input type="hidden" name="price"       value="${param.price}"/>
				<input type="hidden" name="quantity"    value="${param.quantity}"/>
				<input type="hidden" name="source"      value="${param.source}"/>
				<input type="hidden" name="imgUrl"      value="${param.imgUrl}"/>
				<input type="text"   name="savedAddress"
					   placeholder="Enter your full delivery address"
					   value="${param.savedAddress}" required/>
				<button type="submit">Save</button>
			</form>
		</c:if>

		<%-- product cards --%>
		<c:choose>

			<%-- buy now - single product --%>
			<c:when test="${not empty param.productName}">
				<div class="product-card">
					<div class="product-card-top">
						<img src="${pageContext.request.contextPath}/picture/${param.imgUrl}"
							 alt="${param.productName}"/>
						<div class="product-card-info">
							<h2>${param.productName}</h2>
							<p class="exchange-tag">Exchange Within 7 Days</p>
						</div>
						<span class="product-price">
							Rs.<fmt:formatNumber value="${param.price}" pattern="#,##0"/>
						</span>
					</div>
					<div class="qty-row">
						<p class="delivery-tag">&#128666; Guaranteed within 2-3 days</p>
						<div class="qty-box">
							<form action="${pageContext.request.contextPath}/payment" method="get">
								<input type="hidden" name="cartId"       value="${param.cartId}"/>
								<input type="hidden" name="source"       value="${param.source}"/>
								<input type="hidden" name="productName"  value="${param.productName}"/>
								<input type="hidden" name="price"        value="${param.price}"/>
								<input type="hidden" name="imgUrl"       value="${param.imgUrl}"/>
								<input type="hidden" name="savedAddress" value="${param.savedAddress}"/>
								<input type="hidden" name="quantity"
									   value="${param.quantity > 1 ? param.quantity - 1 : 1}"/>
								<button type="submit" class="qty-btn">&#8722;</button>
							</form>
							<span class="qty-display">${param.quantity}</span>
							<form action="${pageContext.request.contextPath}/payment" method="get">
								<input type="hidden" name="cartId"       value="${param.cartId}"/>
								<input type="hidden" name="source"       value="${param.source}"/>
								<input type="hidden" name="productName"  value="${param.productName}"/>
								<input type="hidden" name="price"        value="${param.price}"/>
								<input type="hidden" name="imgUrl"       value="${param.imgUrl}"/>
								<input type="hidden" name="savedAddress" value="${param.savedAddress}"/>
								<input type="hidden" name="quantity"
									   value="${param.quantity + 1}"/>
								<button type="submit" class="qty-btn">&#43;</button>
							</form>
						</div>
					</div>
				</div>
			</c:when>

			<%-- cart checkout - one card per selected item --%>
			<c:otherwise>
				<c:choose>
					<c:when test="${fn:length(itemNames) > 0}">
						<c:forEach var="i" begin="0" end="${fn:length(itemNames) - 1}">
							<div class="product-card">
								<div class="product-card-top">
									<img src="${pageContext.request.contextPath}/picture/${itemImgs[i]}"
										 alt="${itemNames[i]}"/>
									<div class="product-card-info">
										<h2>${itemNames[i]}</h2>
										<p class="exchange-tag">Exchange Within 7 Days</p>
									</div>
									<span class="product-price">
										Rs.<fmt:formatNumber value="${itemPrices[i]}" pattern="#,##0"/>
									</span>
								</div>
								<div class="qty-row">
									<p class="delivery-tag">&#128666; Guaranteed within 2-3 days</p>
									<span class="qty-label">Qty: ${itemQtys[i]}</span>
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="product-card">
							<div class="product-card-top">
								<div class="product-card-info">
									<h2>Cart Order</h2>
									<p class="exchange-tag">Exchange Within 7 Days</p>
								</div>
								<span class="product-price">
									Rs.<fmt:formatNumber value="${param.cartTotal}" pattern="#,##0"/>
								</span>
							</div>
							<div class="qty-row">
								<p class="delivery-tag">&#128666; Guaranteed within 2-3 days</p>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</c:otherwise>

		</c:choose>

		<%-- order detail section --%>
		<div class="order-detail">
			<h3>Order Detail</h3>

			<div class="detail-row">
				<span>Subtotal</span>
				<span>Rs.<fmt:formatNumber value="${subtotal}" pattern="#,##0"/></span>
			</div>

			<div class="detail-row">
				<span>Discount</span>
				<span>Rs.0</span>
			</div>

			<div class="detail-row">
				<span>Shipping Fee</span>
				<span>Rs.100</span>
			</div>

			<div class="detail-divider"></div>

			<div class="detail-row total-row">
				<span>Total Amount</span>
				<span>Rs.<fmt:formatNumber value="${total}" pattern="#,##0"/></span>
			</div>
		</div>

		<%-- confirm form --%>
		<form action="${pageContext.request.contextPath}/payment"
			  method="post" id="confirmForm">
			<input type="hidden" name="cartId"        value="${param.cartId}"/>
			<input type="hidden" name="cartTotal"     value="${param.cartTotal}"/>
			<input type="hidden" name="itemNames"     value="${param.itemNames}"/>
			<input type="hidden" name="itemQtys"      value="${param.itemQtys}"/>
			<input type="hidden" name="itemPrices"    value="${param.itemPrices}"/>
			<input type="hidden" name="itemImgs"      value="${param.itemImgs}"/>
			<input type="hidden" name="productName"   value="${param.productName}"/>
			<input type="hidden" name="price"         value="${param.price}"/>
			<input type="hidden" name="quantity"      value="${param.quantity}"/>
			<input type="hidden" name="source"        value="${param.source}"/>
			<input type="hidden" name="destination"   value="${param.savedAddress}"/>
			<input type="hidden" name="paymentMethod" value="Cash on Delivery"/>

			<div class="confirm-row">
				<button type="submit" class="confirm-btn">Confirm</button>
			</div>
		</form>

	</c:if>

</main>

<%@ include file="footer.jsp" %>

</body>
</html>