package org.iproduct.di;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
	private Map<String, Class<?>> beans = new ConcurrentHashMap<>();
	
	public ApplicationContext() {
	}
	
	public ApplicationContext(String mainPackage) {
		
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
