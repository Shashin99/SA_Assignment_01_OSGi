package com.osgi.customer_management_subscriber;

import com.osgi.customer_management_publisher.CustomerService;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ServiceActivator implements BundleActivator {

	ServiceReference serviceReference;
	
	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("Start Customer Management Subscriber Service");
		serviceReference = bundleContext.getServiceReference(CustomerService.class.getName());
		
		
		CustomerService customerService = (CustomerService)bundleContext.getService(serviceReference);
		CustomerDashboard(customerService);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Good Bye !!!.");
		System.out.println("Stop Customer Management Subscriber Service");
		bundleContext.ungetService(serviceReference);

	}
	
	private void CustomerDashboard(CustomerService customerService) {
		
		Scanner sc = new Scanner(System.in);
		String selection = null;
		
		int cusDashChoice;
		String choice = "y";
		
		System.out.println("\n\n");
		System.out.println("-------------------------- Customer Management Dashboard --------------------");
		System.out.println("1 => Customer Registration");
		System.out.println("2 => Get All Customer Details");
		System.out.println("3 => View Home Page");
		System.out.println("4 => Order Item");
		System.out.println("5 => Delete Customer");
		System.out.println("6 => Generate Item Report");
		
		System.out.println("Please Select Your Selection");
		
		cusDashChoice = Integer.parseInt(sc.nextLine().trim());
		
		switch (cusDashChoice) {
		
			case 1:
				customerService.addNewCustomer();
				CustomerDashboard(customerService);
			break;
			
			case 2:
				customerService.viewAllCustomers();
				CustomerDashboard(customerService);
			break;
			
           case 3:
        	   customerService.viewAllItems();
        	   CustomerDashboard(customerService);
			break;
			
          case 4:
        	  customerService.orderItem();
			
				while(choice.equals("y")) {
					
					System.out.printf("\nDo you want to buy another product:(y/n) ");
					choice = sc.nextLine().trim().toLowerCase();
					
					if(choice.equals("y")) {
						
						customerService.orderItem();
					}
				}
				CustomerDashboard(customerService);
			break;
			
            case 5:
            	customerService.removeCustomer();
            	CustomerDashboard(customerService);
  			break;
  			
            case 6:
          	  	customerService.getOrderReport();
    			CustomerDashboard(customerService);
    		break;
  			
            default:
            	System.out.println("Customer Option has been incorrect please try again ");
            	//CustomerDashboard(customerService);
		}
		
	}

}
