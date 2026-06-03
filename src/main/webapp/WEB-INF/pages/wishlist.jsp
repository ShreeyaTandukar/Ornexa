<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <!-- Dynamic loop for wishlist items -->
                <c:forEach var="item" items="${wishlistItems}">
                    <tr>
                        <!-- Remove item -->
                        <td class="remove-item">
                            <form action="WishListServlet" method="post">
                                <input type="hidden" name="productId" value="${item.id}" />
                                <input type="hidden" name="action" value="remove" />
                                <button type="submit">&times;</button>
                            </form>
                        </td>
                        
                        <!-- Product info -->
                        <td class="product-info">
                            <img src="${item.imageUrl}" alt="${item.name}">
                            <span class="product-name">${item.name}</span>
                        </td>
                        
                        <!-- Price -->
                        <td class="price">Rs. ${item.price}</td>
                        
                        <!-- Stock status -->
                        <td class="stock-status ${item.stockStatus}">
                            ● ${item.stockStatus}
                        </td>
                        
                        <!-- Add to cart -->
                        <td>
                            <form action="CartServlet" method="post">
                                <input type="hidden" name="productId" value="${item.id}" />
                                <input type="hidden" name="action" value="add" />
                                <button class="add-to-cart">ADD TO CART</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
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
