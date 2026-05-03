<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/product.css">
</head>
<body>
<%@ include file="Header.jsp" %>
<div class="container2">

        <div class="top_row">
            <div class="product_text">
                <h2>Engraved Round</h2>
                <h2>Mens's Necklace</h2>

                <div class="divider">
                    <div class="line_left"></div>
                    <span class="diamond"></span>
                    <div class="line_right"></div>
                </div>

                <p class="text2">PERSONALIZE WITH YOUR ENGRAVING</p>
                <button class="product_button">VIEW COLLECTION</button>
            </div>

            <div class="product_image">
                <img src="../picture/product imgae.png">
            </div>
        </div>

       <div class="filters">

    <!-- TYPE DROPDOWN -->
    <div class="dropdown">

        <button class="dropdown-btn" onclick="toggleDropdown('typeMenu')">
            Filter: Type ▾
        </button>

        <div class="dropdown-content" id="typeMenu">
            <a href="#">Gold</a>
            <a href="#">Silver</a>
            <a href="#">Diamond</a>
        </div>

    </div>
    
    <div class="dropdown">

        <button class="dropdown-btn" onclick="toggleDropdown('styleMenu')">
            Sort By: Style ▾
        </button>

        <div class="dropdown-content" id="styleMenu">

            <div class="submenu">
                <p class="submenu-title">Modern ▸</p>
                <div class="submenu-items">
                    <a href="#">Men</a>
                    <a href="#">Women</a>
                </div>
            </div>

            <div class="submenu">
                <p class="submenu-title">Traditional ▸</p>
                <div class="submenu-items">
                    <a href="#">Men</a>
                    <a href="#">Women</a>
                </div>
            </div>

        </div>

    </div>

</div>

        <div class="left_product">

            <div class="left_box">
                <img src="../picture/image 68.png">

                <div class="text_left">
                    <h3>Upcoming Arrivals</h3>
                    <button class="product_button">Book Now</button>
                </div>

                <img src="../picture/image 69.png">
            </div>

            <div class="right_cards">

                <div class="cards">
                    <img src="../picture/ring-product.png">
                    <h3>Meander ring</h3>
                    <p>Rs 4000</p>
                    <button class="product_button">Explore</button>
                </div>

                <div class="cards">
                    <img src="../picture/Rectangle 318.png">
                    <h3>Vine Drop Earrings</h3>
                    <p>Rs 3500</p>
                    <button class="product_button">Explore</button>
                </div>

                <div class="cards">
                    <img src="../picture/Rectangle 315.png">
                    <h3>Makasi</h3>
                    <p>Rs 2500</p>
                    <button class="product_button">Explore</button>
                </div>

                <div class="cards">
                    <img src="../picture/kali.png">
                    <h3>Kalli</h3>
                    <p>Rs 15000</p>
                    <button class="product_button">Explore</button>
                </div>

                <div class="cards">
                    <img src="../picture/choker.png">
                    <h3>Regency Princess Choker</h3>
                    <p>Rs 3000</p>
                    <button class="product_button">Explore</button>
                </div>

                <div class="cards">
                    <img src="../picture/throne.png">
                    <h3>Thorn Cross Pendant</h3>
                    <p>Rs 4000</p>
                    <button class="product_button">Explore</button>
                </div>

            </div>
        </div>

        <div class="bottom">
            <div class="cards">
                <img src="../picture/peacock.png">
                <h3>Peacock Choker</h3>
                <p>Rs 2,00,000</p>
                <button class="product_button">Explore</button>
            </div>
            <div class="cards">
                <img src="../picture/braclet-men.png">
                <h3>Engraved Curb Link Bracelet</h3>
                <p>Rs 8000</p>
                <button class="product_button">Explore</button>
            </div>
            <div class="cards">
                <img src="../picture/sikri.png">
                <h3>Navaratna Sikri</h3>
                <p>Rs 10000</p>
                <button class="product_button">Explore</button>
            </div>
        </div>

    </div>
    	<%@ include file="footer.jsp" %>
    

</body>
<script src="product.js"></script>

</html>