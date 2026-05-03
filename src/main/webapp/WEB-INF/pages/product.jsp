<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
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
				<img src="<%= request.getContextPath() %>/picture/productimage.png">
            </div>
        </div>

       <div class="filters">

    <!-- TYPE DROPDOWN -->
    <div class="dropdown">

        <button class="dropdown-btn" onclick="toggleDropdown('typeMenu')">
            Filter: Type ▾
        </button>

        <div class="dropdown-content" id="typeMenu">
        	<a href="<%=request.getContextPath()%>/ProductServlet">All</a>
            <a href="<%=request.getContextPath() %>/ProductServlet?material=Gold">Gold</a>
            <a href="<%=request.getContextPath() %>/ProductServlet?material=Silver">Silver</a>
            <a href="<%=request.getContextPath() %>/ProductServlet?material=Diamond">Diamond</a>
            <a href="<%=request.getContextPath() %>/ProductServlet?material=Others">Others</a>
        </div>

    </div>
    
    <div class="dropdown">

        <button class="dropdown-btn" onclick="toggleDropdown('styleMenu')">
            Sort By: Style ▾
        </button>

        <div class="dropdown-content" id="styleMenu">

            <div class="submenu">
                <a href="<%=request.getContextPath()%>/ProductServlet">All</a>
            
                <p class="submenu-title">Modern ▸</p>
                <div class="submenu-items">
                    <a href="<%=request.getContextPath() %>/ProductServlet?style=Modern&gender=Men">Men</a>
                    <a href="<%=request.getContextPath() %>/ProductServlet?style=Modern&gender=Women">Women</a>
                </div>
            </div>

            <div class="submenu">
                <p class="submenu-title">Traditional ▸</p>
                <div class="submenu-items">
                    <a href="<%=request.getContextPath() %>/ProductServlet?style=Traditional&gender=Men">Men</a>
                    <a href="<%=request.getContextPath() %>/ProductServlet?style=Traditional&gender=Women">Women</a>
                </div>
            </div>

        </div>

    </div>

</div>

        <div class="left_product">

            <div class="left_box">
                <img src="<%= request.getContextPath() %>/picture/image68.png">

                <div class="text_left">
                    <h3>Upcoming Arrivals</h3>
                    <button class="product_button">Book Now</button>
                </div>

                <img src="<%= request.getContextPath() %>/picture/image69.png">

            </div>

            <div class="right_cards">
				
				<c:forEach var="p" items="${rightProducts}">

				    <div class="cards">
				        <img src="<%=request.getContextPath()%>/picture/${p.imgUrl}">
				
				        <h3>${p.name}</h3>
				        <p>Rs ${p.price}</p>
				
						<a href="<%=request.getContextPath()%>/DescriptionServlet?action=detail&id=${p.id}">
						    <button class="product_button">Explore</button>
						</a>

					</div>
				</c:forEach>
				
				</div>

        </div>

        <div class="bottom">

			<c:forEach var="p" items="${bottomProducts}">
			    <div class="cards">
			        <img src="<%=request.getContextPath()%>/picture/${p.imgUrl}">
			
			        <h3>${p.name}</h3>
			        <p>Rs ${p.price}</p>
			
						<a href="<%=request.getContextPath()%>/DescriptionServlet?id=${p.id}">
						    <button class="product_button">Explore</button>
						</a>			    
				</div>
			</c:forEach>
			
			</div>
			

    </div>
    	<%@ include file="footer.jsp" %>
    

</body>
<script>
function toggleDropdown(id) {
    const menu = document.getElementById(id);

    document.querySelectorAll('.dropdown-content').forEach(el => {
        if (el.id !== id) el.style.display = "none";
    });

    menu.style.display = menu.style.display === "block" ? "none" : "block";
}

document.addEventListener('click', function (e) {
    if (!e.target.closest('.dropdown')) {
        document.querySelectorAll('.dropdown-content').forEach(el => {
            el.style.display = "none";
        });
    }
});

document.querySelectorAll('.submenu-title').forEach(title => {
    title.addEventListener('click', function () {
        this.parentElement.classList.toggle('open');
    });
});

</script>

</html>