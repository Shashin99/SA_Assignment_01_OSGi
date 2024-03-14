package com.osgi.supplier_management_publisher;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.osgi.phoneshopdb.DatabaseConnector;
import com.osgi.phoneshopdb.DatabaseConnectorImpl;

public class SupplierImpl implements SupplierService{

	private Connection connection = null; 
	private Statement statement;
	private ResultSet  resultSet;
	private DatabaseConnector dbConnection;
	
	private static PreparedStatement preparedStatement = null;
	
	Scanner sc = new Scanner(System.in);
	
	
	public SupplierImpl() {
		super();
		this.dbConnection = new DatabaseConnectorImpl();
		this.connection = dbConnection.getDatabaseConnection();
	
	}

	@Override
	public void addNewSupplier() {
		
		Scanner sc = new Scanner(System.in);
		
		SupplierModel supplierModel = new SupplierModel();

		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************");
		System.out.println("********************<<<<<<<<<< Supplier Register >>>>>>>>>>>****************");
		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************\n\n");

		
		System.out.println("Enter Your First Name ::");
		supplierModel.setFirstName(sc.nextLine().trim());
		
		System.out.println("Enter Your Last Name ::");
		supplierModel.setLastName(sc.nextLine().trim());
		
		System.out.println("Enter Your Email ::");
		supplierModel.setEmail(sc.nextLine().trim());
		
		System.out.println("Enter Your NIC ::");
		supplierModel.setNic(sc.nextLine().trim());
		
		System.out.println("Enter Your Mobile Number ::");
		supplierModel.setPhone(sc.nextLine().trim());
		
		System.out.println("Enter Your Company Name ::");
		supplierModel.setCompanyName(sc.nextLine().trim());
		
		
		try {
			
			String query = "INSERT INTO supplier(firstName,lastName,email,nic,phone,companyName,isActive) VALUES(?, ?, ?, ?, ?, ?, '1')";
			
			preparedStatement = connection.prepareStatement(query); 
			
			preparedStatement.setString(1, supplierModel.getFirstName());
			preparedStatement.setString(2, supplierModel.getLastName());
			preparedStatement.setString(3, supplierModel.getEmail());
			preparedStatement.setString(4, supplierModel.getNic());
			preparedStatement.setString(5, supplierModel.getPhone());
			preparedStatement.setString(6, supplierModel.getCompanyName());
			
			int isSuccess = preparedStatement.executeUpdate();
			
			if(isSuccess > 0) {
				System.out.println("Supplier Registration Has Been Successfully ");
			}else {
				System.out.println("Error Has Been Occured Please Try Again..");	
			}			
		}catch(Exception ex) {
			
			System.out.println("supplierSaveError : " + ex.getMessage());
		}	
	}

	@Override
	public void getAllSupplierDetails() {
try {
			
			String query = "SELECT supp_id, firstName, lastName, email, nic, phone , companyName FROM supplier WHERE isActive =1 ";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			System.out.println("****************************************************************************");
			System.out.println("****************************************************************************");
			System.out.println("*********************<<<<<<<<<< Supplier Details >>>>>>>>>>>*****************");
			System.out.println("****************************************************************************");
			System.out.println("****************************************************************************\n\n");
			System.out.println
			(
					String.format
					(
							"%20s %20s %20s %30s %20s %20s %20s\n", 
							"SupplierId", "First Name", "Last Name", "Email", "NIC" , "Mobile Number", "CompanyName"
					)
			);
			
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
			
			while(resultSet.next()) {
				
				System.out.printf
				(
						"%20d %20s %20s %30s %20s %20s %20s\n", 
						resultSet.getInt("supp_id"),
						resultSet.getString("firstName"),
						resultSet.getString("lastName"),
						resultSet.getString("email"),
						resultSet.getString("nic"),
						resultSet.getString("phone"),
						resultSet.getString("companyName")
						
						
				);
				
				System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			}
			
			
			
		}catch(Exception ex) {
			
			System.out.println("getAllSupplierDetailsException:" + ex.getMessage());
			
		}
		
	}

	@Override
	public void deleteSupplier() {
		Integer supp_id;
		
		System.out.print("\nPlease Enter Supplier ID : ");
		supp_id = (sc.nextInt());
		
		try {		
			String query = "UPDATE supplier SET isActive = 0 WHERE supp_id = ?";
						preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, supp_id);
			int isSuccess = preparedStatement.executeUpdate();			
			if(isSuccess > 0) {	
				System.out.println("Delete Supplier Has Been Successfully..");			
			}else {
				
				System.out.println("Cannot Find Supplier..");				
			}	
		}catch (Exception ex) {			
			System.out.println("supplierDeleteException : " + ex.getMessage());			
		}		
		
	}

	@Override
	public void getSupplierById() {
		Integer supp_id;
		
		System.out.println("Enter Supplier Id : ");
		supp_id = (sc.nextInt());		
		String query = "SELECT * FROM supplier WHERE supp_id = '"+ supp_id +"' && isActive = 1";		
		try {		
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);		
			while (resultSet.next()) {  			
				
						System.out.println("Suplier Id:" + resultSet.getInt("supp_id"));
						System.out.println("First name:" + resultSet.getString("firstName")); 
						System.out.println("Last name:" + resultSet.getString("lastName")); 
						System.out.println("Email:" +resultSet.getString("email"));
						System.out.println("Nic:" + resultSet.getString("nic"));
						System.out.println("Phone:" + resultSet.getString("phone"));
						System.out.println("Company name:" +resultSet.getString("companyName"));
				
			}
		} catch (Exception ex) {			
			System.out.println("Error Has Been Occured Please Try Again");
			System.out.println(ex.getMessage());			
		}		
		
	}

	@Override
	public void stockOrder() {
Scanner sc = new Scanner(System.in);
		
		StockOrderModel stockOrderModel = new StockOrderModel();

		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************");
		System.out.println("******************<<<<<<<<<< Stock Order Register >>>>>>>>>>>***************");
		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************\n\n");
		
		System.out.println("Please Enter Number of Phones Stock::");
		stockOrderModel.setNoOfStocks(sc.nextLine().trim());
		
		System.out.println("Please Enter Phone Product Name");
		stockOrderModel.setProductName(sc.nextLine().trim());
		
		System.out.println("Please Enter Company Name");
		stockOrderModel.setSupplierCompany(sc.nextLine().trim());
		
	
		
		
		try {
			
			String query = "INSERT INTO stockorder(noOfStocks,productName,supplierCompany,isActive) VALUES(?, ?, ?, '1')";			
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, stockOrderModel.getNoOfStocks());
			preparedStatement.setString(2, stockOrderModel.getProductName());
			preparedStatement.setString(3, stockOrderModel.getSupplierCompany());
					
			int isSuccess = preparedStatement.executeUpdate();
			
			if(isSuccess > 0) {				
				System.out.println(" Stock Sale Order Has Been Successfully");				
			}else {				
				System.out.println("Error Has Been Occured Please Try Again");				
			}			
		}catch (Exception ex) {			
			System.out.println("stockOrder : " + ex.getMessage());			
		}		
		
	}

	@Override
	public void getsupplierReport() {
			try {
			
			String query = "SELECT supp_id,firstName,lastName,email,nic,phone,companyName FROM supplier";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			File directory = new File("D:\\SLIIT - Projects\\SA Assignment 01");
			
			directory.mkdirs();
			
			File file = new File(directory,"ItemSupplierReport.txt");
			FileWriter fileWriter = new FileWriter(file);
			
			fileWriter.write(String.format("++++++++++++++++++++++++++++++++++++++++++++++++++++++ Stock Item Report +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n"));
			fileWriter.write(
					
					String.format
					(
							"%20s %20s %20s %20s %20s\n", 
							"Supplier Id","First Name", "Last Name", "Email","Nic","Phone","Company Name"
					)
			);
			
			fileWriter.write(String.format("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n"));
			
			while(resultSet.next()) {
				
				fileWriter.write(
						
						String.format(
								
								"%20s %20s %20s %20s %20s\n", 
								resultSet.getInt("supp_id"),
								resultSet.getString("firstName"),
								resultSet.getString("lastName"),
								resultSet.getString("email"),
								resultSet.getString("nic"),
								resultSet.getString("phone"),
								resultSet.getString("companyName")
								
						)
				);
				
				fileWriter.write(String.format("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"));
			}
			
			fileWriter.flush();
			fileWriter.close();
			
			
			System.out.println("Supplier Report Has Been Generated Successfully");
				
			
		}catch (Exception ex) {
			
			System.out.println("SupplierReportException:" + ex.getMessage());
		}
		
	}

}
