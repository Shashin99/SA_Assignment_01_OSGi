package com.osgi.customer_management_publisher;

import com.osgi.phoneshopdb.DatabaseConnector;
import com.osgi.phoneshopdb.DatabaseConnectorImpl;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class CustomerImpl implements CustomerService{

	private Connection connection = null; 
	private Statement statement;
	private ResultSet  resultSet;
	private DatabaseConnector dbConnection;
	
	private static PreparedStatement preparedStatement = null;
	
	Scanner sc = new Scanner(System.in);
	
	ItemOrderModel order = new ItemOrderModel();

	public CustomerImpl() {
		super();
		this.dbConnection = new DatabaseConnectorImpl();
		this.connection = dbConnection.getDatabaseConnection();
	}

	@Override
	public void addNewCustomer() {
		
		Scanner sc = new Scanner(System.in);

		CustomerModel customerModel = new CustomerModel();

		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************");
		System.out.println("*********************<<<<<<<<<< Customer Signup >>>>>>>>>>>*****************");
		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************\n\n");

		System.out.println("Enter Your First Name ::");
		customerModel.setFirstName(sc.nextLine().trim());

		System.out.println("Enter Your Last Name ::");
		customerModel.setLastName(sc.nextLine().trim());

		System.out.println("Enter Your Email ::");
		customerModel.setEmail(sc.nextLine().trim());

		System.out.println("Enter Your Phone Number ::");
		customerModel.setPhone(sc.nextLine().trim());

		System.out.println("Enter Your Password ::");
		customerModel.setPassword(sc.nextLine().trim());

		try {
			String query = "insert into user(firstName,lastName,email,phone,password,isActive) values(?,?,?,?,?,'1') ";

			preparedStatement = connection.prepareStatement(query); 

			preparedStatement.setString(1,customerModel.getFirstName());
			preparedStatement.setString(2,customerModel.getLastName());
			preparedStatement.setString(3,customerModel.getEmail());
			preparedStatement.setString(4,customerModel.getPhone());
			preparedStatement.setString(5,customerModel.getPassword());

			int isSuccess = preparedStatement.executeUpdate();

			if(isSuccess > 0) {
				System.out.println("User Signup  Has Been Created Successfully");
			}else {
				System.out.println("Error Has Been Occured Please Try Again");
			}

		}catch(Exception ex) {
			System.out.println("customerSaveError : " + ex.getMessage());
		}
	}

	@Override
	public void viewAllCustomers() {
		
		try {
			
			String query = "SELECT u_id, firstName, lastName, email, phone FROM user WHERE isActive = 1";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			System.out.println("****************************************************************************");
			System.out.println("****************************************************************************");
			System.out.println("*********************<<<<<<<<<< Customer Details >>>>>>>>>>>****************");
			System.out.println("****************************************************************************");
			System.out.println("*****************************************************************************\n\n");
			System.out.println
			(
					String.format
					(
							"%20s %20s %20s %20s %20s\n", 
							"User Id", "First Name", "Last Name","Email", "Phone Number"
					)
			);
			
           System.out.println("--------------------------------------------------------------------------------------------------------");
			
			
			while(resultSet.next()) {
				
				System.out.printf
				(
						"%20d %20s %20s %20s %20s\n", 
						resultSet.getInt("u_id"),
						resultSet.getString("firstName"),
						resultSet.getString("lastName"),
						resultSet.getString("email"),
						resultSet.getString("phone")
						
						
				);
				
				System.out.println("--------------------------------------------------------------------------------------------------------");
			}
			
			
			
		}catch(Exception ex) {
			
			System.out.println("getAllCustomerDetailsException:" + ex.getMessage());
			
		}

		
	}

	@Override
	public void viewAllItems() {
		
		try {
			
			String query = "SELECT item_id, itemName, brand, unitPrice FROM item WHERE isActive =1";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			System.out.println("\n*****************************************************************************************************");
			System.out.println("*******************************************************************************************************");
			System.out.println("************************************ Welcome Best Mobile Store **************************************");
			System.out.println("*******************************************************************************************************");
			System.out.println("*******************************************************************************************************");
			
			System.out.println
			(
					String.format
					(
							"%20s %20s %20s %20s\n", 
							"item Id", "Item Name", "Brand", "Price"  
					)
			);
			
			System.out.println("--------------------------------------------------------------------------------------------------------");
			
			while(resultSet.next()) {
				
				System.out.printf
				(
						"%20d %20s %20s %20s\n", 
						resultSet.getInt("item_id"),
						resultSet.getString("itemName"),
						resultSet.getString("brand"),
						resultSet.getString("unitPrice")	
				);
				
				System.out.println("--------------------------------------------------------------------------------------------------------");
			}

		}catch(Exception ex) {
			System.out.println("viewAllItemsException:" + ex.getMessage());
		}
		
	}

	@Override
	public void orderItem() {
		int item_id;
		
		System.out.println("***************************** Place Your  Order Here ***********************************");
		
		System.out.println("Enter Your Delivery Address Here:");
 		order.setDeliveryaddress(sc.nextLine());
		System.out.println("Please Enter Item ID Here: ");
		item_id = (sc.nextInt());

		
		System.out.println("\n Processing ..................................\n\n");
		
             try {
			
			String query = "SELECT item_id,itemName,brand,unitPrice  FROM item WHERE item_id = '"+ item_id+"' && isActive = 1";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			System.out.println("======================Your Order Is Ready==========================");
			
			while (resultSet.next()) {  
				
		    	  System.out.println("Item ID :" + resultSet.getInt("item_id"));
		    	  System.out.println("Item Name :" + resultSet.getString("itemName"));
		    	  System.out.println("Brand :" + resultSet.getString("brand"));
		    	  System.out.println("Price :" + resultSet.getString("unitPrice"));
		    	  
		    	 
		    	 
		    	  order.setPrice(resultSet.getString("unitPrice"));
		    	  order.setItemName(resultSet.getString("itemName"));
		    	  order.setItem_id(resultSet.getInt("item_id"));
		    	  
		    	 
		      }	
			System.out.println("==================================================================\n\n");
		}catch(Exception ex) {
			
			System.out.println("orderItem:" + ex.getMessage());
			
		}
            setOrder();
		
	}

	private void setOrder() {
		String query = "insert into phoneoders(phone_order_id,itemName,deliveryaddress,price,isActive)values(?,?,?,?,'1')";
        try {
			
			preparedStatement = connection.prepareStatement(query); 
			
			preparedStatement.setInt(1,order.getPhone_order_id());
			preparedStatement.setString(2,order.getItemName());
			preparedStatement.setString(3,order.getDeliveryaddress());
			preparedStatement.setString(4,order.getPrice());
			
			int isSuccess = preparedStatement.executeUpdate();
			
			if(isSuccess > 0) {
				System.out.println("Your Order Has Been Successfully Thank You!! ");
			}else {
				System.out.println("Error Has Been Occured Please Try Again");
			}
			
		}catch (Exception ex) {
			System.out.println("order : " + ex.getMessage());
		}
	}

	@Override
	public void removeCustomer() {
		 Integer u_id;
			
			System.out.print("\nPlease Enter Customer ID : ");
			u_id = (sc.nextInt());
			
			try {
				String query = "UPDATE user SET isActive = 0 WHERE u_id = ?";
				
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,u_id);
				
				int isSuccess = preparedStatement.executeUpdate();
				
				if(isSuccess > 0) {
					System.out.println("Delete Customer Has Been Successfully...");
				}else {
					System.out.println("Cannot Find The Customer.. Try Again..");
				}
				
			}catch (Exception ex) {
				System.out.println("userDeleteException : " + ex.getMessage());
			}
	   	 
	   	 
		
	}

	@Override
	public void getOrderReport() {
		try {
			
			String query = "SELECT phone_order_id, itemid, itemName ,deliveryaddress, price FROM phoneorders";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			File directory = new File("D:\\SLIIT - Projects\\SA Assignment 01");
			
			directory.mkdirs();
			
			File file = new File(directory,"OrderItemReport.txt");
			FileWriter fileWriter = new FileWriter(file);
			
			fileWriter.write(String.format("++++++++++++++++++++++++++++++++++++++++++++++++++++++ Order Report +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n"));
			fileWriter.write(
					
					String.format
					(
							"%30s %30s %30s %30s %30s\n", 
							"Order Id","Item Id", "Item Name", "Address","Price list"
					)
			);
			
			fileWriter.write(String.format("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n"));
			
			while(resultSet.next()) {
				
				fileWriter.write(
						
						String.format(
								"%30d %30d %30s %30s %30s \n", 
								resultSet.getInt("phone_order_id"),
								resultSet.getInt("item_id"),
								resultSet.getString("itemName"),
								resultSet.getString("deliveryaddress"),
								resultSet.getString("price")
						)
				);
				
				fileWriter.write(String.format("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"));
			}
			
			fileWriter.flush();
			fileWriter.close();
			
			System.out.println("Report Genaration Has Been Successfully");

		}catch (Exception ex) {
			
			System.out.println("orderItemDetailsReportException:" + ex.getMessage());

		}
	}
}
