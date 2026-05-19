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
<jsp:include page="AdminSideBar.jsp" />

    
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
		        <select name="filtermaterial" onchange="this.form.submit()">

				    <option value="" ${param.filtermaterial == null || param.filtermaterial == '' ? 'selected' : ''}>
				        All
				    </option>
				
				    <option value="Gold" ${param.filtermaterial == 'Gold' ? 'selected' : ''}>
				        Gold
				    </option>
				
				    <option value="Silver" ${param.filtermaterial == 'Silver' ? 'selected' : ''}>
				        Silver
				    </option>
				
				    <option value="Diamond" ${param.filtermaterial == 'Diamond' ? 'selected' : ''}>
				        Diamond
				    </option>
				
				    <option value="Platinum" ${param.filtermaterial == 'Platinum' ? 'selected' : ''}>
				        Platinum
				    </option>

				</select>
		    </div>
		
		    <div class="filter-item">
			    <label>STYLE</label>
			
			    <select name="filterstyle" onchange="this.form.submit()">
			
			        <option value="" ${param.filterstyle == null || param.filterstyle == '' ? 'selected' : ''}>
			            All
			        </option>
			
			        <option value="Traditional" ${param.filterstyle == 'Traditional' ? 'selected' : ''}>
			            Traditional
			        </option>
			
			        <option value="Modern" ${param.filterstyle == 'Modern' ? 'selected' : ''}>
			            Modern
			        </option>
			
			    </select>
			</div>
		   <div class="filter-item">
			    <label>GENDER</label>
			
			    <select name="filtergender" onchange="this.form.submit()">
			
			        <option value="" ${param.filtergender == null || param.filtergender == '' ? 'selected' : ''}>
			            All
			        </option>
			
			        <option value="Men" ${param.filtergender == 'Men' ? 'selected' : ''}>
			            Men
			        </option>
			
			        <option value="Women" ${param.filtergender == 'Women' ? 'selected' : ''}>
			            Women
			        </option>
			
			    </select>
			</div>
		            <div class="filter-reset">
		
		    <a href="Product?filtermaterial=&filterstyle=&filtergender=">
			    RESET FILTERS <i class="fas fa-redo"></i>
			</a>
			</div>
		
		</div>
		
		</form>
        

      
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

                  
                    <td class="product-cell">
						<img src="<%=request.getContextPath()%>/picture/${p.imgUrl}" width="50">
                        <span class="product-title">${p.name}</span>
                    </td>

                    <td>
                        <span class="badge">${p.style}</span>
                    </td>

                 
                    <td class="price-cell">
                        Rs. ${p.price}
                    </td>

                 
                    <td>
                        ${p.stockQuantity} units
                    </td>

                    <td class="action-cell">
                    

                        <button  type="button"
                        class="icon-btn"
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

 <!--Pop up-->

<div id="editModal" class="modal ${not empty openEditModal ? 'show' : ''}">

    <div class="modal-content">

      
        <span class="close" onclick="closeEditModal()">&times;</span>

        <h2>Edit Product</h2>

        <c:if test="${not empty errorMsg}">
            <p style="color:red;">${errorMsg}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/Product" method="post">

            <input type="hidden" name="action" value="update">

            <input type="hidden" id="editId" name="id">

            <input type="text" id="editName" name="name" placeholder="Name">
            <input type="number" id="editPrice" name="price" placeholder="Price">
            <input type="number" id="editStock" name="stockQuantity" placeholder="Stock">

        
            <select name="material" id="editMaterial">
                <option value="">Select Material</option>
                <option value="Gold">Gold</option>
                <option value="Silver">Silver</option>
                <option value="Diamond">Diamond</option>
            </select>

            <select name="gender" id="editGender">
                <option value="">Select Gender</option>
                <option value="Men">Men</option>
                <option value="Women">Women</option>
            </select>

            <select name="style" id="editStyle">
                <option value="">Select Style</option>
                <option value="Traditional">Traditional</option>
                <option value="Modern">Modern</option>
            </select>


            <input type="text" id="editDesc" name="description" placeholder="Description">

            <select name="categoryId" id="editCat">
                <option value="">Select Category</option>
                <option value="1">Necklace</option>
                <option value="2">Earring</option>
                <option value="3">Ring</option>
                <option value="4">Bracelet</option>
            </select>

            <button type="submit">UPDATE</button>

        </form>

    </div>
</div>

 <!--java script-->
<script>


function closeEditModal() {
    document.getElementById("editModal").classList.remove("show");
    document.body.classList.remove("modal-open");
}

function openEditModal(id, name, price, stock, material, gender, style, desc,imgUrl, cat) {

    document.getElementById("editModal").classList.add("show");
    document.body.classList.add("modal-open");

    document.getElementById("editId").value = id;
    document.getElementById("editName").value = name;
    document.getElementById("editPrice").value = price;
    document.getElementById("editStock").value = stock;
    document.getElementById("editMaterial").value = material;
    document.getElementById("editGender").value = gender;
    document.getElementById("editStyle").value = style;
    document.getElementById("editDesc").value = desc;
    document.getElementById("editCat").value = String(cat);}

</script>

<c:if test="${not empty openEditModal}">
<script>
document.addEventListener("DOMContentLoaded", function () {

    document.getElementById("editModal").classList.add("show");
    document.body.classList.add("modal-open");

    document.getElementById("editId").value = "${id}";
    document.getElementById("editName").value = "${name}";
    document.getElementById("editPrice").value = "${price}";
    document.getElementById("editStock").value = "${stockQuantity}";
    document.getElementById("editMaterial").value = "${material}";
    document.getElementById("editGender").value = "${gender}";
    document.getElementById("editStyle").value = "${style}";
    document.getElementById("editDesc").value = "${description}";
    document.getElementById("editCat").value ="${categoryId}";
    });
</script>
</c:if>

<jsp:include page="footer.jsp" />

</body>
</html>