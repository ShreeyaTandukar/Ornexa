<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet"  href="../css/stylesheet.css">
</head>
<body>
    <div class="login-box">

        <div class="login-image">
            <img id="slider" src="image1.png" alt="jewellery">
        </div>

        <div class="login-form">
            <h2>Login</h2>

            <form>
                <div class="row">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required>
                </div>

                <div class="row">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <div class="row">
                    <div class="forgot-button">
                        <p>Forgot Password?</p>
                    </div>
                </div>

                <div class="row">
                    <button type="submit" class="login-button">Login</button>
                </div>

                <div class="row">
                    <div class="register-button">
                        <p>Don't have an account? <a href="register.jsp">Register</a></p>
                    </div>
                </div>
            </form>
        </div>
    </div>
	<script>
		const images = ["image1.png", "image2.png", "image3.png", "image4.png"];
		
		const slider = document.getElementById("slider");
		
		let index = 0;
		let direction = 1; 
		
		function changeImage() {
		    slider.src = images[index];
		
		    index += direction;
		
		    if (index === images.length - 1 || index === 0) {
		        direction = direction * -1;
		    }
		}
		
		changeImage();
		
		setInterval(changeImage, 3000);
	</script>
</body>
</html>
