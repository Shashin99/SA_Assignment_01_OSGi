package com.osgi.customer_management_publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	public void start(BundleContext bundleContext) throws Exception {
	
		System.out.println("Start Customer Management Publisher Service");
		CustomerService customerService = new CustomerImpl();
		
		serviceRegistration = bundleContext.registerService(CustomerService.class.getName(), customerService , null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Stop Customer Management Publisher Service");
		serviceRegistration.unregister();

	}
}
