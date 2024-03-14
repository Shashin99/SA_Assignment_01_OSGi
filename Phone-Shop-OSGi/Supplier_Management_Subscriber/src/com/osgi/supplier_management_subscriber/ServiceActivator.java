package com.osgi.supplier_management_subscriber;

import com.osgi.supplier_management_publisher.SupplierService;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ServiceActivator implements BundleActivator {

	ServiceReference serviceReference;


	public void start(BundleContext bundleContext) throws Exception {
		serviceReference = bundleContext.getServiceReference(SupplierService.class.getName());
		
		System.out.println("Start Supplier Management Subscriber Service");
		SupplierService supplierService = (SupplierService)bundleContext.getService(serviceReference);
		SupplierDashboard(supplierService);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Good Bye !!!.");
		System.out.println("Stop Supplier Management Subscriber Service");
		bundleContext.ungetService(serviceReference);
	
	}


	private void SupplierDashboard(SupplierService supplierService) {
		
		Scanner sc = new Scanner(System.in);
		
		int supDashChoice;
		String choice = "y";
		
		System.out.println("\n\n");
		System.out.println("==============Supplier Management Dashboard===============");
		System.out.println("\n");
		System.out.println("1 => Supplier Registration");
		System.out.println("2 => Get All Supplier Details");
		System.out.println("3 => Get Supplier by Id"); 
		System.out.println("4 => Delete Supplier"); 
		System.out.println("5 => Place Stock Order");
		System.out.println("6 => Get Supplier Report");
		
		System.out.println("Please Select Your Option");
		
		supDashChoice = Integer.parseInt(sc.nextLine().trim());
		
		switch(supDashChoice) {
			
			case 1:
				supplierService.addNewSupplier();
				
				while(choice.equals("y")) {				
					System.out.printf("\nDo You Want To Add Another Supplier(y/n) ");
					choice = sc.nextLine().trim().toLowerCase();
				
					if(choice.equals("y")) {
					
						supplierService.addNewSupplier();
					}					
				}
				SupplierDashboard(supplierService);
				break;
				
			case 2:			
				supplierService.getAllSupplierDetails();
				SupplierDashboard(supplierService);
				break;
				
			case 3:		
				
				
				supplierService.getSupplierById();
				SupplierDashboard(supplierService);
				
			
				break;
			
			case 4:
				supplierService.deleteSupplier();				
				while(choice.equals("y")) {				
					System.out.printf("\nDo You Want To Delete Another Supplier(y/n) ");
					choice = sc.nextLine().trim().toLowerCase();					
					if(choice.equals("y")) {						
						supplierService.deleteSupplier();
					}
				}				
				SupplierDashboard(supplierService);
				
				break;
			
			case 5:
				supplierService.stockOrder();			
				while(choice.equals("y")) {				
					System.out.printf("\nDo You Want To Add Another Stock Order(y/n) ");
					choice = sc.nextLine().trim().toLowerCase();				
					if(choice.equals("y")) {					
						supplierService.stockOrder();
					}				
				}
				SupplierDashboard(supplierService);
				break;
				
               case 6:
				
				supplierService.getsupplierReport();
				SupplierDashboard(supplierService);
			default:
				
				System.out.println("Supplier Option Has Been Incorrect Please Try Again ");
				//SupplierDashboard(supplierService);		
		}
		

	}
}
