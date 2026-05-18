<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn"  uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
</head>
<body>
	<%@ include file="Header.jsp" %>

	<main class="cart-container">

		<div class="cart-header">
			<a href="javascript:history.back()" class="back-btn">&#8592;</a>
			<h1>My Cart</h1>
		</div>

		<c:choose>

			<c:when test="${empty cartItems}">
				<p class="empty-cart">Your cart is empty.</p>
			</c:when>

			<c:otherwise>

				<%-- checkout form is defined here, items link to it using form attribute --%>
				<form action="${pageContext.request.contextPath}/CartServlet"
					  method="post" id="checkoutForm">
					<input type="hidden" name="action" value="checkout"/>
					<input type="hidden" name="cartId" value="${cartId}"/>
				</form>

				<%-- loop through each cart item and show it as a card --%>
				<c:forEach var="item" items="${cartItems}">
					<div class="cart-item" data-subtotal="${item.subTotal}">

						<%-- checkbox is linked to checkout form even though its outside it --%>
						<input type="checkbox"
							   class="item-checkbox"
							   name="selectedItems"
							   value="${item.cartItemId}"
							   form="checkoutForm"
							   checked/>

						<div class="item-image">
							<img src="${pageContext.request.contextPath}/picture/${item.imgUrl}"
								 alt="${item.itemName}">
						</div>

						<div class="item-details">
							<h2 class="item-name">${item.itemName}</h2>
							<p class="current-price">
								Rs.<fmt:formatNumber value="${item.priceAtTime}" pattern="#,##0.00"/>
							</p>
						</div>

						<div class="item-pricing">
							<%-- quantity buttons in their own separate form --%>
							<div class="quantity-selector">
								<form action="${pageContext.request.contextPath}/CartServlet"
									  method="post" style="display:contents;">
									<input type="hidden" name="action"      value="update"/>
									<input type="hidden" name="cartItemId"  value="${item.cartItemId}"/>
									<input type="hidden" name="priceAtTime" value="${item.priceAtTime}"/>
									<button type="submit" name="quantity"
											value="${item.productQuantity - 1}"
											class="qty-btn">&#8722;</button>
									<span class="qty-display">${item.productQuantity}</span>
									<button type="submit" name="quantity"
											value="${item.productQuantity + 1}"
											class="qty-btn">&#43;</button>
								</form>
							</div>
							<span class="item-subtotal">
								Rs.<fmt:formatNumber value="${item.subTotal}" pattern="#,##0.00"/>
							</span>
						</div>

						<%-- remove button in its own form --%>
						<form action="${pageContext.request.contextPath}/CartServlet"
							  method="post" style="display:contents;">
							<input type="hidden" name="action"     value="remove"/>
							<input type="hidden" name="cartItemId" value="${item.cartItemId}"/>
							<button type="submit" class="remove-btn">&#10005;</button>
						</form>

					</div>
				</c:forEach>

				<%-- summary bar at the bottom --%>
				<div class="cart-summary">
					
					<div class="checkout-section">
						<span class="total-label">
							Total: <strong id="summaryTotal">
								Rs.<fmt:formatNumber value="${cartTotal}" pattern="#,##0.00"/>
							</strong>
						</span>
						<button type="submit" form="checkoutForm" class="checkout-btn">
							Check Out (${fn:length(cartItems)})
						</button>
					</div>
				</div>

			</c:otherwise>

		</c:choose>

	</main>

	<%@ include file="footer.jsp" %>

<script>
	// update the total whenever a checkbox is checked or unchecked
	function updateTotal() {
		var cards = document.querySelectorAll('.cart-item');
		var total = 0;
		cards.forEach(function(card) {
			var cb = card.querySelector('.item-checkbox');
			if (cb && cb.checked) {
				total += parseFloat(card.getAttribute('data-subtotal'));
			}
		});
		document.getElementById('summaryTotal').innerText =
			'Rs.' + total.toLocaleString('en-IN', {
				minimumFractionDigits: 2,
				maximumFractionDigits: 2
			});
	}



	// listen for changes on each item checkbox
	document.querySelectorAll('.item-checkbox').forEach(function(cb) {
		cb.addEventListener('change', updateTotal);
	});

	// run once on load to set the correct initial total
	updateTotal();
</script>

</body>
</html>