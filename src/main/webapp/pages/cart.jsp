<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<link rel="stylesheet" href="../css/cart.css">
</head>
<body>
	<%@ include file="Header.jsp" %>
	<main class="cart-container">
    <div class="cart-header">
        <a href="#" class="back-btn">&#8592;</a>
        <h1>My Cart</h1>
    </div>

    <div class="cart-item">
        <div class="item-selection">
            <input type="checkbox" checked>
        </div>
        <div class="item-image">
            <img src="desc image.png" alt="Kalli">
        </div>
        <div class="item-details">
            <h2 class="item-name">Kalli</h2>
        </div>
        <div class="item-pricing">
            <p class="current-price">Rs.15000</p>
            <div class="quantity-selector">
                <button>-</button>
                <span>1</span>
                <button>+</button>
            </div>
        </div>
    </div>

    <div class="cart-item">
        <div class="item-selection">
            <input type="checkbox">
        </div>
        <div class="item-image">
            <img src="men.jpeg" alt="Engraved Round Men's Necklace">
        </div>
        <div class="item-details">
            <h2 class="item-name">Engraved Round Men’s Necklace</h2>
            <p class="old-price">Rs.13000</p>
        </div>
        <div class="item-pricing">
            <p class="current-price">Rs.6500</p>
            <div class="quantity-selector">
                <button>-</button>
                <span>1</span>
                <button>+</button>
            </div>
        </div>
    </div>

    <div class="cart-summary">
        <div class="select-all">
            <input type="checkbox" id="all">
            <label for="all">All</label>
        </div>
        <div class="checkout-section">
            <span class="subtotal">Subtotal: <strong>Rs.15000</strong></span>
            <button class="checkout-btn">Check Out(1)</button>
        </div>
    </div>
</main>
<%@ include file="footer.jsp" %>
</body>
</html>