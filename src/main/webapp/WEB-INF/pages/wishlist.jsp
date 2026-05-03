<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Wishlist</title>
<link rel="stylesheet" href="../css/wishlist.css">
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="wishlist-container">
    <h2 class="wishlist-title">My Wishlist</h2>
    
    <div class="wishlist-table-wrapper">
        <table class="wishlist-table">
            <thead>
                <tr>
                    <th colspan="2">PRODUCT NAME</th>
                    <th>UNIT PRICE</th>
                    <th>STOCK STATUS</th>
                    <th>ACTIONS</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="remove-item"><span>&times;</span></td>
                    <td class="product-info">
                        <img src="../picture/kali.png" alt="Kalli">
                        <span class="product-name">Kalli</span>
                    </td>
                    <td class="price">Rs.15,000</td>
                    <td class="stock-status in-stock">● IN STOCK</td>
                    <td>
                        <a href="cart.jsp">
                            <button class="add-to-cart">ADD TO CART</button>
                        </a>
                    </td>
                </tr>
                <tr>
                    <td class="remove-item"><span>&times;</span></td>
                    <td class="product-info">
                        <img src="../picture/braclet-men.png" alt="Bracelet">
                        <span class="product-name">Engraved Curb Link Bracelet</span>
                    </td>
                    <td class="price">Rs.8,000</td>
                    <td class="stock-status in-stock">● IN STOCK</td>
                    <td>
                        <a href="cart.jsp">
                            <button class="add-to-cart">ADD TO CART</button>
                        </a>
                    </td>
                </tr>
                <tr>
                    <td class="remove-item"><span>&times;</span></td>
                    <td class="product-info">
                        <img src="men.jpeg" alt="Necklace">
                        <span class="product-name">Engraved Round Men's Necklace</span>
                    </td>
                    <td class="price">
                        Rs. 6,500 <br>
                        <small style="text-decoration: line-through; color: #999;">Rs. 13,000</small>
                    </td>
                    <td class="stock-status low-stock">● LOW STOCK</td>
                    <td>
                        <a href="cart.jsp">
                            <button class="add-to-cart">ADD TO CART</button>
                        </a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="wishlist-footer">
        <a href="product.jsp" class="continue-shopping">&larr; CONTINUE SHOPPING</a>
        <div class="pagination">
            <span class="page-num active">1</span>
            <span class="arrow">&gt;</span>
        </div>
    </div>
</section>
<%@ include file="footer.jsp" %>
</body>
</html>