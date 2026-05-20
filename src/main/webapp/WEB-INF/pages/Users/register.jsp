<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/register.css">

</head>
<body>

<!-- Background Video -->
<video autoplay muted loop id="bg-video">
    <source src="<%= request.getContextPath() %>/videos/background2.mp4" type="video/mp4">
</video>

<div class="overlay"></div>

<main class="container">

    <!-- LEFT TEXT -->
    <section class="welcome-text">
        <h1>Welcome</h1>
        <p>Experience the new standard of design</p>
    </section>

    <!-- FORM -->
    <section class="form-container">
        <div class="form-card">

            <h2>Create Your Account</h2>

            <!-- ERROR MESSAGE -->
            <p style="color:red;">
                <%= request.getAttribute("validating") != null ? request.getAttribute("validating") : "" %>
            </p>

            <p class="subtitle">Enter your details to get started</p>

            <form action="registerServlet" method="post" enctype="multipart/form-data">

                <!-- PROFILE SVG UPLOAD -->
                <div class="profile-upload">

                    <!-- FIX: use display:none instead of hidden -->
                    <input type="file" id="image" name="image" accept="image/*"  style="display:none;">

                    <!-- SVG clickable -->
                    <label for="image" class="circle">
                        <svg width="40" height="40" viewBox="0 0 24 24" fill="none"
                             stroke="currentColor" stroke-width="2"
                             stroke-linecap="round" stroke-linejoin="round">
                            <path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"></path>
                            <circle cx="12" cy="13" r="4"></circle>
                        </svg>
                    </label>

                    <p class="profile-label">PROFILE PORTRAIT</p>
                </div>

                <div class="input-grid">

                    <div class="field">
                        <label for="firstName">FIRST NAME</label>
                        <input type="text" id="firstName" name="firstName">
                    </div>

                    <div class="field">
                        <label for="lastName">LAST NAME</label>
                        <input type="text" id="lastName" name="lastName" >
                    </div>

                    <div class="field">
                        <label for="username">USERNAME</label>
                        <input type="text" id="username" name="username" >
                    </div>

                    <div class="field">
                        <label for="email">EMAIL ADDRESS</label>
                        <input type="text" id="email" name="email" >
                    </div>

                    <div class="field">
                        <label for="phoneNumber">PHONE NUMBER</label>
                        <input type="tel" id="phoneNumber" name="phoneNumber"  >
                    </div>

                    <div class="field">
                        <label for="dob">DATE OF BIRTH</label>
                        <input type="date" id="dob" name="dob">
                    </div>

                </div>

                <div class="field full-width">
                    <label for="password">PASSWORD</label>
                    <input type="password" id="password" name="password" >
                </div>

                <button type="submit" class="btn-submit">Create Account</button>

            </form>

            <p class="login-footer">
                Already have an account? <a href="loginServlet">Login</a>
            </p>

        </div>
    </section>

</main>

</body>
</html>
