package com.osgi.item_management_publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	private ServiceRegistration serviceRegistration;
	
	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("Start Item Management Publisher Service");
		
		ItemService itemService = new ItemImpl();
		
		serviceRegistration = bundleContext.registerService(ItemService.class.getName(),itemService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {

		System.out.println("Stop Item Management Publisher Service");
		serviceRegistration.unregister();
		
	}

}
