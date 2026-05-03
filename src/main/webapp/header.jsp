<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

      <img src="ornexa.png" class="logo">

      <ul class="navbar-nav">
        <li class="nav-item active">
          <a class="nav-link" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Our Collection</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">About Us</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Contact Us</a>
        </li>
      </ul>

      <div class="nav-right">
        <div class="search-container">
           <i class="fa-solid fa-magnifying-glass search-icon"></i>
          <input type="text" placeholder="Search..." class="search-box">
         
        </div>

        <div class="icon-wrapper">
          <i class="fa-regular fa-heart icon"></i>
        </div>

        <div class="icon-wrapper cart">
          <i class="fa-solid fa-cart-shopping icon"></i>
        </div>

        <div class="icon-wrapper">
          <i class="fa-regular fa-user icon"></i>
        </div>
      </div>

    </nav>
  </div>
</header>

</body>
</html>