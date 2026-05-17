<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ page isELIgnored="false" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<header class="header_section">
  <div class="container">
    <nav class="navbar">

      <img src="<%= request.getContextPath() %>/picture/ornexa.png" class="logo">

      <ul class="navbar-nav">
        <li class="nav-item active">
          <a class="nav-link" href="<%= request.getContextPath() %>/Home_pageServlet">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<%= request.getContextPath() %>/ProductServlet">Our Collection</a>
        </li>
        <li class="nav-item">
         <a class="nav-link" href="<%= request.getContextPath() %>/AboutUsServlet">About Us</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<%= request.getContextPath() %>/ContactUsServlet">Contact Us</a>
        </li>
      </ul>

      <div class="nav-right">
        <form action="${pageContext.request.contextPath}/ProductServlet" method="GET" class="search-form">
                    <button type="submit" class="search-btn">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                    <input type="text" name="searchQuery" placeholder="Search..." class="search-box">
                </form>

        <div class="icon-wrapper">
        	<a href="<%= request.getContextPath() %>/WishListServlet">
          		<i class="fa-regular fa-heart icon"></i>
          	</a>
        </div>

        <div class="icon-wrapper cart">
        	<a href="<%= request.getContextPath() %>/CartServlet">
          		<i class="fa-solid fa-cart-shopping icon"></i>
          	</a>
        </div>

        <div class="icon-wrapper profile">
          <i class="fa-regular fa-user icon"></i>

          <div class="dropdown">
          	<c:if test="${not empty user}">
    			<a href="<%= request.getContextPath() %>/userServlet">👤 Profile</a>
			</c:if>

			<c:if test="${empty user}">
    			<a href="<%= request.getContextPath() %>/loginServlet">🔑 Login</a>
    			<a href="<%= request.getContextPath() %>/registerServlet">📝 Register</a>
			</c:if>

          </div>
          
        </div>
      </div>

    </nav>
  </div>
</header>

</body>
</html>
