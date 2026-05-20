package com.Ornexa.model;

public class stock {

    private int productId;
    private String productName;
    private double productPrice;
    private int stockQuantity;
    private String material;
    private String productDescription;
    private String imgUrl;
    private int categoryId;
    private String categoryName;
    private String stockStatus;

    public stock() { //default constructor
    	
    };
    
    //constructor
    public stock(int productId, String productName, double productPrice, int stockQuantity, String material, String productDescription,
            String imgUrl, int categoryId, String categoryName, String stockStatus) {

			   this.productId = productId;
			   this.productName = productName;
			   this.productPrice = productPrice;
			   this.stockQuantity = stockQuantity;
			   this.material = material;
			   this.productDescription = productDescription;
			   this.imgUrl = imgUrl;
			   this.categoryId = categoryId;
			   this.categoryName = categoryName;
			   this.stockStatus = stockStatus;
			}
    
    //getters and setters
    public int getProductId() { 
    	return productId; 
    }
    
    public void setProductId(int productId) { 
    	this.productId = productId; 
    }

    public String getProductName() { 
    	return productName; 
    }
    
    public void setProductName(String productName) { 
    	this.productName = productName; 
    }
    

    public double getProductPrice() { 
    	return productPrice; 
    }
    
    public void setProductPrice(double productPrice) { 
    	this.productPrice = productPrice; 
    }

    public int getStockQuantity() { 
    	return stockQuantity; 
    }
    
    public void setStockQuantity(int stockQuantity) { 
    	this.stockQuantity = stockQuantity; 
    }

    public String getMaterial() { 
    	return material; 
    }
    
    public void setMaterial(String material) { 
    	this.material = material; 
    }

    public String getProductDescription() {
    	return productDescription; 
    }
    
    public void setProductDescription(String productDescription) { 
    	this.productDescription = productDescription;
    }

    public String getImgUrl() { 
    	return imgUrl; 
    }
    
    public void setImgUrl(String imgUrl) { 
    	this.imgUrl = imgUrl;
    }

    public int getCategoryId() { 
    	return categoryId; 
    }
    
    public void setCategoryId(int categoryId) { 
    	this.categoryId = categoryId; 
    }

    public String getCategoryName() { 
    	return categoryName; 
    }
    
    public void setCategoryName(String categoryName) { 
    	this.categoryName = categoryName; 
    }

    public String getStockStatus() { 
    	return stockStatus; 
    }
    
    public void setStockStatus(String stockStatus) { 
    	this.stockStatus = stockStatus; 
    }
}