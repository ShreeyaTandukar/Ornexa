<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Product - Jewelry Store</title>
    
    
</head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/addProduct.css" />
<body>
    
	<div class="admin-layout">

    <jsp:include page="AdminSideBar.jsp"/>
	

    <!-- Main Content -->
    <div class="main-content">
        <header>
            <h1>Add New Product</h1>
            <p>Fill in the details to add a new jewelry item</p>
        </header>

        	        <section class="add-product">
            <h2>Add New Product</h2>
				<form action="<%=request.getContextPath()%>/AddProductServlet" method="post" enctype="multipart/form-data">
                <label for="pname">Product Name</label>
                <input type="text" id="pname" name="pname" required>

                <label for="price">Price</label>
                <input type="text" id="price" name="price" required>

                <label for="image">Image</label>
                <input type="file" id="image" name="image" accept="image/*">

                <button type="submit">Add Product</button>
            </form>
        </section>
    </div>
    </div>
</body>
</html>
