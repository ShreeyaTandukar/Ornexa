<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
 <link rel="stylesheet"  href="../css/style.css">
</head>
<body>
    <div class="register-box">

        <div class="register-image">
            <img id="slider" src="image1.png" alt="jewellery">
        </div>

        <div class="register-form">
            <h2>Create Your Account</h2>
            <p>Enter your details to get started</p>

            <form action="${pageContext.request.contextPath }/register" method="post" enctype="multipart/form-data">
                <div class="row">
				    <div class="col">
					    <label for="firstName">First Name</label> 
                        <input type="text" id="firstName" name="firstName" required>
				    </div>
				    <div class="col">
					    <label for="lastName">Last Name</label> 
                        <input type="text" id="lastName" name="lastName" required>
				    </div>
			    </div>
			    <div class="row">
				    <div class="col">
					    <label for="username">Username</label> 
                        <input type="text" id="username" name="username" required>
				    </div>
				    <div class="col">
					    <label for="email">Email</label> 
                        <input type="email" id="email" name="email" required>
				    </div>
			    </div>
			    <div class="row">
				    <div class="col">
					    <label for="phoneNumber">Phone Number</label> 
                        <input type="tel" id="phoneNumber" name="phoneNumber" required>
				    </div>
				    <div class="col">
					    <label for="dob">Date of Birth</label> 
                        <input type="date" id="dob" name="dob" required>
				    </div>
			    </div>
			    <div class="row">
				    <div class="col">
					    <label for="password">Password</label> 
                        <input type="password" id="password" name="password" required>
				    </div>
			    </div>
                <div class="row">
                    <button type="submit" class="create">Create Account</button>
                </div>
                

                <div class="login-button">
                    <p>Already have an account? <a href="login.jsp">Login</a></p>
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
