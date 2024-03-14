package com.osgi.item_management_publisher;

public class ItemModel {
	
	private int item_id;
	private String itemName;
	private String brand;
	private String unitPrice;
	private String quantity;
	
	public ItemModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemModel(int item_id, String itemName, String brand, String unitPrice, String quantity) {
		super();
		this.item_id = item_id;
		this.itemName = itemName;
		this.brand = brand;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	

}
