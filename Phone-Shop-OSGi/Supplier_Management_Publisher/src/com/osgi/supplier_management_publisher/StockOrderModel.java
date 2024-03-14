package com.osgi.supplier_management_publisher;

public class StockOrderModel {

	private int stock_order_id;
	private String noOfStocks;
	private String productName;
	private String supplierCompany;
	

	public StockOrderModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockOrderModel(int stock_order_id, String noOfStocks, String productName, String supplierCompany) {
		super();
		this.stock_order_id = stock_order_id;
		this.noOfStocks = noOfStocks;
		this.productName = productName;
		this.supplierCompany = supplierCompany;
	}

	public int getStock_order_id() {
		return stock_order_id;
	}

	public void setStock_order_id(int stock_order_id) {
		this.stock_order_id = stock_order_id;
	}

	public String getNoOfStocks() {
		return noOfStocks;
	}

	public void setNoOfStocks(String noOfStocks) {
		this.noOfStocks = noOfStocks;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSupplierCompany() {
		return supplierCompany;
	}

	public void setSupplierCompany(String supplierCompany) {
		this.supplierCompany = supplierCompany;
	}
	
	
}
