package com.Ornexa.model;

public class CartItem {
	
	private int cartItemId;
	private String itemName;
	private double priceAtTime;
	private int productQuantity;
	private double subTotal;
	private int cartId;
	private String imgUrl;

	// Default Constructor
	public CartItem() {}

	// Parameterized Constructor
	public CartItem(int cartItemId, String itemName, double priceAtTime,
			int productQuantity, double subTotal, int cartId, String imgUrl) {
		this.cartItemId = cartItemId;
		this.itemName = itemName;
		this.priceAtTime = priceAtTime;
		this.productQuantity = productQuantity;
		this.subTotal = subTotal;
		this.cartId = cartId;
		this.imgUrl = imgUrl;
	}

	public int getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPriceAtTime() {
		return priceAtTime;
	}
	public void setPriceAtTime(double priceAtTime) {
		this.priceAtTime = priceAtTime;
	}

	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}