<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/description.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>
<body>
<%@ include file="Header.jsp" %>
 <div class="product-container">
 
    
    <div class="left-col">
     
      <div class="img-placeholder">
		<img src="<%=request.getContextPath()%>/picture/${product.imgUrl}" alt="${product.name}">
      </div>
    </div>
 
   
    <div class="right-col">
 
      <h1>${product.name}</h1>
      <h2 class="price">Rs.${product.price}</h2>
 
    
      <div class="stars">
        <span class="star">&#9733;</span>
        <span class="star">&#9733;</span>
        <span class="star">&#9733;</span>
        <span class="star">&#9733;</span>
        <span class="star empty">&#9733;</span>
      </div>
 
      <p class="tagline">Shop with us: where every piece tells a story</p>
 

      <p class="label">Quantity</p>
      <div class="qty-box">
        <button onclick="changeQty(-1)">−</button>
        <span id="qty">1</span>
        <button onclick="changeQty(1)">+</button>
      </div>
 
      <div class="btn-row">
        <span class="cart-icon">&#128722;</span>
        <button class="btn-dark">Add To Cart</button>
        <button class="btn-dark">Buy Now</button>
      </div>
 
      <p class="exchange"> 7 Days Exchange</p>
 
      
 
   
      <h3>Product Description</h3>
      <p>
		${product.description}
      </p>
 
      
    <br><br>
     
      <h3>Product Specification</h3>
      <p><strong>Material:</strong> ${product.material}</p>
      <p><strong>Design Style:</strong> ${product.style}</p>
      <p><strong>Weight:</strong> Substantial and heavy; traditionally worn as a pair to provide a "grounding" feel.</p>
      <p><strong>Closure:</strong> Flexible open-ended cuff design (allows for slight adjustment to fit over the ankle or wrist).</p>
 
      
    <br><br>
   
      <h3>Care Instructions</h3>
      <ul>
        <li><strong>Cleaning:</strong> Use a soft polishing cloth. Avoid harsh chemicals that may strip the natural "oxidized" patina.</li>
        <li><strong>Storage:</strong> Keep in a dry, airtight pouch to prevent tarnishing.</li>
      </ul>
 
    </div>
  </div>

  <script>
  let qty = 1;

  function changeQty(amount) {
    qty += amount;

    if (qty < 1) {
      qty = 1;
    }

    document.getElementById("qty").innerText = qty;
  }
</script>

<%@ include file="footer.jsp" %>
</body>
</html>