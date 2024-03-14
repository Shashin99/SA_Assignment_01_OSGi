package com.osgi.employee_management_publisher;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.osgi.phoneshopdb.DatabaseConnector;
import com.osgi.phoneshopdb.DatabaseConnectorImpl;

public class EmployeeImpl implements EmployeeService{
	
	private Connection connection = null; 
	private Statement statement;
	private ResultSet  resultSet;
	private DatabaseConnector dbConnection;
	
	private static PreparedStatement preparedStatement = null;
	
	Scanner sc = new Scanner(System.in);
	
	

	public EmployeeImpl() {
		super();
		this.dbConnection = new DatabaseConnectorImpl();
		this.connection = dbConnection.getDatabaseConnection();
	}

	@Override
	public void addNewEmployee() {

		Scanner sc = new Scanner(System.in);

		EmployeeModel employeeModel = new EmployeeModel();

		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************");
		System.out.println("********<<<<<<<<<< Best Mobile Employee Register Console >>>>>>>>>>>********");
		System.out.println("****************************************************************************");
		System.out.println("****************************************************************************\n\n");


		System.out.println("Enter Employee First Name ::");
		employeeModel.setFirstName(sc.nextLine().trim());

		System.out.println("Enter Employee Last Name ::");
		employeeModel.setLastName(sc.nextLine().trim());

		System.out.println("Enter Employee Email ::");
		employeeModel.setEmail(sc.nextLine().trim());

		System.out.println("Enter Phone Number ::");
		employeeModel.setPhone(sc.nextLine().trim());

		try {

			String query = "insert into employee(firstName,lastName,email,phone,isActive) values(?,?,?,?,'1') ";

			preparedStatement = connection.prepareStatement(query); 

			preparedStatement.setString(1,employeeModel.getFirstName());
			preparedStatement.setString(2,employeeModel.getLastName());
			preparedStatement.setString(3,employeeModel.getEmail());
			preparedStatement.setString(4,employeeModel.getPhone());

			int isSuccess = preparedStatement.executeUpdate();

			if(isSuccess > 0) {

				System.out.println("Employee Registration Has Been Created Successfully");

			}else {

				System.out.println("Error Has Been Occured Please Try Again");

			}


		}catch(Exception ex) {

			System.out.println("employeeSaveError : " + ex.getMessage());
		}

		
	}

	@Override
	public void deleteEmployee() {

		Integer emp_id;
		
		System.out.print("\nPlease Enter Employee ID :");
		emp_id = (sc.nextInt());
		
		try {
			
			String query = "UPDATE employee SET isActive = 0 WHERE emp_id = ?";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, emp_id);
			int isSuccess = preparedStatement.executeUpdate();
			
			if(isSuccess > 0) {
				
				System.out.println("Employee Has Been Deleted Successfully...");
				
			}else {
				
				System.out.println("Cannot Find The Employee.. Try Again..");
				
			}
			
		}catch (Exception ex) {
			
			System.out.println("employeeDeleteException : " + ex.getMessage());
			
		}
		
		
	}

	@Override
	public void getAllEmployeeDetails() {
	
		try {
			
			String query = "SELECT emp_id, firstName, lastName, email, phone FROM employee WHERE isActive = 1";
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			System.out.println("****************************************************************************");
			System.out.println("****************************************************************************");
			System.out.println("*********************<<<<<<<<<< Employee Details >>>>>>>>>>>****************");
			System.out.println("****************************************************************************");
			System.out.println("****************************************************************************\n\n");
			System.out.println
			(
					String.format
					(
							"%20s %20s %20s %20s %20s\n", 
							"Employee Id", "First Name", "Last Name","Email", "Phone Number"
					)
			);
			
           System.out.println("--------------------------------------------------------------------------------------------------------");
			
			
			while(resultSet.next()) {
				
				System.out.printf
				(
						"%20d %20s %20s %20s %20s\n", 
						resultSet.getInt("emp_id"),
						resultSet.getString("firstName"),
						resultSet.getString("lastName"),
						resultSet.getString("email"),
						resultSet.getString("phone")
						
						
				);
				
				System.out.println("--------------------------------------------------------------------------------------------------------");
			}
			
			
			
		}catch(Exception ex) {
			
			System.out.println("getAllEmployeeDetailsException:" + ex.getMessage());
			
		}
		
	}

	@Override
	public void getEmployeeById() {

		Integer emp_Id;
		
		System.out.println("Enter Employee Id : ");
		emp_Id = (sc.nextInt());
		
		String query = "SELECT * FROM employee WHERE emp_id = '"+ emp_Id +"' && isActive = 1";
		
		try {
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			System.out.println("****************************************************************************");
			System.out.println("****************************************************************************");
			System.out.println("*********************<<<<<<<<<< Employee Details >>>>>>>>>>>*****************");
			System.out.println("****************************************************************************");
			System.out.println("****************************************************************************\n\n");
			
			while (resultSet.next()) {  
				
		    	  System.out.println("EmployeeId:" + resultSet.getInt("emp_id"));
		    	  System.out.println("First Name:" + resultSet.getString("firstName"));
		    	  System.out.println("Last Name:" + resultSet.getString("lastName"));
		    	  System.out.println("Email:" + resultSet.getString("email"));
		    	  System.out.println("Phone Number:" + resultSet.getString("phone"));
		    	    
		      }	
			
			System.out.println("==============================================================================");

		} catch (Exception ex) {
			
			System.out.println("Error Has Been Occured Please Try Again");
			System.out.println(ex.getMessage());
			
		}
		
	}

	@Override
	public void getEmployeeReport() {
		
		try {
			
			String query = "SELECT emp_id,firstName ,lastName, email,phone FROM employee";
			
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			File directory = new File("D:\\SLIIT - Projects\\SA Assignment 01");
			
			directory.mkdirs();
			
			File file = new File(directory,"BestMobileEmployeeReport.txt");
			FileWriter fileWriter = new FileWriter(file);
			
			fileWriter.write(String.format("++++++++++++++++++++++++++++++++++++++++++++++++++++++ Best Mobile Employee Report +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n"));
			fileWriter.write(
					
					String.format
					(
							"%30s %30s %30s %30s %30s\n", 
							"Employee Id","First Name", "Last Name", "Email","Phone"
					)
			);
			
			fileWriter.write(String.format("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n"));
			
			while(resultSet.next()) {
				
				fileWriter.write(
						
						String.format(
								
								"%30d %30s %30s %30s %30s \n", 
								resultSet.getInt("emp_id"),
								resultSet.getString("firstName"),
								resultSet.getString("lastName"),
								resultSet.getString("email"),
								resultSet.getString("phone")
								
								
						)
				);
				
				fileWriter.write(String.format("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"));
			}
			
			fileWriter.flush();
			fileWriter.close();
			
			
			System.out.println("Report Genaration Has Been Successfully");
				
			
		}catch (Exception ex) {
			
			System.out.println("getEmployeeReportException:" + ex.getMessage());
			
			
		}
	}

}
