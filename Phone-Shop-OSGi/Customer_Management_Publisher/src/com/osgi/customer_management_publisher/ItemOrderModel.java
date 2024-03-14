package com.osgi.customer_management_publisher;

public class ItemOrderModel {

	private int phone_order_id;
	private int item_id;
	private String itemName;
	private String deliveryaddress;
	private String price;
	private boolean isActive;
	
	public ItemOrderModel() {
		super();
	}

	public ItemOrderModel(int phone_order_id, int item_id, String itemName, String deliveryaddress, String price,
			boolean isActive) {
		super();
		this.phone_order_id = phone_order_id;
		this.item_id = item_id;
		this.itemName = itemName;
		this.deliveryaddress = deliveryaddress;
		this.price = price;
		this.isActive = isActive;
	}

	public int getPhone_order_id() {
		return phone_order_id;
	}

	public void setPhone_order_id(int phone_order_id) {
		this.phone_order_id = phone_order_id;
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

	public String getDeliveryaddress() {
		return deliveryaddress;
	}

	public void setDeliveryaddress(String deliveryaddress) {
		this.deliveryaddress = deliveryaddress;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
	
	
}
