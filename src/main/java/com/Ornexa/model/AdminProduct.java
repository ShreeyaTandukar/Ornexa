package com.Ornexa.model;

public class AdminProduct {

    private int id;
    private String name;
    private double price;
    private int stockQuantity;
    private String material;
    private String gender;
    private String description;
    private String imgUrl;
    private int categoryId;
    private String style;

   
    public AdminProduct() {  //default constructor
    }  

   
    public AdminProduct(int id, String name, double price, int stockQuantity,  //constructor
                   String material, String gender, String description,
                   String imgUrl, int categoryId, String style) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.material = material;
        this.gender = gender;
        this.description = description;
        this.imgUrl = imgUrl;
        this.categoryId = categoryId;
        this.style=style;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getMaterial() {
        return material;
    }

    public String getGender() {
        return gender;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getCategoryId() {
        return categoryId;
    }
	public String getStyle() {
		return style;
	}
	
	
    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


	public void setStyle(String style) {
		this.style = style;
		
	}
}


