 package com.osgi.employee_management_subscriber;

import com.osgi.employee_management_publisher.EmployeeService;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ServiceActivator implements BundleActivator {

	ServiceReference serviceReference;


	public void start(BundleContext bundleContext) throws Exception {
		 
        System.out.println("Start Employee Management Subscriber Service");
		serviceReference = bundleContext.getServiceReference(EmployeeService.class.getName());
		
		
		EmployeeService employeeService = (EmployeeService)bundleContext.getService(serviceReference);
		EmployeeDashboard(employeeService);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Good Bye !!!.");
		System.out.println("Stop Employee Management Subscriber Service");
		bundleContext.ungetService(serviceReference);
	}
	
	public void  EmployeeDashboard(EmployeeService employeeService) {
		
		Scanner sc = new Scanner(System.in);
		
		int empDashChoice;
		String choice = "y";
		
		System.out.println("\n\n");
		System.out.println("==============Employee Management Dashboard===============");
		System.out.println("1 => Employee Registration");
		System.out.println("2 => Get All Employee Details");
		System.out.println("3 => Get Employee By Id");
		System.out.println("4 => Delete Employee");
		System.out.println("5 => Get Employee Report");
		
		System.out.println("Please Select Your Option");
		
		empDashChoice = Integer.parseInt(sc.nextLine().trim());
		
		switch (empDashChoice) {
		case 1:
			
			employeeService.addNewEmployee();
			EmployeeDashboard(employeeService);
			break;
			
		case 2:
			employeeService.getAllEmployeeDetails();;
			EmployeeDashboard(employeeService);
			break;
			
		case 3:
			employeeService.getEmployeeById();
			EmployeeDashboard(employeeService);
			break;
			
		
		case 4:
			employeeService.deleteEmployee();
			EmployeeDashboard(employeeService);
			break;
			
		case 5:
			employeeService.getEmployeeReport();
			EmployeeDashboard(employeeService);
			break;
			
		default:
			System.out.println("Employee Option has been incorrect please try again ");
			//EmployeeDashboard(employeeService);
		}
		
	}

}
