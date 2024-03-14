package com.osgi.phoneshopdb;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private ServiceRegistration serviceRegistration;
	
	DatabaseConnector connection = new DatabaseConnectorImpl();

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("Strat Database Publisher Service");
		DatabaseConnector connection = new DatabaseConnectorImpl();
		serviceRegistration = bundleContext.registerService(DatabaseConnector.class.getName(),connection, null);
	
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Stop Database Publisher Service");
		serviceRegistration.unregister();
		
	}

}
