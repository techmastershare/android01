package com.quysnhat.producthorizontallistview.models;

public class Product {
	public class Category {
		public static final int UNKNOWN = -1;
		public static final int LAPTOP = 0;
		public static final int PHONE = 1;
		public static final int TELEVISION = 2;
	}
	
	private String name = "";
	private String description = "";
	private int price = 0;
	private int imageId = -1;
	private int category = Category.UNKNOWN;
	
	public Product(final String _name, final String _description, int _price, int _imageId, int _category){
		name = _name;
		description = _description;
		price = _price;
		imageId = _imageId;
		category = _category;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getPrice(){
		return price;
	}
	
	public void setPrice(int price){
		this.price = price;
	}


	public int getImageId() {
		return imageId;
	}


	public void setImageId(int imageId) {
		this.imageId = imageId;
	}


	public int getCategory() {
		return category;
	}


	public void getCategory(int _category) {
		this.category = _category;
	}


	public String getCategoryString() {
		switch (category) {
		case Category.LAPTOP:
			return "Laptop";
		case Category.PHONE:
			return "Phone";
		case Category.TELEVISION:
			return "Television";
		default:
			return "Unknown";
		}
	}
	
	public String getPriceString(){
		return String.valueOf(price) + " $ ";
	}

}
