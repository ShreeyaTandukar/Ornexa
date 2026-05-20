<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Wishlist</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/wishlist.css">
</head>

<body>

<!-- HEADER -->
<jsp:include page="Header.jsp" />

<section class="wishlist-container">

    <h2 class="wishlist-title">My Wishlist</h2>

    <div class="wishlist-table-wrapper">

        <table class="wishlist-table">

            <thead>
                <tr>
                    <th colspan="2">PRODUCT</th>
                    <th>UNIT PRICE</th>
                    <th>STOCK STATUS</th>
                    <th>ACTIONS</th>
                </tr>
            </thead>

            <tbody>

                <!-- EMPTY STATE -->
                <c:if test="${empty wishlistItems}">
                    <tr>
                        <td colspan="5" class="empty-wishlist">
                            Your wishlist is empty.
                        </td>
                    </tr>
                </c:if>

                <!-- ITEMS -->
                <c:forEach var="item" items="${wishlistItems}">

                    <tr>

                        <!-- REMOVE -->
                        <td class="remove-item">

                            <form action="${pageContext.request.contextPath}/wishlist"
                                  method="post">

                                <input type="hidden" name="productId" value="${item.productId}" />
                                <input type="hidden" name="action" value="remove" />

                                <button type="submit">&times;</button>

                            </form>

                        </td>

                        <!-- PRODUCT -->
                        <td class="product-info">

							<img src="${pageContext.request.contextPath}/picture/${item.imgUrl}">                                

                            <span class="product-name">
                                ${item.name}
                            </span>

                        </td>

                        <!-- PRICE -->
                        <td class="price">
                            Rs. ${item.price}
                        </td>

                        <!-- STOCK -->
                        <td class="stock-status">

                            <c:choose>
                                <c:when test="${item.stockStatus eq 'IN STOCK'}">
                                    ● IN STOCK
                                </c:when>

                                <c:when test="${item.stockStatus eq 'LOW STOCK'}">
                                    ● LOW STOCK
                                </c:when>

                                <c:otherwise>
                                    ● OUT OF STOCK
                                </c:otherwise>
                            </c:choose>

                        </td>

                        <!-- ADD TO CART -->
                        <td>

                            <form action="${pageContext.request.contextPath}/cart"
                                  method="post">

                                <input type="hidden" name="productId" value="${item.productId}" />
                                <input type="hidden" name="action" value="add" />

                                <button type="submit" class="add-to-cart">
                                    ADD TO CART
                                </button>

                            </form>

                        </td>

                    </tr>

                </c:forEach>

            </tbody>

        </table>

    </div>

    <!-- FOOTER -->
    <div class="wishlist-footer">

        <a href="${pageContext.request.contextPath}/ProductServlet"
           class="continue-shopping">
            &larr; CONTINUE SHOPPING
        </a>

        <div class="pagination">
            <span class="page-num active">1</span>
            <span class="arrow">&gt;</span>
        </div>

    </div>

</section>

<!-- FOOTER -->
<jsp:include page="footer.jsp" />

</body>
</html>