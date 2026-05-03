<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact Us - Ornexa</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/c.css" />
</head>
<body>

    <!-- Include Header -->
    <jsp:include page="Header.jsp" />

    <!-- Main Content -->
    <div class="main">
        <h1>Let’s Connect</h1>
        <p>Whether you’re drawn to timeless cultural designs or sleek modern styles, Ornexa is here to help you find the perfect piece.</p>
        <p>If you have any questions about our jewelry, need help with an order, or want more details about a product, simply fill out the contact form below.</p>
        <p>Looking for something special? You can order your favorite Ornexa designs, crafted with a blend of heritage inspiration and modern craftsmanship to suit every style and occasion.</p>
        <h4>Every piece tells a story – let us help you find yours.</h4>

        <form action="submitContact.jsp" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="contact">Contact Number:</label>
            <input type="text" id="contact" name="contact" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="comment">Comment:</label>
            <textarea id="comment" name="comment" rows="5" placeholder="Write here..." required></textarea>

            <button type="submit">Submit</button>
        </form>
    </div>

    <!-- Include Footer -->
    <jsp:include page="footer.jsp" />

</body>
</html>
    