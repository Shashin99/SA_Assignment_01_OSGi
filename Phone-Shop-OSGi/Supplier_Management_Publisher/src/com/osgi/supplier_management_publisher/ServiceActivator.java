package com.osgi.supplier_management_publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {


	private ServiceRegistration serviceRegistration;

	public void start(BundleContext bundleContext) throws Exception {

		System.out.println("Start Supplier Management Publisher Service");
		SupplierService supplierService = new SupplierImpl();
			
		serviceRegistration = bundleContext.registerService(SupplierService.class.getName(), supplierService , null);
	}

	public void stop(BundleContext bundleContext) throws Exception {

		System.out.println("Stop Supplier Management Publisher Service");
		serviceRegistration.unregister();
	}

}
