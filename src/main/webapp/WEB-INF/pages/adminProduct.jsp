<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Product</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/adminProduct.css">
</head>
<body>

<div class="admin-layout">
<jsp:include page="AdminSideBar.jsp"/>

    
    <!-- MAIN -->
 <main class="main-content">
        <header class="inventory-header">
            <div class="header-text">
                <h1>Product Inventory</h1>
				<p class="curating">
				    CURATING EXCELLENCE • ${fn:length(products)} ITEMS
				</p>            
			</div>
            <div class="tabs">
                <button class="tab-btn active">ALL ITEMS</button>
                <button class="tab-btn">DRAFTS</button>
                <button class="tab-btn">ARCHIVED</button>
            </div>
        </header>

      <form action="Product" method="get">

		<div class="filters-container">
		
		    <div class="filter-item">
		        <label>MATERIAL</label>
		        <select name="material" onchange="this.form.submit()">

				    <option value="" ${param.material == null || param.material == '' ? 'selected' : ''}>
				        All
				    </option>
				
				    <option value="Gold" ${param.material == 'Gold' ? 'selected' : ''}>
				        Gold
				    </option>
				
				    <option value="Silver" ${param.material == 'Silver' ? 'selected' : ''}>
				        Silver
				    </option>
				
				    <option value="Diamond" ${param.material == 'Diamond' ? 'selected' : ''}>
				        Diamond
				    </option>
				
				    <option value="Platinum" ${param.material == 'Platinum' ? 'selected' : ''}>
				        Platinum
				    </option>

				</select>
		    </div>
		
		    <div class="filter-item">
			    <label>STYLE</label>
			
			    <select name="style" onchange="this.form.submit()">
			
			        <option value="" ${param.style == null || param.style == '' ? 'selected' : ''}>
			            All
			        </option>
			
			        <option value="Casual" ${param.style == 'Casual' ? 'selected' : ''}>
			            Casual
			        </option>
			
			        <option value="Formal" ${param.style == 'Formal' ? 'selected' : ''}>
			            Formal
			        </option>
			
			    </select>
			</div>
		   <div class="filter-item">
			    <label>GENDER</label>
			
			    <select name="gender" onchange="this.form.submit()">
			
			        <option value="" ${param.gender == null || param.gender == '' ? 'selected' : ''}>
			            All
			        </option>
			
			        <option value="Male" ${param.gender == 'Male' ? 'selected' : ''}>
			            Male
			        </option>
			
			        <option value="Female" ${param.gender == 'Female' ? 'selected' : ''}>
			            Female
			        </option>
			
			    </select>
			</div>
		            <div class="filter-reset">
		
		    <a href="Product?material=&style=&gender=">
			    RESET FILTERS <i class="fas fa-redo"></i>
			</a>
			</div>
		
		</div>
		
		</form>
        

        <!-- TABLE -->
        <table class="inventory-table">

            <thead>
                <tr>
                    <th>PRODUCT DETAILS</th>
                    <th>COLLECTION</th>
                    <th>VALUATION</th>
                    <th>INVENTORY</th>
                    <th> ACTIONS</th>
                </tr>
            </thead>

            <tbody>

                <c:forEach var="p" items="${products}">

                <tr>

                    <!-- PRODUCT -->
                    <td class="product-cell">
                        <img src="<%=request.getContextPath()%>/${p.imgUrl}" width="50">
                        <span class="product-title">${p.name}</span>
                    </td>

                    <!-- COLLECTION -->
                    <td>
                        <span class="badge">${p.style}</span>
                    </td>

                    <!-- PRICE -->
                    <td class="price-cell">
                        Rs. ${p.price}
                    </td>

                    <!-- STOCK -->
                    <td>
                        ${p.stockQuantity} units
                    </td>

                    <!-- ACTIONS -->
                    <td class="action-cell">
                    

                        <!-- EDIT -->
                        <button class="icon-btn"
                            onclick="openEditModal(
                                '${p.id}',
                                '${fn:escapeXml(p.name)}',
                                '${p.price}',
                                '${p.stockQuantity}',
                                '${fn:escapeXml(p.material)}',
                                '${fn:escapeXml(p.gender)}',
                                '${fn:escapeXml(p.style)}',
                                '${fn:escapeXml(p.description)}',
                                '${fn:escapeXml(p.imgUrl)}',
                                '${p.categoryId}'
                            )">
                            <i class="fas fa-pen"></i>
                        </button>

                        <!-- DELETE -->
                       <form action="Product" method="post" style="display:inline;">
						    <input type="hidden" name="action" value="delete">
						    <input type="hidden" name="id" value="${p.id}">
						    <button type="submit" class="icon-btn"><i class="fas fa-trash-alt"></i></button>
						</form>

                    </td>

                </tr>

                </c:forEach>

            </tbody>

        </table>

    </main>

</div>


<!-- ================= EDIT MODAL ================= -->
<div id="editModal" class="modal">
    <div class="modal-content">

        <span onclick="closeEditModal()">&times;</span>

        <h2>Edit Product</h2>
      <p style="color:red;">
		    <%= request.getAttribute("errorMsg") != null
		        ? request.getAttribute("errorMsg")
		        : "" %>
		</p>

        <form action="Product" method="post">

            <input type="hidden" name="action" value="update">
            <input type="hidden" id="editId" name="id">

            <input type="text" id="editName" name="name"  placeholder="Name">
            <input type="number" id="editPrice" name="price" placeholder="Price">
            <input type="number" id="editStock" name="stockQuantity" placeholder="Stock">
            <input type="text" id="editMaterial" name="material" placeholder="Material">
            <input type="text" id="editGender" name="gender" placeholder="Gender">
            <input type="text" id="editStyle" name="style" placeholder="Style">
            <input type="text" id="editDesc" name="description" placeholder="Description" >
            <input type="text" id="editImg" name="imgUrl" placeholder="Image URL">
            <input type="number" id="editCat" name="categoryId" placeholder="Category ID">
            

            <button type="submit">UPDATE</button>

        </form>

    </div>
</div>

<!-- ================= JS ================= -->
<script>



function openEditModal(id, name, price, stock, material, gender, style, desc, img, cat) {

    document.getElementById("editModal").style.display = "block";

    document.getElementById("editId").value = id;
    document.getElementById("editName").value = name;
    document.getElementById("editPrice").value = price;
    document.getElementById("editStock").value = stock;
    document.getElementById("editMaterial").value = material;
    document.getElementById("editGender").value = gender;
    document.getElementById("editStyle").value = style;
    document.getElementById("editDesc").value = desc;
    document.getElementById("editImg").value = img;
    document.getElementById("editCat").value = cat;
}

function closeEditModal() {
    document.getElementById("editModal").style.display = "none";
}
window.onload = function () {

    <% if(request.getAttribute("openAddModal") != null) { %>
        openAddModal();
    <% } %>

    <% if(request.getAttribute("openEditModal") != null) { %>
        document.getElementById("editModal").style.display = "block";
    <% } %>

};

</script>

<%@ include file="footer.jsp" %>

</body>
</html>