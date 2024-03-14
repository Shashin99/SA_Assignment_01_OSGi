package com.osgi.item_management_subscriber;

import com.osgi.item_management_publisher.ItemService;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ServiceActivator implements BundleActivator {


	ServiceReference serviceReference;
	
	public void start(BundleContext bundleContext) throws Exception {

		System.out.println("Start Item Management Subscriber Service");
		
		serviceReference = bundleContext.getServiceReference(ItemService.class.getName());
		
		ItemService inventoryService = (ItemService)bundleContext.getService(serviceReference);
		ItemDashboard(inventoryService);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {

		System.out.println("Good Bye !!!.");
		System.out.println("Stop Item Management Subscriber Service");
		bundleContext.ungetService(serviceReference);
	}

	public void ItemDashboard(ItemService inventoryService) {
		
		Scanner sc = new Scanner(System.in);
		
		int itemDashChoice;
		
		System.out.println("\n\n");
		System.out.println("==============Item Management Dashboard===============");
		System.out.println("1 => Add New Item");
		System.out.println("2 => Get All Item Details");
		System.out.println("3 => Get Item Details By Id");
		System.out.println("4 => Delete Item");
		System.out.println("5 => Genarate Item Stock Report");
		System.out.println("Please Select Your Option");
		
		itemDashChoice = Integer.parseInt(sc.nextLine().trim());
		
		switch(itemDashChoice) {
		
		case 1:
			inventoryService.addNewItem();
			ItemDashboard(inventoryService);
			break;
			
		case 2:
			inventoryService.getAllItemDetails();;;
			ItemDashboard(inventoryService);
			break;
			
		case 3:
			inventoryService.getItemById();     
			ItemDashboard(inventoryService);
			break;
			
		case 4:
			inventoryService.deleteItem();;
			ItemDashboard(inventoryService);
			break;
			
		case 5:
			inventoryService.genarateItemDetailsReport();
			ItemDashboard(inventoryService);
			break;
		default:
			
			System.out.println("User Option has been incorrect please try again ");
			//ProductDashBoard(inventoryService);
			
			
			
			

	  }

	}
}
