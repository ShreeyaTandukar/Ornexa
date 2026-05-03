package com.Ornexa.utils;

public class ValidationUtils {
   
    public static String validationRegister(String firstName, String lastName, String username, 
                                          String email, String phoneNum, String dob, String password) {
        
        if (firstName == null || firstName.isEmpty() ||
            lastName == null || lastName.isEmpty() ||
            username == null || username.isEmpty() ||
            email == null || email.isEmpty() ||
            phoneNum == null || phoneNum.isEmpty() ||
            password == null || password.isEmpty()) {
            return "Please, fill all fields.";
        }

        if (!email.contains("@") || !email.contains(".")) {
            return "Invalid format of email.";
        }

        if (phoneNum.length() != 10) { // Changed to != 10 since phone numbers are exactly 10 digits
            return "The length of phone number must be exactly 10 digits.";
        }

        if (password.length() < 8) {
            return "Password must have at least 8 characters.";
        }

        if (!(password.contains("@") || password.contains("#") || 
              password.contains("$") || password.contains("%") || 
              password.contains("*") || password.contains("!"))) {
            return "Password must contain at least one special character (@, #, $, %, *, !).";
        }

     
        return null; 
    }
    public static String validateStock(String qty) {

        // empty check
        if (qty == null || qty.trim().isEmpty()) {
            return "Please enter quantity.";
        }

        // integer check
        try {
            int quantity = Integer.parseInt(qty);

            if (quantity <= 0) {
                return "Quantity must be greater than 0.";
            }

        } catch (NumberFormatException e) {
            return "Quantity must be a valid integer.";
        }

        return null;
    }
    
    public static String validateProduct(String name,
            String price,
            String stock,
            String categoryId) {
					
					if (name == null || name.trim().isEmpty() ||
					price == null || price.trim().isEmpty() ||
					stock == null || stock.trim().isEmpty()) {
					
					return "Please fill all required fields.";
					}
					
					try {
					
					double p = Double.parseDouble(price);
					
					if (p <= 0) {
					return "Price must be greater than 0.";
					}
					
					} catch (Exception e) {
					return "Invalid price.";
					}
					
					try {
					
					int s = Integer.parseInt(stock);
					
					if (s < 0) {
					return "Stock cannot be negative.";
					}
					
					} 
					catch (Exception e) {
						return "Stock must be integer.";
					}
					
					if (categoryId != null && !categoryId.trim().isEmpty()) {
					
					try {
					
					int cat = Integer.parseInt(categoryId);
					
					if (cat <= 0) {
					return "Invalid category.";
					}
					
					} catch (Exception e) {
					return "Category ID must be integer.";
					}
					}
					
					return null;
					}
					   
}