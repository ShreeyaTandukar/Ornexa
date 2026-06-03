<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page isELIgnored="false" %>
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

        <main>
        <h1>Contact Us</h1>

        <section class="connect-section">
            <h2>Let’s Connect</h2>
            <p>Whether you’re drawn to timeless cultural designs or sleek modern styles, Ornexa is here to help you find the perfect piece.</p>
            <p>If you have any questions about our jewelry, need help with an order, or want more details about a product, simply fill out the contact form below.</p>
            <p>Looking for something special? You can order your favorite Ornexa designs, crafted with a blend of heritage inspiration and modern craftsmanship to suit every style and occasion.</p>
            <p><strong>Every piece tells a story — let us help you find yours.</strong></p>
        </section>

        <!-- Side by Side Container -->
        <div class="contact-main">
            <!-- Get In Touch -->
            <section class="info-section">
                <h2>Get In Touch</h2>
                <div class="info-grid">
                    <div class="info-card">
                        <i class="fas fa-envelope"></i>
                        <h3>Email Us</h3>
                        <a href="mailto:hello@ornexa.com">hello@ornexa.com</a>
                    </div>
                    <div class="info-card">
                        <i class="fas fa-phone"></i>
                        <h3>Call Us</h3>
                        <a href="tel:+9779841234567">+977 984-1234567</a>
                    </div>
                    <div class="info-card">
                        <i class="fas fa-clock"></i>
                        <h3>Business Hours</h3>
                        <p>Sunday - Friday<br>10:00 AM - 6:00 PM</p>
                    </div>
                    <div class="info-card">
                        <i class="fas fa-map-marker-alt"></i>
                        <h3>Visit Us</h3>
                        <p>Kathmandu, Nepal</p>
                    </div>
                </div>
            </section>

            <!-- Contact Form -->
            <div class="form-container">
                <form action="ContactServlet" method="post" id="contactForm">
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" id="name" name="name" placeholder="Your full name" required>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="contact">Contact Number:</label>
                            <input type="text" id="contact" name="contact" placeholder="+977 98XXXXXXXX" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" placeholder="your@email.com" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="comment">Comment:</label>
                        <textarea id="comment" name="comment" rows="7" placeholder="Write your message here..." required></textarea>
                    </div>

                    <button type="submit">
                        <i class="fas fa-paper-plane"></i> Submit Message
                    </button>
                </form>
            </div>
        </div>
    </main>
    <!-- Include Footer -->
    <jsp:include page="footer.jsp" />

</body>
</html>
    