package com.osgi.employee_management_publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("Start Employee Management Publisher Service");
		EmployeeService employeeService = new EmployeeImpl();
		
		serviceRegistration = bundleContext.registerService(EmployeeService.class.getName(), employeeService , null);
	
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("Stop Employee Management Publisher Service");
		serviceRegistration.unregister();
		
	}

}
