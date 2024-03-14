package com.osgi.item_management_publisher;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.osgi.phoneshopdb.DatabaseConnector;
import com.osgi.phoneshopdb.DatabaseConnectorImpl;

public class ItemImpl implements ItemService{

	
	private Connection connection = null; 
	private Statement statement;
	private ResultSet  resultSet;
	private DatabaseConnector dbConnection;
	
	private static PreparedStatement preparedStatement = null;
	
	Scanner sc = new Scanner(System.in);
	
	public ItemImpl() {
		super();
		this.dbConnection = new DatabaseConnectorImpl();
		this.connection = dbConnection.getDatabaseConnection();
	}

	@Override
	public void addNewItem() {
		Scanner sc = new Scanner(System.in);
		
        
		ItemModel itemModel = new ItemModel();

		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************");
		System.out.println("******************<<<<<<<<<< Item Register Console >>>>>>>>>>>**************");
		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************\n\n");


		System.out.println("Enter Your Item Name ::");
		itemModel.setItemName(sc.nextLine().trim());

		System.out.println("Enter Your Item Brand Name ::");
		itemModel.setBrand(sc.nextLine().trim());

		System.out.println("Enter Your Unit Price ::");
		itemModel.setUnitPrice(sc.nextLine().trim());

		System.out.println("Enter Your Quantity ::");
		itemModel.setQuantity(sc.nextLine().trim());

		try {

			String query = "insert into item(itemName,brand,unitPrice,quantity,isActive) values(?,?,?,?,'1') ";

			preparedStatement = connection.prepareStatement(query); 

			preparedStatement.setString(1,itemModel.getItemName());
			preparedStatement.setString(2,itemModel.getBrand());
			preparedStatement.setString(3,itemModel.getUnitPrice());
			preparedStatement.setString(4,itemModel.getQuantity());

			int isSuccess = preparedStatement.executeUpdate();

			if(isSuccess > 0) {

				System.out.println("Item  Successfully Added");

			}else {
				System.out.println("Error Has Been Occured Please Try Again");

			}

		}catch(Exception ex) {
			System.out.println("itemSaveError : " + ex.getMessage());
		}
		
	}

	@Override
	public void deleteItem() {
		Integer item_id;
		
		System.out.print("\nPlease Enter Item ID : ");
		item_id = (sc.nextInt());
		
		try {
			
			String query = "UPDATE item SET isActive = 0 WHERE item_id = ?";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, item_id);
			int isSuccess = preparedStatement.executeUpdate();
			
			if(isSuccess > 0) {
				
				System.out.println("Item Has Been Deleted Successfully...");
			}else {	
				System.out.println("Cannot Find The Item... Try Again...");	
			}
			
		}catch (Exception ex) {
			System.out.println("itemDeleteException : " + ex.getMessage());
		}
	}

	@Override
	public void getAllItemDetails() {
		
		try {
			
            String query = "SELECT item_id,itemName,brand,unitPrice,quantity FROM item WHERE isActive = 1";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			System.out.println("****************************************************************************");
			System.out.println("****************************************************************************");
			System.out.println("*********************<<<<<<<<<< Item Details >>>>>>>>>>>********************");
			System.out.println("****************************************************************************");
			System.out.println("****************************************************************************\n\n");
			System.out.println
			(
					String.format
					(
							"%20s %20s %20s %20s %20s  \n", 
							"item_id","ItemName","Item Brand"," Unit Price","Quantity" 
					)
			);
			
			System.out.println("--------------------------------------------------------------------------------------------------------");
			
			
			while(resultSet.next()) {
				
				System.out.printf
				(
						"%20d %20s %20s %20s %20d\n", 
						resultSet.getInt("item_id"),
						resultSet.getString("itemName"),
						resultSet.getString("brand"),
						resultSet.getString("unitPrice"),
						resultSet.getInt("quantity")
						
						
				);
				
				System.out.println("--------------------------------------------------------------------------------------------------------");
			}
			
			
			
		}catch(Exception ex) {
			
			System.out.println("getAllItemDetailsException:" + ex.getMessage());
			
		}
		
	}

	@Override
	public void getItemById() {
		Integer Itemid;
		
		System.out.println("Enter Item ID : ");
		Itemid = (sc.nextInt());
		
		String query = " SELECT *  FROM item WHERE item_id = ' "+ Itemid +"' && isActive = 1 " ;
		
		try {

			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			System.out.println("****************************************************************************");
			System.out.println("****************************************************************************");
			System.out.println("***********************<<<<<<<<<< Item Details >>>>>>>>>>>******************");
			System.out.println("****************************************************************************");
			System.out.println("****************************************************************************\n\n");
			
			while (resultSet.next()) {  
				
		    	  System.out.println("ItemId      :" + resultSet.getInt("item_id"));
		    	  System.out.println("Item Name   :" + resultSet.getString("itemName"));
		    	  System.out.println("Brand Name :" + resultSet.getString("brand"));
		    	  System.out.println("Unit Price       :" + resultSet.getString("unitPrice"));
		    	  System.out.println("Quentity       :" + resultSet.getString("quantity"));
		    	    
		      }	
			
			System.out.println("==============================================================================");

		} catch (Exception ex) {
			
			System.out.println("Error Has Been Occured Please Try Again");
			System.out.println(ex.getMessage());
			
			
		}
		
	}

	@Override
	public void genarateItemDetailsReport() {
		
		try {
		String query = "SELECT item_id,itemName,brand,unitPrice,quantity FROM item";
		
		statement = connection.createStatement();
		resultSet = statement.executeQuery(query);
		
		File directory = new File("D:\\SLIIT - Projects\\SA Assignment 01");
		
		directory.mkdirs();
		
		File file = new File(directory,"ItemReport.txt");
		FileWriter fileWriter = new FileWriter(file);
		
		fileWriter.write(String.format("++++++++++++++++++++++++++++++++++++++++++++++++++++++ Item Report +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n"));
		fileWriter.write(
				
				String.format
				(
						"%30s %30s %30s %30s %30s\n", 
						"item Id","itemName", "brand", "unitPrice","quantity"
				)
		);
		
		fileWriter.write(String.format("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n"));
		
		while(resultSet.next()) {
			
			fileWriter.write(
					
					String.format(
							
							"%20d %20s %20s %20s %20d\n", 
							resultSet.getInt("item_id"),
							resultSet.getString("itemName"),
							resultSet.getString("brand"),
							resultSet.getString("unitPrice"),
							resultSet.getInt("quantity")
					)
			);
			
			fileWriter.write(String.format("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"));
		}
		
		fileWriter.flush();
		fileWriter.close();

		System.out.println("Item Report Has Been Successfully");
			
		
	}catch (Exception ex) {
		
		System.out.println("ItemReportException:" + ex.getMessage());
	}
		
	}

}
