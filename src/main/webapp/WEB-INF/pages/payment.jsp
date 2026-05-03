<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment - Ornexa</title>
    <link rel="stylesheet" href="../css/payment.css">
</head>
<body>
    <%@ include file="Header.jsp" %>
    
    <main class="checkout-container">
        <div class="checkout-header">
            <a href="Cart.jsp" class="back-btn">&#8592;</a>
        </div>

        <div class="address-section">
            <div class="address-info">
                <span class="location-icon">&#128205;</span>
                <div>
                    <p><strong>Username, 98........</strong></p>
                    <p class="address-text">Address</p>
                </div>
            </div>
            <a href="#" class="edit-link">EDIT</a>
        </div>

        <div class="product-card">
            <div class="product-info-top">
                <img src="desc image.png" alt="Kalli" class="product-img">
                <div class="product-text">
                    <h2>Kalli</h2>
                    <p>Exchange Within 7 Days</p>
                </div>
                <div class="product-price">
                    <p class="price-val"><u>Rs.15000</u></p>
                    <div class="quantity-box">
                        <button class="qty-btn" onclick="decrement()">-</button>
                        <span id="qty-number">1</span>
                        <button class="qty-btn" onclick="increment()">+</button>
                    </div>
                </div>
            </div>
            <div class="product-guarantee">
                <p>Guaranteed within 2-3 days</p>
            </div>
        </div>

        <div class="order-details">
            <h3>Order Detail</h3>
            <div class="detail-row">
                <span>Subtotal</span>
                <span>Rs.15000</span>
            </div>
            <div class="detail-row">
                <span>Discount</span>
                <span>Rs.0</span>
            </div>
            <div class="detail-row">
                <span>Shipping Fee</span>
                <span>Rs.100</span>
            </div>
            <hr class="divider">
            <div class="detail-row total-row">
                <span>Total Amount</span>
                <span>Rs.15100</span>
            </div>
        </div>

        <div class="action-container">
            <button class="proceed-btn">Proceed To Pay</button>
        </div>
    </main>

    <%@ include file="footer.jsp" %>

    <script>
        function increment() {
            let num = document.getElementById('qty-number');
            num.innerText = parseInt(num.innerText) + 1;
        }
        function decrement() {
            let num = document.getElementById('qty-number');
            if(parseInt(num.innerText) > 1) {
                num.innerText = parseInt(num.innerText) - 1;
            }
        }
    </script>
</body>
</html>